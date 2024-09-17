package cl.sii.crs2.sara.export.entities.crs_sas;

import lombok.Data;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "SAS_CRS_PAYMENT")
public class SasCrsPayment {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payment_crdw")
    Long idPayment;

    @Column(name = "payment_type_crdw")
    String paymentType;
    @Column(name = "pay_currency_crdw")
    String payCurrency;
    @Column(name = "payment_crdw")
    BigDecimal payment;


    @ManyToOne
    @JoinColumn(name = "id_account_crdw", referencedColumnName = "id_account_crdw", foreignKey = @ForeignKey(name = "FK_SASCRS_PAY"))
    SasCrsAccount sasCrsAccount;

}
