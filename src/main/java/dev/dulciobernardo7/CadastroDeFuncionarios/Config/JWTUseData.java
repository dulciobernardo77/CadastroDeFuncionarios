package dev.dulciobernardo7.CadastroDeFuncionarios.Config;

import lombok.Builder;

@Builder
public record JWTUseData(Long id,String nome,String email) {
}
