package ru.leonid.labs.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.leonid.labs.dto.AuthRequestDTO;
import ru.leonid.labs.dto.AuthResponseDTO;
import ru.leonid.labs.dto.RegisterRequestDTO;
import ru.leonid.labs.security.JwtUtil;
import ru.leonid.labs.service.UserService;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements ru.leonid.labs.controller.impl.AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @Override
    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterRequestDTO request) {
        try {
            userService.saveUser(request.getUsername(), request.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new AuthResponseDTO("Created"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new AuthResponseDTO("Error creating user: " + e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO request) {
        try {
            SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            ));

            String token = jwtUtil.generateToken(request.getUsername());

            return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponseDTO(token));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new AuthResponseDTO("Error creating user: " + e.getMessage()));
        }
    }
}