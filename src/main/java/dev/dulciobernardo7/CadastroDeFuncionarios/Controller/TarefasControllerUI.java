package dev.dulciobernardo7.CadastroDeFuncionarios.Controller;

import dev.dulciobernardo7.CadastroDeFuncionarios.Controller.DTO.TarefasDTO;
import dev.dulciobernardo7.CadastroDeFuncionarios.Service.TarefasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/tarefas/ui")
@Tag(name = "Tarefas - interface web", description = "Páginas web para consulta e gerenciamento de tarefas")
public class TarefasControllerUI {
    private final TarefasService tarefasService;

    public TarefasControllerUI(TarefasService tarefasService) {
        this.tarefasService = tarefasService;
    }

    @GetMapping("/lista")
    @Operation(summary = "Exibir lista de tarefas", description = "Carrega a lista de tarefas cadastradas na interface web.")
    @ApiResponse(responseCode = "200", description = "Página HTML com a lista de tarefas.", content = @Content(mediaType = "text/html"))
    public String listarTarefas(Model model) {
        List<TarefasDTO> tarefasDTOS = tarefasService.listatodasTarefas();
        model.addAttribute("tarefas", tarefasDTOS);
        return "listaTarefas";
    }

    @GetMapping("/deletar/{number}")
    @Operation(summary = "Excluir tarefa pela interface", description = "Exclui a tarefa informada e retorna à lista.")
    @ApiResponse(responseCode = "302", description = "Redirecionamento para /tarefas/ui/lista.")
    public String excluirTarefaPorId(@PathVariable Long number) {
        tarefasService.excluirTarefasPorId(number);
        return "redirect:/tarefas/ui/lista";
    }

    @GetMapping("/lista/{number}")
    @Operation(summary = "Exibir detalhes da tarefa", description = "Carrega os detalhes da tarefa ou a página de listagem quando ela não existe.")
    @ApiResponse(responseCode = "200", description = "Página HTML com os detalhes ou a lista de tarefas.", content = @Content(mediaType = "text/html"))
    public String mostrarTarefaPorId(@PathVariable Long number, Model model) {
        TarefasDTO tarefasDTO = tarefasService.listatodasTarefasPorId(number);
        if (tarefasDTO != null) {
            model.addAttribute("tarefas", tarefasDTO);
            return "detalhesTarefas";
        }

        model.addAttribute("tarefas", null);
        return "listaTarefas";
    }

    @GetMapping("/adicionar")
    @Operation(summary = "Exibir formulário de tarefa", description = "Carrega o formulário de cadastro de tarefa.")
    @ApiResponse(responseCode = "200", description = "Página HTML com o formulário de cadastro.", content = @Content(mediaType = "text/html"))
    public String mostrarFormularioAdicionarTarefa(Model model) {
        model.addAttribute("tarefas", new TarefasDTO());
        return "adicionarTarefas";
    }

    @PostMapping("/salvar")
    @Operation(summary = "Salvar tarefa pela interface", description = "Cadastra a tarefa enviada pelo formulário e retorna à lista.")
    @ApiResponse(responseCode = "302", description = "Redirecionamento para /tarefas/ui/lista com a confirmação do cadastro.")
    public String salvarTarefa(@ModelAttribute TarefasDTO tarefasDTO, RedirectAttributes redirectAttributes) {
        tarefasService.cadastroDeTarefas(tarefasDTO);
        redirectAttributes.addFlashAttribute("mensagem", "Tarefa cadastrada com sucesso!");
        return "redirect:/tarefas/ui/lista";
    }
}
