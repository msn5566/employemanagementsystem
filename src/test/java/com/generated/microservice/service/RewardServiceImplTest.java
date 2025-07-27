
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class RewardServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private RewardServiceImpl rewardService;

    @Test
    void addRewardToEmployee_EmployeeExists_RewardAdded() {
        String employeeId = "123";
        RewardDTO rewardDTO = new RewardDTO();
        rewardDTO.setName("Bonus");
        rewardDTO.setDescription("Performance bonus");
        rewardDTO.setDate(LocalDate.now());

        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        employee.setContactInformation("original contact info");

        when(employeeRepository.findByEmployeeId(employeeId)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        rewardService.addRewardToEmployee(employeeId, rewardDTO);

        verify(employeeRepository, times(1)).findByEmployeeId(employeeId);
        verify(employeeRepository, times(1)).save(employee);
        assertEquals("original contact info; Reward Name: Bonus, Description: Performance bonus, Date: " + LocalDate.now(), employee.getContactInformation());
    }

    @Test
    void addRewardToEmployee_EmployeeDoesNotExist_ThrowsException() {
        String employeeId = "123";
        RewardDTO rewardDTO = new RewardDTO();
        rewardDTO.setName("Bonus");
        rewardDTO.setDescription("Performance bonus");

        when(employeeRepository.findByEmployeeId(employeeId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> rewardService.addRewardToEmployee(employeeId, rewardDTO));

        verify(employeeRepository, times(1)).findByEmployeeId(employeeId);
        verify(employeeRepository, never()).save(any(Employee.class));
    }
}