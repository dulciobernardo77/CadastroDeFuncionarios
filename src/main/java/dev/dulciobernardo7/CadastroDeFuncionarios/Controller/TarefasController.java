package dev.dulciobernardo7.CadastroDeFuncionarios.Controller;

import dev.dulciobernardo7.CadastroDeFuncionarios.Controller.DTO.*;
import dev.dulciobernardo7.CadastroDeFuncionarios.Service.TarefasService;
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
@RequestMapping("/tarefas")
@Tag(name = "Tarefas", description = "Operações para gerenciamento de tarefas")
public class TarefasController {

    private final TarefasService tarefasService;

    public TarefasController(TarefasService tarefasService) {
        this.tarefasService = tarefasService;
    }

    @PostMapping("/cadastrar")
    @Operation(summary = "Cadastrar tarefa", description = "Cria uma nova tarefa no sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Tarefa cadastrada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para o cadastro.")
    })
    public ResponseEntity<String> CadastraPessoa(@RequestBody TarefasDTO tarefasDTO) {
        TarefasDTO tarefasDTO1 = tarefasService.cadastroDeTarefas(tarefasDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Tarefa  Cadastrado: " + tarefasDTO1.getNomeDaTarefa() + " com Sucesso");
    }

    @GetMapping("/lista")
    @Operation(summary = "Listar tarefas", description = "Retorna todas as tarefas cadastradas.")
    @ApiResponse(responseCode = "200", description = "Lista de tarefas retornada com sucesso.")
    public ResponseEntity<List<TarefasDTO>> TodasPessoas() {
        List<TarefasDTO> tarefasDTOS = tarefasService.ListatodasTarefas();
        return ResponseEntity.ok(tarefasDTOS);
    }

    @GetMapping("/lista/{number}")
    @Operation(summary = "Buscar tarefa por ID", description = "Consulta uma tarefa específica pelo identificador.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarefa encontrada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada.")
    })
    public ResponseEntity<String> MostrarTodasPessoasPorId(
            @Parameter(name = "id", description = "ID da tarefa", required = true, in = ParameterIn.PATH)
            @PathVariable Long id) {
        TarefasDTO tarefasDTO = tarefasService.ListatodasTarefasPorId(id);
        if (tarefasDTO != null) {
            return ResponseEntity.ok("Tarefa  encontrado: " + tarefasDTO.getNomeDaTarefa());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O ninja com IDs " + id + " Nao encontrado");
        }
    }

    @PutMapping("/altera/{number}")
    @Operation(summary = "Atualizar tarefa", description = "Atualiza os dados de uma tarefa existente pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada para atualização.")
    })
    public ResponseEntity<?> AlteraPorId(
            @Parameter(name = "id", description = "ID da tarefa a ser atualizada", required = true, in = ParameterIn.PATH)
            @PathVariable Long id,
            @RequestBody TarefasDTO tarefas) {
        TarefasDTO tarefasDTO = tarefasService.AtualizarTarefas(id, tarefas);
        if (tarefasDTO != null) {
            return ResponseEntity.ok(tarefasDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O ninja com IDs " + id + " Nao encontrado");
        }
    }

    @DeleteMapping("/Deletar/{number}")
    @Operation(summary = "Excluir tarefa", description = "Remove uma tarefa do sistema pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarefa excluída com sucesso."),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada para exclusão.")
    })
    public ResponseEntity<String> ExcluirPessoaPorId(
            @Parameter(name = "id", description = "ID da tarefa a ser excluída", required = true, in = ParameterIn.PATH)
            @PathVariable Long id) {
        if (tarefasService.ListatodasTarefasPorId(id) != null) {
            tarefasService.ExcluirTarefasPorId(id);
            return ResponseEntity.ok("O funcionario  com o IDs " + id + " Excluido.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O ninja com IDs " + id + " Nao encontrado");
        }
    }
}
