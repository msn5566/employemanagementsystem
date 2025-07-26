import com.generated.microservice.entity.Employee;
import com.generated.microservice.repository.EmployeeRepository;
import com.generated.microservice.service.EmployeeServiceImpl;
import com.generated.microservice.service.PhotoMatchingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private PhotoMatchingService photoMatchingService;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void findEmployeesByPhoto_ReturnsEmployeesWhenPhotoMatches() {
        // Arrange
        MockMultipartFile photo = new MockMultipartFile("photo", "test.jpg", "image/jpeg", "test image content".getBytes());
        List<String> matchingEmployeeIds = new ArrayList<>();
        matchingEmployeeIds.add("1");
        matchingEmployeeIds.add("2");

        List<Employee> employees = new ArrayList<>();
        Employee employee1 = new Employee();
        employee1.setEmployeeId("1");
        employees.add(employee1);
        Employee employee2 = new Employee();
        employee2.setEmployeeId("2");
        employees.add(employee2);

        when(photoMatchingService.findMatchingEmployeeIds(Mockito.any(MultipartFile.class))).thenReturn(matchingEmployeeIds);
        when(employeeRepository.findAllByEmployeeIdIn(matchingEmployeeIds)).thenReturn(employees);

        // Act
        List<Employee> result = employeeService.findEmployeesByPhoto(photo);

        // Assert
        assertEquals(2, result.size());
        assertEquals("1", result.get(0).getEmployeeId());
        assertEquals("2", result.get(1).getEmployeeId());
    }

    @Test
    void findEmployeesByPhoto_ReturnsEmptyListWhenNoPhotoMatches() {
        // Arrange
        MockMultipartFile photo = new MockMultipartFile("photo", "test.jpg", "image/jpeg", "test image content".getBytes());
        List<String> matchingEmployeeIds = new ArrayList<>(); // No matches

        when(photoMatchingService.findMatchingEmployeeIds(Mockito.any(MultipartFile.class))).thenReturn(matchingEmployeeIds);

        // Act
        List<Employee> result = employeeService.findEmployeesByPhoto(photo);

        // Assert
        assertEquals(0, result.size());
    }
}