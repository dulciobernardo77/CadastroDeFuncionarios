package dev.dulciobernardo7.CadastroDePessoas.mapper;

import dev.dulciobernardo7.CadastroDePessoas.Controller.DTO.PessoaDTO;
import dev.dulciobernardo7.CadastroDePessoas.Entity.PessoaModel;
import org.springframework.stereotype.Component;


@Component
public class PessoaMapper {

    public PessoaModel map(PessoaDTO pessoaDTO){
        return PessoaModel
                .builder()
                .id(pessoaDTO.getId())
                .nome(pessoaDTO.getNome())
                .idade(pessoaDTO.getIdade())
                .nacionalidade(pessoaDTO.getNacionalidade())
                .bi(pessoaDTO.getBi())
                .sexo(pessoaDTO.getSexo())
                .email(pessoaDTO.getEmail())
                .telefone(pessoaDTO.getTelefone())
                .nivel(pessoaDTO.getNivel())
                .imgUrl(pessoaDTO.getImgUrl())
                .tarefa(pessoaDTO.getTarefa())
                .build();
    }

    public PessoaDTO map(PessoaModel pessoaModel){
        return PessoaDTO
                .builder()
                .id(pessoaModel.getId())
                .nome(pessoaModel.getNome())
                .idade(pessoaModel.getIdade())
                .nacionalidade(pessoaModel.getNacionalidade())
                .bi(pessoaModel.getBi())
                .sexo(pessoaModel.getSexo())
                .email(pessoaModel.getEmail())
                .telefone(pessoaModel.getTelefone())
                .nivel(pessoaModel.getNivel())
                .imgUrl(pessoaModel.getImgUrl())
                .tarefa(pessoaModel.getTarefa())
                .build();
    }
}
