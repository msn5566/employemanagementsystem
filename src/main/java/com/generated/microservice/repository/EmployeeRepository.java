package com.generated.microservice.repository;

import com.generated.microservice.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
    List<Employee> findByNameContainingIgnoreCase(String name);
}