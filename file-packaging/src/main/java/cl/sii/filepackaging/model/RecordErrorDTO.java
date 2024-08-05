package cl.sii.filepackaging.model;

import lombok.Data;

@Data
public class RecordErrorDTO {
    String code;
    String detail;
    String docRefIDInError;
    String fieldPath;
}
