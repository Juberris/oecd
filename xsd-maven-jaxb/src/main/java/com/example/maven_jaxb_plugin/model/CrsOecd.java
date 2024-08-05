package com.example.maven_jaxb_plugin.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

@XmlRootElement(name = "CRS_OECD")
public class CrsOecd {
    private List<CrsBody> crsBody;

    @XmlElementWrapper(name = "CrsBody")
    @XmlElement(name = "CrsBody")
    public List<CrsBody> getCrsBody() {
        return crsBody;
    }

    public void setCrsBody(List<CrsBody> crsBody) {
        this.crsBody = crsBody;
    }
}

class CrsBody {
    private ReportingFi reportingFi;

    @XmlElement(name = "ReportingFI")
    public ReportingFi getReportingFi() {
        return reportingFi;
    }

    public void setReportingFi(ReportingFi reportingFi) {
        this.reportingFi = reportingFi;
    }
}

class ReportingFi {
    private DocSpec docSpec;

    @XmlElement(name = "DocSpec")
    public DocSpec getDocSpec() {
        return docSpec;
    }

    public void setDocSpec(DocSpec docSpec) {
        this.docSpec = docSpec;
    }
}

class DocSpec {
    private String docRefId;

    @XmlElement(name = "DocRefId")
    public String getDocRefId() {
        return docRefId;
    }

    public void setDocRefId(String docRefId) {
        this.docRefId = docRefId;
    }
}
