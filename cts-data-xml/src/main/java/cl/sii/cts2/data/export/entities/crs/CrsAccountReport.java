package cl.sii.cts2.data.export.entities.crs;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "CRS_ACCOUNT_REPORT")
public class CrsAccountReport {

    @Id
    @Column(name = "account_report_id")
    Long crsAccountReportId;

    @Column(name = "doc_type_indic")
    String docTypeIndic;
    @Column(name = "doc_ref_id")
    String docRefId;
    @Column(name = "account_number")
    String accountNumber;

}
