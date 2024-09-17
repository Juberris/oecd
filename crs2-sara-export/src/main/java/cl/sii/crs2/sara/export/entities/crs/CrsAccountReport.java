package cl.sii.crs2.sara.export.entities.crs;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "CRS_ACCOUNT_REPORT",indexes = { @Index(name = "idx_ac_message_ref_id", columnList = "message_ref_id") })
public class CrsAccountReport {

    @Id
    @Column(name = "crs_account_report_id")
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "crs_account_holder_id", referencedColumnName = "crs_account_holder_id", foreignKey = @ForeignKey(name = "FK_ACC_HOLD"))
    CrsAccountHolder crsAccountHolder;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "crsAccountReport", orphanRemoval = true)
    List<CrsControllingPerson> crsControllingPersonList = new ArrayList<>();
    public void addCrsControllingPerson(CrsControllingPerson p){
        crsControllingPersonList.add(p);
        p.setCrsAccountReport(this);
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "crsAccountReport")
    List<CrsPayment> crsPaymentList = new ArrayList<>();
    public void addCrsPayment(CrsPayment d){
        crsPaymentList.add(d);
        d.setCrsAccountReport(this);
    }

}
