package cl.sii.filepackaging.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class FileDataList {
    private String typeExchange;
    private List<FileRequest> files= new ArrayList<>();

}
