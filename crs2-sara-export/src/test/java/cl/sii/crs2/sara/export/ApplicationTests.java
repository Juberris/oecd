package cl.sii.crs2.sara.export;

import cl.sii.crs2.sara.export.domain.crs.v2.oecd.ties.crs.v2.CRSOECD;
import cl.sii.crs2.sara.export.service.CtsService;
import cl.sii.crs2.sara.export.service.ExportService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.lang.reflect.Field;
import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(classes= Application.class)
@Slf4j
class ApplicationTests {
	@Autowired
	ExportService pruebaService;

	@Autowired
	CtsService ctsService;

	@Test
	public void crs() {
		//pruebaService.updateRelationships();
		//pruebaService.getSaraSubmissions("2024-01-01","2024-12-31");
		//pruebaService.process();
	}

	@Test
	public void cts(){
		ctsService.getPayload();
	}

}
