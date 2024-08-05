package cl.sii.crs2.sara.export.service;
import cl.sii.crs2.sara.export.entities.CountryInfo;
import cl.sii.crs2.sara.export.util.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class CtsExchangeRelationship {
    public static final String[] CBC_2016_COUNTRIES = { "CO", "FR", "GB", "MX", "DK" };
    private static final Http HTTP = new Http();
    private String crsUrl = "https://eoitaxressources.oecd.org/AEOIPortal/ExchangeRelationship/Exchange.aspx";
    private String cbcUrl = "https://eoitaxressources.oecd.org/AEOIPortal/ExchangeRelationship/ExchangeCBC.aspx";
    private File contactsFile;
    private File exchangeRelationshipsFile;
    private Map<String, CountryInfo> exchangeRelationships = new HashMap<>();
    public Set<String> crsCountries = new TreeSet<>();
    public Set<String> cbcCountries = new TreeSet<>();

    public Collection<CountryInfo> get() {
        return exchangeRelationships.values();
    }

    public CtsExchangeRelationship(String basedir) {
        exchangeRelationshipsFile = new File(basedir, "exchange_relationships.json");
    }

    public void setCrsUrl(String crsUrl) {
        this.crsUrl = crsUrl;
    }

    public void setCbcUrl(String cbcUrl) {
        this.cbcUrl = cbcUrl;
    }

    public boolean isRelationshipExchangeActivated(String type, String srcIso, String dstIso, Integer period) {
        CountryInfo er = getRelationshipExchange(type, srcIso, dstIso);
        if (er != null && er.getYear() != null) {
            return period >= er.getYear();
        }
        return false;
    }

    public CountryInfo getRelationshipExchange(String type, String srcIso, String dstIso) {
        String key = Strings.concat("_", type, srcIso, dstIso);
        CountryInfo er = exchangeRelationships.get(key);
        if (er == null) {
            er = new CountryInfo();
        }
        return er;
    }

    public boolean isRelationshipExchangeActivated(CountryInfo er, Integer period) {
        if (er != null) {
            boolean result = er.getYear() != null ? period >= er.getYear() : false;
            return result;
        }
        return false;
    }

    public boolean isRelationshipExchangeActivated(String type, String srcIso, Set<String> dstIsos, Integer period) {
        for (String dstIso : dstIsos) {
            if (isRelationshipExchangeActivated(type, srcIso, dstIso, period)) {
                return true;
            }
        }
        return false;
    }

    public void cbc(Listener l) {
        exchange("CBC", cbcUrl + "?from=45&to=0&PageNo=", "Body_cusExchangeRelationResults_grdvListResult", l);
        exchange("CBC", cbcUrl + "?from=0&to=45&PageNo=", "Body_cusExchangeRelationResults_grdvListResult", l);
    }

    public void crs(Listener l) {
        exchange("CRS", crsUrl + "?from=45&to=0&PageNo=", "Body_cusExchangeRelationResults_grdvListResult", l);
        exchange("CRS", crsUrl + "?from=0&to=45&PageNo=", "Body_cusExchangeRelationResults_grdvListResult", l);
    }

    public synchronized void loadCountryInfos(boolean reseted) {
        if (reseted || !exchangeRelationshipsFile.exists()) {
            exchangeRelationships.clear();
            //
            crs(new CtsExchangeRelationship.Listener() {

                @Override
                public void on(String srcCountry, String dstCountry, String year, boolean dty, String txt) {
                    String srcIso = Countries.INSTANCE.byName.get(srcCountry);
                    String dstIso = Countries.INSTANCE.byName.get(dstCountry);
                    if (Strings.anyNull(srcIso, dstIso)) {
                        log.warn(srcCountry + "=" + srcIso + ", " + dstCountry + "=" + dstIso);
                    }
                    CountryInfo er = new CountryInfo();
                    er.setType("CRS");
                    er.setSrcIso(srcIso);
                    er.setSrcName(Countries.INSTANCE.byIso.get(srcIso));
                    er.setDstIso(dstIso);
                    er.setDstName(Countries.INSTANCE.byIso.get(dstIso));
                    er.setYear(Integer.valueOf(year));
                    er.setDty(dty);
                    er.setTxt(txt);
                    exchangeRelationships.put(Strings.concat("_", "CRS", srcIso, dstIso), er);
                    if ("CL".equals(srcIso)) {
                        crsCountries.add(dstIso);
                    }
                }
            });
            cbc(new CtsExchangeRelationship.Listener() {

                @Override
                public void on(String srcCountry, String dstCountry, String year, boolean dty, String txt) {
                    String srcIso = Countries.INSTANCE.byName.get(srcCountry);
                    String dstIso = Countries.INSTANCE.byName.get(dstCountry);
                    if (Strings.anyNull(srcIso, dstIso)) {
                        log.warn(srcCountry + "=" + srcIso + ", " + dstCountry + "=" + dstIso);
                    }
                    if (Strings.equals(srcIso, CBC_2016_COUNTRIES) || Strings.equals(dstIso, CBC_2016_COUNTRIES)) {
                        year = "2016";
                    }
                    CountryInfo er = new CountryInfo();
                    er.setType("CBC");
                    er.setSrcIso(srcIso);
                    er.setSrcName(Countries.INSTANCE.byIso.get(srcIso));
                    er.setDstIso(dstIso);
                    er.setDstName(Countries.INSTANCE.byIso.get(dstIso));
                    er.setYear(Integer.valueOf(year));
                    er.setDty(dty);
                    er.setTxt(txt);

                    exchangeRelationships.put(Strings.concat("_", "CBC", srcIso, dstIso), er);
                    if ("CL".equals(srcIso)) {
                        cbcCountries.add(dstIso);
                    }
                }
            });
            SaraSerDe.toJson(exchangeRelationshipsFile, exchangeRelationships);
            exchangeRelationships = transform(exchangeRelationships);
        } else {
            exchangeRelationships = transform(SaraSerDe.fromJsonMap(exchangeRelationshipsFile, CountryInfo.class));
        }
    }

    public static class Listener {
        public void on(String srcCountry, String dstCountry, String year, boolean dty, String txt) {
        }

        public void on(String key, CountryInfo r) {
        }
    }

    public static void exchange(String type, String url, String tableId, Listener l) {
        String urlWithPage = url;
        try {
            AtomicInteger pages = new AtomicInteger(1);
            HashSet<String> done = new HashSet<>();
            for (int page = 0; page < pages.get(); page++) {
                urlWithPage = url + page;
                HttpClient client = Http.create(urlWithPage);
                try {
                    HttpGet request = new HttpGet(urlWithPage);
                    HttpResponse response = client.execute(HTTP.wrap(request));
                    HTTP.process(response, (code, msg, data) -> {
                        boolean repeated = false;
                        Document doc = Jsoup.parse(IOUtils.toString(data));
                        Element table = doc.getElementById(tableId);
                        Elements rows = table.getElementsByTag("tr");
                        int count = 0;
                        for (int ri = 0; ri < rows.size(); ri++) {
                            Element row = rows.get(ri);
                            Elements cols = row.getElementsByTag("td");
                            if (cols.size() > 0) {
                                if (cols.size() == 3) {
                                    count++;
                                    String srcCountry = null;
                                    String dstCountry = null;
                                    String year = null;
                                    List<String> texts = null;
                                    boolean found = false;
                                    for (int ci = 0; ci < cols.size(); ci++) {
                                        Element col = cols.get(ci);
                                        texts = text(col, new ArrayList<String>());
                                        out: for (String text : texts) {
                                            if (ci == 0) {
                                                srcCountry = text;
                                            } else if (ci == 1) {
                                                dstCountry = text;
                                            } else if (ci == 2) {
                                                System.out.println(type + ": " + srcCountry + "->" + dstCountry);
                                                String[] parts = text.split("\\s");
                                                boolean dty = text.contains("(DTY)");
                                                for (String part : parts) {
                                                    if (part.length() == 4) {
                                                        year = part;
                                                        try {
                                                            int yearInt = Integer.parseInt(year);
                                                            //l.on(srcCountry, dstCountry, year, dty);
                                                            if (dty) {
                                                                year = String.valueOf((yearInt + 1));
                                                            }
                                                            l.on(srcCountry, dstCountry, year, dty, texts.toString());
                                                            found = true;
                                                            break out;
                                                        } catch (Exception e) {
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    if (!found) {
                                        l.on(srcCountry, dstCountry, null, false, texts.toString());
                                    }
                                    String key = srcCountry + "/" + dstCountry;
                                    if (!(repeated = done.contains(key))) {
                                        done.add(key);
                                    }
                                } else {
                                    if (count == 10 && !repeated) {
                                        pages.incrementAndGet();
                                    }
                                    count = 0;
                                }
                            }
                        }
                    });
                } catch (Exception e) {
                    log.error(urlWithPage + "->" + SaraException.source(e));
                    e.printStackTrace();
                    page--;
                } finally {
                    HttpClientUtils.closeQuietly(client);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error con " + urlWithPage, e);
        }
    }


    protected Map<String, CountryInfo> transform(Map<String, CountryInfo> exchangeRelationships) {
        Map<String, CountryInfo> exchangeRelationshipsTmp = new HashMap<>();
        for (String key : exchangeRelationships.keySet()) {
            Object object = exchangeRelationships.get(key);
            CountryInfo er = null;
            if (!CountryInfo.class.isAssignableFrom(object.getClass())) {
                er = SaraSerDe.JSON_SERDE.convertValue(object, CountryInfo.class);
                exchangeRelationships.put(key, er);
            } else {
                er = (CountryInfo) object;
            }
            exchangeRelationshipsTmp.put(key, er);
        }
        return exchangeRelationshipsTmp;
    }
    public static List<String> text(Node parent, List<String> list) {
        if (parent != null) {
            if (parent instanceof TextNode) {
                String text = ((TextNode) parent).getWholeText();
                if (text != null && text.trim().length() > 0) {
                    list.add(text.trim());
                }
                return list;
            }
            List<Node> children = parent.childNodes();
            if (children != null && children.size() > 0) {
                for (Node child : children) {
                    list = text(child, list);
                }
            }
        }
        return list;
    }

    public synchronized void loadExchangeRelationships(boolean reseted) {
        if (reseted || !exchangeRelationshipsFile.exists()) {
            exchangeRelationships.clear();
            //
            crs(new CtsExchangeRelationship.Listener() {

                @Override
                public void on(String srcCountry, String dstCountry, String year, boolean dty, String txt) {
                    String srcIso = Countries.INSTANCE.byName.get(srcCountry);
                    String dstIso = Countries.INSTANCE.byName.get(dstCountry);
                    if (Strings.anyNull(srcIso, dstIso)) {
                        log.warn(srcCountry + "=" + srcIso + ", " + dstCountry + "=" + dstIso);
                    }
                    CountryInfo er = new CountryInfo();
                    er.setType("CRS");
                    er.setSrcIso(srcIso);
                    er.setSrcName(Countries.INSTANCE.byIso.get(srcIso));
                    er.setDstIso(dstIso);
                    er.setDstName(Countries.INSTANCE.byIso.get(dstIso));
                    er.setYear(Integer.valueOf(year));
                    er.setDty(dty);
                    er.setTxt(txt);

                    exchangeRelationships.put(Strings.concat("_", "CRS", srcIso, dstIso), er);
                    if ("CL".equals(srcIso)) {
                        crsCountries.add(dstIso);
                    }
                }
            });
            cbc(new CtsExchangeRelationship.Listener() {

                @Override
                public void on(String srcCountry, String dstCountry, String year, boolean dty, String txt) {
                    String srcIso = Countries.INSTANCE.byName.get(srcCountry);
                    String dstIso = Countries.INSTANCE.byName.get(dstCountry);
                    if (Strings.anyNull(srcIso, dstIso)) {
                        log.warn(srcCountry + "=" + srcIso + ", " + dstCountry + "=" + dstIso);
                    }
                    if (Strings.equals(srcIso, CBC_2016_COUNTRIES) || Strings.equals(dstIso, CBC_2016_COUNTRIES)) {
                        year = "2016";
                    }
                    CountryInfo er = new CountryInfo();
                    er.setType("CBC");
                    er.setSrcIso(srcIso);
                    er.setSrcName(Countries.INSTANCE.byIso.get(srcIso));
                    er.setDstIso(dstIso);
                    er.setDstName(Countries.INSTANCE.byIso.get(dstIso));
                    er.setYear(Integer.valueOf(year));
                    er.setDty(dty);
                    er.setTxt(txt);
                    exchangeRelationships.put(Strings.concat("_", "CBC", srcIso, dstIso), er);
                    if ("CL".equals(srcIso)) {
                        cbcCountries.add(dstIso);
                    }
                }
            });
            SaraSerDe.toJson(exchangeRelationshipsFile, exchangeRelationships);
            exchangeRelationships = transform(exchangeRelationships);
        } else {
            exchangeRelationships = transform(SaraSerDe.fromJsonMap(exchangeRelationshipsFile, CountryInfo.class));
        }
    }


}
