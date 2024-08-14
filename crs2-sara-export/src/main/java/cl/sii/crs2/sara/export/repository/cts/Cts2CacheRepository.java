package cl.sii.crs2.sara.export.repository.cts;

import cl.sii.crs2.sara.export.entities.cts.Cts2Cache;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Cts2CacheRepository extends JpaRepository<Cts2Cache, String> {

    Optional<Cts2Cache> findByKey(String key);
}