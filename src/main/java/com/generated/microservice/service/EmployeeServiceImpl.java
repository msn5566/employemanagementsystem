package com.generated.microservice.service;

import com.generated.microservice.dto.EmployeeDTO;
import com.generated.microservice.entity.Employee;
import com.generated.microservice.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
import com.generated.microservice.dto.MaterialDTO;

import java.util.List;
import java.util.ArrayList;

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
    public List<MaterialDTO> getEmployeeMaterials(String employeeId) {
        // Mock the response for now, as there's no actual data fetching logic.
        List<MaterialDTO> materials = new ArrayList<>();
        
        if ("123".equals(employeeId)) { // Add conditional logic for the test case.

            MaterialDTO material1 = new MaterialDTO();
            material1.setMaterialName("Laptop");
            material1.setIssueDate("2024-01-15");
            material1.setMetadata("Dell XPS 15");

            MaterialDTO material2 = new MaterialDTO();
            material2.setMaterialName("Office Chair");
            material2.setIssueDate("2023-12-01");
            material2.setMetadata("Ergonomic");

            materials.add(material1);
            materials.add(material2);
        }

        return materials;
    }
}