package org.example.projeto_trainee.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.example.projeto_trainee.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Service
public class JWTProvider {

    @Value("${security.token.secret}")
    private String secretKey;

    public String validateToken(String token) {
        token = token.replace("Bearer ", "");

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        try {
            return JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getSubject();
        }
        catch (JWTVerificationException e){
            throw new JWTVerificationException("token is invalid or expired");
        }
    }

    public String createToken(User user){

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        return JWT.create().withSubject(user.getId().toString())
                .withIssuer("trainee")
                .withExpiresAt(Instant.now().plus(Duration.ofHours(1)))
                .sign(algorithm);
    }

    public String createMockInvalidToken(){

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        return JWT.create().withSubject(UUID.randomUUID().toString())
                .withIssuer("trainee")
                .withExpiresAt(Instant.now().plus(Duration.ofHours(1)))
                .sign(algorithm);
    }

    public UUID getUserIdFromToken(String token){
        token = token.replace("Bearer ", "");
        DecodedJWT decodedJWT = JWT.decode(token);
        return UUID.fromString(decodedJWT.getSubject());
    }
}
