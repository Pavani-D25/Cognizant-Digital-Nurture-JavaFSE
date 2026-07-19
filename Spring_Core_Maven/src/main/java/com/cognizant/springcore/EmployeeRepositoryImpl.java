package com.cognizant.springcore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final Map<Integer, String> employeeStore = new HashMap<>();
    private int nextId = 1;

    public EmployeeRepositoryImpl() {
        employeeStore.put(1, "Alice Johnson");
        employeeStore.put(2, "Bob Smith");
        nextId = 3;
    }

    @Override
    public String findById(int id) {
        return employeeStore.get(id);
    }

    @Override
    public List<String> findAll() {
        return new ArrayList<>(employeeStore.values());
    }

    @Override
    public void save(String name) {
        employeeStore.put(nextId++, name);
    }
}
