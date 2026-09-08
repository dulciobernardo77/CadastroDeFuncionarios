package dev.dulciobernardo7.CadastroDePessoas.mapper;


import dev.dulciobernardo7.CadastroDePessoas.Controller.DTO.TarefasDTO;
import dev.dulciobernardo7.CadastroDePessoas.Entity.TarefasModel;
import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Component;

@Component
public class TarefasMapper {

    public TarefasModel map(TarefasDTO tarefasDTO){
        return TarefasModel
                .builder()
                .id(tarefasDTO.getId())
                .nomeDaTarefa(tarefasDTO.getNomeDaTarefa())
                .dificuldade(tarefasDTO.getDificuldade())
                .pessoas(tarefasDTO.getPessoas())
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
