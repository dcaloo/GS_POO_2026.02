package br.com.fiap.eduardo555933.controller;

import br.com.fiap.eduardo555933.dto.TrechoDTO;
import br.com.fiap.eduardo555933.service.BairroService;
import br.com.fiap.eduardo555933.service.TrechoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/trechos")
@RequiredArgsConstructor
public class TrechoController {
    private final TrechoService trechoService;
    private final BairroService bairroService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("trechos", trechoService.listar());
        model.addAttribute("bairros", bairroService.listar());
        return "trechos/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("trecho", new TrechoDTO());
        model.addAttribute("bairros", bairroService.listar());
        return "trechos/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("trecho") TrechoDTO dto, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("bairros", bairroService.listar());
            return "trechos/form";
        }

        if(dto.getId() == null) {
            trechoService.salvar(dto);
        } else {
            trechoService.atualizar(dto.getId(), dto);
        }

        return "redirect:/trechos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("trecho", trechoService.buscarPorId(id));
        model.addAttribute("bairros", bairroService.listar());
        return "trechos/form";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            trechoService.excluir(id);
            redirectAttributes.addFlashAttribute("sucesso", "Trecho excluido com sucesso");
        } catch(IllegalStateException e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
        }

        return "redirect:/trechos";
    }
}
