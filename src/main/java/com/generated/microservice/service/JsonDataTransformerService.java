package com.generated.microservice.service;

import com.generated.microservice.model.source.Order;
import com.generated.microservice.model.target.PurchaseOrder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface JsonDataTransformerService {
    String transform(String xmlData, String mappingJson) throws IOException;
    PurchaseOrder transformPOJO(Order order, String mappingJson) throws IOException;
    PurchaseOrder transformFromFiles(MultipartFile sourceXmlFile, MultipartFile targetXmlFile, MultipartFile mappingJsonFile) throws IOException;

}