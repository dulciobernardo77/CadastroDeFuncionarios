package dev.dulciobernardo7.CadastroDeFuncionarios.Controller;

import dev.dulciobernardo7.CadastroDeFuncionarios.Controller.DTO.UserDTO;
import dev.dulciobernardo7.CadastroDeFuncionarios.Entity.User;
import dev.dulciobernardo7.CadastroDeFuncionarios.Service.UserService;
import dev.dulciobernardo7.CadastroDeFuncionarios.mapper.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@Tag(name = "Autenticação - interface web", description = "Páginas web para login e cadastro de usuário")
public class AuthViewController {

    private final UserService userService;

    @GetMapping("/")
    @Operation(summary = "Abrir página inicial", description = "Redireciona para a página de login.")
    @ApiResponse(responseCode = "302", description = "Redirecionamento para /login.")
    public String home() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    @Operation(summary = "Exibir página de login", description = "Retorna a página de login e, quando presente, informa erro de autenticação.")
    @ApiResponse(responseCode = "200", description = "Página HTML de login.", content = @Content(mediaType = "text/html"))
    public String loginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "E-mail ou senha inválidos.");
        }
        return "login";
    }

    @GetMapping("/register")
    @Operation(summary = "Exibir página de cadastro", description = "Retorna o formulário HTML para cadastro de usuário.")
    @ApiResponse(responseCode = "200", description = "Página HTML de cadastro.", content = @Content(mediaType = "text/html"))
    public String registerPage(Model model) {
        model.addAttribute("usuario", new UserDTO(null, "", "", ""));
        return "register";
    }

    @PostMapping("/register")
    @Operation(summary = "Enviar cadastro de usuário", description = "Valida os dados do formulário e redireciona conforme o resultado do cadastro.")
    @ApiResponses({
            @ApiResponse(responseCode = "302", description = "Redireciona para /login em caso de sucesso ou /register quando houver erro.")
    })
    public String registerUser(@ModelAttribute("usuario") UserDTO userDTO, RedirectAttributes redirectAttributes) {
        if (userDTO.nome() == null || userDTO.nome().isBlank() ||
                userDTO.email() == null || userDTO.email().isBlank() ||
                userDTO.senha() == null || userDTO.senha().isBlank()) {
            redirectAttributes.addFlashAttribute("erro", "Preencha nome, e-mail e senha para continuar.");
            return "redirect:/register";
        }

        try {
            User user = UserMapper.map(userDTO);
            userService.Save(user);
            redirectAttributes.addFlashAttribute("mensagem", "Cadastro realizado com sucesso!");
            return "redirect:/login";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("erro", "Não foi possível realizar o cadastro. Tente novamente.");
            return "redirect:/register";
        }
    }
}
