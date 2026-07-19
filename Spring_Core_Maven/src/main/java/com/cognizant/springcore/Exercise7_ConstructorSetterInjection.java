package com.cognizant.springcore;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Exercise7_ConstructorSetterInjection {

    public static void main(String[] args) {
        System.out.println("=== Exercise 7: Constructor and Setter Injection ===\n");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        EmployeeService service = context.getBean(EmployeeService.class);
        System.out.println("Injected service greeting: " + service.getGreeting());
        System.out.println("Employees:");
        service.getAllEmployees().forEach(emp -> System.out.println("  - " + emp));

        Exercise5_IoCConfigService configService = (Exercise5_IoCConfigService) context.getBean("configService");
        System.out.println("\nApp Name: " + configService.getAppName());
        System.out.println("Summary: " + configService.getEmployeeSummary());

        context.close();
        System.out.println("\n=== Exercise 7 Complete ===");
    }
}
