package br.ifpe.jaboatao.transportadora2.web.controller;

import br.ifpe.jaboatao.transportadora2.model.Motorista;
import br.ifpe.jaboatao.transportadora2.service.MotoristaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/motoristas")
public class MotoristaController {

    private final MotoristaService service;

    public MotoristaController(MotoristaService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("motoristas", service.listar());
        return "motoristas-list";
    }

    @GetMapping("/buscar")
    public String buscar(@RequestParam(required = false) String tipo,
                         @RequestParam(required = false) String valor,
                         Model model) {
        List<Motorista> motoristas;
        
        if (tipo != null && valor != null && !valor.trim().isEmpty()) {
            if ("nome".equals(tipo)) {
                motoristas = service.buscarPorNome(valor);
            } else if ("cnh".equals(tipo)) {
                motoristas = service.buscarPorCnh(valor);
            } else {
                motoristas = service.listar();
            }
        } else {
            motoristas = service.listar();
        }
        
        model.addAttribute("motoristas", motoristas);
        return "motoristas-list";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("motorista", new Motorista());
        return "motorista-form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Motorista motorista) {
        service.salvar(motorista);
        return "redirect:/motoristas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Motorista motorista = service.buscar(id);
        if (motorista == null) {
            return "redirect:/motoristas";
        }
        model.addAttribute("motorista", motorista);
        return "motorista-form";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        service.excluir(id);
        return "redirect:/motoristas";
    }
}