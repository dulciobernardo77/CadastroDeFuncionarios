package dev.dulciobernardo7.CadastroDeFuncionarios.Controller.DTO;

import dev.dulciobernardo7.CadastroDeFuncionarios.Entity.*;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record PessoaDTO (
     Long id,
    @NotEmpty (message = "O nome do Funcionario e obrigatorio") String nome,
     Integer idade,
     String nacionalidade,
     String bi,
     Character sexo,
     String email,
     String telefone,
     String nivel,
     String imgUrl,
     TarefasModel tarefa) {

     public PessoaDTO() {
          this(null, null, 0, null, null, null, null, null, null, null, null);
     }
}
