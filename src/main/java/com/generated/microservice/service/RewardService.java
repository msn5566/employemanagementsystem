package com.generated.microservice.service;

import com.generated.microservice.dto.RewardDTO;
import com.generated.microservice.entity.Reward;

public interface RewardService {

    Reward createReward(String employeeId, RewardDTO rewardDTO);
}