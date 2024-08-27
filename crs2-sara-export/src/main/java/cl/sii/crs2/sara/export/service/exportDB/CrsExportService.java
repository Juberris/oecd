package cl.sii.crs2.sara.export.service.exportDB;


import cl.sii.crs2.sara.export.entities.oecd.MessageSpec;
import cl.sii.crs2.sara.export.repository.MessageSpecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CrsExportService {

   Object oecd;
    String version;

    @Autowired
    MessageSpecRepository messageSpecRepository;

    public void initCrsExportService(Object oecd, String version) {
        this.oecd = oecd;
        this.version=version;
    }

    public void process() {

        cargarMessageSpec(this.oecd);

    }

    void cargarMessageSpec(Object obj){
        MessageSpec ms= new MessageSpec();
      if (this.version.equals("v1")){
          cl.sii.crs2.sara.export.domain.crs.v1.oecd.ties.crs.v1.MessageSpecType messageSpec= ((cl.sii.crs2.sara.export.domain.crs.v1.oecd.ties.crs.v1.CRSOECD) this.oecd).getMessageSpec();
            ms.setMessageRefId(messageSpec.getMessageRefId());
           cl.sii.crs2.sara.export.domain.crs.v1.oecd.ties.isocrstypes.v1.CountryCodeType tc=messageSpec.getTransmittingCountry();
            ms.setTransmittingCountry(tc.value());
          cl.sii.crs2.sara.export.domain.crs.v1.oecd.ties.isocrstypes.v1.CountryCodeType rc=messageSpec.getReceivingCountry();
            ms.setReceivingCountry(rc.value());
          cl.sii.crs2.sara.export.domain.crs.v1.oecd.ties.crs.v1.MessageTypeEnumType mt=messageSpec.getMessageType();
            ms.setMessageType(mt.value());
            ms.setWarning(messageSpec.getWarning());
            ms.setContact(messageSpec.getContact());
          cl.sii.crs2.sara.export.domain.crs.v1.oecd.ties.crs.v1.CrsMessageTypeIndicEnumType mti=messageSpec.getMessageTypeIndic();
            ms.setMessageTypeIndic(mti!=null?mti.value():null);
            ms.setReportingPeriod(messageSpec.getReportingPeriod().toGregorianCalendar().getTime());
            ms.setTimestamp(messageSpec.getTimestamp().toGregorianCalendar().getTime());

      }else {
          cl.sii.crs2.sara.export.domain.crs.v2.oecd.ties.crs.v2.MessageSpecType messageSpec= ((cl.sii.crs2.sara.export.domain.crs.v2.oecd.ties.crs.v2.CRSOECD) this.oecd).getMessageSpec();
          ms.setMessageRefId(messageSpec.getMessageRefId());
          cl.sii.crs2.sara.export.domain.crs.v2.oecd.ties.isocrstypes.v1.CountryCodeType tc=messageSpec.getTransmittingCountry();
          ms.setTransmittingCountry(tc.value());
          cl.sii.crs2.sara.export.domain.crs.v2.oecd.ties.isocrstypes.v1.CountryCodeType rc=messageSpec.getReceivingCountry();
          ms.setReceivingCountry(rc.value());
          cl.sii.crs2.sara.export.domain.crs.v2.oecd.ties.crs.v2.MessageTypeEnumType mt=messageSpec.getMessageType();
          ms.setMessageType(mt.value());
          ms.setWarning(messageSpec.getWarning());
          ms.setContact(messageSpec.getContact());
          cl.sii.crs2.sara.export.domain.crs.v2.oecd.ties.crs.v2.CrsMessageTypeIndicEnumType mti=messageSpec.getMessageTypeIndic();
          ms.setMessageTypeIndic(mti!=null?mti.value():null);
          ms.setReportingPeriod(messageSpec.getReportingPeriod().toGregorianCalendar().getTime());
          ms.setTimestamp(messageSpec.getTimestamp().toGregorianCalendar().getTime());
      }


      ms.setCreatedAt(new Date());
      ms.setCreatedBy("AI");
        messageSpecRepository.save(ms);
    }


}
