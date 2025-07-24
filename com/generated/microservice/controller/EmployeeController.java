package com.generated.microservice.controller;

import com.generated.microservice.entity.Employee;
import com.generated.microservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/searchByPhoto")
    public ResponseEntity<List<Employee>> searchEmployeesByPhoto(@RequestParam("photo") MultipartFile photo) {
        List<Employee> employees = employeeService.findEmployeesByPhoto(photo);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}