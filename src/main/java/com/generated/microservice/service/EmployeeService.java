package com.generated.microservice.service;

import com.generated.microservice.entity.Employee;
import com.generated.microservice.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.Valid;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee addEmployee(@Valid Employee employee) {
        return employeeRepository.save(employee);
    }

    public Optional<Employee> getEmployeeById(String id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

     public Employee updateEmployee(String id, @Valid Employee updatedEmployee) {
        Employee existingEmployee = employeeRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + id));

        existingEmployee.setName(updatedEmployee.getName());
        existingEmployee.setContactInformation(updatedEmployee.getContactInformation());

        return employeeRepository.save(existingEmployee);
    }

    public void deleteEmployee(String id) {
        employeeRepository.deleteById(id);
    }

    public List<Employee> findEmployeesByPhoto(MultipartFile photo) throws IOException {
        // This is a placeholder.  A real implementation would:
        // 1. Process the image (e.g., extract features, compare to existing employee photos)
        // 2. Query the database based on the processed image data.

        //For now, this will use ContactInformation, but this is a simplification
        //and assumes the photo data, or a hash, is stored there.
        String photoData = new String(photo.getBytes());
        return employeeRepository.findByContactInformation(photoData);
    }
}