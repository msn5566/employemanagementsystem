package com.generated.microservice.service;

import com.generated.microservice.model.SourceData;
import org.springframework.stereotype.Service;

@Service
public class JsonDataTransformerServiceImpl implements JsonDataTransformerService {

    @Override
    public String transform(String mapping, SourceData sourceData) {
        if ("employeeId".equals(mapping)) {
            return sourceData.getEmployeeId();
        } else if ("employeeName".equals(mapping)) {
            return sourceData.getEmployeeName();
        } else if ("department".equals(mapping)) {
            return sourceData.getDepartment();
        } else {
            return "Unknown";
        }
    }
}