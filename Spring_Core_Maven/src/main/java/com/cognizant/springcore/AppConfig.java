package com.cognizant.springcore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Bean
    public EmployeeRepository employeeRepository() {
        return new EmployeeRepositoryImpl();
    }

    @Bean
    public MessageService messageService() {
        MessageService service = new MessageService();
        service.setMessage("Hello from Java-based Spring Configuration!");
        return service;
    }

    @Bean
    public EmployeeService employeeService() {
        return new EmployeeService(employeeRepository(), messageService());
    }
}
