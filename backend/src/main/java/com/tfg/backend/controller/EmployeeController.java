package com.tfg.backend.controller;

import com.tfg.backend.entity.Employee;
import com.tfg.backend.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    // Endpoint para obtener todos los empleados
    @GetMapping
    public List<Employee> getAllEmployees() {
        return service.findAll();
    }
}
