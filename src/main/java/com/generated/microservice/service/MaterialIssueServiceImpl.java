package com.generated.microservice.service;

import com.generated.microservice.dto.MaterialIssueDTO;
import com.generated.microservice.entity.MaterialIssue;
import com.generated.microservice.repository.MaterialIssueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MaterialIssueServiceImpl implements MaterialIssueService {

    private final MaterialIssueRepository materialIssueRepository;

    @Override
    public List<MaterialIssueDTO> getMaterialsIssuedByEmployeeId(String employeeId) {
        List<MaterialIssue> materialIssues = materialIssueRepository.findByEmployeeEmployeeId(employeeId);
        return materialIssues.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private MaterialIssueDTO convertToDTO(MaterialIssue materialIssue) {
        MaterialIssueDTO dto = new MaterialIssueDTO();
        dto.setMaterialName(materialIssue.getMaterial().getName());
        dto.setIssueDate(materialIssue.getIssueDate());
        dto.setQuantity(materialIssue.getQuantity());
        return dto;
    }
}