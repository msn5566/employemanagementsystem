package com.generated.microservice.service;

import org.springframework.web.multipart.MultipartFile;

public interface MappingService {

    Object mapData(MultipartFile sourceXml, MultipartFile mappingCsv) throws Exception;
}