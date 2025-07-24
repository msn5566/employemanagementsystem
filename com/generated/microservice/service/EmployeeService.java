package com.generated.microservice.service;

import com.generated.microservice.dto.EmployeeSimilarity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmployeeService {
    List<EmployeeSimilarity> findEmployeesByPhoto(MultipartFile photo);
}