package com.generated.microservice.service;

import com.generated.microservice.dto.EmployeeDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmployeeService {

    String addEmployee(EmployeeDTO employeeDTO);

    List<EmployeeDTO> findEmployeesByPhoto(MultipartFile photo);
}