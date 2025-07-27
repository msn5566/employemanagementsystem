import com.generated.microservice.dto.RewardDTO;
import com.generated.microservice.entity.Employee;
import com.generated.microservice.entity.Reward;
import com.generated.microservice.repository.EmployeeRepository;
import com.generated.microservice.repository.RewardRepository;
import com.generated.microservice.service.RewardServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class RewardServiceImplTest {

    @Mock
    private RewardRepository rewardRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private RewardServiceImpl rewardService;

    private Employee employee;
    private RewardDTO rewardDTO;

    @BeforeEach
    void setUp() {
        employee = new Employee();
        employee.setId("123");
        employee.setName("John Doe");

        rewardDTO = new RewardDTO();
        rewardDTO.setName("Bonus Reward");
        rewardDTO.setDescription("Outstanding performance");
        rewardDTO.setDate("2024-01-01");
    }

    @Test
    void createReward_ValidInput_ReturnsCreatedReward() {
        when(employeeRepository.findById("123")).thenReturn(Optional.of(employee));
        when(rewardRepository.save(any(Reward.class))).thenAnswer(invocation -> {
            Reward savedReward = invocation.getArgument(0);
            savedReward.setId("reward123");
            return savedReward;
        });

        Reward createdReward = rewardService.createReward("123", rewardDTO);

        assertEquals("reward123", createdReward.getId());
        assertEquals("123", createdReward.getEmployeeId());
        assertEquals("Bonus Reward", createdReward.getName());
        assertEquals("Outstanding performance", createdReward.getDescription());
        assertEquals("2024-01-01", createdReward.getDate());
    }

    @Test
    void createReward_EmployeeNotFound_ThrowsException() {
        when(employeeRepository.findById("123")).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            rewardService.createReward("123", rewardDTO);
        });

        assertEquals("Employee not found with id: 123", exception.getMessage());
    }
}