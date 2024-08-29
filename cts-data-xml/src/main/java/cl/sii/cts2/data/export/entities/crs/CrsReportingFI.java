package cl.sii.cts2.data.export.entities.crs;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "CRS_REPORTING_FI")
public class CrsReportingFI {

    @Id
    @Column(name="crs_reporting_id")
    Long crsReportingId;

    @Column(name="doc_ref_id")
    String docRefId;

    String name;
    @Column(name="res_country_code")
    String resCountryCode;

    String fiIN;

    @Column(name="doc_type_indic")
    String docTypeIndic;
    @Column(name="corr_doc_ref_id")
    String corrDocRefId;
    @Column(name="corr_message_ref_id")
    String corrMessageRefId;
    @Column(name="message_ref_id")
    String messageRefId;

    @Column(name = "created_at")
    Date createdAt;
    @Column(name = "created_by")
    String createdBy;
    @Column(name = "updated_at")
    Date updatedAt;
    @Column(name = "updated_by")
    String updatedBy;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "crsReportingFI")
    @ToString.Exclude
    List<CrsAddress> crsAddressList = new ArrayList<>();

    public void addAdress(CrsAddress a){
        crsAddressList.add(a);
        a.setCrsReportingFI(this);
    }
}
