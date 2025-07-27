```java
package com.generated.microservice.service;

import com.generated.microservice.dto.RewardDTO;
import com.generated.microservice.entity.Employee;

public interface RewardService {
    Employee addRewardToEmployee(String employeeId, RewardDTO rewardDTO);
}