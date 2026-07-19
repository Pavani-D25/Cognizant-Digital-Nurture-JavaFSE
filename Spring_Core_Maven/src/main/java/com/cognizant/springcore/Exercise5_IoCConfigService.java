package com.cognizant.springcore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("configService")
public class Exercise5_IoCConfigService {

    private final EmployeeRepository employeeRepository;

    @Value("${app.name:Cognizant Spring Training}")
    private String appName;

    @Autowired
    public Exercise5_IoCConfigService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public String getAppName() {
        return appName;
    }

    public String getEmployeeSummary() {
        return "Employees in " + appName + ": " + employeeRepository.findAll().size();
    }
}
