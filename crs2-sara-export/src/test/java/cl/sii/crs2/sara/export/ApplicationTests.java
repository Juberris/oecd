package cl.sii.crs2.sara.export;

import cl.sii.crs2.sara.export.service.ExportCtsService;
import cl.sii.crs2.sara.export.service.ExportSasService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(classes= Application.class)
@Slf4j
class ApplicationTests {
	@Autowired
	ExportSasService sasService;

	@Autowired
	ExportCtsService ctsService;

	@Test
	public void crs() {
		sasService.updateRelationships();
		sasService.getSaraSubmissions("2024-01-01","2024-12-31");
		sasService.process();
	}

	@Test
	public void cts(){
		ctsService.process();
	}

}
