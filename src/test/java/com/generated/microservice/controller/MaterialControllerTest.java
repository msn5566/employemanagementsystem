import com.generated.microservice.controller.MaterialController;
import com.generated.microservice.dto.MaterialDTO;
import com.generated.microservice.service.MaterialService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class MaterialControllerTest {

    @Mock
    private MaterialService materialService;

    @InjectMocks
    private MaterialController materialController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(materialController).build();
    }

    @Test
    void getMaterialsByEmployeeId_shouldReturnOkAndListOfMaterials() {
        // Arrange
        String employeeId = "123";
        List<MaterialDTO> materials = new ArrayList<>();
        MaterialDTO material1 = new MaterialDTO();
        material1.setId("1");
        material1.setEmployeeId(employeeId);
        material1.setMaterialName("Laptop");
        material1.setIssueDate("2023-01-01");
        materials.add(material1);

        when(materialService.getMaterialsByEmployeeId(employeeId)).thenReturn(materials);

        // Act
        ResponseEntity<List<MaterialDTO>> response = materialController.getMaterialsByEmployeeId(employeeId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(materials, response.getBody());
    }

    @Test
    void getMaterialsByEmployeeId_shouldReturnOkAndEmptyListWhenNoMaterialsFound() {
        // Arrange
        String employeeId = "456";
        List<MaterialDTO> materials = new ArrayList<>();

        when(materialService.getMaterialsByEmployeeId(employeeId)).thenReturn(materials);

        // Act
        ResponseEntity<List<MaterialDTO>> response = materialController.getMaterialsByEmployeeId(employeeId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(materials, response.getBody());
    }
}