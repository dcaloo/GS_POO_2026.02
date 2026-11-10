package br.com.fiap.eduardo555933.controller;

import br.com.fiap.eduardo555933.dto.LeituraDTO;
import br.com.fiap.eduardo555933.service.LeituraService;
import br.com.fiap.eduardo555933.service.TrechoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/leituras")
@RequiredArgsConstructor
public class LeituraController {

    private final LeituraService leituraService;
    private final TrechoService trechoService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("leituras", leituraService.listar());
        model.addAttribute("trechos", trechoService.listar());
        return "leituras/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("leitura", new LeituraDTO());
        model.addAttribute("trechos", trechoService.listar());
        return "leituras/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("leitura") LeituraDTO dto, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("trechos", trechoService.listar());
            return "leituras/form";
        }

        if(dto.getId() == null) {
            leituraService.salvar(dto);
        } else {
            leituraService.atualizar(dto.getId(), dto);
        }

        return "redirect:/leituras";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("leitura", leituraService.buscarPorId(id));
        model.addAttribute("trechos", trechoService.listar());
        return "leituras/form";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        leituraService.excluir(id);
        redirectAttributes.addFlashAttribute("sucesso", "Leitura excluida com sucesso");

        return "redirect:/leituras";
    }
}
