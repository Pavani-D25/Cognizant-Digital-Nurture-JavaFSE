package com.cognizant.springcore;

import java.util.List;

public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private MessageService messageService;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeService(EmployeeRepository employeeRepository, MessageService messageService) {
        this.employeeRepository = employeeRepository;
        this.messageService = messageService;
    }

    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    public String getEmployee(int id) {
        return employeeRepository.findById(id);
    }

    public List<String> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public void addEmployee(String name) {
        employeeRepository.save(name);
    }

    public String getGreeting() {
        if (messageService != null) {
            return messageService.getMessage();
        }
        return "No message service configured";
    }
}
