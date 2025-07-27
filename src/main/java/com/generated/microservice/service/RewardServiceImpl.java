```java
package com.generated.microservice.service;

import com.generated.microservice.dto.RewardDTO;
import com.generated.microservice.entity.Employee;
import com.generated.microservice.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RewardServiceImpl implements RewardService {

    private final EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public Employee addRewardToEmployee(String employeeId, RewardDTO rewardDTO) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new NoSuchElementException("Employee not found with id: " + employeeId));

        Employee.Reward reward = new Employee.Reward();
        reward.setDescription(rewardDTO.getDescription());
        reward.setDate(rewardDTO.getDate());
        reward.setValue(rewardDTO.getValue());

        employee.getRewards().add(reward);
        return employeeRepository.save(employee);
    }
}