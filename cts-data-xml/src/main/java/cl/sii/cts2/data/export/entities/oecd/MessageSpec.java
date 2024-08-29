package cl.sii.cts2.data.export.entities.oecd;

import jakarta.persistence.*;
import lombok.*;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
    @Table(name = "MESSAGE_SPEC")
public class MessageSpec {
    @Id
    @Column(name = "message_ref_id")
    String messageRefId;
    @Column(name = "sending_company_in")
    String sendingCompanyIN;
    @Column(name = "transmitting_country")
    String transmittingCountry;
    @Column(name = "receiving_country")
    String receivingCountry;
    @Column(name = "message-type")
    String messageType;
    @Column(name = "warning", length = 2000)
    String warning;

    String contact;
    @Column(name = "message_type_indic")
    String messageTypeIndic;
    @Column(name = "reporting_period")
    Date reportingPeriod;
    String filename;
    Date timestamp;

    @Column(name = "created_at")
    Date createdAt;
    @Column(name = "created_by")
    String createdBy;
    @Column(name = "updated_at")
    Date updatedAt;
    @Column(name = "updated_by")
    String updatedBy;



    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "MESSAGE_CORRMESSAGE",  joinColumns = @JoinColumn(name = "message_ref_id"), foreignKey = @ForeignKey(name = "FK_CRS_R1"))
    @Column(name = "corr_message_ref_id", nullable = false)
    private Set<String> corrMessageRefId =new HashSet<>();

}
