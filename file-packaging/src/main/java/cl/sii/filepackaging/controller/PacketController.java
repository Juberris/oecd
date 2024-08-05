package cl.sii.filepackaging.controller;

import cl.sii.filepackaging.model.AppProp;
import cl.sii.filepackaging.model.FileDataList;
import cl.sii.filepackaging.model.FileRequest;
import cl.sii.filepackaging.service.PacketService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/cts")
@Slf4j
public class PacketController {

    @Autowired
    PacketService service;

    @Value("${dir.uploaded-folder}")
    private String UPLOADED_FOLDER;

    @Autowired
    AppProp ctsProps;

    @PostMapping("/upload")
    public ResponseEntity<String> upload( @RequestPart("file") MultipartFile file,
                                          @RequestPart("data") String data){
        String prop=ctsProps.getUri();
        FileRequest obj= new FileRequest();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            obj = objectMapper.readValue(data, FileRequest.class);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("Invalid data format.", HttpStatus.BAD_REQUEST);
        }
        if(!Objects.equals(file.getOriginalFilename(), obj.getFileName())){
            return new ResponseEntity<>("File name is not the same as the data.", HttpStatus.BAD_REQUEST);
        }
        try {
            // Ensure the directory exists
            Path uploadPath = Paths.get(UPLOADED_FOLDER+"//" +obj.getCountryCodeReceiver()+"//" + obj.getFileFormatCd());
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // ht{
            byte[] bytes = file.getBytes();

            Path path = uploadPath.resolve(Objects.requireNonNull(file.getOriginalFilename()));
            Files.write(path, bytes);
            obj.setFile(bytes);
            obj.setFileFormatCd(file.getOriginalFilename().substring(file.getOriginalFilename().length()-3));
            // Process additional parameters
            System.out.println("File Name: " + obj.getFileName());
            System.out.println("Type: " + obj.getTypeExchange());
            System.out.println("Country Code: " + obj.getCountryCodeReceiver());
            Map<String,String> out=service.createPackage(obj);
            return new ResponseEntity<>("Successfully uploaded - " + file.getOriginalFilename(), HttpStatus.OK);

        } catch (IOException e) {

            return new ResponseEntity<>("File upload failed.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/upload-list")
    public ResponseEntity<String> upload( @RequestPart("file") MultipartFile[] files,
                                          @RequestPart("data") String data) throws JsonProcessingException, JAXBException {

        FileDataList fileDataList=new FileDataList();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
        JsonNode jsonNode = objectMapper.readValue(data, JsonNode.class);

        fileDataList.setTypeExchange(jsonNode.fields().next().getKey());


        jsonNode.fields().next().getValue().forEach(i->{
            FileRequest f= new FileRequest();
            f.setFileName(i.get("fileName").textValue());
            f.setFileFormatCd(i.get("FileFormatCd").textValue());
            f.setCountryCodeReceiver(i.get("countryCodeReceiver").textValue());
            for (MultipartFile file : files) {
                if (f.getFileName().equals(file.getOriginalFilename())) {
                    try {
                        f.setFile(file.getBytes());
                    } catch (IOException e) {
                       f.setFile(null);
                       log.error("Error en archivo request {}", f.getFileName());
                    }
                    break;
                }
            }
            fileDataList.getFiles().add(f);
        });

        Map<String,String> out=service.createPackage(fileDataList);
            return new ResponseEntity<>("Successfully unpackaged" , HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>("File unpackaged failed.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/unpack")
    public ResponseEntity<String> unpackage( @RequestPart("file") MultipartFile file){
        try {
            FileRequest obj= new FileRequest();
            obj.setFileName(file.getOriginalFilename());
            obj.setFile(file.getBytes());

            String[] dataFileName=file.getOriginalFilename().split("_");
            obj.setCountryCodeReceiver(dataFileName[0]);
            obj.setTypeExchange(dataFileName[1]);
            Map<String,String> out=service.unPackage(obj);

            return new ResponseEntity<>("Successfully unpackaged - " + file.getOriginalFilename(), HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>("File unpackaged failed.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
