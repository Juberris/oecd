package cl.sii.crs2.sara.export.model;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;


@Data
public class SubmissionRequest {
    Sender sender=new Sender();

    @Getter(AccessLevel.NONE)
    private ObjectMapper objectMapper=new ObjectMapper();

    ObjectNode receiver = objectMapper.createObjectNode();
    String payloadType;
    String from;
    String to;
    Boolean last;

    @Data
    public static class Sender{
        String subjectId;
    }
}


