package cl.sii.cts2.data.export.entities.crs;

import lombok.Data;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "CRS_PAYMENT")
public class CrsPayment {

    @Id
    @Column(name = "crs_payment_id")
    Long idPayment;


    String type;
    @Column(name = "curr_code")
    String currCode;
    @Column(name = "payment_amnt")
    String paymentAmnt;


    @ManyToOne
    @JoinColumn(name = "crs_account_report_id", referencedColumnName = "crs_account_report_id", foreignKey = @ForeignKey(name = "FK_CRS_PAY"))
    CrsAccountReport crsAccountReport;

}
