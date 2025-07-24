import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmployeeService {

    List<Employee> findEmployeesByPhoto(MultipartFile photo);
}