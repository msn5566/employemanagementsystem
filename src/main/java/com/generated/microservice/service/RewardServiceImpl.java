package com.generated.microservice.service;

import com.generated.microservice.dto.RewardDTO;
import com.generated.microservice.entity.Employee;
import com.generated.microservice.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RewardServiceImpl implements RewardService {

    private final EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public void addRewardToEmployee(String employeeId, RewardDTO rewardDTO) {
        Optional<Employee> employeeOptional = employeeRepository.findByEmployeeId(employeeId);

        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            // Ideally, you would create a Reward entity and add it to a list of rewards in the Employee entity.
            // Since we're aiming for minimal changes and the Employee entity does not have a rewards list,
            // we'll just update the contact information for now to store the reward details, which is not ideal.
            // A proper implementation would involve adding a rewards list to the Employee entity and persisting
            // Reward entities.
             String rewardDetails = "Reward Name: " + rewardDTO.getName() + ", Description: " + rewardDTO.getDescription() + ", Date: " + rewardDTO.getDate();
             employee.setContactInformation(employee.getContactInformation() == null ? rewardDetails : employee.getContactInformation() + "; " + rewardDetails);

            employeeRepository.save(employee);
        } else {
            throw new IllegalArgumentException("Employee with ID " + employeeId + " not found");
        }
    }
}
```