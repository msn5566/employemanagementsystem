
import com.generated.microservice.controller.RewardController;
import com.generated.microservice.dto.RewardDTO;
import com.generated.microservice.entity.Reward;
import com.generated.microservice.service.RewardService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class RewardControllerTest {

    @Mock
    private RewardService rewardService;

    @InjectMocks
    private RewardController rewardController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(rewardController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void createReward_ValidInput_ReturnsCreated() throws Exception {
        RewardDTO rewardDTO = new RewardDTO();
        rewardDTO.setName("Bonus Reward");
        rewardDTO.setDescription("Outstanding performance");
        rewardDTO.setDate("2024-01-01");

        Reward createdReward = new Reward();
        createdReward.setId("reward123");
        createdReward.setEmployeeId("123");
        createdReward.setName("Bonus Reward");
        createdReward.setDescription("Outstanding performance");
        createdReward.setDate("2024-01-01");

        when(rewardService.createReward(eq("123"), any(RewardDTO.class))).thenReturn(createdReward);

        mockMvc.perform(post("/rewards/123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(rewardDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("reward123"))
                .andExpect(jsonPath("$.employeeId").value("123"))
                .andExpect(jsonPath("$.name").value("Bonus Reward"));
    }

    @Test
    void createReward_InvalidInput_ReturnsBadRequest() throws Exception {
        RewardDTO rewardDTO = new RewardDTO();
        rewardDTO.setName(""); // Invalid: Name is empty
        rewardDTO.setDescription("Outstanding performance");
        rewardDTO.setDate("2024-01-01");

        mockMvc.perform(post("/rewards/123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(rewardDTO)))
                .andExpect(status().isBadRequest());
    }
}