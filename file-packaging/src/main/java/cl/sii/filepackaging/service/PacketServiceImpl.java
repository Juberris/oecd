package cl.sii.filepackaging.service;

import cl.sii.filepackaging.model.*;
import cl.sii.filepackaging.model.schema.generic_status.oecd.ties.gsm.v1.ErrorDetailType;
import cl.sii.filepackaging.model.schema.generic_status.oecd.ties.gsm.v1.RecordErrorType;
import cl.sii.filepackaging.model.schema.generic_status.oecd.ties.gsm.v1.ValidationErrorsType;
import cl.sii.filepackaging.model.schema.wrapper.oecd.ties.cfw.isocfwtypes.v1.CountryCodeType;
import cl.sii.filepackaging.model.schema.cts2.BinaryEncodingSchemeCdType;
import cl.sii.filepackaging.model.schema.cts2.CTSCommunicationTypeCdType;
import cl.sii.filepackaging.model.schema.cts2.CTSSenderFileMetadataType;
import cl.sii.filepackaging.model.schema.cts2.FileFormatCdType;
import cl.sii.filepackaging.model.schema.wrapper.oecd.ties.cfw.v1.*;
import cl.sii.filepackaging.script.Cts2ExportScript;
import cl.sii.filepackaging.util.*;
import cl.sii.filepackaging.util.security.CtsSecurity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static cl.sii.filepackaging.util.FieldPathUtil.getFieldPath;

@Service
@Slf4j
public class PacketServiceImpl implements PacketService {
    @Autowired
    CtsHttpApi ctsHttpApi;
    @Autowired
    AppProp ctsProps;

    @Autowired
    StatusMessageService statusMessageService;

    String sender;
    private  File inboxDir;
    private  File outboxDir;
    private  Path rootLocation;
    CTSSenderFileMetadataType metaData = new CTSSenderFileMetadataType();
    CtsKeystore ctsKeystore= new CtsKeystore();
    private byte[] payloadKey;
    ByteArrayOutputStream dataPacketOut = new ByteArrayOutputStream();
    @Autowired
    Unpacker unpacker;


    @Override
    public Map<String, String> createPackage(FileRequest obj) {
       sender=obj.getCountryCodeSender()==null?ctsProps.getSender():obj.getCountryCodeSender();

       inboxDir=new File(ctsProps.getInbox());
       outboxDir=new File(ctsProps.getOutbox());
        Date date = new Date();
        String tid = Ids.uuid();
        //boolean updated=true;
        boolean updated =ctsHttpApi.updateCert(Country.CountryCode.valueOf(obj.getCountryCodeReceiver()));
        if (updated) {
            //CONSTRUIR METADATA
            buildMetadata(sender, obj.getCountryCodeReceiver(), obj.getTypeExchange(),obj.getFileFormatCd());
            InputStream payload=new ByteArrayInputStream(obj.getFile());
            dataPacketOut = new ByteArrayOutputStream();
            try {
                //FIRMA DOCUMENTO - COMPRESION Y CIFRADO
                build(payload,dataPacketOut,obj.getCountryCodeReceiver(), obj.getFileFormatCd());
                dataPacketOut.close();
                //CREAR ARCHIVO EN DISCO
                //ARCHIVOS QUE RECIBIMOS VIENEN CON EL TID, LOS QUE ENVIAMOS NO
                String filename = IO.buildSenderFileName(this.metaData, date) + (!sender.equals("CL") ? "_" + tid : "") + ".zip";
                File dpdir= (sender.equals("CL")) ? new File(outboxDir,obj.getCountryCodeReceiver()): inboxDir;
                if (!dpdir.exists()) {
                    dpdir.mkdirs();
                }
                File zip = new File(dpdir, filename);
                log.info("Publicando archivo [" + zip + "]");
                Files.copy(new ByteArrayInputStream(dataPacketOut.toByteArray()),zip.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            log.info("Error al buscar-actualizar certificado de: {}", obj.getCountryCodeReceiver());
            throw new RuntimeException("Error al buscar-actualizar certificado");
        }
        return Map.of();
    }

    @Override
    public Map<String, String> createPackage(FileDataList obj) throws JAXBException {
        sender=ctsProps.getSender();
        inboxDir=new File(ctsProps.getInbox());
        outboxDir=new File(ctsProps.getOutbox());
        Date date = new Date();
        String tid = Ids.uuid();
        String countryCodeReceiver=obj.getFiles().get(0).getCountryCodeReceiver();
        //boolean updated=true;
        boolean updated =ctsHttpApi.updateCert(Country.CountryCode.valueOf(countryCodeReceiver));
        if (updated) {
            //CONSTRUIR METADATA
            buildMetadata(sender, countryCodeReceiver, obj.getTypeExchange(),"XML");
            InputStream payload=getXmlWrapper(obj, countryCodeReceiver);
            dataPacketOut = new ByteArrayOutputStream();
            try {
                //FIRMA DOCUMENTO - COMPRESION Y CIFRADO
                build(payload,dataPacketOut,countryCodeReceiver, "XML");
                dataPacketOut.close();
                //CREAR ARCHIVO EN DISCO
                //ARCHIVOS QUE RECIBIMOS VIENEN CON EL TID, LOS QUE ENVIAMOS NO
                String filename = IO.buildSenderFileName(this.metaData, date) + (!sender.equals("CL") ? "_" + tid : "") + ".zip";
                File dpdir= (sender.equals("CL")) ? new File(outboxDir,countryCodeReceiver): inboxDir;
                if (!dpdir.exists()) {
                    dpdir.mkdirs();
                }
                File zip = new File(dpdir, filename);
                Files.copy(new ByteArrayInputStream(dataPacketOut.toByteArray()),zip.toPath(), StandardCopyOption.REPLACE_EXISTING);
                log.info("Publicando archivo [" + zip + "]");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            log.info("Error al buscar-actualizar certificado de: {}", countryCodeReceiver);
            throw new RuntimeException("Error al buscar-actualizar certificado");
        }
        return Map.of();
    }


    void buildMetadata(String sender, String receiver, String _type, String extensionFile){

        CTSCommunicationTypeCdType type = CTSCommunicationTypeCdType.fromValue(_type);
        Integer period = Calendar.getInstance().get(Calendar.YEAR);
        Date date = new Date();
        String msgRefId = sender + period + receiver + Ids.uuid();
        metaData.setCTSSenderCountryCd(sender);
        metaData.setCTSReceiverCountryCd(receiver);
        metaData.setCTSCommunicationTypeCd(type);
        metaData.setSenderFileId(OS.concat("_", sender, receiver, type.value(), msgRefId));
        metaData.setFileFormatCd(FileFormatCdType.fromValue(extensionFile.toUpperCase()));
        metaData.setBinaryEncodingSchemeCd(BinaryEncodingSchemeCdType.NONE);
        metaData.setFileCreateTs(OS.date2UtcXmlDate(date));
        metaData.setTaxYear(OS.yearXmlDate(period));
        metaData.setFileRevisionInd(false);
        metaData.setOriginalCTSTransmissionId(null);
        metaData.setSenderContactEmailAddressTxt(ctsProps.getContact());

    }

    void build(InputStream payloadData, OutputStream out, String country, String extensionFile) throws IOException {
        IOResources resources = new IOResources();
        String payloadXmlName = metaData.getCTSSenderCountryCd() + "_" + metaData.getCTSCommunicationTypeCd() + "_Payload.xml";

        String payloadName = metaData.getCTSSenderCountryCd() + "_" + metaData.getCTSCommunicationTypeCd() + "_Payload";

        String keyName = metaData.getCTSReceiverCountryCd() + "_" + metaData.getCTSCommunicationTypeCd() + "_Key";

        String mdName = metaData.getCTSSenderCountryCd() + "_" + metaData.getCTSCommunicationTypeCd() + "_Metadata.xml";

        try {
            //Firma Documento

            ctsKeystore.setCtsHttpApi(ctsHttpApi);
            ctsKeystore.setKeystorePrePath(ctsProps.getKeystorePrePath());
            ctsKeystore.setKeystorePwd(ctsProps.getKeystorePwd());
            ctsKeystore.setKeyPwd(ctsProps.getKeyPwd());
            ctsKeystore.setKeyLength(Integer.parseInt(ctsProps.getKeyLength()));

            File payloadSignedFile = resources.add(OS.tmp("cts"));
            OutputStream payloadSignedOut = resources.add(new FileOutputStream(payloadSignedFile));

            CtsSecurity.signXml(payloadData, payloadSignedOut, ctsKeystore.getSenderPath(ctsProps.getSender()), ctsKeystore.getKeystorePwd(), ctsKeystore.getKeyPwd(),extensionFile.toUpperCase());
            payloadSignedOut.close();

            //Compresion y Cifrado

            File payloadFile = resources.add(OS.tmp("cts"));
            OutputStream payloadOut = resources.add(new FileOutputStream(payloadFile));
            InputStream payloadDataSigned = resources.add(new FileInputStream(payloadSignedFile));
            byte[] keyData = CtsSecurity.zipAndCipher(payloadXmlName, payloadDataSigned, payloadOut);
            payloadOut.close();
            //Cifrado Llave
            File keyFile = resources.add(OS.tmp("cts"));
            OutputStream keyOut = resources.add(new FileOutputStream(keyFile));

            keyOut.write(CtsSecurity.cipher(keyData, ctsKeystore.getReceiverPath(country), null));
            keyOut.close();
            InputStream keyIn = resources.add(new FileInputStream(keyFile));
            payloadKey = IOUtils.toByteArray(keyIn);
            keyIn.close();
            //MetaData
            File mdFile = resources.add(OS.tmp("cts"));
            OutputStream mdOut = resources.add(new FileOutputStream(mdFile));
            String mdXml = Xml.toXml(metaData, true);
            if (log.isTraceEnabled()) {
                log.trace(mdXml);
            }
            mdOut.write(mdXml.getBytes());
            mdOut.close();
            //Compresion
            String[] dataFilePaths = new String[] { payloadName, keyName, mdName };
            InputStream[] payloads = new InputStream[] { //
                    resources.add(new FileInputStream(payloadFile)), //
                    resources.add(new FileInputStream(keyFile)), //
                    resources.add(new FileInputStream(mdFile)) };
            CtsSecurity.zip(dataFilePaths, payloads, out);
        } catch (IOException e) {
            log.error("Error al crear package ===> {} ", e.getMessage());
        }finally {
            resources.close();
        }
    }

    @Override
    public Map<String, String> unPackage(FileRequest obj)  {
        statusMessageService.clearValidationErrors();
        AtomicReference<String> typeCd= new AtomicReference<>();
        String msgRefId="";
        String countryCodeSender="";
        try {

        countryCodeSender= obj.getFileName().substring(0,2);
        Path inboxPath= Paths.get(ctsProps.getInbox() +"//" + countryCodeSender +"//" + obj.getFileName().substring(0,obj.getFileName().length()-4));
        if (!Files.exists(inboxPath)) {
            try {
                Files.createDirectories(inboxPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        unpacker.setCtsKeystore(ctsKeystore);
        this.rootLocation=inboxPath;
        File file =this.store(obj.getFile(),obj.getFileName());
        AtomicReference<String> extension= new AtomicReference<>();

        System.out.println("Ruta Archivo ==> " + inboxPath + "//" + obj.getFileName());
        byte[] payload=unpacker.openZip(file, (byte[] pyldBytes, byte[] mdBytes, byte[] keyBytes) -> {
            CTSSenderFileMetadataType md = getMdObject(new ByteArrayInputStream(mdBytes));
            extension.set(md.getFileFormatCd()==null?"xml": md.getFileFormatCd().value().toLowerCase());
            typeCd.set(md.getCTSCommunicationTypeCd().value().toUpperCase());
            String mdStr= new String(mdBytes, StandardCharsets.UTF_8);
            File payFile =this.store(mdBytes,"metadata.xml");
            String mrid = md.getSenderFileId().substring(IO.concat("_", md.getCTSSenderCountryCd(), md.getCTSReceiverCountryCd(), md.getCTSCommunicationTypeCd().value(), "").length());
            byte[] keyBytesDescripted = unpacker.decryptKey(ctsProps.getSender(), keyBytes);
            return  unpacker.decryptPayload(keyBytesDescripted, pyldBytes);
        });

            if (extension.get().equals("xml")){
                try (ByteArrayOutputStream data = new ByteArrayOutputStream()) {
                    Cts2ExportScript.modify(new OutputStreamWriter(data), new Cts2ExportScript.XmlMod() {
                        @Override
                        public boolean include(String path) {
                            return path.contains("_OECD");
                        }
                    }, l -> {
                        Xml.read(new BOMInputStream(new ByteArrayInputStream(payload)), l);
                    });

                    //System.out.println(data.toString());
                    if (typeCd.get().equals("DTCAEOI")){
                        if (!Files.exists(Path.of(inboxPath + "//files"))) {
                            try {
                                Files.createDirectories(Path.of(inboxPath + "//files"));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        JAXBContext context = JAXBContext.newInstance(CTSFileWrapperOECD.class);
                        Unmarshaller unmarshaller = context.createUnmarshaller();
                        InputStream inputData= new ByteArrayInputStream(data.toByteArray());
                        CTSFileWrapperOECD wrapper= (CTSFileWrapperOECD) unmarshaller.unmarshal(inputData);
                         msgRefId=wrapper.getMessageSpec().getMessageRefId();
                        //validar si ya existe refId
                        //de lo contrario, registrar error
                        boolean existRefId=false;
                        if(existRefId) {
                            //80000	DocRefID already used - The DocRefID is already used for another record.
                            statusMessageService.addRecordError("80000",msgRefId,"messageRefId");
                        }

                        wrapper.getFileAttach().forEach(f->{
                            String contentBase64 =f.getFileWrapper();
                            contentBase64 = contentBase64.replaceAll("[^A-Za-z0-9+/=]", "");
                            byte[] decodedBytes = Base64Utils.decodeFromString(contentBase64);
                            File payFile =this.store(decodedBytes, "files//" + f.getFileName());
                        });
                    }
                    File payFile =this.store(data.toByteArray(), "payload."+extension.get());
                    log.info("Xml payload descargado en {}", payFile.getAbsolutePath());
                }
            }else {
                InputStream inputStream = new ByteArrayInputStream(payload);
                JAXBContext jaxbContext = JAXBContext.newInstance(Signature.class);
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                Signature payClass = (Signature) unmarshaller.unmarshal(inputStream);
                String contentBase64 = payClass.getSignatureObject().getContent().trim();
                //contentBase64 = contentBase64.replaceAll("[^A-Za-z0-9+/=]", "");
                byte[] decodedBytes = Base64Utils.decodeFromString(contentBase64);

                File payFile = this.store(decodedBytes, "payload." + extension.get());
                log.info("Payload descargado en {}", payFile.getAbsolutePath());

            }
        }catch (Exception e){
            //50002	Failed Decryption - The receiving Competent Authority could not decrypt the referenced file.
            statusMessageService.addRecordError("50002",null,null);
            log.error("Error al decodificar ", e);
        }

        //STATUS MESSAGE
        MessageSpecDTO msg= new MessageSpecDTO();
        msg.setOriginalMessageRefId(msgRefId);
        msg.setReceivingCountry("CL");
        msg.setTransmittingCountry(countryCodeSender);
        msg.setContact(ctsProps.getContact());
        msg.setMessageType(typeCd.get());
        try {
            createPackage(statusMessageService.getStatusMessage(msg));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return Map.of();
    }

    File store(byte[] fileBytes, String filename) {
        try {
            Path filePath = this.rootLocation.resolve(filename);
            try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
                fos.write(fileBytes);
            }
            return filePath.toFile();
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file " + filename, e);
        }
    }

    public CTSSenderFileMetadataType getMdObject(InputStream is) {
        if (is != null) {
            return Xml.fromXml(is, CTSSenderFileMetadataType.class);
        }
        return null;
    }

    private InputStream getXmlWrapper(FileDataList obj, String countryCodeReceiver) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(CTSFileWrapperOECD.class);

        Marshaller marshaller= context.createMarshaller();
       marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        CTSFileWrapperOECD wp= new CTSFileWrapperOECD();
        MessageSpecType msg= new MessageSpecType();
        String msgRefId = Ids.buildMessageRefId(obj.getTypeExchange(),"CL",countryCodeReceiver,DateTime.year(new Date()));
        msg.setMessageRefId(msgRefId);
        msg.setTransmittingCountry(CountryCodeType.valueOf("CL"));
        msg.setReceivingCountry(CountryCodeType.valueOf(countryCodeReceiver));
        msg.setMessageType(MessageTypeEnumType.fromValue(obj.getTypeExchange()));
        msg.setTimestamp(DateTime.date2UtcXmlDate(DateTime.getUniqueToday()));
        wp.setMessageSpec(msg);
        obj.getFiles().forEach(fi->{
            FileAttachType f= new FileAttachType();
            f.setFileName(fi.getFileName());
            f.setBinaryEncodingSchemeCd(BinaryEncodingSchemeEnumType.BASE_64);
            f.setFileFormatCd(FileFormatEnumType.valueOf(fi.getFileFormatCd()));
            byte[] binaryFile= fi.getFile();
            String fileString = new String(binaryFile, StandardCharsets.UTF_8);
            f.setFileWrapper(Base64Utils.encodeToString(binaryFile).toString().replaceAll("[^A-Za-z0-9+/=]", ""));
            wp.getFileAttach().add(f);
        });

        StringWriter sw = new StringWriter();
        marshaller.marshal(wp,sw);
        String str = sw.toString();
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);

        return new ByteArrayInputStream(bytes);
    }


}
