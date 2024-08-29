package cl.sii.cts2.data.export.repository.crs;

import cl.sii.cts2.data.export.entities.crs.CrsAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface CrsAccountRepository extends JpaRepository<CrsAccount, String> {
	@Query(value = "SELECT COUNT(a) FROM CrsAccount a WHERE a.fi.id = :fiId")
	long countByFiId(@Param("fiId") String fiId);

	@Modifying
	@Query("UPDATE CrsAccount e SET e.isAgreement = 'N'")
	void updateIniIsAgreement();

	@Query(value = "SELECT a FROM CrsAccount a WHERE a.fi.id = :fiId")
	Collection<CrsAccount> findAllByFiId(@Param("fiId") String fiId);

	@Query(value="SELECT  t.ID_ACCOUNT_CRDW AS idAccount,\n" +
			"CASE WHEN t.agr IS NOT NULL THEN 'Y'\n" +
			"ELSE 'N' END AS isAgreement\n" +
			"from\n" +
			"(SELECT p.ID_ACCOUNT_CRDW , \n" +
			"(SELECT ci.dst_iso  FROM country_info ci\n" +
			"WHERE ci.\"TYPE\" ='CRS'\n" +
			"AND ci.SRC_ISO = 'CL'\n" +
			"AND ci.dst_ISO=p.cod_pais) agr \n" +
			"from      \n" +
			"(SELECT  t.ID_ACCOUNT_CRDW, min(t.cod_pais) AS cod_pais from\n" +
			"(SELECT ID_ACCOUNT_CRDW,\n" +
			"\t\tI_ADDRESS_COUNTRY_CODE_CRDW AS cod_pais\n" +
			"FROM CRS_ACCOUNT\n" +
			"UNION ALL\n" +
			"SELECT ID_ACCOUNT_CRDW,\t\n" +
			"\tI_RES_COUNTRY_CODE_CRDW AS cod_pais\n" +
			"FROM CRS_ACCOUNT\n" +
			"UNION ALL\n" +
			"SELECT ID_ACCOUNT_CRDW,\t\n" +
			"\tO_ADDRESS_COUNTRY_CODE_CRDW AS cod_pais\n" +
			"FROM CRS_ACCOUNT\n" +
			"UNION ALL\n" +
			"SELECT ID_ACCOUNT_CRDW,\t\n" +
			"\tO_RES_COUNTRY_CODE_CRDW AS cod_pais\n" +
			"FROM CRS_ACCOUNT) t\n" +
			"WHERE  T.COD_PAIS NOT in('CL')\n" +
			"AND t.COD_PAIS!=''\n" +
			"GROUP BY t.ID_ACCOUNT_CRDW) p) t", nativeQuery = true)
	List<Object[]> obtenerConvenios();

	@Modifying
	@Query("UPDATE CrsAccount c SET c.isAgreement =:isAgree WHERE c.idAccount=:idAccount")
	void updateIsAgreement(@Param("idAccount") String idAccount, @Param("isAgree") String isAgree);
}
