package com.generated.microservice.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class EmployeePhotoDTO {
    private MultipartFile photo;
}