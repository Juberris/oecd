package cl.sii.cts2.data.export.repository;

import cl.sii.cts2.data.export.entities.crs.CrsAccountReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrsAccountReportRepository extends JpaRepository<CrsAccountReport, Long> {
}