package dev.dulciobernardo7.CadastroDePessoas.Controller;

import dev.dulciobernardo7.CadastroDePessoas.Controller.DTO.PessoaDTO;
import dev.dulciobernardo7.CadastroDePessoas.Service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
@Tag(name = "Pessoas", description = "Operações para cadastro, consulta, atualização e remoção de pessoas")
public class PessoaController {
    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping("/boavindas")
    @Operation(summary = "Mensagem de boas-vindas", description = "Retorna uma mensagem inicial da API de cadastro de pessoas.")
    public String boaVindas() {
        return "Seja bem vindo na minha aplicao backend de cadastro de pessoas";
    }

    @PostMapping("/cadastrar")
    @Operation(summary = "Cadastrar pessoa", description = "Cria um novo cadastro de pessoa no sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Pessoa cadastrada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para cadastro.")
    })
    public ResponseEntity<String> cadastraPessoa(@RequestBody PessoaDTO pessoaDTO) {
        PessoaDTO pessoaDTO1 = pessoaService.cadastroDeFuncionario(pessoaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Funcionario Cadastrado" + pessoaDTO1.getNome() + " com Sucesso");
    }

    @GetMapping("/lista")
    @Operation(summary = "Listar pessoas", description = "Retorna todas as pessoas cadastradas.")
    @ApiResponse(responseCode = "200", description = "Lista de pessoas retornada com sucesso.")
    public ResponseEntity<List<PessoaDTO>> todasPessoas() {
        List<PessoaDTO> pessoaDTOList = pessoaService.ListatodasPessoas();
        return ResponseEntity.ok(pessoaDTOList);
    }

    @GetMapping("/lista/{number}")
    @Operation(summary = "Buscar pessoa por ID", description = "Consulta uma pessoa específica pelo identificador.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pessoa encontrada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada.")
    })
    public ResponseEntity<String> mostrarTodasPessoasPorId(
            @Parameter(name = "id", description = "ID da pessoa", required = true, in = ParameterIn.PATH)
            @PathVariable Long id) {

        PessoaDTO pessoaDTO = pessoaService.ListatodasPessoasporId(id);
        if (pessoaDTO != null) {
            return ResponseEntity.ok("Funcionario com o IDs " + id + " encontrado: " + pessoaDTO.getNome());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O Funcionario com IDs " + id + " Nao encontrado");
        }
    }

    @PutMapping("/alterar/{id}")
    @Operation(summary = "Atualizar pessoa", description = "Atualiza os dados de uma pessoa existente pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pessoa atualizada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada para atualização.")
    })
    public ResponseEntity<?> AlteraPorId(
            @Parameter(name = "id", description = "ID da pessoa a ser atualizada", required = true, in = ParameterIn.PATH)
            @PathVariable Long id,
            @RequestBody PessoaDTO pessoa) {
        PessoaDTO pessoaDTO = pessoaService.Atualizarfuncionario(id, pessoa);
        if (pessoaDTO != null) {
            return ResponseEntity.ok(pessoaDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O Funcionario com IDs " + id + " Nao encontrado");
        }
    }

    @DeleteMapping("/deletar/{number}")
    @Operation(summary = "Excluir pessoa", description = "Remove uma pessoa do sistema pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pessoa excluída com sucesso."),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada para exclusão.")
    })
    public ResponseEntity<String> ExcluirPessoaPorId(
            @Parameter(name = "id", description = "ID da pessoa a ser excluída", required = true, in = ParameterIn.PATH)
            @PathVariable Long id) {
        if (pessoaService.ListatodasPessoasporId(id) != null) {
            pessoaService.ExcluirPessoaPorId(id);
            return ResponseEntity.ok("O funcionario  com o IDs " + id + " Excluido.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O Funcionario com IDs " + id + " Nao encontrado");
        }
    }
}