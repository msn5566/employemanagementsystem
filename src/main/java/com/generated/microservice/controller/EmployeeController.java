package com.generated.microservice.controller;

import com.generated.microservice.dto.EmployeeDTO;
import com.generated.microservice.dto.RewardDTO;
import com.generated.microservice.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/{employeeId}/rewards")
    public ResponseEntity<String> addRewardToEmployee(
            @PathVariable String employeeId,
            @Valid @RequestBody RewardDTO rewardDTO) {
        employeeService.addRewardToEmployee(employeeId, rewardDTO);
        return new ResponseEntity<>("Reward added to employee profile successfully", HttpStatus.OK);
    }
}