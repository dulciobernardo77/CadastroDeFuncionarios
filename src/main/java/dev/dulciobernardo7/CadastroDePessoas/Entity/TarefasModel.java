package dev.dulciobernardo7.CadastroDePessoas.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.dulciobernardo7.CadastroDePessoas.Entity.PessoaModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tb_tarefas")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TarefasModel {

    //Atributos da classe tarefa

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nomedatarefas")
    private String nomeDaTarefa;

    @Column(name = "dificuldade")
    private String dificuldade;

    //OneToMany - uma tarefa pode ter varias Funcionarios

    @OneToMany(mappedBy = "tarefa")
    @JsonIgnore
    private List<PessoaModel> pessoas;

}