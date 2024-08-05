package cl.sii.crs2.sara.export.client;

import cl.sii.crs2.sara.export.config.FeignConfig;
import cl.sii.crs2.sara.export.model.SubmissionRequest;
import cl.sii.crs2.sara.export.model.SubmissionResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(value = "sara-client", url = "${cl.sii.sara.crs.api.uri}", configuration = FeignConfig.class)
public interface SaraClient {

    @PostMapping(value=  "/submissions", consumes="application/json")
    SubmissionResponse getSubmission(@RequestBody Map<String, Object> req);

    @GetMapping(value="/senderPayloadGET/{senderId}/{subjectId}/{receptionId}/{dataPayloadId}/CRS_DOM/1.0/{period}/DATA/SII_CRS")
    String getPayload(@PathVariable("senderId") String senderId,@PathVariable("subjectId") String subjectId, @PathVariable("receptionId") String receptionId,
                      @PathVariable("dataPayloadId") String dataPayloadId,@PathVariable("period") String period);
}
