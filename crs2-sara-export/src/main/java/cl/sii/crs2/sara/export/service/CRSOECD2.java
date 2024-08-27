package cl.sii.crs2.sara.export.service;


import cl.sii.crs2.sara.export.domain.crs.v2.oecd.ties.commontypesfatcacrs.v2.AddressFixType;
import cl.sii.crs2.sara.export.domain.crs.v2.oecd.ties.crs.v2.CRSOECD;
import cl.sii.crs2.sara.export.domain.crs.v2.oecd.ties.crs.v2.PersonPartyType;
import cl.sii.crs2.sara.export.domain.crs.v2.oecd.ties.isocrstypes.v1.CountryCodeType;
import cl.sii.crs2.sara.export.entities.crs.CrsAccount;
import cl.sii.crs2.sara.export.entities.crs.CrsControllingPerson;
import cl.sii.crs2.sara.export.entities.crs.CrsFI;
import cl.sii.crs2.sara.export.entities.crs.CrsPayment;
import cl.sii.crs2.sara.export.repository.crs.CrsAccountRepository;
import cl.sii.crs2.sara.export.repository.crs.CrsFIRepository;
import cl.sii.crs2.sara.export.util.Ids;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class CRSOECD2 {
    @Autowired
    CrsFIRepository crsFIRepository;

    @Autowired
    CrsAccountRepository crsAccountRepository;

    public void process(Unmarshaller unmarshaller, File xmlFile) throws JAXBException {
        CRSOECD crsoecd=(CRSOECD) unmarshaller.unmarshal(xmlFile);
        CrsFI fi=getFI(crsoecd);
        if(fi.getFiId()!=null) {
            crsFIRepository.saveAndFlush(fi);
        }
        getAccount(crsoecd, fi);

        System.out.println("MessageRefId==> " + crsoecd.getMessageSpec().getMessageRefId());
    }


    CrsFI getFI(CRSOECD c){
        CrsFI data = new CrsFI();
        String countryDefault = c.getMessageSpec().getReceivingCountry().value();
        c.getCrsBody().forEach(b->{
            data.setFiId(b.getReportingFI().getDocSpec().getDocRefId());
            data.setFiName(b.getReportingFI().getName().get(0).getValue());
            c.getCrsBody().get(0).getReportingFI().getIN().forEach(i->{
                if (data.getFiIn() == null) {
                    if (i.getIssuedBy()!=null) data.setFiInCountry(i.getIssuedBy().value());
                    data.setFiId(i.getValue());
                }
                if (countryDefault.equals(data.getFiInCountry())) {
                    data.setFiInCountry(i.getIssuedBy().value());
                    data.setFiIn(i.getValue());
                }
            });
            b.getReportingFI().getAddress().get(0).getContent().forEach(a->{
                if (a.getName().getLocalPart().equals("CountryCode")){
                    data.setFiAddressCountry(a.getValue().toString());
                }
                if (a.getName().getLocalPart().equals("AddressFix")){
                    AddressFixType aft = (AddressFixType) a.getValue();
                    data.setFiAddressFixStreet(aft.getStreet());
                    data.setFiAddressFixCity(aft.getCity());
                    data.setFiAddressFixPostCode(aft.getPostCode());
                }
                if (a.getName().getLocalPart().equals("AddressFree")){
                    data.setFiAddressFree(a.getValue().toString());
                }

            });
        });
        data.setCountry(c.getMessageSpec().getTransmittingCountry().value());
        data.setFiReportingPeriod(c.getMessageSpec().getReportingPeriod().toString());
        return data;
    }

    void getAccount(CRSOECD c, CrsFI fi){

        String countryDefault = c.getMessageSpec().getReceivingCountry().value();
        List<CrsAccount> lacc= new ArrayList<>();
        c.getCrsBody().forEach(a->{
            a.getReportingGroup().forEach(r->{
                //ACCOUNTS
                r.getAccountReport().forEach(acc->{
                    CrsAccount data = new CrsAccount();
                    data.setReceivingCountry(countryDefault);

                    data.setIdAccount(acc.getDocSpec().getDocRefId());
                    if (acc.getAccountHolder().getAcctHolderType()!=null)
                        data.setOAcctHolderType(acc.getAccountHolder().getAcctHolderType().value());

                    acc.getPayment().forEach(p->{
                        CrsPayment cp = new CrsPayment();
                        cp.setIdPayment(Ids.INSTANCE.id());
                        cp.setPaymentType(p.getType().value());
                        cp.setPayCurrency(p.getPaymentAmnt().getCurrCode().value());
                        if(p.getPaymentAmnt().getValue()!=null){
                            cp.setPayment(p.getPaymentAmnt().getValue());
                        }
                        data.addPayment(cp);
                    });

                    if(acc.getAccountHolder().getIndividual()!=null){
                        acc.getAccountHolder().getIndividual().getResCountryCode().forEach(rc->{
                            data.setIResCountryCode(rc!=null?rc.value():null);
                        });
                        data.setType("I");
                        acc.getAccountHolder().getIndividual().getName().forEach(n->{
                            data.setIFirstName(n.getFirstName().getValue());
                         if(!n.getMiddleName().isEmpty())   data.setIMiddleName(n.getMiddleName().get(0).getValue());
                            data.setILastName(n.getLastName().getValue());
                        });

                        acc.getAccountHolder().getIndividual().getAddress().forEach(ad->{
                            ad.getContent().forEach(dt->{
                                if(dt.getName().getLocalPart().equals("CountryCode")){
                                    CountryCodeType cct= (CountryCodeType) dt.getValue();
                                    data.setIAddressCountryCode(cct!=null?cct.value():null);
                                }
                                if (dt.getName().getLocalPart().equals("AddressFix")){
                                    AddressFixType aft = (AddressFixType) dt.getValue();
                                    data.setIAddressFixStreet(aft.getStreet());
                                    data.setIAddressFixCity(aft.getCity());
                                }
                                if (dt.getName().getLocalPart().equals("AddressFree")){
                                    data.setIAddressFree(dt.getValue().toString());
                                }
                            });
                        });
                        PersonPartyType.BirthInfo ppt=acc.getAccountHolder().getIndividual().getBirthInfo();
                        if (ppt!=null){
                            data.setIBirthDate(ppt.getBirthDate()!=null?ppt.getBirthDate().toString():null);
                           if(ppt.getCountryInfo()!=null)
                               data.setIBirthCountryInfo(ppt.getCountryInfo().getCountryCode()!=null?ppt.getCountryInfo().getCountryCode().value(): null);
                        }

                        acc.getAccountHolder().getIndividual().getTIN().forEach(t->{
                            data.setITin(t.getValue());
                            data.setITinCountry(t.getIssuedBy()!=null?t.getIssuedBy().value():null);
                            if (countryDefault.equals(data.getITinCountry())) {
                                data.setITinCountry(countryDefault);
                                data.setITin(t.getValue());
                            }
                        });
                    }else{
                        data.setType("O");
                        data.setOName(acc.getAccountHolder().getOrganisation().getName().get(0).getValue());
                        acc.getAccountHolder().getOrganisation().getResCountryCode().forEach(rc->{
                            data.setOResCountryCode(rc!=null?rc.value():null);
                        });
                        acc.getAccountHolder().getOrganisation().getAddress().forEach(ad->{
                            ad.getContent().forEach(dt->{
                                if(dt.getName().getLocalPart().equals("CountryCode")){
                                    CountryCodeType cct= (CountryCodeType) dt.getValue();
                                    data.setOAddressCountryCode(cct.value());
                                }
                                if (dt.getName().getLocalPart().equals("AddressFix")){
                                    AddressFixType aft = (AddressFixType) dt.getValue();
                                    data.setOAddressFixStreet(aft.getStreet());
                                    data.setOAddressFixCity(aft.getCity());
                                }
                                if (dt.getName().getLocalPart().equals("AddressFree")){
                                    data.setOAddressFree(dt.getValue().toString());
                                }
                            });
                        });

                        acc.getAccountHolder().getOrganisation().getIN().forEach(i->{
                            data.setOInCountry(i.getIssuedBy()!=null?i.getIssuedBy().value():null);
                            data.setOIn(i.getValue());
                            if (countryDefault.equals(data.getOInCountry())) {
                                data.setOInCountry(countryDefault);
                                data.setOIn(i.getValue());
                            }
                        });

                        acc.getControllingPerson().forEach(cp->{
                            CrsControllingPerson ccp = new CrsControllingPerson();
                            ccp.setIdControllingPerson(Ids.INSTANCE.id());
                            if(cp.getIndividual()!=null) {
                               cp.getIndividual().getName().forEach(n -> {
                                    data.setIFirstName(n.getFirstName().getValue());
                                    data.setIMiddleName(!n.getMiddleName().isEmpty()? n.getMiddleName().get(0).getValue():null);
                                    data.setILastName(n.getLastName()!=null?n.getLastName().getValue():null);
                                });

                                cp.getIndividual().getTIN().forEach(t->{
                                    data.setITin(t.getValue());
                                    data.setITinCountry(t.getIssuedBy().value());
                                    if (countryDefault.equals(data.getITinCountry())) {
                                        data.setITinCountry(countryDefault);
                                        data.setITin(t.getValue());
                                    }
                                });

                                cp.getIndividual().getAddress().forEach(ad->{
                                    ad.getContent().forEach(dt->{
                                        if(dt.getName().getLocalPart().equals("CountryCode")){
                                            CountryCodeType cct= (CountryCodeType) dt.getValue();
                                            ccp.setCpAddressCountrycode(cct.value());
                                        }
                                        if (dt.getName().getLocalPart().equals("AddressFix")){
                                            AddressFixType aft = (AddressFixType) dt.getValue();
                                            ccp.setCpAddressfixStreet(aft.getStreet());
                                            ccp.setCpAddressfixCity(aft.getCity());
                                        }
                                        if (dt.getName().getLocalPart().equals("AddressFree")){
                                            ccp.setCpAddressfree(dt.getValue().toString());
                                        }
                                    });
                                });
                            }


                            ccp.setCpTinCountry(data.getITinCountry());
                            ccp.setCpTin(data.getITin());
                            ccp.setCpFirstname(data.getIFirstName());
                            ccp.setCpMiddlename(data.getIMiddleName());
                            ccp.setCpLastname(data.getILastName());

                            if(cp.getIndividual()!=null) {
                                PersonPartyType.BirthInfo ppt = cp.getIndividual().getBirthInfo();
                                if (ppt != null) {
                                    ccp.setCpBirthDate(ppt.getBirthDate().toString());
                                    if (ppt.getCountryInfo() != null)
                                        ccp.setCpBirthCountryInfo(ppt.getCountryInfo().getCountryCode().value());
                                }
                            }
                            data.addControllingPerson(ccp);
                        });

                    }

                    data.setNumber(acc.getAccountNumber().getValue());
                    if (acc.getAccountNumber().getAcctNumberType()!=null) data.setNumberCode(acc.getAccountNumber().getAcctNumberType().value());
                    data.setCurrency(acc.getAccountBalance().getCurrCode()!=null?acc.getAccountBalance().getCurrCode().value():null);
                    data.setBalance(acc.getAccountBalance().getValue());
                    data.setFi(fi);
                    lacc.add(data);
                    //crsAccountRepository.save(data);
                });
            });

        });
        crsAccountRepository.saveAllAndFlush(lacc);
    }
}

