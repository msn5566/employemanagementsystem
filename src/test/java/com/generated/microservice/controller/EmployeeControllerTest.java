
package com.generated.microservice.controller;

import com.generated.microservice.dto.RewardDTO;
import com.generated.microservice.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    void addRewardToEmployee_ValidInput_ReturnsOk() throws Exception {
        String employeeId = "123";
        RewardDTO rewardDTO = new RewardDTO();
        rewardDTO.setName("Bonus");
        rewardDTO.setDescription("Performance bonus");

        doNothing().when(employeeService).addRewardToEmployee(employeeId, rewardDTO);

        mockMvc.perform(post("/employees/{employeeId}/rewards", employeeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rewardDTO)))
                .andExpect(status().isOk());

        verify(employeeService, times(1)).addRewardToEmployee(employeeId, rewardDTO);
    }

    @Test
    void addRewardToEmployee_InvalidInput_ReturnsBadRequest() throws Exception {
        String employeeId = "123";
        RewardDTO rewardDTO = new RewardDTO();
        rewardDTO.setDescription("Performance bonus"); // Missing name

        mockMvc.perform(post("/employees/{employeeId}/rewards", employeeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rewardDTO)))
                .andExpect(status().isBadRequest());

        verify(employeeService, never()).addRewardToEmployee(anyString(), any());
    }
}