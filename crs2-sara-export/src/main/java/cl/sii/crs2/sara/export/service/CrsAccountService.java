package cl.sii.crs2.sara.export.service;


import cl.sii.crs2.sara.export.entities.CountryInfo;
import cl.sii.crs2.sara.export.entities.crs.CrsAccount;
import cl.sii.crs2.sara.export.repository.CountryInfoRepository;
import cl.sii.crs2.sara.export.repository.crs.CrsAccountRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Slf4j
public class CrsAccountService {
    @Autowired
    CrsAccountRepository crsAccountRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void updateAgreement(){

        crsAccountRepository.updateIniIsAgreement();
        log.info("##### updateAgreement");
        List<Object[]> l = crsAccountRepository.obtenerConvenios();
        int i=0;
        int batchSize = 100;
        for (Object[] o : l) {
            crsAccountRepository.updateIsAgreement((String)o[0],String.valueOf(o[1]));
//            entityManager.createQuery("UPDATE CrsAccount c SET c.isAgreement =:isAgree WHERE c.idAccount=:idAccount")
//                    .setParameter("isAgree", String.valueOf(o[1]))
//                    .setParameter("idAccount", (String)o[0])
//                    .executeUpdate();
           i++;
            if (i % batchSize == 0) {
                entityManager.flush();
                entityManager.clear();
            }
            System.out.println(i);
        }

    };



}
