package cl.sii.cts2.data.export;

import cl.sii.cts2.data.export.service.CtsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes= Application.class)
@Slf4j
class ApplicationTests {


	@Autowired
	CtsService ctsService;



	@Test
	public void cts(){
		ctsService.getPayload();
	}

}
