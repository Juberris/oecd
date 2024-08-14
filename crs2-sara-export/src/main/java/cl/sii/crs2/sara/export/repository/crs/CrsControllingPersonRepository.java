package cl.sii.crs2.sara.export.repository.crs;

import cl.sii.crs2.sara.export.entities.crs.CrsControllingPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrsControllingPersonRepository extends JpaRepository<CrsControllingPerson, Long> {
}