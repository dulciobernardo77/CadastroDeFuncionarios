package dev.dulciobernardo7.CadastroDeFuncionarios.mapper;


import dev.dulciobernardo7.CadastroDeFuncionarios.Controller.DTO.*;
import dev.dulciobernardo7.CadastroDeFuncionarios.Entity.*;
import org.springframework.stereotype.Component;

@Component
public class TarefasMapper {

    public TarefasModel map(TarefasDTO tarefasDTO){
        return TarefasModel
                .builder()
                .id(tarefasDTO.id())
                .nomeDaTarefa(tarefasDTO.nomeDaTarefa())
                .dificuldade(tarefasDTO.dificuldade())
                .pessoas(tarefasDTO.pessoas())
                .build();
    }

    public TarefasDTO map(TarefasModel tarefasModel){

        return  TarefasDTO
                .builder()
                .id(tarefasModel.getId())
                .nomeDaTarefa(tarefasModel.getNomeDaTarefa())
                .dificuldade(tarefasModel.getDificuldade())
                .pessoas(tarefasModel.getPessoas())
                .build();

    }
}
