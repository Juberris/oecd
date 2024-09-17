package cl.sii.crs2.sara.export.entities.crs;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "CRS_ACCOUNT_HOLDER")
public class CrsAccountHolder {
    @Id
    @Column(name = "crs_account_holder_id")
    Long crsAccountHolderId;

    @Column(name = "res_country_code")
    String resCountryCode;
    @Column(name = "acc_in")
    String accIN;
    @Column(name = "acc_tin")
    String accTIN;
    @Column(name = "issued_by")
    String issuedBy;
    @Column(name = "first_name")
    String firstName;
    @Column(name = "middle_name")
    String middleName;
    @Column(name = "last_name")
    String lastName;
    @Column(name = "acct_holder_type")
    String acctHolderType;
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

    @OneToOne(mappedBy = "crsAccountHolder")
    private CrsAccountReport crsAccountReport;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "crsAccountHolder")
    @ToString.Exclude
    List<CrsAddress> crsAddressList = new ArrayList<>();

    public void addAdress(CrsAddress a){
        crsAddressList.add(a);
        a.setCrsAccountHolder(this);
    }

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "ACCOUNTHOLDER_RCC",  joinColumns = @JoinColumn(name = "crs_account_holder_id"), foreignKey = @ForeignKey(name = "FK_AH_RCC1"))
    @Column(name = "acc_res_country_code", nullable = false)
    private Set<String> resCountryCodeSet =new HashSet<>();

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "ACCOUNTHOLDER_NATIONALY",  joinColumns = @JoinColumn(name = "crs_account_holder_id"), foreignKey = @ForeignKey(name = "FK_AH_RNAT1"))
    @Column(name = "acc_nationality", nullable = false)
    private Set<String> nationalitySet =new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "ACCOUNTHOLDER_TIN", joinColumns = @JoinColumn(name = "crs_account_holder_id"), foreignKey = @ForeignKey(name = "FK_AH_RTIN1"))
    private Set<TinEmbeddable> TINSet=new HashSet<>();;

//    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
//    @CollectionTable(name = "ACCOUNTHOLDER_TIN",  joinColumns = @JoinColumn(name = "crs_account_holder_id"), foreignKey = @ForeignKey(name = "FK_AH_RTIN1"))
//    @Column(name = "acc_tin", nullable = false)
//    private Set<String> TINSet =new HashSet<>();

}
