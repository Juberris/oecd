package cl.sii.crs2.sara.export.controller;

import cl.sii.crs2.sara.export.service.CtsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cts")
public class CtsController {

    @Autowired
    CtsService ctsService;

    @PostMapping("/pyld")
    public ResponseEntity<String> getSubmissions(){
        try {

            System.out.println("######### INICIANDO PROCESO PYLD#########");
            ctsService.getPayload();

        }catch (Exception e){
            return new ResponseEntity<>("Error CTS process", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Successfully CTS process", HttpStatus.OK);
    }
}
