package dev.dulciobernardo7.CadastroDePessoas.Controller.DTO;

import dev.dulciobernardo7.CadastroDePessoas.Entity.TarefasModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO {

    private Long id;
    private String nome ;
    private   int idade;
    private String nacionalidade;
    private String bi;
    private char sexo;
    private String email;
    private String telefone;
    private String nivel;
    private String imgUrl;
    private TarefasModel tarefa;
}
