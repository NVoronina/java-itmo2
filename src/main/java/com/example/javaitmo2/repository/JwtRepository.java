package com.example.javaitmo2.repository;

import com.example.javaitmo2.dto.response.UserResponse;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Repository;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;

@Repository
public class JwtRepository {
    private String secret = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";

    public String getToken(UserResponse existingUser) {

        return Jwts.builder()
                .claim("name", existingUser.getName().concat(" ").concat(existingUser.getSurname()))
                .claim("email", existingUser.getEmail())
                .setSubject(existingUser.getName())
                .setId(existingUser.getUuid())
                .setIssuedAt(Timestamp.from(Instant.now()))
                .setExpiration(Timestamp.from(Instant.now().plus(1, ChronoUnit.MINUTES)))
                .signWith(this.getHmacKey())
                .compact();
    }

    public boolean validateJwtToken(String token) {
            try {
                Jws<Claims> jwt = Jwts.parserBuilder()
                        .setSigningKey(this.getHmacKey())
                        .build().parseClaimsJws(token);
                System.out.println(jwt.getBody().getExpiration());
                return true;
            } catch (SignatureException|MalformedJwtException|ExpiredJwtException|UnsupportedJwtException|IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            return false;
    }

    private Key getHmacKey() {
        return new SecretKeySpec(Base64.getDecoder().decode(this.secret),
                SignatureAlgorithm.HS256.getJcaName());
    }
}
