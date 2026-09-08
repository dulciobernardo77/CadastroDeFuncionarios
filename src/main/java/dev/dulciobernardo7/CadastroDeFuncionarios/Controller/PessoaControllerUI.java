package dev.dulciobernardo7.CadastroDeFuncionarios.Controller;


import dev.dulciobernardo7.CadastroDeFuncionarios.Controller.DTO.PessoaDTO;
import dev.dulciobernardo7.CadastroDeFuncionarios.Service.PessoaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/pessoas/ui")
public class PessoaControllerUI {

    private final PessoaService pessoaService;

    public PessoaControllerUI(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
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
        model.addAttribute("pessoas", new PessoaDTO());
        return "adicionarPessoas";
    }

    @PostMapping("/salvar")
    public String salvarPessoa(@ModelAttribute PessoaDTO pessoaDTO, RedirectAttributes redirectAttributes) {
        pessoaService.cadastroDeFuncionario(pessoaDTO);
        redirectAttributes.addFlashAttribute("mensagem", "Funcionario cadastrado com sucesso!");
        return "redirect:/pessoas/ui/lista";
    }
}
