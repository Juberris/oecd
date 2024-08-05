package cl.sii.filepackaging.model;

import lombok.Data;

@Data
public class MessageSpecDTO {
    String transmittingCountry;
    String receivingCountry;
    String messageType;
    String contact;
    String originalMessageRefId;

}
