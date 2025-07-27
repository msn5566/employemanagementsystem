```java
package com.generated.microservice.service;

import com.generated.microservice.dto.RewardDTO;
import com.generated.microservice.entity.Employee;
import com.generated.microservice.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDate;
import java.util.Optional;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class RewardServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private RewardServiceImpl rewardService;

    @Test
    public void testAddRewardToEmployee_EmployeeExists_RewardAdded() {
        // Arrange
        String employeeId = "123";
        RewardDTO rewardDTO = new RewardDTO();
        rewardDTO.setDescription("Good job");
        rewardDTO.setDate(LocalDate.now());
        rewardDTO.setValue(50.0);

        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);

        when(employeeRepository.findByEmployeeId(employeeId)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        // Act
        Employee updatedEmployee = rewardService.addRewardToEmployee(employeeId, rewardDTO);

        // Assert
        assertNotNull(updatedEmployee);
        assertEquals(employeeId, updatedEmployee.getEmployeeId());
        assertEquals(1, updatedEmployee.getRewards().size());
        assertEquals("Good job", updatedEmployee.getRewards().get(0).getDescription());
        verify(employeeRepository).save(employee);
    }

    @Test
    public void testAddRewardToEmployee_EmployeeNotFound_ThrowsException() {
        // Arrange
        String employeeId = "123";
        RewardDTO rewardDTO = new RewardDTO();
        rewardDTO.setDescription("Good job");
        rewardDTO.setDate(LocalDate.now());
        rewardDTO.setValue(50.0);

        when(employeeRepository.findByEmployeeId(employeeId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> rewardService.addRewardToEmployee(employeeId, rewardDTO));
    }
}