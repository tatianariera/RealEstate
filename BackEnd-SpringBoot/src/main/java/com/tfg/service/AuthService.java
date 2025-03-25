package com.tfg.service;

import com.tfg.dto.LoginRequest;
import com.tfg.dto.RegisterRequest;
import com.tfg.exception.EmailAlreadyExistsException;
import com.tfg.exception.EmailNotFoundException;
import com.tfg.entity.User;
import com.tfg.repository.UserRepository;
import com.tfg.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    // Método para registrar un nuevo usuario
    public void register(RegisterRequest registerRequest) {
        // Verifica si el correo electrónico ya está en uso
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new EmailAlreadyExistsException("El correo electrónico ya está en uso");
        }

        // Aquí creamos un nuevo objeto User y lo asignamos desde RegisterRequest
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword())); // Encriptamos la contraseña

        // Guardamos el nuevo usuario en la base de datos
        userRepository.save(user);
    }

    // Método para iniciar sesión
    public String login(LoginRequest loginRequest) {
        // Buscamos al usuario por el correo electrónico
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new EmailNotFoundException("Usuario no encontrado"));

        // Comprobamos si la contraseña proporcionada coincide con la encriptada
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Credenciales incorrectas");
        }

        // Si las credenciales son correctas, generamos un token JWT
        return jwtTokenProvider.createToken(user.getEmail());
    }
}
