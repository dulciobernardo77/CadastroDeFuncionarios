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
    public List<PessoaDTO> listatodasPessoas(){
        List<PessoaModel> pessoaModelList = pessoasRepository.findAll();
        return  pessoaModelList.stream()
        .map(pessoaMapper::map).collect(Collectors.toList());
    }

    //Metodo para lista todo as pessoas pelo id
    public PessoaDTO listatodasPessoasporId(Long id){
      Optional<PessoaModel> pessoaModel = pessoasRepository.findById(id);
      return  pessoaModel.map(pessoaMapper::map)
              .orElse(null);
    }

    //Metodo para cadastro de fucionario
    public PessoaDTO cadastroDeFuncionario(PessoaDTO pessoaDTO){
        if (pessoaDTO.bi() != null && pessoasRepository.existsByBi(pessoaDTO.bi())) {
            throw new IllegalArgumentException("Já existe um funcionário com este BI.");
        }

        PessoaModel pessoaModel =  pessoaMapper.map(pessoaDTO);
        pessoaModel =   pessoasRepository.save(pessoaModel);
        return  pessoaMapper.map(pessoaModel);
    }

    //Metodo para eliminar  fucionario
    public void excluirPessoaPorId(Long id){
        pessoasRepository.deleteById(id);
    }

    // Metodo para Alterar dados dos funcionarios (UPDATE)
    public PessoaDTO atualizarFuncionario(Long id, PessoaDTO pessoaDTO) {

        Optional<PessoaModel> pessoaExistente = pessoasRepository.findById(id);

        if (pessoaExistente.isEmpty()) {
            return null;
        }

        PessoaModel pessoa = pessoaExistente.get();

        if (pessoaDTO.bi() != null &&
                pessoasRepository.existsByBiAndIdNot(pessoaDTO.bi(), id)) {

            throw new IllegalArgumentException(
                    "Já existe outro funcionário com este BI."
            );
        }

        if (pessoaDTO.nome() != null) {
            pessoa.setNome(pessoaDTO.nome());
        }

        if (pessoaDTO.idade() != null) {
            pessoa.setIdade(pessoaDTO.idade());
        }

        if (pessoaDTO.nacionalidade() != null) {
            pessoa.setNacionalidade(pessoaDTO.nacionalidade());
        }

        if (pessoaDTO.bi() != null) {
            pessoa.setBi(pessoaDTO.bi());
        }

        if (pessoaDTO.sexo() != null) {
            pessoa.setSexo(pessoaDTO.sexo());
        }

        if (pessoaDTO.email() != null) {
            pessoa.setEmail(pessoaDTO.email());
        }

        if (pessoaDTO.telefone() != null) {
            pessoa.setTelefone(pessoaDTO.telefone());
        }

        if (pessoaDTO.nivel() != null) {
            pessoa.setNivel(pessoaDTO.nivel());
        }

        if (pessoaDTO.imgUrl() != null) {
            pessoa.setImgUrl(pessoaDTO.imgUrl());
        }

        if (pessoaDTO.tarefa() != null) {
            pessoa.setTarefa(pessoaDTO.tarefa());
        }

        PessoaModel pessoaSalva = pessoasRepository.save(pessoa);

        return pessoaMapper.map(pessoaSalva);
    }

}