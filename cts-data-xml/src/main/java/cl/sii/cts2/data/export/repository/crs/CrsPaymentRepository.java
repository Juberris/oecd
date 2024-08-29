package cl.sii.cts2.data.export.repository.crs;

import cl.sii.cts2.data.export.entities.crs.CrsPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrsPaymentRepository extends JpaRepository<CrsPayment, Long> {
}