package com.cognizant.springcore;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Exercise1_BasicSpringApp {

    public static void main(String[] args) {
        System.out.println("=== Exercise 1: Configuring a Basic Spring Application ===\n");

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        MessageService messageService = (MessageService) context.getBean("messageService");
        System.out.println("Message from Spring: " + messageService.getMessage());

        messageService.setMessage("Updated message via setter!");
        System.out.println("Updated message: " + messageService.getMessage());

        EmployeeRepository repository = (EmployeeRepository) context.getBean("employeeRepository");
        System.out.println("\nAll employees:");
        repository.findAll().forEach(emp -> System.out.println("  - " + emp));

        ((ClassPathXmlApplicationContext) context).close();
        System.out.println("\n=== Exercise 1 Complete ===");
    }
}
