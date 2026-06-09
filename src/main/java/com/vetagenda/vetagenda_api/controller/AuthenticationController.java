package com.vetagenda.vetagenda_api.controller;

import com.vetagenda.vetagenda_api.domain.dto.request.LoginRequest;
import com.vetagenda.vetagenda_api.domain.dto.request.RegisterRequest;
import com.vetagenda.vetagenda_api.domain.dto.response.LoginResponse;
import com.vetagenda.vetagenda_api.domain.entity.UsuarioEntity;
import com.vetagenda.vetagenda_api.infra.security.TokenService;
import com.vetagenda.vetagenda_api.repository.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final UsuarioRepository repository;

    private final TokenService tokenService;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getLogin(), data.getPassword());

        var auth = this.authenticationManager.authenticate(usernamePassword);

        var user = (UsuarioEntity) auth.getPrincipal();
        var token = tokenService.generateToken(user);

        return ResponseEntity.ok(new LoginResponse(token, user.getRole().getRole()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest data) {
        if (this.repository.findByLogin(data.getLogin()) != null) {
            return ResponseEntity.badRequest().body("Erro: Usuário já cadastrado com este login.");
        }

        String encryptedPassword = passwordEncoder.encode(data.getPassword());

        UsuarioEntity novoUsuario = UsuarioEntity.builder()
                .login(data.getLogin())
                .password(encryptedPassword)
                .role(data.getRole())
                .build();

        this.repository.save(novoUsuario);

        return ResponseEntity.ok().build();
    }

}
