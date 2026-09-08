package dev.dulciobernardo7.CadastroDeFuncionarios.Service;


import dev.dulciobernardo7.CadastroDeFuncionarios.Controller.DTO.TarefasDTO;
import dev.dulciobernardo7.CadastroDeFuncionarios.Entity.TarefasModel;
import dev.dulciobernardo7.CadastroDeFuncionarios.Repository.TarefasRepository;
import dev.dulciobernardo7.CadastroDeFuncionarios.mapper.TarefasMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TarefasService {

    private final TarefasMapper tarefasMapper;
    private final TarefasRepository tarefasRepository;

    public TarefasService(TarefasRepository tarefasRepository, TarefasMapper tarefasMapper) {
        this.tarefasRepository = tarefasRepository;
        this.tarefasMapper = tarefasMapper;
    }


    //Metodo para lista todo as tarefas
    public List<TarefasDTO> ListatodasTarefas(){
        List<TarefasModel> tarefasModelList = tarefasRepository.findAll();
         return tarefasModelList.stream()
                 .map(tarefasMapper::map)
                 .collect(Collectors.toList());
    }

    //Metodo para lista todo as Tarefas pelo id
   public TarefasDTO ListatodasTarefasPorId(Long id){
        Optional<TarefasModel> tarefasModel = tarefasRepository.findById(id);
        return tarefasModel.map(tarefasMapper::map)
                .orElse(null);
    }
    //Metodo para cadastro de Tarefas
    public TarefasDTO cadastroDeTarefas(TarefasDTO tarefasDTO){
        TarefasModel tarefasModel = tarefasMapper.map(tarefasDTO);
        tarefasModel = tarefasRepository.save(tarefasModel);
        return tarefasMapper.map(tarefasModel);
    }


    // Metodo para Alterar dados das Tarefas (UPDATE)
    public TarefasDTO AtualizarTarefas(Long id ,TarefasDTO tarefasDTO){
        Optional<TarefasModel> tarefasExistente = tarefasRepository.findById(id);
        if (tarefasExistente.isPresent()){
            TarefasModel tarefasAtualizadas = tarefasMapper.map(tarefasDTO);
            tarefasAtualizadas.setId(id);
            TarefasModel tarefaSalvas = tarefasRepository.save(tarefasAtualizadas);
            return tarefasMapper.map(tarefaSalvas);
        }
        return null;
    }

    //Metodo para Deletar Tarefas (DELETE)
    public void ExcluirTarefasPorId(Long id){
        tarefasRepository.deleteById(id);
    }


}
