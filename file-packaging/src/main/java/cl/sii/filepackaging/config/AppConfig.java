package cl.sii.filepackaging.config;

import cl.sii.filepackaging.model.AppProp;
import cl.sii.filepackaging.model.CodeErrorStatus;
import cl.sii.filepackaging.model.schema.generic_status.oecd.ties.gsm.v1.ValidationErrorsType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@Slf4j
public class AppConfig {
    @Value("${cl.sii.sdi.lob.ss.sara.domain.cts.api.pwd}")
    String pwd;
    @Value("${cl.sii.sdi.lob.ss.sara.domain.cts.api.cert}")
    String cert;
    @Value("${cl.sii.sdi.lob.ss.sara.domain.cts.api.uri}")
    String uri;
    @Value("${cl.sii.sdi.lob.ss.sara.domain.cts.ks.keystorePrePath}")
    String keystorePrePath;
    @Value("${cl.sii.sdi.lob.ss.sara.domain.cts.api.usr}")
    String usr;
    @Value("${cts2.country.code.sender}")
    String sender;
    @Value("${dir.inbox}")
    String inbox;
    @Value("${dir.outbox}")
    String outbox;

    @Value("${cl.sii.sdi.lob.ss.sara.domain.cts.ks.keystorePwd}")
    String keystorePwd;
    @Value("${cl.sii.sdi.lob.ss.sara.domain.cts.ks.keyPwd}")
    String keyPwd;
    @Value("${cl.sii.sdi.lob.ss.sara.domain.cts.ks.keyLength}")
    String keyLength;
    @Value("${cts.contact}")
    String contact;

    @Value("${cts.code.error-status}")
    String rutaCodeErrorStatus;

    private final ObjectMapper objectMapper=new ObjectMapper();

    @Bean
    public AppProp ctsProps() {
        return new AppProp(pwd, cert, uri, usr, sender, inbox, outbox,
                           keystorePrePath, keystorePwd, keyPwd, keyLength,
                           contact);
    }

    @Bean
    public ValidationErrorsType validationErrors(){
        return new ValidationErrorsType();
    }

    @Bean
    public Map<Integer, CodeErrorStatus> statusErrorMap() {

        return getList();
    }

    Map<Integer, CodeErrorStatus> getList()  {
        File jsonFile = new File(rutaCodeErrorStatus);

        try {
            List<CodeErrorStatus> list= objectMapper.readValue(jsonFile, new TypeReference<List<CodeErrorStatus>>() {});
            return list.stream().collect(Collectors.toMap(CodeErrorStatus::getCode,ces ->ces));

        } catch (IOException e) {
            log.error("NO SE PUDO OBTENER LISTADO DE CODIGOS DE ESTADO Y ERRORES {}",e.getMessage());
            return null;
        }
    }
}
