package com.generated.microservice.controller;

import com.generated.microservice.dto.EmployeeSimilarity;
import com.generated.microservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping(value = "/search-by-photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmployeeSimilarity>> searchEmployeesByPhoto(@RequestParam("photo") MultipartFile photo) {
        List<EmployeeSimilarity> employees = employeeService.findEmployeesByPhoto(photo);
        return ResponseEntity.ok(employees);
    }
}