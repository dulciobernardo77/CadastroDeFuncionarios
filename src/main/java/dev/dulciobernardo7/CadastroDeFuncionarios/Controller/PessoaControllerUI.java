package dev.dulciobernardo7.CadastroDeFuncionarios.Controller;


import dev.dulciobernardo7.CadastroDeFuncionarios.Controller.DTO.PessoaDTO;
import dev.dulciobernardo7.CadastroDeFuncionarios.Service.PessoaService;
import dev.dulciobernardo7.CadastroDeFuncionarios.Service.TarefasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/pessoas/ui")
@Tag(name = "Pessoas - interface web", description = "Páginas web para consulta e gerenciamento de pessoas")
public class PessoaControllerUI {

    private final PessoaService pessoaService;
    private final TarefasService tarefasService;

    public PessoaControllerUI(PessoaService pessoaService, TarefasService tarefasService) {
        this.pessoaService = pessoaService;
        this.tarefasService = tarefasService;
    }

    @GetMapping("/lista")
    @Operation(summary = "Exibir lista de pessoas", description = "Carrega a lista de pessoas cadastradas na interface web.")
    @ApiResponse(responseCode = "200", description = "Página HTML com a lista de pessoas.", content = @Content(mediaType = "text/html"))
    public String TodasPessoas(Model model){
        List<PessoaDTO> pessoaDTOList = pessoaService.listatodasPessoas();
        model.addAttribute("pessoas",pessoaDTOList);
        return "listaPessoas";
    }

    @GetMapping("/deletar/{number}")
    @Operation(summary = "Excluir pessoa pela interface", description = "Exclui a pessoa informada e retorna à lista.")
    @ApiResponse(responseCode = "302", description = "Redirecionamento para /pessoas/ui/lista.")
    public String ExcluirPessoaPorId(@PathVariable Long number ){
        pessoaService.excluirPessoaPorId(number);
        return "redirect:/pessoas/ui/lista";
    }

    @GetMapping("/lista/{number}")
    @Operation(summary = "Exibir detalhes da pessoa", description = "Carrega os detalhes da pessoa; se não existir, retorna à página de listagem.")
    @ApiResponse(responseCode = "200", description = "Página HTML com os detalhes ou a lista de pessoas.", content = @Content(mediaType = "text/html"))
    public String MostrarTodasPessoasPorId(@PathVariable Long number, Model model){
        PessoaDTO pessoaDTO =  pessoaService.listatodasPessoasporId(number);
        if (pessoaDTO != null){
            model.addAttribute("pessoas",pessoaDTO);
            return "detalhesPessoas";
        }else {
            model.addAttribute("pessoas",pessoaDTO);
            return "listaPessoas";

        }
    }

    @GetMapping("/adicionar")
    @Operation(summary = "Exibir formulário de pessoa", description = "Carrega o formulário de cadastro e as tarefas disponíveis.")
    @ApiResponse(responseCode = "200", description = "Página HTML com o formulário de cadastro.", content = @Content(mediaType = "text/html"))
    public String mostrarFormularioAdicionarFuncionario(Model model) {
        model.addAttribute("pessoas",new PessoaDTO());
        model.addAttribute("tarefas", tarefasService.listatodasTarefas());
        return "adicionarPessoas";
    }

    @GetMapping("/altera/{number}")
    @Operation(summary = "Exibir formulário de edição", description = "Carrega o formulário de edição da pessoa informada.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Página HTML com o formulário de edição.", content = @Content(mediaType = "text/html")),
            @ApiResponse(responseCode = "302", description = "Pessoa inexistente; redirecionamento para a lista.")
    })
    public String mostrarFormularioAlteradoFuncionario(@PathVariable Long number, Model model) {
        PessoaDTO pessoaDTO = pessoaService.listatodasPessoasporId(number);
        if (pessoaDTO == null) {
            return "redirect:/pessoas/ui/lista";
        }

        model.addAttribute("pessoas", pessoaDTO);
        model.addAttribute("tarefas", tarefasService.listatodasTarefas());
        return "alterarPessoas";
    }

    @PostMapping("/alterar/{number}")
    @Operation(summary = "Alterar pessoa pela interface", description = "Atualiza os dados enviados pelo formulário e retorna à lista.")
    @ApiResponse(responseCode = "302", description = "Redirecionamento para /pessoas/ui/lista com o resultado da operação.")
    public String alterarPessoa(@PathVariable Long number,
                                @ModelAttribute PessoaDTO pessoaDTO,
                                RedirectAttributes redirectAttributes) {
        try {
            pessoaService.atualizarFuncionario(number, pessoaDTO);
            redirectAttributes.addFlashAttribute("mensagem", "Funcionario alterado com sucesso!");
        } catch (IllegalArgumentException exception) {
            redirectAttributes.addFlashAttribute("mensagem", exception.getMessage());
        }
        return "redirect:/pessoas/ui/lista";
    }
}
