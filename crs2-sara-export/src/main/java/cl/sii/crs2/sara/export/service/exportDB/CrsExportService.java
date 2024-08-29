package cl.sii.crs2.sara.export.service.exportDB;

import cl.sii.crs2.sara.export.entities.crs.CrsAddress;
import cl.sii.crs2.sara.export.entities.crs.CrsReportingFI;
import cl.sii.crs2.sara.export.entities.oecd.MessageSpec;
import cl.sii.crs2.sara.export.repository.CrsReportingFIRepository;
import cl.sii.crs2.sara.export.repository.MessageSpecRepository;
import cl.sii.crs2.sara.export.util.Ids;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


@Service
public class CrsExportService {
    private static final String COUNTRY_CODE = "CountryCode";
    private static final String ADDRESS_FREE = "AddressFree";
    private static final String ADDRESS_FIX = "AddressFix";
    Object oecd;
    String version;
    String filename;
    String messageRefId;


    @Getter
    List<CrsReportingFI> lFI= new ArrayList<>();
    @Getter
    List<MessageSpec> lMS= new ArrayList<>();

    @Autowired
    MessageSpecRepository messageSpecRepository;

    @Autowired
    CrsReportingFIRepository reportingFIRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public void initCrsExportService(Object oecd, String version, String filename) {
        this.oecd = oecd;
        this.version=version;
        this.filename=filename;
    }

    public void process() {
       cargarMessageSpec();
       cargarReportingFI();

    }


    void cargarMessageSpec(){
        MessageSpec ms= new MessageSpec();
        Set<String> corrMRefid = new HashSet<>();
      if (this.version.equals("v1")){
          cl.sii.crs2.sara.export.domain.crs.v1.oecd.ties.crs.v1.MessageSpecType messageSpec= ((cl.sii.crs2.sara.export.domain.crs.v1.oecd.ties.crs.v1.CRSOECD) this.oecd).getMessageSpec();

            ms.setMessageRefId(messageSpec.getMessageRefId());
            this.messageRefId=messageSpec.getMessageRefId();
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
          corrMRefid.addAll(messageSpec.getCorrMessageRefId());
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
          corrMRefid.addAll(messageSpec.getCorrMessageRefId());
      }

          ms.setFilename(filename);
          ms.setCreatedAt(new Date());
          ms.setCreatedBy("AI");
          ms.setCorrMessageRefId(corrMRefid);

          lMS.add(ms);
       // messageSpecRepository.save(ms);

    }


    void cargarReportingFI(){
        if (this.version.equals("v1")){
            List<cl.sii.crs2.sara.export.domain.crs.v1.oecd.ties.crs.v1.CrsBodyType> crsBodyList= ((cl.sii.crs2.sara.export.domain.crs.v1.oecd.ties.crs.v1.CRSOECD) this.oecd).getCrsBody();
            crsBodyList.forEach(b->{
                CrsReportingFI fi= new CrsReportingFI();
                fi.setMessageRefId(this.messageRefId);

                cl.sii.crs2.sara.export.domain.crs.v1.oecd.ties.crs.v1.CorrectableOrganisationPartyType reportingFI = b.getReportingFI();
                reportingFI.getResCountryCode().forEach(c->{
                    fi.setResCountryCode(c.value());
                });
                reportingFI.getIN().forEach(i->{
                    fi.setFiIN(i.getValue());
                });
                reportingFI.getName().forEach(n->{
                    fi.setName(n.getValue());
                });

                cl.sii.crs2.sara.export.domain.crs.v1.oecd.ties.stf.v4.DocSpecType docSpecType= reportingFI.getDocSpec();
                if (docSpecType!=null) {
                    cl.sii.crs2.sara.export.domain.crs.v1.oecd.ties.stf.v4.OECDDocTypeIndicEnumType otiet = docSpecType.getDocTypeIndic();
                    fi.setDocTypeIndic(otiet != null ? otiet.value() : null);
                    fi.setDocRefId(docSpecType.getDocRefId());
                }
                    reportingFI.getAddress().forEach(a->{
                    CrsAddress address= new CrsAddress();
                    a.getContent().forEach(ad->{
                        if (ad.getName().getLocalPart().equals(COUNTRY_CODE)) {
                            address.setCountryCode(ad.getValue().toString());
                        }
                        if (ad.getName().getLocalPart().equals(ADDRESS_FREE)) {
                            address.setAddressFree(ad.getValue().toString());
                        }
                        if (ad.getName().getLocalPart().equals(ADDRESS_FIX)) {
                            cl.sii.crs2.sara.export.domain.crs.v1.oecd.ties.commontypesfatcacrs.v1.AddressFixType aft = (cl.sii.crs2.sara.export.domain.crs.v1.oecd.ties.commontypesfatcacrs.v1.AddressFixType) ad.getValue();
                            address.setAddressFixStreet(aft.getStreet());
                            address.setAddressFixBuildingIdentifier(aft.getBuildingIdentifier());
                            address.setAddressFixSuiteIdentifier(aft.getSuiteIdentifier());
                            address.setAddressFixFloorIdentifier(aft.getFloorIdentifier());
                            address.setAddressFixDistrictName(aft.getDistrictName());
                            address.setAddressFixPOB(aft.getPOB());
                            address.setAddressFixPostCode(aft.getPostCode());
                            address.setAddressFixCity(aft.getCity());
                            address.setAddressFixCountrySubentity(aft.getCountrySubentity());
                        }

                    });
                    address.setIdAddress(Ids.INSTANCE.id());
                    fi.addAdress(address);

                });
                fi.setCreatedAt(new Date());
                fi.setCreatedBy("AI");
                fi.setCrsReportingId(Ids.INSTANCE.id());
                lFI.add(fi);

               // reportingFIRepository.saveAndFlush(fi);
            });

        }else{
            List<cl.sii.crs2.sara.export.domain.crs.v2.oecd.ties.crs.v2.CrsBodyType> crsBodyList= ((cl.sii.crs2.sara.export.domain.crs.v2.oecd.ties.crs.v2.CRSOECD) this.oecd).getCrsBody();

            crsBodyList.forEach(b->{
                CrsReportingFI fi= new CrsReportingFI();
                fi.setMessageRefId(this.messageRefId);

                cl.sii.crs2.sara.export.domain.crs.v2.oecd.ties.crs.v2.CorrectableOrganisationPartyType reportingFI = b.getReportingFI();
                reportingFI.getResCountryCode().forEach(c->{
                    fi.setResCountryCode(c.value());
                });
                reportingFI.getIN().forEach(i->{
                    fi.setFiIN(i.getValue());
                });
                reportingFI.getName().forEach(n->{
                    fi.setName(n.getValue());
                });

               cl.sii.crs2.sara.export.domain.crs.v2.oecd.ties.crsstf.v5.DocSpecType docSpecType= reportingFI.getDocSpec();

                cl.sii.crs2.sara.export.domain.crs.v2.oecd.ties.crsstf.v5.OECDDocTypeIndicEnumType otiet=docSpecType.getDocTypeIndic();
                fi.setDocTypeIndic(otiet!=null?otiet.value():null);
                fi.setDocRefId(docSpecType.getDocRefId());

                reportingFI.getAddress().forEach(a->{
                    CrsAddress address= new CrsAddress();
                    a.getContent().forEach(ad->{
                        if (ad.getName().getLocalPart().equals(COUNTRY_CODE)) {
                            address.setCountryCode(ad.getValue().toString());
                        }
                        if (ad.getName().getLocalPart().equals(ADDRESS_FREE)) {
                            address.setAddressFree(ad.getValue().toString());
                        }
                        if (ad.getName().getLocalPart().equals(ADDRESS_FIX)) {
                            cl.sii.crs2.sara.export.domain.crs.v2.oecd.ties.commontypesfatcacrs.v2.AddressFixType aft = (cl.sii.crs2.sara.export.domain.crs.v2.oecd.ties.commontypesfatcacrs.v2.AddressFixType) ad.getValue();
                            address.setAddressFixStreet(aft.getStreet());
                            address.setAddressFixBuildingIdentifier(aft.getBuildingIdentifier());
                            address.setAddressFixSuiteIdentifier(aft.getSuiteIdentifier());
                            address.setAddressFixFloorIdentifier(aft.getFloorIdentifier());
                            address.setAddressFixDistrictName(aft.getDistrictName());
                            address.setAddressFixPOB(aft.getPOB());
                            address.setAddressFixPostCode(aft.getPostCode());
                            address.setAddressFixCity(aft.getCity());
                            address.setAddressFixCountrySubentity(aft.getCountrySubentity());
                        }

                    });
                    address.setIdAddress(Ids.INSTANCE.id());
                    fi.addAdress(address);
                });
                fi.setCreatedAt(new Date());
                fi.setCreatedBy("AI");
                fi.setCrsReportingId(Ids.INSTANCE.id());
                lFI.add(fi);
               // reportingFIRepository.saveAndFlush(fi);
            });
        }

    }

    @Transactional
    public void saveAll(){
        int batchSize = 100;
        AtomicInteger i= new AtomicInteger();
        this.lMS.forEach(m->{
            messageSpecRepository.saveAndFlush(m);
            i.getAndIncrement();
            if (i.get() % batchSize == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        });

        i.set(0);
        this.lFI.forEach(f->{
            reportingFIRepository.saveAndFlush(f);
            i.getAndIncrement();
            if (i.get() % batchSize == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        });
    }

}
