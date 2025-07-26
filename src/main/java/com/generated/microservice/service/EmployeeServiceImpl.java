package com.generated.microservice.service;

import com.generated.microservice.dto.EmployeeDTO;
import com.generated.microservice.entity.Employee;
import com.generated.microservice.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PhotoMatchingService photoMatchingService;

    @Override
    public String addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setContactInformation(employeeDTO.getContactInformation());
        String employeeId = UUID.randomUUID().toString();
        employee.setEmployeeId(employeeId);
        employeeRepository.save(employee);
        return employeeId;
    }

    @Override
    public List<Employee> findEmployeesByPhoto(MultipartFile photo) {
        List<String> matchingEmployeeIds = photoMatchingService.findMatchingEmployeeIds(photo);
        if (matchingEmployeeIds.isEmpty()) {
            return List.of(); // Or handle no matches as needed
        }

        return employeeRepository.findAllByEmployeeIdIn(matchingEmployeeIds);
    }
}