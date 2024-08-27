package cl.sii.crs2.sara.export.repository;

import cl.sii.crs2.sara.export.entities.oecd.MessageSpec;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageSpecRepository extends JpaRepository<MessageSpec, String> {
}