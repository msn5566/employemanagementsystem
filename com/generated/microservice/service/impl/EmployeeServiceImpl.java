package com.generated.microservice.service.impl;

import com.generated.microservice.dto.EmployeeSimilarity;
import com.generated.microservice.entity.Employee;
import com.generated.microservice.repository.EmployeeRepository;
import com.generated.microservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeSimilarity> findEmployeesByPhoto(MultipartFile photo) {
        // Dummy implementation: Replace with actual image processing and similarity calculation
        List<Employee> allEmployees = employeeRepository.findAll();
        List<EmployeeSimilarity> employeeSimilarities = new ArrayList<>();
        Random random = new Random();

        for (Employee employee : allEmployees) {
            // Generate a random similarity score for demonstration purposes
            double similarityScore = random.nextDouble();
            employeeSimilarities.add(new EmployeeSimilarity(employee, similarityScore));
        }

        // Sort by similarity score (descending) - Replace with actual sorting logic
        employeeSimilarities.sort((e1, e2) -> Double.compare(e2.getSimilarityScore(), e1.getSimilarityScore()));

        return employeeSimilarities;
    }
}