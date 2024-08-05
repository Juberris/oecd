package cl.sii.filepackaging.service;

import cl.sii.filepackaging.model.FileDataList;
import cl.sii.filepackaging.model.FileRequest;

import javax.xml.bind.JAXBException;
import java.util.Map;

public interface PacketService {

    Map<String,String> createPackage(FileRequest obj);
    Map<String,String> createPackage(FileDataList obj) throws JAXBException;
    Map<String,String> unPackage(FileRequest obj) throws Exception;
}
