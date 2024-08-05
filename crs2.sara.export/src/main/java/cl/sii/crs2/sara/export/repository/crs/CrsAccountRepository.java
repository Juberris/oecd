package cl.sii.crs2.sara.export.repository.crs;

import cl.sii.crs2.sara.export.entities.crs.CrsAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface CrsAccountRepository extends JpaRepository<CrsAccount, String> {
	@Query(value = "SELECT COUNT(a) FROM CrsAccount a WHERE a.fi.id = :fiId")
	long countByFiId(@Param("fiId") String fiId);

	@Query(value = "SELECT a FROM CrsAccount a WHERE a.fi.id = :fiId")
	Collection<CrsAccount> findAllByFiId(@Param("fiId") String fiId);

}
