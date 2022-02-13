package com.example.javaitmo2.repository;

import com.example.javaitmo2.entity.UserEntity;
import com.example.javaitmo2.beans.UserSessionBean;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;

@Repository
public class JwtRepository {
    @Autowired
    UserSessionBean userSession;

    private String secret = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";

    public String getToken(UserEntity existingUser) {

        return Jwts.builder()
                .claim("name", existingUser.getName().concat(" ").concat(existingUser.getSurname()))
                .claim("email", existingUser.getEmail())
                .setSubject(existingUser.getName())
                .setId(existingUser.getId().toString())
                .setIssuedAt(Timestamp.from(Instant.now()))
                .setExpiration(Timestamp.from(Instant.now().plus(1, ChronoUnit.HOURS)))
                .signWith(this.getHmacKey())
                .compact();
    }

    public boolean validateJwtToken(String token) {
            try {
                Jws<Claims> jwt = Jwts.parserBuilder()
                        .setSigningKey(this.getHmacKey())
                        .build().parseClaimsJws(token);
                userSession.setUserUuid(jwt.getBody().getId());

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
