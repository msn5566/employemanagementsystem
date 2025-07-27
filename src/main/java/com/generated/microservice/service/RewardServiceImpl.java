package com.generated.microservice.service;

import com.generated.microservice.dto.RewardDTO;
import com.generated.microservice.entity.Employee;
import com.generated.microservice.entity.Reward;
import com.generated.microservice.repository.EmployeeRepository;
import com.generated.microservice.repository.RewardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RewardServiceImpl implements RewardService {

    private final RewardRepository rewardRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public Reward createReward(String employeeId, RewardDTO rewardDTO) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + employeeId));

        Reward reward = new Reward();
        reward.setEmployeeId(employeeId);
        reward.setName(rewardDTO.getName());
        reward.setDescription(rewardDTO.getDescription());
        reward.setDate(rewardDTO.getDate());

        return rewardRepository.save(reward);
    }
}