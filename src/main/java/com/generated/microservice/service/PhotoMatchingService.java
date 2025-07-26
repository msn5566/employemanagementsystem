package com.generated.microservice.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PhotoMatchingService {
    List<String> findMatchingEmployeeIds(MultipartFile photo);
}