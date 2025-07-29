package com.generated.microservice.service;

import com.generated.microservice.entity.Employee;
import com.generated.microservice.entity.Material;
import com.generated.microservice.dto.MaterialDTO;
import com.generated.microservice.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {

    private final EmployeeRepository employeeRepository;

    @Override
    public List<MaterialDTO> getMaterialsByEmployeeId(String employeeId) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId);
        if (employee != null && employee.getMaterials() != null) {
            return employee.getMaterials().stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
        }
        return List.of();
    }

    private MaterialDTO convertToDto(Material material) {
        MaterialDTO materialDTO = new MaterialDTO();
        materialDTO.setId(material.getId());
        materialDTO.setMaterialName(material.getMaterialName());
        materialDTO.setIssueDate(material.getIssueDate());
        materialDTO.setDescription(material.getDescription());
        return materialDTO;
    }
}