package cl.sii.cts2.data.export.repository;

import cl.sii.cts2.data.export.entities.oecd.MessageSpec;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageSpecRepository extends JpaRepository<MessageSpec, String> {
}