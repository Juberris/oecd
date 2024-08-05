package cl.sii.crs2.sara.export.service;


import cl.sii.crs2.sara.export.entities.CountryInfo;
import cl.sii.crs2.sara.export.entities.crs.CrsAccount;
import cl.sii.crs2.sara.export.repository.CountryInfoRepository;
import cl.sii.crs2.sara.export.repository.crs.CrsAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CrsAccountService {
    @Autowired
    CrsAccountRepository crsAccountRepository;

    @Autowired
    CountryInfoRepository countryInfoRepository;

    public void updateAgreement(){
        List<CrsAccount> l = crsAccountRepository.findAll();

        l.forEach(a->{
            String isAgreement="N";
            String dstIso=getCountryCode(a.getIAddressCountryCode(), a.getIResCountryCode(),
                        a.getOAddressCountryCode(), a.getOResCountryCode());
            if(dstIso!=null){
                List<CountryInfo> list= countryInfoRepository.findByTypeAndSrcIsoAndDstIso("CRS","CL", dstIso);
                if (!list.isEmpty()){
                    isAgreement="Y";
                }
            }


            CrsAccount upd= new CrsAccount();
            BeanUtils.copyProperties(a, upd);
            upd.setIsAgreement(isAgreement);
            crsAccountRepository.save(upd);
               System.out.println(a.getIdAccount() + " == " +getCountryCode(a.getIAddressCountryCode(), a.getIResCountryCode(),
                              a.getOAddressCountryCode(), a.getOResCountryCode()));

        });

    };

    String getCountryCode(String iAddressCountryCode, String iResCountryCode,
                          String oAddressCountryCode, String oResCountryCode){

        if (iAddressCountryCode!=null && !iAddressCountryCode.equals("CL")){
           return iAddressCountryCode;
        }
        if (iResCountryCode!=null && !iResCountryCode.equals("CL")){
            return iResCountryCode;
        }
        if (oAddressCountryCode!=null && !oAddressCountryCode.equals("CL")){
            return oAddressCountryCode;
        }
        if (oResCountryCode!=null && !oResCountryCode.equals("CL")){
            return oResCountryCode;
        }
        return null;
    }

}
