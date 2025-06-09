package com.tfg.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfg.backend.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {}
    
