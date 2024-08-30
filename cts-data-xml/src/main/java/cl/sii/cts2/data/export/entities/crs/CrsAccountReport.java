package cl.sii.cts2.data.export.entities.crs;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "CRS_ACCOUNT_REPORT")
public class CrsAccountReport {

    @Id
    @Column(name = "account_report_id")
    Long crsAccountReportId;

    @Column(name = "message_ref_id")
    String messageRefId;

    @Column(name = "doc_type_indic")
    String docTypeIndic;
    @Column(name = "doc_ref_id")
    String docRefId;
    @Column(name = "account_number")
    String accountNumber;
    @Column(name = "corr_doc_ref_id")
    String corrDocRefId;
    @Column(name = "corr_message_ref_id")
    String corrMessageRefId;
    @Column(name = "acct_number_type")
    String acctNumberType;
    @Column(name = "undocumented_account")
    String undocumentedAccount;
    @Column(name = "closed_account")
    String closedAccount;
    @Column(name = "dormant_account")
    String dormantAccount;

    @Column(name = "account_balance")
    String accountBalance;
    @Column(name = "curr_code")
    String currCode;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "crsAccount")
    @ToString.Exclude
    List<CrsPayment> crsPaymentList = new ArrayList<>();

    public void addPayment(CrsPayment p) {
        crsPaymentList.add(p);
        p.setCrsAccount(this);
    }

}
