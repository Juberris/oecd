package cl.sii.crs2.sara.export.entities.oecd;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


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

    String sendingCompanyIN;
    String transmittingCountry;
    String receivingCountry;
    String messageType;
    String warning;
    String contact;
    String messageTypeIndic;
    Date reportingPeriod;
    Date timestamp;
    Date createdAt;
    String createdBy;
    Date updatedAt;
    String updatedBy;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "MESSAGE_CORRMESSAGE",  joinColumns = @JoinColumn(name = "message_ref_id"), foreignKey = @ForeignKey(name = "FK_CRS_R1"))
    @Column(name = "corr_message_ref_id", nullable = false)
    private Set<String> corrMessageRefId =new HashSet<>();

}
