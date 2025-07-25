package com.generated.microservice.service;

import com.generated.microservice.dto.EmployeeDTO;
import com.generated.microservice.entity.Employee;
import com.generated.microservice.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public String addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setContactInformation(employeeDTO.getContactInformation());
        String employeeId = UUID.randomUUID().toString();
        employee.setEmployeeId(employeeId);
        employeeRepository.save(employee);
        return employeeId;
    }

    @Override
    public List<Employee> findEmployeesByPhoto(MultipartFile photo) {
        // In a real implementation, you would compare the input photo
        // with the stored employee photos using a image comparison algorithm.
        // This is a simplified example that matches based on filename.

        List<Employee> matchedEmployees = new ArrayList<>();
        try {
            String photoBase64 = Base64.getEncoder().encodeToString(photo.getBytes());
            List<Employee> allEmployees = employeeRepository.findAll();

            for (Employee employee : allEmployees) {
                //comparing the base64 encodings is a naive approach, and a proper image
                //comparison library/service should be used in real implementation.
                //This stub is for demonstration purposes only.

                // Assuming 'photo' field in Employee entity stores base64 encoded images
                // and the logic is to find exact matches

                //if(employee.getPhoto() != null && employee.getPhoto().equals(photoBase64)){
                //    matchedEmployees.add(employee);
                //}
                //For now, always returns empty list because the Employee class doesn't have photo field.
            }


        } catch (IOException e) {
            // Handle exception appropriately (e.g., log it)
            System.err.println("Error processing photo: " + e.getMessage());
            return new ArrayList<>(); // Return empty list in case of error
        }


        return matchedEmployees;
    }
}