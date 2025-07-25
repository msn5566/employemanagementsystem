package com.generated.microservice.repository;

import com.generated.microservice.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
    // Repository method to find employees by photo (assuming photo is stored as a string)
    // NOTE: This is a placeholder.  A real implementation would handle image data properly.
    List<Employee> findByPhoto(String photo);
}