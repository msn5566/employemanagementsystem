package com.generated.microservice.service;

import com.generated.microservice.dto.MaterialDTO;
import com.generated.microservice.entity.Material;
import com.generated.microservice.repository.MaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;

    @Override
    public List<MaterialDTO> getMaterialsByEmployeeId(String employeeId) {
        List<Material> materials = materialRepository.findByEmployeeId(employeeId);
        return materials.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private MaterialDTO convertToDto(Material material) {
        MaterialDTO materialDTO = new MaterialDTO();
        materialDTO.setId(material.getId());
        materialDTO.setEmployeeId(material.getEmployeeId());
        materialDTO.setMaterialName(material.getMaterialName());
        materialDTO.setIssueDate(material.getIssueDate());
        return materialDTO;
    }
}