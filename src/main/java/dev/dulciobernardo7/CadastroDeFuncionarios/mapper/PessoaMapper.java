package dev.dulciobernardo7.CadastroDeFuncionarios.mapper;

import dev.dulciobernardo7.CadastroDeFuncionarios.Controller.DTO.*;
import dev.dulciobernardo7.CadastroDeFuncionarios.Entity.*;
import org.springframework.stereotype.Component;


@Component
public class PessoaMapper {

    public PessoaModel map(PessoaDTO pessoaDTO){
        return PessoaModel
                .builder()
                .id(pessoaDTO.id())
                .nome(pessoaDTO.nome())
                .idade(pessoaDTO.idade())
                .nacionalidade(pessoaDTO.nacionalidade())
                .bi(pessoaDTO.bi())
                .sexo(pessoaDTO.sexo())
                .email(pessoaDTO.email())
                .telefone(pessoaDTO.telefone())
                .nivel(pessoaDTO.nivel())
                .imgUrl(pessoaDTO.imgUrl())
                .tarefa(pessoaDTO.tarefa())
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
