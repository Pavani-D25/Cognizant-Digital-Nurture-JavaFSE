package com.cognizant.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class HelloWorldController {

    @GetMapping("/hello")
    public Map<String, String> sayHello() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello World!");
        response.put("service", "Spring REST API");
        response.put("version", "1.0");
        return response;
    }

    @GetMapping("/hello/plain")
    public String sayHelloPlain() {
        return "Hello World! This is a plain text response.";
    }
}
