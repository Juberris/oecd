package cl.sii.crs2.sara.export.repository;

import cl.sii.crs2.sara.export.entities.CountryInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface CountryInfoRepository extends JpaRepository<CountryInfo, Long> {

    List<CountryInfo> findByTypeAndSrcIsoAndDstIso(String type, String srcIso, String dstIso);
}