package cl.sii.crs2.sara.export.repository.crs_sas;

import cl.sii.crs2.sara.export.entities.crs_sas.SasCrsPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SasCrsPaymentRepository extends JpaRepository<SasCrsPayment, Long> {
}