package com.generated.microservice.controller;

import com.generated.microservice.dto.EmployeeDTO;
import com.generated.microservice.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import com.generated.microservice.entity.Employee;

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

    @PostMapping("/searchByPhoto")
    public ResponseEntity<List<Employee>> searchEmployeesByPhoto(@RequestParam("photo") MultipartFile photo) {
        List<Employee> employees = employeeService.findEmployeesByPhoto(photo);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}