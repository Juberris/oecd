package cl.sii.filepackaging.util;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import cl.sii.filepackaging.util.Country.CountryCode;


@Component
@Slf4j
public class CtsHttpApi {
    @Value("${cl.sii.sdi.lob.ss.sara.domain.cts.api.uri}")
    private String uri;
    @Autowired
    HttpApiConnect httpApiConnect;


    public boolean updateCert(final CountryCode countryCode) {

        try {
            String accessToken =httpApiConnect.getToken();
            return httpApiConnect.getApiCert(accessToken, countryCode);

        } catch (Exception e) {
           log.error("Error al obtener certificado de ==> {}",countryCode.name());
           return false;
        }

    }
}
