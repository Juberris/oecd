package cl.sii.crs2.sara.export.repository.crs;


import cl.sii.crs2.sara.export.entities.crs.CrsPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrsPaymentRepository extends JpaRepository<CrsPayment, Long> {
}