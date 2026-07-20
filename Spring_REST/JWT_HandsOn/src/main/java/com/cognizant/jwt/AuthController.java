package com.cognizant.jwt;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtTokenProvider tokenProvider;

    public AuthController(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        if ("admin".equals(request.getUsername()) && "admin123".equals(request.getPassword())) {
            String token = tokenProvider.generateToken(request.getUsername(), "ADMIN");
            return ResponseEntity.ok(new AuthResponse(token, "Login successful"));
        }
        if ("user".equals(request.getUsername()) && "user123".equals(request.getPassword())) {
            String token = tokenProvider.generateToken(request.getUsername(), "USER");
            return ResponseEntity.ok(new AuthResponse(token, "Login successful"));
        }
        return ResponseEntity.status(401).body(new AuthResponse(null, "Invalid credentials"));
    }

    @GetMapping("/validate")
    public ResponseEntity<Map<String, Object>> validateToken(@RequestHeader("Authorization") String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            if (tokenProvider.validateToken(token)) {
                String username = tokenProvider.getUsernameFromToken(token);
                return ResponseEntity.ok(Map.of(
                    "valid", true,
                    "username", username,
                    "message", "Token is valid"
                ));
            }
        }
        return ResponseEntity.status(401).body(Map.of(
            "valid", false,
            "message", "Invalid or missing token"
        ));
    }
}
