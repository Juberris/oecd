package cl.sii.crs2.sara.export.entities.crs;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "CRS_CONTROLLING_PERSON")
public class CrsControllingPerson {

    @Id
    @Column(name = "crs_controlling_person_id")
    Long controllingPersonId;

    @Column(name = "ctrlg_person_type")
    String ctrlgPersonType;

    @Column(name = "res_country_code")
    String resCountryCode;
    @Column(name = "issued_by")
    String issuedBy;
    @Column(name = "tin")
    String TIN;
    @Column(name = "firs_tname")
    String firstName;
    @Column(name = "middle_name")
    String middleName;
    @Column(name = "last_name")
    String lastName;
    @Column(name = "nationality")
    String nationality;
    @Column(name = "birth_date")
    String birthDate;
    @Column(name = "birth_city")
    String birthCity;
    @Column(name = "birth_city_subentity")
    String birthCitySubentity;
    @Column(name = "birth_country_code")
    String birthCountryCode;
    @Column(name = "birth_former_country_name")
    String birthFormerCountryName;

    @ManyToOne
    @JoinColumn(name = "crs_account_report_id", referencedColumnName = "crs_account_report_id", foreignKey = @ForeignKey(name = "FK_ACC_CTRLPERS"))
    private CrsAccountReport crsAccountReport;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "crsControllingPerson")
    @ToString.Exclude
    List<CrsAddress> crsAddressList = new ArrayList<>();
    public void addAdress(CrsAddress a){
        crsAddressList.add(a);
        a.setCrsControllingPerson(this);
    }
}
