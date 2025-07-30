package com.generated.microservice.service;

import com.generated.microservice.dto.EmployeeDTO;
import com.generated.microservice.entity.Employee;
import com.generated.microservice.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
    public void exportEmployeesToExcel(HttpServletResponse response) throws IOException {
        List<Employee> employees = employeeRepository.findAll();

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Employees");

        XSSFRow headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Employee ID");
        headerRow.createCell(1).setCellValue("Name");
        headerRow.createCell(2).setCellValue("Contact Information");

        int rowNum = 1;
        for (Employee employee : employees) {
            XSSFRow row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(employee.getEmployeeId());
            row.createCell(1).setCellValue(employee.getName());
            row.createCell(2).setCellValue(employee.getContactInformation());
        }

        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
        } catch (IOException e) {
            throw new IOException("Error exporting to Excel", e);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    // Log the error, but don't rethrow as it might mask the original exception
                    System.err.println("Error closing output stream: " + e.getMessage());
                }
            }
            try {
                workbook.close();
            } catch (IOException e) {
                System.err.println("Error closing workbook: " + e.getMessage());
            }
        }
    }
}