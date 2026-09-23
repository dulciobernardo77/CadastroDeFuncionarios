package dev.dulciobernardo7.CadastroDeFuncionarios.Controller.DTO;

import dev.dulciobernardo7.CadastroDeFuncionarios.Entity.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import java.util.List;

@Builder
public record TarefasDTO (
    Long id,
   @NotEmpty (message = "O nome da tarefas  e obrigatorio") String nomeDaTarefa,
    String dificuldade,
    List<PessoaModel> pessoas){

    public TarefasDTO() {
        this(null, null, null, null);
    }
}