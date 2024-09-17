package cl.sii.crs2.sara.export.service.exportDB;


import cl.sii.crs2.sara.export.entities.crs.*;
import cl.sii.crs2.sara.export.entities.oecd.MessageSpec;
import cl.sii.crs2.sara.export.repository.crs.CrsAccountReportRepository;
import cl.sii.crs2.sara.export.repository.crs.CrsReportingFIRepository;
import cl.sii.crs2.sara.export.repository.MessageSpecRepository;
import cl.sii.crs2.sara.export.util.Ids;
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
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


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
        //XmlMapper xmlMapper = new XmlMapper();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = objectMapper.writeValueAsString(this.oecd);
            objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonString);
            JsonNode messageSpec = rootNode.get("messageSpec");
            ms.setMessageRefId(messageSpec.get("messageRefId").textValue());
            this.messageRefId=ms.getMessageRefId();
            ms.setTransmittingCountry(messageSpec.get("transmittingCountry").textValue());
            ms.setReceivingCountry(messageSpec.get("receivingCountry").textValue());
            ms.setMessageType(messageSpec.get("messageType").textValue());
            ms.setWarning(messageSpec.get("warning").textValue());
            ms.setContact(messageSpec.get("contact").textValue());
            ms.setMessageTypeIndic(messageSpec.get("messageTypeIndic").textValue());
            Date date = new Date(messageSpec.get("reportingPeriod").asLong());
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            ms.setReportingPeriod(date);
            Date timestamp = new Date(messageSpec.get("timestamp").asLong());
            ms.setTimestamp(timestamp);
            JsonNode corrmfid= messageSpec.get("corrMessageRefId");
            if (!corrmfid.isNull()){
                ArrayNode listCorr = (ArrayNode) corrmfid;
                listCorr.forEach(c->{
                    corrMRefid.add(c.toString());
                });
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        ms.setFilename(filename);
        ms.setCreatedAt(new Date());
        ms.setCreatedBy("AI");
        ms.setCorrMessageRefId(corrMRefid);
        lMS.add(ms);
        // messageSpecRepository.save(ms);

    }


    void cargarReportingFI(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = objectMapper.writeValueAsString(this.oecd);
            objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonString);
            JsonNode crsBody=rootNode.get("crsBody");
            if (!crsBody.isNull()){
                ArrayNode crsBodyList= (ArrayNode) crsBody;
                crsBodyList.forEach(b->{
                    CrsReportingFI fi= new CrsReportingFI();
                    fi.setMessageRefId(this.messageRefId);
                    JsonNode rFI = b.get("reportingFI");
                   JsonNode rcc= rFI.get("resCountryCode");
                   if(!rcc.isNull()){
                       ArrayNode rccList=(ArrayNode) rcc;
                       rccList.forEach(c->{
                           fi.setResCountryCode(c.textValue());
                       });
                   }

                    JsonNode rin= rFI.get("in");
                    if(!rin.isNull()){
                        ArrayNode rinList=(ArrayNode) rin;
                        rinList.forEach(c->{
                            fi.setFiIN(c.get("value").textValue());
                        });
                    }

                    JsonNode name= rFI.get("name");
                    if(!name.isNull()){
                        ArrayNode nameList=(ArrayNode) name;
                        nameList.forEach(c->{
                            fi.setName(c.get("value").textValue());
                        });
                    }

                    JsonNode docSpec= rFI.get("docSpec");
                    if(!docSpec.isNull()){
                        fi.setDocTypeIndic(docSpec.get("docTypeIndic").textValue());
                        fi.setDocRefId(docSpec.get("docRefId").textValue());
                    }

                    JsonNode addressNode=rFI.get("address");
                    if(!addressNode.isNull()){
                        ArrayNode addressList=(ArrayNode) addressNode;
                        addressList.forEach(a->{
                            CrsAddress address= new CrsAddress();
                            JsonNode content=a.get("content");
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
                                    if(!c.get("name").textValue().contains("CountryCode")) {
                                        address.setCrsAddressId(Ids.INSTANCE.id());
                                        fi.addAdress(address);
                                    }
                                });
                            }
                        });
                    }
                    fi.setCreatedAt(new Date());
                    fi.setCreatedBy("AI");
                    fi.setCrsReportingId(Ids.INSTANCE.id());
                    lFI.add(fi);
                    JsonNode reportingGroup=b.get("reportingGroup");
                    if(!reportingGroup.isNull()){
                        ArrayNode reportingGroupList= (ArrayNode) reportingGroup;
                        reportingGroupList.forEach(this::cargarAccountReport);
                    }

                });

            }
        } catch (Exception e) {
            e.printStackTrace();
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
                    ar.setCorrMessageRefId(!i.get("docSpec").get("corrMessageRefId").isNull()?i.get("docSpec").get("corrMessageRefId").textValue():null);

                    ar.setAccountNumber(!i.get("accountNumber").isNull()?i.get("accountNumber").get("value").textValue():null);
                    ar.setAcctNumberType(!i.get("accountNumber").isNull()?i.get("accountNumber").get("acctNumberType").textValue():null);
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
                        if(!c.get("name").textValue().contains("CountryCode")) {
                            address.setCrsAddressId(Ids.INSTANCE.id());
                            cp.addAdress(address);
                        }
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
        Set<String> setRcc = new HashSet<>();
        JsonNode rcc = IndivOrg.get("resCountryCode");
        if(!rcc.isNull()){
            ArrayNode listRcc = (ArrayNode) rcc;

            listRcc.forEach(r->{
                setRcc.add(r.textValue());
                ah.setResCountryCode(r.textValue());
            });

            if (setRcc.size()>1){
                ah.setResCountryCode(null);
            }
            ah.setResCountryCodeSet(setRcc);
        }

        if(type.equals("I")) {
            JsonNode tin = IndivOrg.get("tin");
            Set<TinEmbeddable> setTIN = new HashSet<>();
            if (!tin.isNull()) {
                ArrayNode listTin = (ArrayNode) tin;
                listTin.forEach(t -> {
                    setTIN.add(new TinEmbeddable(t.get("value").textValue(),t.get("issuedBy").textValue()));
                    ah.setAccTIN(t.get("value").textValue());
                    ah.setIssuedBy(t.get("issuedBy").textValue());
                });
                if (setTIN.size()>1){
                    ah.setAccTIN(null);
                }
                ah.setTINSet(setTIN);
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

                    if(!n.get("lastName").isNull()) {
                        ah.setLastName(n.get("lastName").get("value").textValue());
                    }
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
            Set<String> setNaty = new HashSet<>();
            JsonNode nation = IndivOrg.get("nationality");
            if (!nation.isNull()) {
                ArrayNode listNation = (ArrayNode) nation;
                listNation.forEach(na -> {
                    setNaty.add(na.textValue());
                    ah.setNationality(na.textValue());
                });
                if (setNaty.size()>1){
                    ah.setNationality(null);
                }
                ah.setNationalitySet(setNaty);
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
        int batchSize = 300;
        AtomicInteger i= new AtomicInteger();
        this.lMS.forEach(m->{
            messageSpecRepository.saveAndFlush(m);
            i.getAndIncrement();
            if (i.get() % batchSize == 0) {
                entityManager.flush();
                entityManager.clear();
            }
            System.out.println("LMS ==> "+ i.intValue());
        });

        i.set(0);
        this.lFI.forEach(f->{
            reportingFIRepository.saveAndFlush(f);
            i.getAndIncrement();
            if (i.get() % batchSize == 0) {
                entityManager.flush();
                entityManager.clear();
            }
            System.out.println("LFI ==> "+ i.intValue());
        });


        i.set(0);
        System.out.println("LAR ==> SaveAll");
        accountReportRepository.saveAllAndFlush(this.LAR);

//        this.LAR.forEach(a->{
//            accountReportRepository.saveAndFlush(a);
//            i.getAndIncrement();
//            if (i.get() % batchSize == 0) {
//                entityManager.flush();
//                entityManager.clear();
//            }
//            System.out.println("LAR ==> "+ i.intValue());
//        });


    }

    String convertInputStream(InputStream inputStream) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}



