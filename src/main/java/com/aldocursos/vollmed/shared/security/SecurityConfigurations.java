package com.aldocursos.vollmed.shared.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    // Configura las reglas de seguridad HTTP, deshabilitando CSRF y estableciendo la política de sesión como stateless
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req -> { // Configura las reglas de autorización para las rutas
                    req.requestMatchers("/login").permitAll(); // Permite el acceso a la ruta de login sin autenticación
                    req.anyRequest().authenticated(); // Requiere autenticación para cualquier otra ruta
                })
                .build();
    }

    // Proporciona un bean de AuthenticationManager para manejar la autenticación de usuarios
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // Proporciona un bean de PasswordEncoder para cifrar las contraseñas de los usuarios utilizando BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
