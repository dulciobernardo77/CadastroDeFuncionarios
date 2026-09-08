package dev.dulciobernardo7.CadastroDeFuncionarios.Entity;

import jakarta.persistence.*;
import lombok.*;


//Entity ele transforma uma classe em entidade do banco de dados
@Entity
@Table(name = "tb_Cadastro_de_Pessoas")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "tarefa")
public class PessoaModel {

     //Atributos de uma pessoa
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @Column(name = "nome")
    private String nome ;

    @Column(name = "idade")
    private   int idade;

    @Column(name = "nacionalidade")
    private String nacionalidade;

    @Column(name = "bi")
    private String bi;

    @Column(name = "sexo")
    private char sexo;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String telefone;

    @Column(name = "nivel")
    private String nivel;

    @Column(name = "imgUrl")
    private String imgUrl;

    //@ManyToOne - um pessoa tem uma unica tarefa
    @ManyToOne
    @JoinColumn(name = "tarefas_id")//foreing Key ou chave estrangeira
    private  TarefasModel tarefa;

}