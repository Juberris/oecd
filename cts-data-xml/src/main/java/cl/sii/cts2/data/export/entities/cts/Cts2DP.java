package cl.sii.cts2.data.export.entities.cts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "cts2dp", indexes = { @Index(name = "idx_filename", columnList = "filename", unique = true) })
@JsonIgnoreProperties(value = { "processes"})
public class Cts2DP {
    @Id
    private String id ;
    private Timestamp datetime = new Timestamp(System.currentTimeMillis());
    private String dst;
    private String filename;
    @Column(name = "is_status")
    private Integer isStatus;
    private String mrid;
    @Column(name = "mrid_ref")
    private String mridRef;
    private String sfid;
    private String src;
    @Column(name = "status_code")
    private Integer statusCode;
    private String tid;
    @Column(name = "tid_ref")
    private String tidRef;
    private String type;
    private String utc;





    public String toKey() {
        return filename.replace(".zip", "");
    }

    @OneToMany(mappedBy = "cts2dp", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("datetime")
    @ToString.Exclude
    private List<Cts2DPProcess> processes = new ArrayList<>();
}
