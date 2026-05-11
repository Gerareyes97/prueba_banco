package com.greyes.prueba.orderservice.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody SecurityUser request) {

        String username = "admin";

        String encodedPassword = passwordEncoder.encode("admin123");

        boolean matches = passwordEncoder.matches(request.getPassword(), encodedPassword);

        if (!username.equals(request.getUsername()) || !matches) {
            throw new RuntimeException("Credenciales invalidas");
        }

        String token = jwtService.generateToken(username);

        return Map.of("token", token);
    }
}