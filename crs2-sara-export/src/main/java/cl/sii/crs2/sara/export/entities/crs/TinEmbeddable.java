package cl.sii.crs2.sara.export.entities.crs;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TinEmbeddable {
    String assTIN;
    String issuedBy;
}
