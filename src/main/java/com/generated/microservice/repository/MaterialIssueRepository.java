package com.generated.microservice.repository;

import com.generated.microservice.entity.MaterialIssue;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MaterialIssueRepository extends MongoRepository<MaterialIssue, String> {
    List<MaterialIssue> findByEmployeeId(String employeeId);
}