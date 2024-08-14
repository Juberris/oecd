package cl.sii.crs2.sara.export.entities.crs;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "CRS_ACCOUNT")
public class CrsAccount {
    //DocRefId
    @Id
    @Column(name = "id_account_crdw")
    private String idAccount;

    @Column(name = "number_code_crdw")
    private String numberCode;
    @Column(name = "number_crdw")
    private String number;
    @Column(name = "i_tin_country_crdw")
    private String iTinCountry;
    @Column(name = "i_tin_crdw")
    private String iTin;
    @Column(name = "o_in_country_crdw")
    private String oInCountry;
    @Column(name = "o_in_crdw")
    private String oIn;
    @Column(name = "i_first_name_crdw")
    private String iFirstName;
    @Column(name = "i_middle_name_crdw")
    private String iMiddleName;
    @Column(name = "i_last_name_crdw")
    private String iLastName;
    @Column(name = "o_name_crdw")
    private String oName;
    @Column(name = "i_address_country_code_crdw")
    private String iAddressCountryCode;
    @Column(name = "i_address_fix_street_crdw")
    private String iAddressFixStreet;
    @Column(name = "i_address_fix_city_crdw")
    private String iAddressFixCity;
    @Column(name = "i_address_free_crdw", length = 2000)
    private String iAddressFree;
    @Column(name = "o_address_country_code_crdw")
    private String oAddressCountryCode;
    @Column(name = "o_address_fix_street_crdw")
    private String oAddressFixStreet;
    @Column(name = "o_address_fix_city_crdw")
    private String oAddressFixCity;
    @Column(name = "receiving_country_crdw")
    private String receivingCountry;


    @Column(name = "i_res_country_code_crdw")
    private String iResCountryCode;
    @Column(name = "o_res_country_code_crdw")
    private String oResCountryCode;

    @Column(name = "o_address_free_crdw", length = 2000)
    private String oAddressFree;
    @Column(name = "i_birth_date_crdw")
    private String iBirthDate;
    @Column(name = "i_birth_country_info_crdw")
    private String iBirthCountryInfo;
    @Column(name = "o_acct_holder_type_crdw")
    private String oAcctHolderType;
    @Column(name = "currency_crdw")
    private String currency;
    @Column(name = "balance_crdw")
    private BigDecimal balance;
    @Column(name = "type_crdw")
    private String type;
    @Column(name = "is_agreement_crdw")
    private String isAgreement;

    @ManyToOne(optional = false)//@OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "fi_id_crdw", referencedColumnName = "fi_id_crdw", foreignKey = @ForeignKey(name = "FK_CRS_FI"))
    private CrsFI fi;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "crsAccount")
    @ToString.Exclude
    List<CrsControllingPerson> crsControllingPersonList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "crsAccount")
    @ToString.Exclude
    List<CrsPayment> crsPaymentList = new ArrayList<>();

    public void addPayment(CrsPayment p) {
        crsPaymentList.add(p);
        p.setCrsAccount(this);
    }

    public void addControllingPerson(CrsControllingPerson cp) {
        crsControllingPersonList.add(cp);
        cp.setCrsAccount(this);
    }
}
