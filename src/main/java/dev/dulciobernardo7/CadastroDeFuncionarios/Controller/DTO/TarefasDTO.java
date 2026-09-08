package dev.dulciobernardo7.CadastroDePessoas.Controller.DTO;

import dev.dulciobernardo7.CadastroDePessoas.Entity.PessoaModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarefasDTO {

    private Long id;
    private String nomeDaTarefa;
    private String dificuldade;
    private List<PessoaModel> pessoas;

}
