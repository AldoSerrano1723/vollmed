package com.aldocursos.vollmed.shared.security;

import com.aldocursos.vollmed.modules.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    // Genera un token JWT para un usuario dado
    public String generarToken(Usuario usuario) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("APIVollmed")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(fechaExpiracion())
                    .sign(algoritmo);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error al generar token", exception);
        }
    }

    // Calcula la fecha de expiración del token (1 semana a partir de ahora)
    private Instant fechaExpiracion() {
        return LocalDateTime.now().plusWeeks(1).toInstant(ZoneOffset.UTC);
    }

    // Verifica la validez del token JWT y extrae el sujeto (login del usuario)
    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("APIVollmed")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token invalido o expirado");
        }
    }
}
