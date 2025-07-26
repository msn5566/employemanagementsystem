package com.generated.microservice.repository;

import com.generated.microservice.entity.Employee;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
    List<Employee> findAllByEmployeeIdIn(List<String> employeeIds);
}