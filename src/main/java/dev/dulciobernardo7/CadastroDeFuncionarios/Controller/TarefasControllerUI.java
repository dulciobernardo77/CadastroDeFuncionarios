package dev.dulciobernardo7.CadastroDePessoas.Controller;

import dev.dulciobernardo7.CadastroDePessoas.Controller.DTO.TarefasDTO;
import dev.dulciobernardo7.CadastroDePessoas.Service.TarefasService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/tarefas/ui")
public class TarefasControllerUI {
    private final TarefasService tarefasService;

    public TarefasControllerUI(TarefasService tarefasService) {
        this.tarefasService = tarefasService;
    }

    @GetMapping("/lista")
    public String listarTarefas(Model model) {
        List<TarefasDTO> tarefasDTOS = tarefasService.ListatodasTarefas();
        model.addAttribute("tarefas", tarefasDTOS);
        return "listaTarefas";
    }

    @GetMapping("/deletar/{number}")
    public String excluirTarefaPorId(@PathVariable Long number) {
        tarefasService.ExcluirTarefasPorId(number);
        return "redirect:/tarefas/ui/lista";
    }

    @GetMapping("/lista/{number}")
    public String mostrarTarefaPorId(@PathVariable Long number, Model model) {
        TarefasDTO tarefasDTO = tarefasService.ListatodasTarefasPorId(number);
        if (tarefasDTO != null) {
            model.addAttribute("tarefas", tarefasDTO);
            return "detalhesTarefas";
        }

        model.addAttribute("tarefas", null);
        return "listaTarefas";
    }

    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionarTarefa(Model model) {
        model.addAttribute("tarefas", new TarefasDTO());
        return "adicionarTarefas";
    }

    @PostMapping("/salvar")
    public String salvarTarefa(@ModelAttribute TarefasDTO tarefasDTO, RedirectAttributes redirectAttributes) {
        tarefasService.cadastroDeTarefas(tarefasDTO);
        redirectAttributes.addFlashAttribute("mensagem", "Tarefa cadastrada com sucesso!");
        return "redirect:/tarefas/ui/lista";
    }
}
