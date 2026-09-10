package dev.dulciobernardo7.CadastroDeFuncionarios.Controller;


import dev.dulciobernardo7.CadastroDeFuncionarios.Controller.DTO.PessoaDTO;
import dev.dulciobernardo7.CadastroDeFuncionarios.Service.PessoaService;
import dev.dulciobernardo7.CadastroDeFuncionarios.Service.TarefasService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/pessoas/ui")
public class PessoaControllerUI {

    private final PessoaService pessoaService;
    private final TarefasService tarefasService;

    public PessoaControllerUI(PessoaService pessoaService, TarefasService tarefasService) {
        this.pessoaService = pessoaService;
        this.tarefasService = tarefasService;
    }

    @GetMapping("/lista")
    public String TodasPessoas(Model model){
        List<PessoaDTO> pessoaDTOList = pessoaService.ListatodasPessoas();
        model.addAttribute("pessoas",pessoaDTOList);
        return "listaPessoas";
    }

    @GetMapping("/deletar/{number}")
    public String ExcluirPessoaPorId(@PathVariable Long number ){
        pessoaService.ExcluirPessoaPorId(number);
        return "redirect:/pessoas/ui/lista";
    }

    @GetMapping("/lista/{number}")
    public String MostrarTodasPessoasPorId(@PathVariable Long number, Model model){
        PessoaDTO pessoaDTO =  pessoaService.ListatodasPessoasporId(number);
        if (pessoaDTO != null){
            model.addAttribute("pessoas",pessoaDTO);
            return "detalhesPessoas";
        }else {
            model.addAttribute("pessoas",pessoaDTO);
            return "listaPessoas";

        }
    }

    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionarFuncionario(Model model) {
        model.addAttribute("pessoas",new PessoaDTO());
        model.addAttribute("tarefas", tarefasService.ListatodasTarefas());
        return "adicionarPessoas";
    }

    @GetMapping("/altera/{number}")
    public String mostrarFormularioAlteradoFuncionario(@PathVariable Long number, Model model) {
        PessoaDTO pessoaDTO = pessoaService.ListatodasPessoasporId(number);
        if (pessoaDTO == null) {
            return "redirect:/pessoas/ui/lista";
        }

        model.addAttribute("pessoas", pessoaDTO);
        model.addAttribute("tarefas", tarefasService.ListatodasTarefas());
        return "alterarPessoas";
    }

    @PostMapping("/alterar/{number}")
    public String alterarPessoa(@PathVariable Long number,
                                @ModelAttribute PessoaDTO pessoaDTO,
                                RedirectAttributes redirectAttributes) {
        try {
            pessoaService.Atualizarfuncionario(number, pessoaDTO);
            redirectAttributes.addFlashAttribute("mensagem", "Funcionario alterado com sucesso!");
        } catch (IllegalArgumentException exception) {
            redirectAttributes.addFlashAttribute("mensagem", exception.getMessage());
        }
        return "redirect:/pessoas/ui/lista";
    }
}
