// src/main/java/org/generations/restfuldemodyd/controller/AuthController.java
package org.generations.authservices.controller;

import org.generations.authservices.dto.AuthResponseDTO;
import org.generations.authservices.dto.LoginDTO;
import org.generations.authservices.dto.RegisterDTO;
import org.generations.authservices.model.AppUser;
import org.generations.authservices.model.Role;
import org.generations.authservices.repository.RolRepository;
import org.generations.authservices.repository.UserRepository;
import org.generations.authservices.services.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final UserRepository usuarioRepo;
    private final RolRepository rolRepo;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final long expiresMs;

    public AuthController(AuthenticationManager authManager,
                          UserRepository usuarioRepo,
                          RolRepository rolRepo,
                          PasswordEncoder encoder,
                          JwtService jwtService,
                          @Value("${app.security.jwt.expiration-ms}") long expiresMs) {
        this.authManager = authManager;
        this.usuarioRepo = usuarioRepo;
        this.rolRepo = rolRepo;
        this.encoder = encoder;
        this.jwtService = jwtService;
        this.expiresMs = expiresMs;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO req) {
        if (usuarioRepo.existsByUsername(req.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username ya existe");
        }
        Role rolUser = rolRepo.findByName("ROLE_USER")
                .orElseThrow(() -> new IllegalStateException("ROLE_USER no inicializado"));

        AppUser u = AppUser.builder()
                .username(req.getUsername())
                .password(encoder.encode(req.getPassword()))
                .roles(Set.of(rolUser))
                .build();
        usuarioRepo.save(u);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO req) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
        );
        UserDetails principal = (UserDetails) auth.getPrincipal();
        String token = jwtService.generateToken(principal);
        Set<String> roles = principal.getAuthorities().stream()
                .map(a -> a.getAuthority())
                .collect(Collectors.toSet());

        return ResponseEntity.ok(new AuthResponseDTO(token, expiresMs, principal.getUsername(), roles));
    }
}
