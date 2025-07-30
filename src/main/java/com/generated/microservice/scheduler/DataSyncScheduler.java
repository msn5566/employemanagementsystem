package com.generated.microservice.scheduler;

import com.generated.microservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataSyncScheduler {

    private final EmployeeService employeeService;

    @Scheduled(fixedRate = 60000) // Run every 60 seconds
    public void syncData() {
        employeeService.fetchDataAndSave();
    }
}