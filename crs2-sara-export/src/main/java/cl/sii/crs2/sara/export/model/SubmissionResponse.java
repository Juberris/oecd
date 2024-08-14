package cl.sii.crs2.sara.export.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class SubmissionResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    String metaData;
    List<Data> data=new ArrayList<>();


    @lombok.Data
    public static class Data implements Serializable{
        private static final long serialVersionUID = 2L;
        String receptionId;
        String senderId;
        String subjectId;
        String period;
        String rcvrName;
        String channel;
        String type;
        String version;
        String timestamp;
        String status;
        String dataPayloadId;
        String metaDataPayloadId;
        Integer parts;
        Integer partIndex;

    }
}
