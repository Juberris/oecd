package cl.sii.cts2.data.export.entities.crs;

import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Data
@Entity
@Table(name = "CRS_FI")
public class CrsFI {

    //DocRefId
    @Id
    @Column(name = "fi_id_crdw")
    private String fiId;

    @Column(name = "country_crdw")
    private String country;
    @Column(name = "fi_name_crdw")
    private String fiName;
    @Column(name = "fi_in_country_crdw")
    private String fiInCountry;
    @Column(name = "fi_in_crdw")
    private String fiIn;
    @Column(name = "fi_address_country_crdw")
    private String fiAddressCountry;
    @Column(name = "fi_address_fix_street_crdw")
    private String fiAddressFixStreet;
    @Column(name = "fi_address_fix_city_crdw")
    private String fiAddressFixCity;
    @Column(name = "fi_address_fix_post_code_crdw")
    private String fiAddressFixPostCode;
    @Column(name = "fi_address_free_crdw", length = 2000)
    private String fiAddressFree;
    @Column(name = "fi_reporting_period_crdw")
    private String fiReportingPeriod;

}
