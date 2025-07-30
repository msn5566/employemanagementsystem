import com.generated.microservice.controller.AdminController;
import com.generated.microservice.dto.ExternalEmployeeDTO;
import com.generated.microservice.entity.Employee;
import com.generated.microservice.service.EmployeeService;
import com.generated.microservice.service.ExternalApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AdminControllerTest {

    @Mock
    private ExternalApiService externalApiService;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private AdminController adminController;

    @Mock
    private OAuth2AuthorizedClient authorizedClient;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void syncEmployees_shouldReturnSuccessMessage_whenExternalApiDataIsSuccessfullySynced() throws Exception {
        // Arrange
        List<ExternalEmployeeDTO> externalEmployeeDTOs = new ArrayList<>();
        ExternalEmployeeDTO externalEmployeeDTO1 = new ExternalEmployeeDTO();
        externalEmployeeDTO1.setId("1");
        externalEmployeeDTO1.setName("John Doe");
        externalEmployeeDTO1.setContactInformation("john.doe@example.com");
        externalEmployeeDTOs.add(externalEmployeeDTO1);

        when(externalApiService.fetchExternalEmployeeData(authorizedClient)).thenReturn(externalEmployeeDTOs);

        List<Employee> employees = new ArrayList<>();
        Employee employee1 = new Employee();
        employee1.setEmployeeId("1");
        employee1.setName("John Doe");
        employee1.setContactInformation("john.doe@example.com");
        employees.add(employee1);

        when(employeeService.saveEmployees(employees)).thenReturn(employees);

        // Act & Assert
        mockMvc.perform(post("/admin/sync-employees")
                        .param("authorizedClient", authorizedClient.toString())) // Added dummy parameter
                .andExpect(status().isOk())
                .andExpect(content().string("Employees synchronized successfully."));

        verify(externalApiService).fetchExternalEmployeeData(authorizedClient);
        verify(employeeService).saveEmployees(employees);
    }
}