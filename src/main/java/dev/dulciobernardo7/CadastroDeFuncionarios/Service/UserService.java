package dev.dulciobernardo7.CadastroDeFuncionarios.Service;

import dev.dulciobernardo7.CadastroDeFuncionarios.Entity.User;
import dev.dulciobernardo7.CadastroDeFuncionarios.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public User Save(User user) {
        if (user.getSenha() == null || user.getSenha().isBlank()) {
            throw new IllegalArgumentException("Senha obrigatória");
        }
        user.setSenha(passwordEncoder.encode(user.getSenha()));
        return repository.save(user);
    }
}
