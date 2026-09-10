package dev.dulciobernardo7.CadastroDeFuncionarios.Controller.DTO;

import dev.dulciobernardo7.CadastroDeFuncionarios.Entity.*;

import lombok.Builder;

@Builder
public record PessoaDTO (
     Long id,
     String nome,
     int idade,
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
