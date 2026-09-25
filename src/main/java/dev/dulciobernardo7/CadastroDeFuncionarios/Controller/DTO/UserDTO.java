package dev.dulciobernardo7.CadastroDeFuncionarios.Controller.DTO;

import lombok.Builder;

@Builder
public record UserDTO(
        Long id,
        String nome,
        String email,
        String senha) {
}
