package ru.leonid.labs.controller.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.leonid.labs.dto.AuthRequestDTO;
import ru.leonid.labs.dto.AuthResponseDTO;
import ru.leonid.labs.dto.RegisterRequestDTO;

@RequestMapping("/api/auth")
public interface AuthController {

    @PostMapping("/login")
    ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO request);

    @PostMapping("/register")
    ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterRequestDTO request);
}
