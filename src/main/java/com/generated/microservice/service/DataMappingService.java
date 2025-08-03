package com.generated.microservice.service;

import com.generated.microservice.model.SourceData;
import com.generated.microservice.model.TargetData;

public interface DataMappingService {

    TargetData mapSourceToTarget(SourceData sourceData, String mappingJson);

}