package br.com.fiap.eduardo555933.controller;

import br.com.fiap.eduardo555933.dto.BairroDTO;
import br.com.fiap.eduardo555933.service.BairroService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bairros")
@RequiredArgsConstructor
public class BairroController {
    private final BairroService bairroService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("bairros", bairroService.listar());
        return "bairros/lista";
    }

    @GetMapping("/novo")
    public String novo (Model model) {
        model.addAttribute("bairro", new BairroDTO());
        return "bairros/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("bairro") BairroDTO dto, BindingResult result) {
        if(result.hasErrors()) {
            return "bairros/form";
        }

        if(dto.getId() == null) {
            bairroService.salvar(dto);
        } else {
            bairroService.atualizar(dto.getId(), dto);
        }

        return "redirect:/bairros";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("bairro", bairroService.buscarPorId(id));
        return "bairros/form";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, org.springframework.web.servlet.mvc.support.RedirectAttributes redirectAttributes) {
        try {
            bairroService.excluir(id);
            redirectAttributes.addFlashAttribute("sucesso", "Bairro excluido com sucesso");
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
        }

        return "redirect:/bairros";
    }
}
