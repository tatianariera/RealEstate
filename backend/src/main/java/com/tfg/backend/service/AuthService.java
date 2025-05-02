package com.tfg.backend.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tfg.backend.dto.LoginDto;
import com.tfg.backend.dto.RegisterDto;
import com.tfg.backend.entity.User;
import com.tfg.backend.repository.UserRepository;

@Service
public class AuthService {
    
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User register(RegisterDto registerDto){
        String encodedPassword = passwordEncoder.encode(registerDto.getPassword());

        User user = new User();
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(encodedPassword);
        
        return userRepository.save(user);
    }

    public User login(LoginDto loginDto){
        User user = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
    
        if (passwordEncoder.matches(loginDto.getPassword(), user.getPassword())){
            return user;
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
}
