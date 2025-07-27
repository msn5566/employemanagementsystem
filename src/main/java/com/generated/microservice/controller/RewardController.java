package com.generated.microservice.controller;

import com.generated.microservice.dto.RewardDTO;
import com.generated.microservice.entity.Reward;
import com.generated.microservice.service.RewardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rewards")
@RequiredArgsConstructor
public class RewardController {

    private final RewardService rewardService;

    @PostMapping("/{employeeId}")
    public ResponseEntity<Reward> createReward(@PathVariable String employeeId, @Valid @RequestBody RewardDTO rewardDTO) {
        Reward reward = rewardService.createReward(employeeId, rewardDTO);
        return new ResponseEntity<>(reward, HttpStatus.CREATED);
    }
}