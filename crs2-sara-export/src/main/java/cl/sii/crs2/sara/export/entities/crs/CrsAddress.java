package cl.sii.crs2.sara.export.entities.crs;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "CRS_ADDRESS")
public class CrsAddress {

    @Id
    @Column(name = "id_address")
    Long idAddress;

    @Column(name = "country_code")
    String CountryCode;
    @Column(name = "address_free",length = 2000)
    String AddressFree;
    @Column(name = "address_fix",length = 2000)
    String AddressFix;
    @Column(name = "address_fix_street",length = 2000)
    String addressFixStreet;
    @Column(name = "address_fix_building_identifier")
    String addressFixBuildingIdentifier;
    @Column(name = "address_fix_suite_identifier")
    String addressFixSuiteIdentifier;
    @Column(name = "address_fix_floor_identifier")
    String addressFixFloorIdentifier;
    @Column(name = "address_fix_district_name")
    String addressFixDistrictName;
    @Column(name = "address_fix_pob")
    String addressFixPOB;
    @Column(name = "address_fix_post_code")
    String addressFixPostCode;
    @Column(name = "address_fix_city")
    String addressFixCity;
    @Column(name = "address_fix_country_subentity")
    String addressFixCountrySubentity;

    @Column(name = "created_at")
    Date createdAt;
    @Column(name = "created_by")
    String createdBy;
    @Column(name = "updated_at")
    Date updatedAt;
    @Column(name = "updated_by")
    String updatedBy;

    @ManyToOne
    @JoinColumn(name = "crs_reporting_id", referencedColumnName = "crs_reporting_id", foreignKey = @ForeignKey(name = "FK_CRS_ADD"))
    CrsReportingFI crsReportingFI;
}
