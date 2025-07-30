package com.generated.microservice.service;

import com.generated.microservice.dto.EmployeeDTO;
import com.generated.microservice.dto.MaterialDTO;
import com.generated.microservice.entity.Employee;
import com.generated.microservice.entity.Material;
import com.generated.microservice.repository.EmployeeRepository;
import com.generated.microservice.repository.MaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final MaterialRepository materialRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public List<MaterialDTO> getMaterialsByEmployeeId(String employeeId) {
        List<Material> materials = materialRepository.findByEmployeeId(employeeId);
        return materials.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private MaterialDTO convertToDTO(Material material) {
        MaterialDTO materialDTO = new MaterialDTO();
        materialDTO.setItemName(material.getItemName());
        materialDTO.setIssueDate(material.getIssueDate());
        materialDTO.setNotes(material.getNotes());
        materialDTO.setEmployeeId(material.getEmployeeId());
        return materialDTO;
    }

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
}