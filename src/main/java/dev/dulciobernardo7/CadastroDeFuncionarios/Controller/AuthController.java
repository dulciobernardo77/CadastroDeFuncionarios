package dev.dulciobernardo7.CadastroDeFuncionarios.Controller;

import dev.dulciobernardo7.CadastroDeFuncionarios.Config.TokenService;
import dev.dulciobernardo7.CadastroDeFuncionarios.Controller.DTO.LoginDTO;
import dev.dulciobernardo7.CadastroDeFuncionarios.Controller.DTO.LoginToken;
import dev.dulciobernardo7.CadastroDeFuncionarios.Controller.DTO.UserDTO;
import dev.dulciobernardo7.CadastroDeFuncionarios.Entity.User;
import dev.dulciobernardo7.CadastroDeFuncionarios.Exception.UsenameOrPasswordInvalidExceptions;
import dev.dulciobernardo7.CadastroDeFuncionarios.Service.UserService;
import dev.dulciobernardo7.CadastroDeFuncionarios.mapper.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Autenticação", description = "Operações para cadastro de usuário e autenticação")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokensecurity;

    @PostMapping("/register")
    @Operation(summary = "Cadastrar usuário", description = "Cria uma conta de usuário e retorna os dados cadastrados.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou senha obrigatória.")
    })
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO){
        User userSave = userService.Save(UserMapper.map(userDTO));
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(UserMapper.map(userSave));
    }

    @PostMapping("/login")
    @Operation(summary = "Autenticar usuário", description = "Valida e-mail e senha e retorna um token de autenticação.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Autenticação realizada com sucesso."),
            @ApiResponse(responseCode = "400", description = "E-mail ou senha inválidos.")
    })
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
