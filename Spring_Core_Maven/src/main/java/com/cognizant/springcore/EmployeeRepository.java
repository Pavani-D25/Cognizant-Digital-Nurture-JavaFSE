package com.cognizant.springcore;

import java.util.ArrayList;
import java.util.List;

public interface EmployeeRepository {
    String findById(int id);
    List<String> findAll();
    void save(String name);
}
