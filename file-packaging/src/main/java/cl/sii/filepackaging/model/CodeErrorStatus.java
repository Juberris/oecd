package cl.sii.filepackaging.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class CodeErrorStatus implements Serializable {
    private static final long serialVersionUID = 4421891449504940291L;
    int code;
    String name;
    String description;
    String action;
}
