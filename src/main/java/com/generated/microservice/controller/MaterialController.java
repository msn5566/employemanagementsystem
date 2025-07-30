package com.generated.microservice.controller;

import com.generated.microservice.dto.MaterialDTO;
import com.generated.microservice.service.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/materials")
@RequiredArgsConstructor
public class MaterialController {

    private final MaterialService materialService;

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<MaterialDTO>> getMaterialsByEmployeeId(@PathVariable String employeeId) {
        List<MaterialDTO> materials = materialService.getMaterialsByEmployeeId(employeeId);
        return ResponseEntity.ok(materials);
    }
}