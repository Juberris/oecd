package cl.sii.crs2.sara.export.controller;

import cl.sii.crs2.sara.export.service.CtsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cts")
public class CtsController {

    @Autowired
    CtsService ctsService;
    
}
