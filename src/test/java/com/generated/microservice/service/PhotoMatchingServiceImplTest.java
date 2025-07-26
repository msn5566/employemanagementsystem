import com.generated.microservice.service.PhotoMatchingServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PhotoMatchingServiceImplTest {

    @InjectMocks
    private PhotoMatchingServiceImpl photoMatchingService;

    @Test
    void findMatchingEmployeeIds_AlwaysReturnsEmptyList() {
        // Arrange
        MockMultipartFile photo = new MockMultipartFile("photo", "test.jpg", "image/jpeg", "test image content".getBytes());

        // Act
        List<String> result = photoMatchingService.findMatchingEmployeeIds(photo);

        // Assert
        assertEquals(0, result.size());
    }
}