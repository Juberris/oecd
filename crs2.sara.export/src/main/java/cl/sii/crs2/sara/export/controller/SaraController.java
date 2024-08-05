package cl.sii.crs2.sara.export.controller;

import cl.sii.crs2.sara.export.entities.CountryInfo;
import cl.sii.crs2.sara.export.service.ExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/crs")
public class SaraController {
    @Autowired
    ExportService service;

    @PostMapping("/submissions")
    public ResponseEntity<String> getSubmissions(){
        service.updateRelationships();

        List<CountryInfo> l=service.getCountryInfo();

        return new ResponseEntity<>("Successfully submission", HttpStatus.OK);
    }
}
