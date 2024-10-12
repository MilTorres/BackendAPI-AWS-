package com.matr.mysqlaws.config;


/*
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity*/
public class SecurityConfig {
/*
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/login").permitAll() // Permite acceso a la página de login
                        .requestMatchers("/usuarios").authenticated() // Requiere autenticación para /usuarios
                        .anyRequest().authenticated() // Requiere autenticación para cualquier otra solicitud
                )
                .formLogin(form -> form
                        .loginPage("/login") // URL de la página de inicio de sesión
                        .permitAll()
                )
                .logout(logout -> logout
                        .permitAll() // Permite que todos los usuarios puedan cerrar sesión
                );

        return http.build(); // Devuelve la cadena de filtro de seguridad construida
    }*/
}
