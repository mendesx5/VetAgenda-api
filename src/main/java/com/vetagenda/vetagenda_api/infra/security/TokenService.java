package com.vetagenda.vetagenda_api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.vetagenda.vetagenda_api.domain.entity.UsuarioEntity;
import com.vetagenda.vetagenda_api.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(UsuarioEntity usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                    .withIssuer("vetagenda-api")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(genExpirationData())
                    .sign(algorithm);

            return token;
        } catch (JWTCreationException exception) {
            throw new ResourceNotFoundException("Erro ao gerar token JWT");
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("vetagenda-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new ResourceNotFoundException("");
        }
    }

    private Instant genExpirationData() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
