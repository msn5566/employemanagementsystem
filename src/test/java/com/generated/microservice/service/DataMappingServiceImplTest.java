
import com.generated.microservice.model.SourceData;
import com.generated.microservice.model.TargetData;
import com.generated.microservice.service.DataMappingServiceImpl;
import com.generated.microservice.service.JsonDataTransformerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class DataMappingServiceImplTest {

    @Mock
    private JsonDataTransformerService jsonDataTransformerService;

    @InjectMocks
    private DataMappingServiceImpl dataMappingService;

    private SourceData sourceData;
    private String mappingJson;

    @BeforeEach
    void setUp() {
        sourceData = new SourceData();
        sourceData.setEmployeeId("123");
        sourceData.setEmployeeName("John Doe");
        sourceData.setDepartment("IT");

        mappingJson = "{\"id\": \"employeeId\", \"name\": \"employeeName\", \"team\": \"department\"}";
    }

    @Test
    void mapSourceToTarget_shouldMapDataCorrectly() {
        when(jsonDataTransformerService.transform("employeeId", sourceData)).thenReturn("123");
        when(jsonDataTransformerService.transform("employeeName", sourceData)).thenReturn("John Doe");
        when(jsonDataTransformerService.transform("department", sourceData)).thenReturn("IT");

        TargetData targetData = dataMappingService.mapSourceToTarget(sourceData, mappingJson);

        assertEquals("123", targetData.getId());
        assertEquals("John Doe", targetData.getName());
        assertEquals("IT", targetData.getTeam());
    }
}