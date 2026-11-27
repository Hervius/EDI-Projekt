package com.example.marketing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. CSRF deaktivieren (da wir zustandslose APIs bauen)
                .csrf(csrf -> csrf.disable())

                // 2. Keine Sessions speichern (wir nutzen Tokens)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 3. Regeln für die Endpunkte festlegen
                .authorizeHttpRequests(auth -> auth
                        // Das Frontend (HTML, JS) darf jeder sehen
                        .requestMatchers("/", "/index.html", "/error").permitAll()
                        // Alle anderen Endpunkte (deine API) erfordern einen Token
                        .anyRequest().authenticated()
                )

                // 4. JWT-Validierung aktivieren
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> {}));

        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        String secret = "MeinSuperGeheimerSchluesselFuerDasProjekt123!";
        SecretKeySpec secretKey = new SecretKeySpec(
                secret.getBytes(StandardCharsets.UTF_8),
                "HmacSHA256"
        );
        return NimbusJwtDecoder.withSecretKey(secretKey).build();
    }
}