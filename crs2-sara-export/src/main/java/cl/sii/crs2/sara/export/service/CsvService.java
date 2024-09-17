package cl.sii.crs2.sara.export.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class CsvService {
    @Value("${crs.export.data.out.dir}")
    private String dataOutDir;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveCsv(){
        log.info("Escribiendo CSV");
        log.info("Procesando hacia directorio " + dataOutDir);
        File dir = new File(dataOutDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File[] files = new File(dataOutDir).listFiles();
        if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
        List<String> tables = new ArrayList<>();
        try (final Connection c = jdbcTemplate.getDataSource().getConnection()) {
            try (final ResultSet rsTables = c.getMetaData().getTables(null, "", null, new String[] { "TABLE" })) {
                while (rsTables.next()) {
                    String name = rsTables.getString("TABLE_NAME");
                    if (jdbcTemplate.queryForObject("SELECT COUNT(*) FROM " + name, Integer.class) > 0) {
                        if (name.contains("SAS_CRS_")) {
                            tables.add(name);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (tables.size() == 0) {
            log.warn("No se encontraron tablas para exportar en el esquema CTS!");
        } else {
            ExecutorService pool = Executors.newFixedThreadPool(tables.size());
            for (String name : tables) {
                pool.execute(() -> {
                    File csv = new File(dataOutDir, name.substring(4) + ".csv");
                    log.warn("BGN Generando csv en " + csv);

                    jdbcTemplate.query("SELECT * FROM " + name + " ORDER BY 1,2", new RowCallbackHandler() {
                        @Override
                        public void processRow(ResultSet rs) throws SQLException {
                            ResultSetMetaData rsmd = rs.getMetaData();
                            int cols = rsmd.getColumnCount();
                            String[] colsName = new String[cols];
                            for (int i = 1; i <= cols; i++) {
                                colsName[i - 1] = rsmd.getColumnName(i);
                            }
                            Object[] row = new Object[cols];
                            try {
                                CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader(colsName).withTrim().withQuoteMode(QuoteMode.NON_NUMERIC);
                                try (final CSVPrinter printer = new CSVPrinter(new FileWriter(csv), csvFormat)) {
                                    int total = 0;

                                    //para procesar el primer registro, en el while avanza y empieza con el segundo
                                    for (int i = 1; i <= cols; i++) {
                                        row[i - 1] = rs.getObject(i);
                                    }
                                    printer.printRecord(row);
                                    total = 1;
                                    //

                                    while (rs.next()) {
                                        for (int i = 1; i <= cols; i++) {
                                            row[i - 1] = rs.getObject(i);
                                        }
                                        printer.printRecord(row);
                                        total++;
                                    }
                                    log.warn("END Generando csv en " + csv + ". Total registros: " + total);
                                }
                            } catch (Exception e) {
                                log.error("Error en tabla [" + name + "]", e);
                            }
                        }
                    });

                });
            }
            pool.shutdown();
            try {
                pool.awaitTermination(1, TimeUnit.HOURS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        log.info("FIN Escribiendo CSV");
    }
}
