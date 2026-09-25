package dev.dulciobernardo7.CadastroDeFuncionarios.Controller;

import dev.dulciobernardo7.CadastroDeFuncionarios.Controller.DTO.UserDTO;
import dev.dulciobernardo7.CadastroDeFuncionarios.Entity.User;
import dev.dulciobernardo7.CadastroDeFuncionarios.Service.UserService;
import dev.dulciobernardo7.CadastroDeFuncionarios.mapper.UserMapper;
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
public class AuthViewController {

    private final UserService userService;

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "E-mail ou senha inválidos.");
        }
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("usuario", new UserDTO(null, "", "", ""));
        return "register";
    }

    @PostMapping("/register")
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
