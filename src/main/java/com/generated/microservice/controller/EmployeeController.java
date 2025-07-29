package com.generated.microservice.controller;

import com.generated.microservice.dto.EmployeeDTO;
import com.generated.microservice.entity.MaterialIssue;
import com.generated.microservice.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<String> addEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        String employeeId = employeeService.addEmployee(employeeDTO);
        return new ResponseEntity<>("Employee added successfully with ID: " + employeeId, HttpStatus.CREATED);
    }

    @GetMapping("/{employeeId}/materials")
    public ResponseEntity<List<MaterialIssue>> getMaterialsByEmployeeId(@PathVariable String employeeId) {
        List<MaterialIssue> materials = employeeService.getMaterialsByEmployeeId(employeeId);
        return ResponseEntity.ok(materials);
    }
}