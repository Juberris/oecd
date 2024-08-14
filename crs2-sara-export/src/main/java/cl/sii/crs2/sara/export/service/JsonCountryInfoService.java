package cl.sii.crs2.sara.export.service;

import cl.sii.crs2.sara.export.entities.CountryInfo;
import cl.sii.crs2.sara.export.repository.CountryInfoRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.transaction.annotation.Transactional;



import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

@Service
public class JsonCountryInfoService {
    private final ObjectMapper objectMapper;
    private final CountryInfoRepository countryInfoRepository;

    @Autowired
    public JsonCountryInfoService(ObjectMapper objectMapper, CountryInfoRepository countryInfoRepository) {
        this.objectMapper = objectMapper;
        this.countryInfoRepository = countryInfoRepository;
    }


    public Map<String, CountryInfo> readJsonFile(String filePath) throws IOException {
        File jsonFile = new File(filePath);
        return objectMapper.readValue(jsonFile, new TypeReference<Map<String, CountryInfo>>() {});
    }

    @Transactional
    public void saveCountryInfo(Map<String, CountryInfo> countryInfoMap) {
        System.out.println("Cantidad a guardar ==> " + countryInfoMap.size());
        List<CountryInfo> list = countryInfoMap.values().stream().toList();
        countryInfoRepository.saveAllAndFlush(list);
    }

  public List<CountryInfo> listCountryInfo(){
        return countryInfoRepository.findAll();

  }
}
