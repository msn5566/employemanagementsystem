package com.generated.microservice.repository;

import com.generated.microservice.entity.Material;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MaterialRepository extends MongoRepository<Material, String> {
    List<Material> findByEmployeeId(String employeeId);
}