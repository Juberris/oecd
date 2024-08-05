package cl.sii.filepackaging.service;

import cl.sii.filepackaging.model.*;
import cl.sii.filepackaging.model.schema.generic_status.oecd.ties.gsm.isogsmtypes.v1.CountryCodeType;
import cl.sii.filepackaging.model.schema.generic_status.oecd.ties.gsm.v1.*;
import cl.sii.filepackaging.model.schema.wrapper.oecd.ties.cfw.v1.CTSFileWrapperOECD;
import cl.sii.filepackaging.util.DateTime;
import cl.sii.filepackaging.util.Ids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import static cl.sii.filepackaging.util.FieldPathUtil.getFieldPath;

@Service
public class StatusMessageService {

    @Autowired
    ValidationErrorsType validationErrors;

    @Autowired
    AppProp ctsProps;

    @Autowired
    Map<Integer, CodeErrorStatus> statusErrorMap;

    public FileRequest getStatusMessage(MessageSpecDTO msg) throws JAXBException {
        GenericStatusMessageOECD genericStatusMessage= new GenericStatusMessageOECD();
        MessageSpecType messageSpec=new MessageSpecType();
        messageSpec.setTransmittingCountry(CountryCodeType.valueOf(ctsProps.getSender()));
        messageSpec.setReceivingCountry(CountryCodeType.valueOf(msg.getTransmittingCountry()));
        //DTCAEOIStatus EOIRFreeDTStatus SponFreeDTStatus
        messageSpec.setMessageType(MessageTypeEnumType.valueOf(msg.getMessageType()+"_STATUS"));
        messageSpec.setContact(msg.getContact());
        messageSpec.setMessageRefId(Ids.buildMessageRefId("Status"+msg.getMessageType(),msg.getTransmittingCountry(),msg.getReceivingCountry(), DateTime.year(new Date())));
        messageSpec.setTimestamp(DateTime.date2UtcXmlDate(DateTime.getUniqueToday()));
        //StatusMessage
        GenericMessageStatusType genericMessageStatus = new GenericMessageStatusType();

        OriginalMessageType originalMessageType= new OriginalMessageType();
        originalMessageType.setOriginalMessageRefID(msg.getOriginalMessageRefId());
        genericMessageStatus.setOriginalMessage(originalMessageType);



        ValidationErrorsType validationErrorsType=new ValidationErrorsType();
        RecordErrorType ret= new RecordErrorType();
        ValidationResultType validationResult= new ValidationResultType();
        validationResult.getValidatedBy().add("SII");
        genericMessageStatus.setValidationErrors(validationErrors);
        if (!validationErrors.getRecordError().isEmpty()){
            validationResult.setStatus(FileAcceptanceStatusEnumType.valueOf("REJECTED"));
        }else {
            validationResult.setStatus(FileAcceptanceStatusEnumType.valueOf("ACCEPTED"));
        }
        genericMessageStatus.setValidationResult(validationResult);
        genericStatusMessage.setMessageSpec(messageSpec);
        genericStatusMessage.setGenericStatusMessage(genericMessageStatus);
        genericStatusMessage.setVersion("2.0");
        //marshaller
        JAXBContext context = JAXBContext.newInstance(GenericStatusMessageOECD.class);
        Marshaller marshaller= context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        StringWriter sw = new StringWriter();
        marshaller.marshal(genericStatusMessage,sw);
        String str = sw.toString();
        System.out.println(str);
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);

        FileRequest r= new FileRequest();
        r.setFile(bytes);
        r.setTypeExchange(msg.getMessageType()+"Status");
        r.setFileFormatCd("XML");
        r.setCountryCodeReceiver(msg.getTransmittingCountry());
        r.setCountryCodeSender(ctsProps.getSender());
        return r;
    }

    public void addFieldError(RecordErrorDTO err){
        RecordErrorType recordError= new RecordErrorType();
        recordError.setCode(err.getCode());

        ErrorDetailType detail=new ErrorDetailType();
        detail.setValue(err.getDetail());
        recordError.setDetails(detail);
        recordError.getDocRefIDInError().add(err.getDocRefIDInError());
        RecordErrorType.FieldsInError fieldsInError= new RecordErrorType.FieldsInError();
        fieldsInError.setFieldPath(err.getFieldPath());
        recordError.getFieldsInError().add(fieldsInError);
        validationErrors.getRecordError().add(recordError);
    }

    public void clearValidationErrors(){
        validationErrors.getRecordError().clear();
    }

    public void addRecordError(String code, String docRefInError, String fieldXml){
        RecordErrorDTO err = new RecordErrorDTO();
        err.setCode(code);
        err.setDetail(Optional.ofNullable(statusErrorMap.get(Integer.valueOf(code))).map(CodeErrorStatus::getDescription).orElse(null));
        err.setDocRefIDInError(docRefInError);
        err.setFieldPath(fieldXml!=null?getFieldPath(CTSFileWrapperOECD.class, fieldXml):null);
        addFieldError(err);
    }
}
