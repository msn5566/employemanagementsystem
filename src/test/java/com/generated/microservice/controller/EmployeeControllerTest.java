import com.generated.microservice.entity.Employee;
import com.generated.microservice.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    void searchEmployeesByPhoto_ReturnsOkWithEmployees() throws Exception {
        // Arrange
        MockMultipartFile photo = new MockMultipartFile("photo", "test.jpg", MediaType.IMAGE_JPEG_VALUE, "test image content".getBytes());
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee());
        Mockito.when(employeeService.findEmployeesByPhoto(Mockito.any(MultipartFile.class))).thenReturn(employees);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.multipart("/employees/searchByPhoto")
                        .file(photo)
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    void searchEmployeesByPhoto_ReturnsOkWithNoEmployees() throws Exception {
        // Arrange
        MockMultipartFile photo = new MockMultipartFile("photo", "test.jpg", MediaType.IMAGE_JPEG_VALUE, "test image content".getBytes());
        List<Employee> employees = new ArrayList<>(); // Empty list
        Mockito.when(employeeService.findEmployeesByPhoto(Mockito.any(MultipartFile.class))).thenReturn(employees);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.multipart("/employees/searchByPhoto")
                        .file(photo)
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }
}