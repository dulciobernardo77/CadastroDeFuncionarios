package dev.dulciobernardo7.CadastroDeFuncionarios.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tb_tarefas")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "pessoas")
public class TarefasModel {

    //Atributos da classe tarefa

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_da_tarefa")
    private String nomeDaTarefa;

    @Column(name = "dificuldade")
    private String dificuldade;

    //OneToMany - uma tarefa pode ter varias Funcionarios

    @OneToMany(mappedBy = "tarefa")
    @JsonIgnore
    private List<PessoaModel> pessoas;

}