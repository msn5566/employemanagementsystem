
package com.generated.microservice.service;

import com.generated.microservice.dto.RewardDTO;

public interface RewardService {
    void addRewardToEmployee(String employeeId, RewardDTO rewardDTO);
}