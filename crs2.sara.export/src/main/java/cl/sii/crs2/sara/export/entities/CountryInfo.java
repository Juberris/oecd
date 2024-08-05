package cl.sii.crs2.sara.export.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "COUNTRY_INFO", schema = "CTS")
public class CountryInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_info_id")
    private Long countryInfoId;

    private String type;
    private String srcIso;
    private String srcName;
    private String dstIso;
    private String dstName;

    @Column(name = "cts_year")
    private Integer year;
    private Boolean dty;
    private String txt;
    private String country;
    private String countryIso;
}
