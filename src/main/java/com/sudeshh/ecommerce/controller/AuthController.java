package com.sudeshh.ecommerce.controller;

import com.sudeshh.ecommerce.dto.AuthRequest;
import com.sudeshh.ecommerce.dto.AuthResponse;
import com.sudeshh.ecommerce.model.User;
import com.sudeshh.ecommerce.service.JwtService;
import com.sudeshh.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest request) {
        userService.registerUser(
                request.getUsername(),
                request.getPassword(),
                User.Role.USER
        );
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/register/admin")
    public ResponseEntity<String> registerAdmin(@RequestBody AuthRequest request) {
        userService.registerUser(
                request.getUsername(),
                request.getPassword(),
                User.Role.ADMIN
        );
        return ResponseEntity.ok("Admin registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword())
        );
        UserDetails userDetails =
                userService.loadUserByUsername(request.getUsername());
        User user = userService.findByUsername(request.getUsername());
        String token = jwtService.generateToken(userDetails.getUsername());
        return ResponseEntity.ok(new AuthResponse(token, user.getRole().name()));
    }
}