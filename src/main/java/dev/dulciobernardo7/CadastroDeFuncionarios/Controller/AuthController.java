package dev.dulciobernardo7.CadastroDeFuncionarios.Controller;

import dev.dulciobernardo7.CadastroDeFuncionarios.Config.TokenSecurity;
import dev.dulciobernardo7.CadastroDeFuncionarios.Controller.DTO.LoginDTO;
import dev.dulciobernardo7.CadastroDeFuncionarios.Controller.DTO.LoginToken;
import dev.dulciobernardo7.CadastroDeFuncionarios.Controller.DTO.UserDTO;
import dev.dulciobernardo7.CadastroDeFuncionarios.Entity.User;
import dev.dulciobernardo7.CadastroDeFuncionarios.Exception.UsenameOrPasswordInvalidExceptions;
import dev.dulciobernardo7.CadastroDeFuncionarios.Service.UserService;
import dev.dulciobernardo7.CadastroDeFuncionarios.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cadastrodefuncionarios/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenSecurity tokensecurity;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO){
        User userSave = userService.Save(UserMapper.map(userDTO));
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(UserMapper.map(userSave));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginToken> login(@RequestBody LoginDTO loginDTO){
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.Senha());
            Authentication authenticate = authenticationManager.authenticate(authenticationToken);
            User user = (User) authenticate.getPrincipal();
            String token = tokensecurity.generateToken(user);
            return ResponseEntity.ok(new LoginToken(token));
        }catch (BadCredentialsException e){
            throw new UsenameOrPasswordInvalidExceptions("Nome ou senha invalida");
        }
    }
}
