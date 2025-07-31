package com.generated.microservice.service;

import com.generated.microservice.dto.SourceDataDTO;
import com.generated.microservice.dto.TargetDataDTO;
import org.springframework.web.multipart.MultipartFile;

public interface XmlDataService {
    void processXmlData(MultipartFile sourceXmlFile, MultipartFile targetXmlFile, MultipartFile mappingCsvFile);
}