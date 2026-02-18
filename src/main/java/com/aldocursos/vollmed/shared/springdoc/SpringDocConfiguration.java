package com.aldocursos.vollmed.shared.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {
    @Bean
     public OpenAPI customOpenAPI() {
       return new OpenAPI()
              .components(new Components()
              .addSecuritySchemes("bearer-key",
              new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
               .info(new Info()
                       .title("Vollmed API")
                       .description("API Rest de la aplicación Vollmed, que contiene las funcionalidades CRUD de médicos y de pacientes, además de reserva y cancelamiento de consultas")
                       .contact(new Contact()
                               .name("Aldo Serrano")
                               .email("aldoantonio1723@gmail.com"))
                       .license(new License()
                               .name("MIT License")
                               .url("https://choosealicense.com/licenses/mit/"))
               );
    }
}
