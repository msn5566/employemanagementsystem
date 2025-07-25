package com.generated.microservice.repository;

import com.generated.microservice.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
    // Add method to find employees by photo
    //List<Employee> findByPhoto(String photo); // Placeholder: Actual implementation depends on photo storage and comparison
}