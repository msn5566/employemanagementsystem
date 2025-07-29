package com.generated.microservice.service;

import com.generated.microservice.entity.Material;
import com.generated.microservice.entity.MaterialAssignment;
import com.generated.microservice.dto.MaterialAssignmentDTO;
import com.generated.microservice.dto.MaterialDTO;
import com.generated.microservice.repository.MaterialAssignmentRepository;
import com.generated.microservice.repository.MaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MaterialAssignmentServiceImpl implements MaterialAssignmentService {

    private final MaterialAssignmentRepository materialAssignmentRepository;
    private final MaterialRepository materialRepository;

    @Override
    public List<MaterialAssignmentDTO> getMaterialAssignmentsByEmployeeId(String employeeId) {
        List<MaterialAssignment> assignments = materialAssignmentRepository.findByEmployeeId(employeeId);
        return assignments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private MaterialAssignmentDTO convertToDTO(MaterialAssignment assignment) {
        MaterialAssignmentDTO dto = new MaterialAssignmentDTO();
        dto.setId(assignment.getId());
        dto.setEmployeeId(assignment.getEmployeeId());
        dto.setMaterialId(assignment.getMaterialId());
        dto.setAssignmentDate(assignment.getAssignmentDate());

        Material material = materialRepository.findById(assignment.getMaterialId()).orElse(null);
        if (material != null) {
            MaterialDTO materialDTO = new MaterialDTO();
            materialDTO.setId(material.getId());
            materialDTO.setName(material.getName());
            materialDTO.setDescription(material.getDescription());
            dto.setMaterial(materialDTO);
        }

        return dto;
    }
}