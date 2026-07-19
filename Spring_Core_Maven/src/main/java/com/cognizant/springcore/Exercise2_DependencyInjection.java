package com.cognizant.springcore;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Exercise2_DependencyInjection {

    public static void main(String[] args) {
        System.out.println("=== Exercise 2: Implementing Dependency Injection ===\n");

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        System.out.println("--- Constructor Injection ---");
        EmployeeService serviceWithMessage = (EmployeeService) context.getBean("employeeService");
        System.out.println("Greeting: " + serviceWithMessage.getGreeting());
        System.out.println("All employees:");
        serviceWithMessage.getAllEmployees().forEach(emp -> System.out.println("  - " + emp));

        System.out.println("\n--- Constructor Injection (without MessageService) ---");
        EmployeeService serviceNoMessage = (EmployeeService) context.getBean("employeeServiceNoMessage");
        System.out.println("Greeting: " + serviceNoMessage.getGreeting());

        System.out.println("\n--- Setter Injection ---");
        EmployeeService serviceSetter = (EmployeeService) context.getBean("employeeServiceSetter");
        System.out.println("Greeting: " + serviceSetter.getGreeting());

        System.out.println("\n--- Adding a new employee ---");
        serviceWithMessage.addEmployee("Charlie Brown");
        System.out.println("Updated employee list:");
        serviceWithMessage.getAllEmployees().forEach(emp -> System.out.println("  - " + emp));

        ((ClassPathXmlApplicationContext) context).close();
        System.out.println("\n=== Exercise 2 Complete ===");
    }
}
