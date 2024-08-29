package cl.sii.cts2.data.export.entities.cts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "cts2dp_process", indexes = { @Index(name = "idx_cts2dp_process_datetime", columnList = "datetime", unique = false) })
@JsonIgnoreProperties(value = { "cts2dp" })
public class Cts2DPProcess {
    @Id
    private String id ;
    private Timestamp datetime = new Timestamp(System.currentTimeMillis());
    @Column(name = "status_code")
    private Integer statusCode;
    @Column(columnDefinition = "CLOB")
    private String info;


    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Cts2DP cts2dp;
}
