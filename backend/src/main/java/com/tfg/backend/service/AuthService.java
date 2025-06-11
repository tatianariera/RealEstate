package com.tfg.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.tfg.backend.dto.LoginRequest;
import com.tfg.backend.dto.RegisterRequest;
import com.tfg.backend.entity.Employee;
import com.tfg.backend.entity.User;
import com.tfg.backend.repository.EmployeeRepository;
import com.tfg.backend.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private UserRepository userRepo;

    public void register(RegisterRequest request) {
        // Verifica que el employee exista
        Employee employee = employeeRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("No autorizado: el email no corresponde a ningún empleado"));

        // Verifica que no exista ya un usuario con ese email
        if (userRepo.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Ya existe un usuario con ese email");
        }

        // Crear y guardar el nuevo usuario
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        user.setEmployee(employee);

        userRepo.save(user);
    }

    public User login(LoginRequest request) {
        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!BCrypt.checkpw(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        return user;
    }

}
