package com.tfg.backend.service;

import com.tfg.backend.entity.Employee;
import com.tfg.backend.repository.EmployeeRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Employee findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + id));
    }

    public List<Employee> findAll() {
    return repository.findAll();
}

}
