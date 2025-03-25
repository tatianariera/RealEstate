package com.tfg.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expirationTime; // Tiempo de expiración en milisegundos (por ejemplo, 24 horas)

    // Método para generar el token
    public String createToken(String email) {
        // Crear la clave a partir del secreto
        Key key = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS512.getJcaName());

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key)
                .compact();
    }

    // Método para validar el token
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8)) // Usar los bytes del secreto
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            // Loguea el error o maneja la excepción de manera más detallada si es necesario
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    // Método para extraer el email del token
    public String getEmailFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8)) // Usar los bytes del secreto
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
