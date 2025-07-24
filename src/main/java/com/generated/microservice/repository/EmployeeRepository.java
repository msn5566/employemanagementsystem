package com.generated.microservice.repository;

import com.generated.microservice.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
    // Define method to find employees by photo (you might need to adjust the parameter type)
    // Assuming photo is stored as a String (e.g., a file path or base64 encoded string).  Adjust if needed.
    List<Employee> findByContactInformation(String contactInformation);
}