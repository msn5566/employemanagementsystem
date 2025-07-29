package com.generated.microservice.service;

import com.generated.microservice.dto.MaterialAssignmentDTO;
import com.generated.microservice.dto.MaterialDTO;
import com.generated.microservice.entity.Material;
import com.generated.microservice.entity.MaterialAssignment;
import com.generated.microservice.repository.MaterialAssignmentRepository;
import com.generated.microservice.repository.MaterialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class MaterialAssignmentServiceImplTest {

    @Mock
    private MaterialAssignmentRepository materialAssignmentRepository;

    @Mock
    private MaterialRepository materialRepository;

    @InjectMocks
    private MaterialAssignmentServiceImpl materialAssignmentService;

    private MaterialAssignment materialAssignment;
    private Material material;

    @BeforeEach
    void setUp() {
        materialAssignment = new MaterialAssignment();
        materialAssignment.setId("assignment1");
        materialAssignment.setEmployeeId("employee1");
        materialAssignment.setMaterialId("material1");
        materialAssignment.setAssignmentDate("2024-01-01");

        material = new Material();
        material.setId("material1");
        material.setName("Laptop");
        material.setDescription("Dell XPS 15");
    }

    @Test
    void getMaterialAssignmentsByEmployeeId_shouldReturnListOfMaterialAssignmentDTOs() {
        when(materialAssignmentRepository.findByEmployeeId("employee1")).thenReturn(Collections.singletonList(materialAssignment));
        when(materialRepository.findById("material1")).thenReturn(Optional.of(material));

        List<MaterialAssignmentDTO> assignments = materialAssignmentService.getMaterialAssignmentsByEmployeeId("employee1");

        assertEquals(1, assignments.size());
        assertEquals("assignment1", assignments.get(0).getId());
        assertEquals("employee1", assignments.get(0).getEmployeeId());
        assertEquals("material1", assignments.get(0).getMaterialId());
        assertEquals("2024-01-01", assignments.get(0).getAssignmentDate());

        MaterialDTO materialDTO = assignments.get(0).getMaterial();
        assertEquals("material1", materialDTO.getId());
        assertEquals("Laptop", materialDTO.getName());
        assertEquals("Dell XPS 15", materialDTO.getDescription());
    }

    @Test
    void getMaterialAssignmentsByEmployeeId_shouldReturnEmptyList_whenNoAssignmentsFound() {
        when(materialAssignmentRepository.findByEmployeeId(anyString())).thenReturn(Collections.emptyList());

        List<MaterialAssignmentDTO> assignments = materialAssignmentService.getMaterialAssignmentsByEmployeeId("employee1");

        assertEquals(0, assignments.size());
    }

    @Test
    void getMaterialAssignmentsByEmployeeId_shouldReturnAssignmentWithoutMaterial_whenMaterialNotFound() {
        when(materialAssignmentRepository.findByEmployeeId("employee1")).thenReturn(Collections.singletonList(materialAssignment));
        when(materialRepository.findById("material1")).thenReturn(Optional.empty());

        List<MaterialAssignmentDTO> assignments = materialAssignmentService.getMaterialAssignmentsByEmployeeId("employee1");

        assertEquals(1, assignments.size());
        assertEquals("assignment1", assignments.get(0).getId());
        assertEquals("employee1", assignments.get(0).getEmployeeId());
        assertEquals("material1", assignments.get(0).getMaterialId());
        assertEquals("2024-01-01", assignments.get(0).getAssignmentDate());
        assertEquals(null, assignments.get(0).getMaterial());
    }
}