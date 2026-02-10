package com.aldocursos.vollmed.shared.security;


import com.aldocursos.vollmed.modules.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Security;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TokenService tokenService;

    // Intercepta cada solicitud HTTP para validar el token JWT
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = recuperarToken(request);
        System.out.println(tokenJWT);

        // Si se encuentra un token JWT, valida y extrae la información del usuario
        if (tokenJWT != null){
            var subject = tokenService.getSubject(tokenJWT);
            System.out.println(subject);
            var usuario = repository.findByLogin(subject);
            // Crea un objeto de autenticación con los detalles del usuario
            var authenrtication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            // Guarda la autenticación en el contexto de seguridad de Spring
            SecurityContextHolder.getContext().setAuthentication(authenrtication);
        }

        // Si el token es válido, continúa con la cadena de filtros
        filterChain.doFilter(request,response);
    }

    // Extrae el token JWT del encabezado de autorización
    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null){
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

//    // Excluye la ruta de login del filtro de seguridad
//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//        return request.getServletPath().equals("/login");
//    }
}
