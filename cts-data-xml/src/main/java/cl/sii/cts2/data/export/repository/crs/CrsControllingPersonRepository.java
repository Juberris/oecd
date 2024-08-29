package cl.sii.cts2.data.export.repository.crs;

import cl.sii.cts2.data.export.entities.crs.CrsControllingPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrsControllingPersonRepository extends JpaRepository<CrsControllingPerson, Long> {
}