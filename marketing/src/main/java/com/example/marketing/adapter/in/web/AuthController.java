package com.example.marketing.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtEncoder jwtEncoder;
    public AuthController(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        //  FÜR DAS PROJEKT: Einfache, hartcodierte Prüfung
        if ("admin".equals(username) && "admin".equals(password)) {
            Instant now = Instant.now();
            JwtClaimsSet claims = JwtClaimsSet.builder()
                    .issuer("marketing-service")
                    .issuedAt(now)
                    .expiresAt(now.plus(1, ChronoUnit.HOURS))
                    .subject(username)
                    .build();

            JwsHeader header = JwsHeader.with(() -> "HS256").build();
            String token = jwtEncoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();


            return ResponseEntity.ok(Map.of("token", token));

        }
        return ResponseEntity.status(401).body(Map.of("error", "Invalid username/password combination"));
    }
}
