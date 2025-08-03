package com.generated.microservice.service;

import com.generated.microservice.model.SourceData;

public interface JsonDataTransformerService {

    String transform(String mapping, SourceData sourceData);
}