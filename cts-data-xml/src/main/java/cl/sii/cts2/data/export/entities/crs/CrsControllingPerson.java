package cl.sii.cts2.data.export.entities.crs;

import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "CRS_CONTROLLING_PERSON")
public class CrsControllingPerson {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_controlling_person_crdw")
    Long IdControllingPerson;


    @Column(name = "cp_tin_country_crdw")
    String cpTinCountry;
    @Column(name = "cp_tin_crdw")
    String cpTin;
    @Column(name = "cp_firstname_crdw")
    String cpFirstname;
    @Column(name = "cp_middlename_crdw")
    String cpMiddlename;
    @Column(name = "cp_lastname_crdw")
    String cpLastname;
    @Column(name = "cp_address_countrycode")
    String cpAddressCountrycode;
    @Column(name = "cp_addressfix_street_crdw")
    String cpAddressfixStreet;
    @Column(name = "cp_addressfix_city_crdw")
    String cpAddressfixCity;
    @Column(name = "cp_addressfree_crdw")
    String cpAddressfree;
    @Column(name = "cp_birth_date_crdw")
    String cpBirthDate;
    @Column(name = "cp_birth_country_info_crdw")
    String cpBirthCountryInfo;


    @ManyToOne
    @JoinColumn(name = "id_account_crdw", referencedColumnName = "id_account_crdw", foreignKey = @ForeignKey(name = "FK_CRS_CPERSON"))
    CrsAccount crsAccount;

}
