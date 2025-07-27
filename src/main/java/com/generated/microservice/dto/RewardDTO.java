package com.generated.microservice.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RewardDTO {

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    @NotEmpty
    private String date;
}