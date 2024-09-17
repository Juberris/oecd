package cl.sii.crs2.sara.export.controller;

import cl.sii.crs2.sara.export.service.ExportSasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/crs")
public class SaraController {
    @Autowired
    ExportSasService service;

    @PostMapping("/submissions")
    public ResponseEntity<String> getSubmissions(){
        try {
           // service.updateRelationships();
           // service.getSaraSubmissions("2024-01-01","2024-12-31");
            System.out.println("######### INICIANDO PROCESO #########");
            service.process();

        }catch (Exception e){
            return new ResponseEntity<>("Error submission", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Successfully submission", HttpStatus.OK);


    }
}
