
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.generated.microservice.controller.AdminController;
import com.generated.microservice.dto.ExternalEmployeeDTO;
import com.generated.microservice.entity.Employee;
import com.generated.microservice.service.EmployeeService;
import com.generated.microservice.service.ExternalApiService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AdminControllerTest {

    @Mock
    private ExternalApiService externalApiService;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private AdminController adminController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
    }

    @Test
    void fetchAndSaveEmployees_success() throws Exception {
        // Arrange
        List<ExternalEmployeeDTO> externalEmployeeDTOs = new ArrayList<>();
        ExternalEmployeeDTO externalEmployeeDTO1 = new ExternalEmployeeDTO();
        externalEmployeeDTO1.setEmployeeId("1");
        externalEmployeeDTO1.setName("John Doe");
        externalEmployeeDTO1.setContactInformation("john.doe@example.com");
        externalEmployeeDTOs.add(externalEmployeeDTO1);

        when(externalApiService.fetchEmployeeData()).thenReturn(externalEmployeeDTOs);
        when(employeeService.saveEmployees(anyList())).thenReturn(new ArrayList<>());

        // Act & Assert
        mockMvc.perform(post("/admin/fetch-and-save-employees"))
                .andExpect(status().isOk());
    }

    @Test
    void fetchAndSaveEmployees_externalApiReturnsEmptyList() throws Exception {
        // Arrange
        List<ExternalEmployeeDTO> externalEmployeeDTOs = new ArrayList<>();

        when(externalApiService.fetchEmployeeData()).thenReturn(externalEmployeeDTOs);
        when(employeeService.saveEmployees(anyList())).thenReturn(new ArrayList<>());

        // Act & Assert
        mockMvc.perform(post("/admin/fetch-and-save-employees"))
                .andExpect(status().isOk());
    }
}