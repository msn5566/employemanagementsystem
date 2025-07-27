
package com.generated.microservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RewardDTO {

    @NotBlank(message = "Reward name is required")
    @Size(max = 255, message = "Reward name cannot exceed 255 characters")
    private String name;

    @Size(max = 1000, message = "Reward description cannot exceed 1000 characters")
    private String description;

    private LocalDate date;
}