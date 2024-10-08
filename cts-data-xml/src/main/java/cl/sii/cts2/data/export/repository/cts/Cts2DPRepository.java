package cl.sii.cts2.data.export.repository.cts;

import cl.sii.cts2.data.export.entities.cts.Cts2DP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.List;

public interface Cts2DPRepository extends JpaRepository<Cts2DP, String> {

    @Query("SELECT e FROM Cts2DP e WHERE e.isStatus=0 and e.type='CRS' ORDER BY e.datetime ASC")
    List<Cts2DP> findAllByOrderByDatetimeAsc();

    Optional<Cts2DP> findByFilename(String fileName);


}