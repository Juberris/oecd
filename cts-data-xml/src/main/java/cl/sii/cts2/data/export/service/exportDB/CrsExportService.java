package cl.sii.cts2.data.export.service.exportDB;


import cl.sii.cts2.data.export.entities.crs.*;
import cl.sii.cts2.data.export.entities.oecd.MessageSpec;
import cl.sii.cts2.data.export.repository.CrsAccountReportRepository;
import cl.sii.cts2.data.export.repository.CrsReportingFIRepository;
import cl.sii.cts2.data.export.repository.MessageSpecRepository;
import cl.sii.cts2.data.export.util.Ids;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.datatype.XMLGregorianCalendar;
import java.text.SimpleDateFormat;
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
    @Getter
    List<CrsAccountReport> LAR= new ArrayList<>();

    @Autowired
    MessageSpecRepository messageSpecRepository;

    @Autowired
    CrsAccountReportRepository accountReportRepository;
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
          cl.sii.cts2.data.export.domain.crs.v1.oecd.ties.crs.v1.MessageSpecType messageSpec= ((cl.sii.cts2.data.export.domain.crs.v1.oecd.ties.crs.v1.CRSOECD) this.oecd).getMessageSpec();

            ms.setMessageRefId(messageSpec.getMessageRefId());
            this.messageRefId=messageSpec.getMessageRefId();
           cl.sii.cts2.data.export.domain.crs.v1.oecd.ties.isocrstypes.v1.CountryCodeType tc=messageSpec.getTransmittingCountry();
            ms.setTransmittingCountry(tc.value());
          cl.sii.cts2.data.export.domain.crs.v1.oecd.ties.isocrstypes.v1.CountryCodeType rc=messageSpec.getReceivingCountry();
            ms.setReceivingCountry(rc.value());
          cl.sii.cts2.data.export.domain.crs.v1.oecd.ties.crs.v1.MessageTypeEnumType mt=messageSpec.getMessageType();
            ms.setMessageType(mt.value());
            ms.setWarning(messageSpec.getWarning());
            ms.setContact(messageSpec.getContact());
          cl.sii.cts2.data.export.domain.crs.v1.oecd.ties.crs.v1.CrsMessageTypeIndicEnumType mti=messageSpec.getMessageTypeIndic();
            ms.setMessageTypeIndic(mti!=null?mti.value():null);
            ms.setReportingPeriod(messageSpec.getReportingPeriod().toGregorianCalendar().getTime());
            ms.setTimestamp(messageSpec.getTimestamp().toGregorianCalendar().getTime());
          corrMRefid.addAll(messageSpec.getCorrMessageRefId());
      }else {
          cl.sii.cts2.data.export.domain.crs.v2.oecd.ties.crs.v2.MessageSpecType messageSpec= ((cl.sii.cts2.data.export.domain.crs.v2.oecd.ties.crs.v2.CRSOECD) this.oecd).getMessageSpec();
          ms.setMessageRefId(messageSpec.getMessageRefId());
          cl.sii.cts2.data.export.domain.crs.v2.oecd.ties.isocrstypes.v1.CountryCodeType tc=messageSpec.getTransmittingCountry();
          ms.setTransmittingCountry(tc.value());
          cl.sii.cts2.data.export.domain.crs.v2.oecd.ties.isocrstypes.v1.CountryCodeType rc=messageSpec.getReceivingCountry();
          ms.setReceivingCountry(rc.value());
          cl.sii.cts2.data.export.domain.crs.v2.oecd.ties.crs.v2.MessageTypeEnumType mt=messageSpec.getMessageType();
          ms.setMessageType(mt.value());
          ms.setWarning(messageSpec.getWarning());
          ms.setContact(messageSpec.getContact());
          cl.sii.cts2.data.export.domain.crs.v2.oecd.ties.crs.v2.CrsMessageTypeIndicEnumType mti=messageSpec.getMessageTypeIndic();
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
            List<cl.sii.cts2.data.export.domain.crs.v1.oecd.ties.crs.v1.CrsBodyType> crsBodyList= ((cl.sii.cts2.data.export.domain.crs.v1.oecd.ties.crs.v1.CRSOECD) this.oecd).getCrsBody();
            crsBodyList.forEach(b->{
                CrsReportingFI fi= new CrsReportingFI();
                fi.setMessageRefId(this.messageRefId);

                cl.sii.cts2.data.export.domain.crs.v1.oecd.ties.crs.v1.CorrectableOrganisationPartyType reportingFI = b.getReportingFI();
                reportingFI.getResCountryCode().forEach(c->{
                    fi.setResCountryCode(c.value());
                });
                reportingFI.getIN().forEach(i->{
                    fi.setFiIN(i.getValue());
                });
                reportingFI.getName().forEach(n->{
                    fi.setName(n.getValue());
                });

                cl.sii.cts2.data.export.domain.crs.v1.oecd.ties.stf.v4.DocSpecType docSpecType= reportingFI.getDocSpec();
                if (docSpecType!=null) {
                    cl.sii.cts2.data.export.domain.crs.v1.oecd.ties.stf.v4.OECDDocTypeIndicEnumType otiet = docSpecType.getDocTypeIndic();
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
                            cl.sii.cts2.data.export.domain.crs.v1.oecd.ties.commontypesfatcacrs.v1.AddressFixType aft = (cl.sii.cts2.data.export.domain.crs.v1.oecd.ties.commontypesfatcacrs.v1.AddressFixType) ad.getValue();
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
                    address.setCrsAddressId(Ids.INSTANCE.id());
                    fi.addAdress(address);

                });
                fi.setCreatedAt(new Date());
                fi.setCreatedBy("AI");
                fi.setCrsReportingId(Ids.INSTANCE.id());
                lFI.add(fi);
                b.getReportingGroup().forEach(this::cargarAccountReport);

            });

        }else{
            List<cl.sii.cts2.data.export.domain.crs.v2.oecd.ties.crs.v2.CrsBodyType> crsBodyList= ((cl.sii.cts2.data.export.domain.crs.v2.oecd.ties.crs.v2.CRSOECD) this.oecd).getCrsBody();

            crsBodyList.forEach(b->{
                CrsReportingFI fi= new CrsReportingFI();
                fi.setMessageRefId(this.messageRefId);

                cl.sii.cts2.data.export.domain.crs.v2.oecd.ties.crs.v2.CorrectableOrganisationPartyType reportingFI = b.getReportingFI();
                reportingFI.getResCountryCode().forEach(c->{
                    fi.setResCountryCode(c.value());
                });
                reportingFI.getIN().forEach(i->{
                    fi.setFiIN(i.getValue());
                });
                reportingFI.getName().forEach(n->{
                    fi.setName(n.getValue());
                });

               cl.sii.cts2.data.export.domain.crs.v2.oecd.ties.crsstf.v5.DocSpecType docSpecType= reportingFI.getDocSpec();

                cl.sii.cts2.data.export.domain.crs.v2.oecd.ties.crsstf.v5.OECDDocTypeIndicEnumType otiet=docSpecType.getDocTypeIndic();
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
                            cl.sii.cts2.data.export.domain.crs.v2.oecd.ties.commontypesfatcacrs.v2.AddressFixType aft = (cl.sii.cts2.data.export.domain.crs.v2.oecd.ties.commontypesfatcacrs.v2.AddressFixType) ad.getValue();
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
                    address.setCrsAddressId(Ids.INSTANCE.id());
                    fi.addAdress(address);
                });
                fi.setCreatedAt(new Date());
                fi.setCreatedBy("AI");
                fi.setCrsReportingId(Ids.INSTANCE.id());
                lFI.add(fi);
                b.getReportingGroup().forEach(this::cargarAccountReport);
            });
        }

    }

    void cargarAccountReport(Object reportingGroup){

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String jsonString = objectMapper.writeValueAsString(reportingGroup);

                objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(jsonString);
                JsonNode acc = rootNode.get("accountReport");
                if (!acc.isNull()){
                    ArrayNode listAcc = (ArrayNode) acc;

                    listAcc.forEach(i->{
                        CrsAccountReport ar= new CrsAccountReport();
                        ar.setCrsAccountReportId(Ids.INSTANCE.id());
                        ar.setMessageRefId(this.messageRefId);
                        ar.setDocTypeIndic(i.get("docSpec").get("docTypeIndic").textValue());
                        ar.setDocRefId(i.get("docSpec").get("docRefId").textValue());
                        ar.setCorrDocRefId(i.get("docSpec").get("corrDocRefId").textValue());
                        ar.setCorrMessageRefId(i.get("docSpec").get("corrMessageRefId")!=null?i.get("docSpec").get("corrMessageRefId").textValue():null);
                        ar.setAccountNumber(i.get("accountNumber").get("value").textValue());
                        ar.setAcctNumberType(i.get("accountNumber").get("acctNumberType").textValue());
                        ar.setUndocumentedAccount(i.get("accountNumber").get("undocumentedAccount").toString());
                        ar.setClosedAccount(i.get("accountNumber").get("closedAccount").toString());
                        ar.setDormantAccount(i.get("accountNumber").get("dormantAccount").toString());
                        ar.setAccountBalance(i.get("accountBalance").get("value").toString());
                        ar.setCurrCode(i.get("accountBalance").get("currCode").textValue());
                        CrsAccountHolder ah= new CrsAccountHolder();

                        JsonNode indiv= i.get("accountHolder").get("individual");
                        JsonNode organi= i.get("accountHolder").get("organisation");
                        if(!indiv.isNull()){
                            getIndivOrgani(indiv,ar,ah,"I");
                        }
                        if(!organi.isNull()){
                            ah.setAcctHolderType(i.get("accountHolder").get("acctHolderType").textValue());
                            getIndivOrgani(organi,ar,ah,"O");
                        }

                        JsonNode ctrlngPerson=i.get("controllingPerson");
                        ArrayNode listCP = (ArrayNode) ctrlngPerson;
                        listCP.forEach(p->{
                            CrsControllingPerson cp=new CrsControllingPerson();
                            getControllingPerson(p,ar,cp);
                        });

                        JsonNode payment=i.get("payment");
                        getPayment(payment,ar);
                        this.LAR.add(ar);
                    });

                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }


    }
    void getPayment(JsonNode pay,CrsAccountReport ar){
        if(!pay.isNull()){
            ArrayNode listPay = (ArrayNode) pay;
            listPay.forEach(p->{
                CrsPayment py=new CrsPayment();
                py.setType(p.get("type").textValue());
               JsonNode pa=p.get("paymentAmnt");
               if(!pa.isNull()){
                   py.setPaymentAmnt(pa.get("value").toString());
                   py.setCurrCode(pa.get("currCode").textValue());
               }
                py.setIdPayment(Ids.INSTANCE.id());
                ar.addCrsPayment(py);
            });


        }

    }
void getControllingPerson(JsonNode ctrlP,CrsAccountReport ar, CrsControllingPerson cp){
    cp.setCtrlgPersonType(ctrlP.get("ctrlgPersonType").textValue());

    JsonNode rcc = ctrlP.get("individual").get("resCountryCode");
    if(!rcc.isNull()){
        ArrayNode listRcc = (ArrayNode) rcc;
        String codeCountry="";
        listRcc.forEach(r->{
            cp.setResCountryCode(r.textValue());
        });
    }
    JsonNode tin = ctrlP.get("individual").get("tin");
    if (!tin.isNull()) {
        ArrayNode listTin = (ArrayNode) tin;
        listTin.forEach(t -> {
            cp.setTIN(t.get("value").textValue());
            cp.setIssuedBy(t.get("issuedBy").textValue());
        });
    }

    JsonNode name= ctrlP.get("individual").get("name");
    ArrayNode listName = (ArrayNode) name;
    if (!name.isNull()){
            listName.forEach(n -> {
                cp.setFirstName(n.get("firstName").get("value").textValue());
                JsonNode mname = n.get("middleName");
                if (!mname.isNull()) {
                    ArrayNode listMName = (ArrayNode) mname;
                    listMName.forEach(mi -> {
                        cp.setMiddleName(mi.get("value").textValue());
                    });
                }
                cp.setLastName(n.get("lastName").get("value").textValue());
            });
    }

    JsonNode addr=ctrlP.get("individual").get("address");
    if(!addr.isNull()){
        ArrayNode listAddr = (ArrayNode) addr;
        listAddr.forEach(ad->{
            CrsAddress address= new CrsAddress();
            JsonNode content=ad.get("content");
            if (!content.isNull()) {
                ArrayNode listContent = (ArrayNode) content;
                listContent.forEach(c -> {
                    if(c.get("name").textValue().contains("CountryCode")){
                        address.setCountryCode(c.get("value").textValue());
                    }
                    if(c.get("name").textValue().contains("AddressFix")){
                        JsonNode nodeFix =c.get("value");
                        if(!nodeFix.isNull()){
                            address.setAddressFixStreet(nodeFix.get("street").textValue());
                            address.setAddressFixBuildingIdentifier(nodeFix.get("buildingIdentifier").textValue());
                            address.setAddressFixSuiteIdentifier(nodeFix.get("suiteIdentifier").textValue());
                            address.setAddressFixFloorIdentifier(nodeFix.get("floorIdentifier").textValue());
                            address.setAddressFixDistrictName(nodeFix.get("districtName").textValue());
                            address.setAddressFixPOB(nodeFix.get("pob").textValue());
                            address.setAddressFixPostCode(nodeFix.get("postCode").textValue());
                            address.setAddressFixCity(nodeFix.get("city").textValue());
                            address.setAddressFixCountrySubentity(nodeFix.get("countrySubentity").textValue());
                        }

                    }
                    if(c.get("name").textValue().contains("AddressFree")){
                        address.setAddressFree(c.get("value").textValue());
                    }

                    address.setCrsAddressId(Ids.INSTANCE.id());
                    cp.addAdress(address);
                });
            }
            cp.setControllingPersonId(Ids.INSTANCE.id());
            ar.addCrsControllingPerson(cp);
        });
    }

    JsonNode nation = ctrlP.get("individual").get("nationality");
    if (!nation.isNull()) {
        ArrayNode listNation = (ArrayNode) nation;
        listNation.forEach(na -> {
            cp.setNationality(na.textValue());
        });
    }

    JsonNode birth=ctrlP.get("individual").get("birthInfo");
    if(!birth.isNull()){
        Date date = new Date(birth.get("birthDate").asLong());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        cp.setBirthDate(formatter.format(date));
        cp.setBirthCity(birth.get("city").textValue());
        cp.setBirthCitySubentity(birth.get("citySubentity").textValue());
        cp.setBirthCountryCode(birth.get("countryInfo").isNull()?null:birth.get("countryInfo").get("countryCode").textValue());
    }
}

    void getIndivOrgani(JsonNode IndivOrg,CrsAccountReport ar, CrsAccountHolder ah, String type){

            JsonNode rcc = IndivOrg.get("resCountryCode");
            if(!rcc.isNull()){
                ArrayNode listRcc = (ArrayNode) rcc;
                String codeCountry="";
                listRcc.forEach(r->{
                    ah.setResCountryCode(r.textValue());
                });
            }

            if(type.equals("I")) {
                JsonNode tin = IndivOrg.get("tin");
                if (!tin.isNull()) {
                    ArrayNode listTin = (ArrayNode) tin;
                    listTin.forEach(t -> {
                        ah.setAccTIN(t.get("value").textValue());
                        ah.setIssuedBy(t.get("issuedBy").textValue());
                    });
                }
            }else{
                JsonNode tin = IndivOrg.get("in");
                if (!tin.isNull()) {
                    ArrayNode listTin = (ArrayNode) tin;
                    listTin.forEach(t -> {
                        ah.setAccIN(t.get("value").textValue());
                        ah.setIssuedBy(t.get("issuedBy").textValue());
                    });
                }
            }

            JsonNode name=IndivOrg.get("name");
           ArrayNode listName = (ArrayNode) name;
            if (!name.isNull()){
                if (type.equals("I")) {
                    listName.forEach(n -> {
                        ah.setFirstName(n.get("firstName").get("value").textValue());
                        JsonNode mname = n.get("middleName");
                        if (!mname.isNull()) {
                            ArrayNode listMName = (ArrayNode) mname;
                            listMName.forEach(mi -> {
                                ah.setMiddleName(mi.get("value").textValue());
                            });
                        }
                        ah.setLastName(n.get("lastName").get("value").textValue());
                    });
                } else if (type.equals("O")){
                    listName.forEach(n -> {
                            ah.setFirstName(n.get("value").textValue());
                            });
                }

            }

            JsonNode addr=IndivOrg.get("address");
            if(!addr.isNull()){
                ArrayNode listAddr = (ArrayNode) addr;
                listAddr.forEach(ad->{
                    CrsAddress address= new CrsAddress();
                    JsonNode content=ad.get("content");
                    if (!content.isNull()) {
                        ArrayNode listContent = (ArrayNode) content;
                        listContent.forEach(c -> {
                            if(c.get("name").textValue().contains("CountryCode")){
                                address.setCountryCode(c.get("value").textValue());
                            }
                            if(c.get("name").textValue().contains("AddressFix")){
                                JsonNode nodeFix =c.get("value");
                                if(!nodeFix.isNull()){
                                    address.setAddressFixStreet(nodeFix.get("street").textValue());
                                    address.setAddressFixBuildingIdentifier(nodeFix.get("buildingIdentifier").textValue());
                                    address.setAddressFixSuiteIdentifier(nodeFix.get("suiteIdentifier").textValue());
                                    address.setAddressFixFloorIdentifier(nodeFix.get("floorIdentifier").textValue());
                                    address.setAddressFixDistrictName(nodeFix.get("districtName").textValue());
                                    address.setAddressFixPOB(nodeFix.get("pob").textValue());
                                    address.setAddressFixPostCode(nodeFix.get("postCode").textValue());
                                    address.setAddressFixCity(nodeFix.get("city").textValue());
                                    address.setAddressFixCountrySubentity(nodeFix.get("countrySubentity").textValue());
                                }

                            }
                            if(c.get("name").textValue().contains("AddressFree")){
                                address.setAddressFree(c.get("value").textValue());
                            }

                            address.setCrsAddressId(Ids.INSTANCE.id());
                            ah.addAdress(address);
                        });
                    }

                });
            }
            if(type.equals("I")) {
                    JsonNode nation = IndivOrg.get("nationality");
                    if (!nation.isNull()) {
                        ArrayNode listNation = (ArrayNode) nation;
                        listNation.forEach(na -> {
                            ah.setNationality(na.textValue());
                        });
                    }

                JsonNode birth=IndivOrg.get("birthInfo");
                if(!birth.isNull()){

                    Date date = new Date(birth.get("birthDate").asLong());
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    ah.setBirthDate(formatter.format(date));
                    ah.setBirthCity(birth.get("city").textValue());
                    ah.setBirthCitySubentity(birth.get("citySubentity").textValue());
                    ah.setBirthCountryCode(birth.get("countryInfo").isNull()?null:birth.get("countryInfo").get("countryCode").textValue());
                }
            }
            ah.setCrsAccountHolderId(Ids.INSTANCE.id());
            ar.setCrsAccountHolder(ah);
            ah.setCrsAccountReport(ar);
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


        i.set(0);
        this.LAR.forEach(a->{
            accountReportRepository.saveAndFlush(a);
            i.getAndIncrement();
            if (i.get() % batchSize == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        });


    }

}
