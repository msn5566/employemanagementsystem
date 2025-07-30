package com.generated.microservice.repository;

import com.generated.microservice.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
    // Add this method to find materials assigned to an employee by employeeId
    List<Employee> findByEmployeeId(String employeeId);
}