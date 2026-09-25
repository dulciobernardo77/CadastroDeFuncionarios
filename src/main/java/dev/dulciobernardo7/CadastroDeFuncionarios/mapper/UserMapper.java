package dev.dulciobernardo7.CadastroDeFuncionarios.mapper;

import dev.dulciobernardo7.CadastroDeFuncionarios.Controller.DTO.UserDTO;
import dev.dulciobernardo7.CadastroDeFuncionarios.Entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static User map(UserDTO userDTO){
        return User
                .builder()
                .nome(userDTO.nome())
                .email(userDTO.email())
                .senha(userDTO.senha())
                .build();
    }

    public static UserDTO map(User user){
        return UserDTO
                .builder()
                .id(user.getId())
                .nome(user.getNome())
                .email(user.getEmail())
                .senha(user.getSenha())
                .build();
    }
}
