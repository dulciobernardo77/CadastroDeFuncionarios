package dev.dulciobernardo7.CadastroDeFuncionarios.Config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import dev.dulciobernardo7.CadastroDeFuncionarios.Entity.User;
import jakarta.websocket.Decoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class TokenService {

    @Value("${cadastrodefuncionario.security.secret}")
    private String secret;

    public String generateToken(User user){

        Algorithm algorithm = Algorithm.HMAC256(secret);
        Instant now = Instant.now();

        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("UserId", user.getId())
                .withClaim("Username" , user.getNome())
                .withIssuedAt(now)
                .withExpiresAt(now.plusSeconds(86400))
                .withIssuer("API CadastroDeFuncionarios")
                .sign(algorithm);
    }

    public Optional<JWTUseData> verifyToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            DecodedJWT jwt = JWT.require(algorithm)
                    .build()
                    .verify(token);

            return Optional.of(JWTUseData
                    .builder()
                    .id(jwt.getClaim("userId").asLong())
                    .nome(jwt.getClaim("name").asString())
                    .email(jwt.getSubject())
                    .build());
        }catch (JWTVerificationException ex){
            return  Optional.empty();
        }
    }
}
