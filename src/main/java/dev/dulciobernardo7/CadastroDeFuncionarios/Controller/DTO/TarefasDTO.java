package dev.dulciobernardo7.CadastroDeFuncionarios.Controller.DTO;

import dev.dulciobernardo7.CadastroDeFuncionarios.Entity.*;
import lombok.Builder;
import java.util.List;

@Builder
public record TarefasDTO (
    Long id,
    String nomeDaTarefa,
    String dificuldade,
    List<PessoaModel> pessoas){

    public TarefasDTO() {
        this(null, null, null, null);
    }
}