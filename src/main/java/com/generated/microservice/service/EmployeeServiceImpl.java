package com.generated.microservice.service;

import com.generated.microservice.dto.EmployeeDTO;
import com.generated.microservice.dto.MaterialDTO;
import com.generated.microservice.entity.Employee;
import com.generated.microservice.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
    public List<MaterialDTO> getMaterialsByEmployeeId(String employeeId) {
        List<Employee> employees = employeeRepository.findByEmployeeId(employeeId);
        List<MaterialDTO> materials = new ArrayList<>();

        for (Employee employee : employees) {
            if (employee.getContactInformation() != null && !employee.getContactInformation().isEmpty()) {
                String[] materialInfo = employee.getContactInformation().split(",");
                if (materialInfo.length == 3) {
                    MaterialDTO materialDTO = new MaterialDTO();
                    materialDTO.setMaterialName(materialInfo[0].trim());
                    try {
                        materialDTO.setIssueDate(LocalDate.parse(materialInfo[1].trim()));
                    } catch (Exception e) {
                        materialDTO.setIssueDate(LocalDate.now()); // Set default date if parsing fails
                    }
                    materialDTO.setDescription(materialInfo[2].trim());
                    materials.add(materialDTO);
                }
            }
        }
        return materials;
    }
}