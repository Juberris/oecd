package cl.sii.crs2.sara.export.service.exportDB;

import cl.sii.crs2.sara.export.domain.crs.v2.oecd.ties.crs.v2.CRSOECD;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class CRSOECD2_Export {
    @Autowired
    CrsExportService exportService;

    public void process(Unmarshaller unmarshaller, InputStream contentXml) throws JAXBException {
        CRSOECD crsoecd=(CRSOECD) unmarshaller.unmarshal(contentXml);
        exportService.initCrsExportService(crsoecd,"v2");
        exportService.process();
    }
}
