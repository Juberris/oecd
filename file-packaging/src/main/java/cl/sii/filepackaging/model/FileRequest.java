package cl.sii.filepackaging.model;

import lombok.Data;

@Data
public class FileRequest {
    private String fileName;
    private String fileFormatCd;
    private String countryCodeReceiver;
    private String countryCodeSender;
    private String typeExchange;
    byte[] file;

}
