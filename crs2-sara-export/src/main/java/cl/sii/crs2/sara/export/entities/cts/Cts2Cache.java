package cl.sii.crs2.sara.export.entities.cts;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "cts2_cache")
public class Cts2Cache {
    @Id
    String key;

    @Lob
    byte[] data;
}
