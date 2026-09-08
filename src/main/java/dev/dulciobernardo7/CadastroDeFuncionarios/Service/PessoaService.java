package dev.dulciobernardo7.CadastroDeFuncionarios.Service;

import dev.dulciobernardo7.CadastroDeFuncionarios.Controller.DTO.PessoaDTO;
import dev.dulciobernardo7.CadastroDeFuncionarios.Entity.PessoaModel;
import dev.dulciobernardo7.CadastroDeFuncionarios.mapper.PessoaMapper;
import dev.dulciobernardo7.CadastroDeFuncionarios.Repository.PessoasRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PessoaService {
    private final PessoasRepository pessoasRepository;
    private final PessoaMapper pessoaMapper;

    public PessoaService(PessoasRepository pessoasRepository, PessoaMapper pessoaMapper) {
        this.pessoasRepository = pessoasRepository;
        this.pessoaMapper = pessoaMapper;
    }

    //Metodo para lista todo o pessoas
    public List<PessoaDTO> ListatodasPessoas(){
        List<PessoaModel> pessoaModelList = pessoasRepository.findAll();
        return  pessoaModelList.stream()
        .map(pessoaMapper::map).collect(Collectors.toList());
    }

    //Metodo para lista todo as pessoas pelo id
    public PessoaDTO ListatodasPessoasporId(Long id){
      Optional<PessoaModel> pessoaModel = pessoasRepository.findById(id);
      return  pessoaModel.map(pessoaMapper::map)
              .orElse(null);
    }

    //Metodo para cadastro de fucionario
    public PessoaDTO cadastroDeFuncionario(PessoaDTO pessoaDTO){
        PessoaModel pessoaModel =  pessoaMapper.map(pessoaDTO);
        pessoaModel =   pessoasRepository.save(pessoaModel);
        return  pessoaMapper.map(pessoaModel);
    }

    //Metodo para eliminar  fucionario
    public void ExcluirPessoaPorId(Long id){
        pessoasRepository.deleteById(id);
    }

    // Metodo para Alterar dados dos funcionarios (UPDATE)
    public  PessoaDTO Atualizarfuncionario(Long id, PessoaDTO pessoaDTO){
        Optional<PessoaModel> pessoaExistente = pessoasRepository.findById(id);
        if (pessoaExistente.isPresent()){
            PessoaModel pessoaAtualizada = pessoaMapper.map(pessoaDTO);
            pessoaAtualizada.setId(id);
            PessoaModel pessoaSalva = pessoasRepository.save(pessoaAtualizada);
            return pessoaMapper.map(pessoaSalva) ;
        }
        return null;
    }
}
