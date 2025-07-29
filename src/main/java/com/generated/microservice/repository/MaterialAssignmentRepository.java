package com.generated.microservice.repository;

import com.generated.microservice.entity.MaterialAssignment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MaterialAssignmentRepository extends MongoRepository<MaterialAssignment, String> {
    List<MaterialAssignment> findByEmployeeId(String employeeId);
}