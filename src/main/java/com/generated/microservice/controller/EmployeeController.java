package com.generated.microservice.controller;

import com.generated.microservice.entity.Employee;
import com.generated.microservice.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/search")
    @Operation(summary = "Search employees by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved employees"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<List<Employee>> searchEmployeesByName(@RequestParam String name) {
        List<Employee> employees = employeeService.searchEmployeesByName(name);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}