package cl.sii.crs2.sara.export.service;

import cl.sii.crs2.sara.export.entities.cts.Cts2Cache;
import cl.sii.crs2.sara.export.entities.cts.Cts2DP;
import cl.sii.crs2.sara.export.repository.cts.Cts2CacheRepository;
import cl.sii.crs2.sara.export.repository.cts.Cts2DPRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.util.zip.InflaterInputStream;

@Service
@Slf4j
public class CtsService {

    @Autowired
    Cts2DPRepository cts2DPRepository;

    @Autowired
    Cts2CacheRepository cts2CacheRepository;



    @Transactional(readOnly = true)
    public void getPayload(){

        // SE PREFIERE ADAPTAR EL REPOSITORY PARA NO USAR FILTER
        // cts2DPRepository.findAllByOrderByDatetimeAsc().stream().filter(c->c.getIsStatus()==0).forEach(cts2DP -> {

        cts2DPRepository.findAllByOrderByDatetimeAsc().forEach(cts2DP -> {

            cts2DPRepository.findByFilename(cts2DP.getFilename()).ifPresent(cts -> {
                System.out.println(cts.getFilename());
                String key=cts.getFilename().replace(".zip", "").concat("_pyldClr");
                Cts2Cache cache=cts2CacheRepository.findByKey(key).orElse(null);
                try {
                    if (cache!=null) {
                        byte[] bytes = decompress(cache.getData());

                        byte[] keyClr
                    }

                } catch (Exception e) {
                    throw new RuntimeException("Error",e);
                }

            });
        });
    }

    byte[] decompress(byte[] bytes) throws Exception {
        try (InflaterInputStream iis = new InflaterInputStream(new ByteArrayInputStream(bytes))) {
            return IOUtils.toByteArray(iis);
        }
    }

    byte[] getKeyClr(Cts2DP dp) {
        byte[] bytes =null;
        String _keyClr=dp.getFilename().replace(".zip", "").concat("_keyClr");
        Cts2Cache cache=cts2CacheRepository.findByKey(_keyClr).orElse(null);
        if(cache!=null){
            String _key=dp.getFilename().replace(".zip", "").concat("_key");
            Cts2Cache cacheKey=cts2CacheRepository.findByKey(_key).orElse(null);
            byte[] key =
        }
        return bytes;
    }
}
