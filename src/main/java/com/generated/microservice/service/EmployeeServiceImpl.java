package com.generated.microservice.service;

import com.generated.microservice.dto.EmployeeDTO;
import com.generated.microservice.dto.MaterialAssignmentDTO;
import com.generated.microservice.entity.Employee;
import com.generated.microservice.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

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
    public List<MaterialAssignmentDTO> getMaterialsByEmployeeId(String employeeId) {
        List<Employee> employees = employeeRepository.findByEmployeeId(employeeId);
        List<MaterialAssignmentDTO> materialAssignments = new ArrayList<>();

        for (Employee employee : employees) {
            // Assuming Employee entity contains material information directly.
            // Adapt this section if material information is stored differently.
            MaterialAssignmentDTO materialAssignmentDTO = new MaterialAssignmentDTO();
            materialAssignmentDTO.setMaterialName(employee.getName()); // Assuming employee.getName() returns material name
            materialAssignmentDTO.setIssueDate(LocalDate.now()); // Assuming current date as issue date, needs to be updated based on your data
            materialAssignmentDTO.setNotes(employee.getContactInformation()); // Assuming employee.getContactInformation() returns notes.

            materialAssignments.add(materialAssignmentDTO);
        }
        return materialAssignments;
    }
}