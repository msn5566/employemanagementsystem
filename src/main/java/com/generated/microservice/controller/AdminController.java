package com.generated.microservice.controller;

import com.generated.microservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final EmployeeService employeeService;

    @PostMapping("/syncEmployees")
    public ResponseEntity<String> syncEmployees(@RequestParam String apiUrl) {
        employeeService.syncEmployeesFromExternalApi(apiUrl);
        return ResponseEntity.ok("Employee data synced successfully.");
    }
}