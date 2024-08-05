package cl.sii.filepackaging.client;


import cl.sii.filepackaging.model.AuthRequest;
import cl.sii.filepackaging.model.AuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "cts-client", url = "${cl.sii.sdi.lob.ss.sara.domain.cts.api.uri}")
public interface CtsClient {

    @PostMapping(value=  "/auth/CL_" + "${cl.sii.sdi.lob.ss.sara.domain.cts.api.usr}", produces="text/plain")
    AuthResponse getToken(@RequestBody AuthRequest req);

    @PostMapping(value=  "/certificates/{cc}", produces="text/plain" , consumes = "text/plain")
    String getCert(@PathVariable("cc") String cc);
}
