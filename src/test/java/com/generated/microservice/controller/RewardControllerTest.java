```java
package com.generated.microservice.controller;

import com.generated.microservice.dto.RewardDTO;
import com.generated.microservice.entity.Employee;
import com.generated.microservice.service.EmployeeService;
import com.generated.microservice.service.RewardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class RewardControllerTest {

    @Mock
    private EmployeeService employeeService;

    @Mock
    private RewardService rewardService;

    @InjectMocks
    private EmployeeController employeeController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAddRewardToEmployee_ValidInput_ReturnsOk() throws Exception {
        // Arrange
        String employeeId = "123";
        RewardDTO rewardDTO = new RewardDTO();
        rewardDTO.setDescription("Excellent work");
        rewardDTO.setDate(LocalDate.now());
        rewardDTO.setValue(100.0);

        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);

        when(rewardService.addRewardToEmployee(eq(employeeId), any(RewardDTO.class))).thenReturn(employee);

        // Act
        mockMvc.perform(post("/employees/{employeeId}/rewards", employeeId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(rewardDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
                //.andExpect(content().json(objectMapper.writeValueAsString(employee)));

        // Assert
        verify(rewardService).addRewardToEmployee(eq(employeeId), any(RewardDTO.class));
    }

    @Test
    public void testAddRewardToEmployee_InvalidInput_ReturnsBadRequest() throws Exception {
        // Arrange
        String employeeId = "123";
        RewardDTO rewardDTO = new RewardDTO();
        rewardDTO.setDescription(""); // Invalid: Description is blank
        rewardDTO.setDate(LocalDate.now());
        rewardDTO.setValue(100.0);

        // Act & Assert
        mockMvc.perform(post("/employees/{employeeId}/rewards", employeeId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(rewardDTO)))
                .andExpect(status().isBadRequest());
    }
}