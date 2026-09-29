package dev.dulciobernardo7.CadastroDeFuncionarios.Config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import dev.dulciobernardo7.CadastroDeFuncionarios.Entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class TokenSecurity {

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


}
