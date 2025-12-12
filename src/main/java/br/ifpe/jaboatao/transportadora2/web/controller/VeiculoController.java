package br.ifpe.jaboatao.transportadora2.web.controller;

import br.ifpe.jaboatao.transportadora2.model.Veiculo;
import br.ifpe.jaboatao.transportadora2.service.VeiculoService;
import br.ifpe.jaboatao.transportadora2.service.MotoristaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoService veiculoService;
    private final MotoristaService motoristaService;

    public VeiculoController(VeiculoService veiculoService, MotoristaService motoristaService) {
        this.veiculoService = veiculoService;
        this.motoristaService = motoristaService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("veiculos", veiculoService.listar());
        return "veiculos-list";
    }

    @GetMapping("/buscar")
    public String buscar(@RequestParam(required = false) String tipo,
                    @RequestParam(required = false) String valor,
                    Model model) {
        List<Veiculo> veiculos;
        
        if (tipo != null && valor != null && !valor.trim().isEmpty()) {
            if ("placa".equals(tipo)) {
                veiculos = veiculoService.buscarPorPlaca(valor);
            } else if ("marca".equals(tipo)) {
                veiculos = veiculoService.buscarPorMarca(valor);
            } else {
                veiculos = veiculoService.listar();
            }
        } else {
            veiculos = veiculoService.listar();
        }
        
        model.addAttribute("veiculos", veiculos);
        return "veiculos-list";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("veiculo", new Veiculo());
        model.addAttribute("motoristas", motoristaService.listar());
        return "veiculo-form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Veiculo veiculo) {
        veiculoService.salvar(veiculo);
        return "redirect:/veiculos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Veiculo veiculo = veiculoService.buscar(id);
        if (veiculo == null) {
            return "redirect:/veiculos";
        }
        model.addAttribute("veiculo", veiculo);
        model.addAttribute("motoristas", motoristaService.listar());
        return "veiculo-form";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        veiculoService.excluir(id);
        return "redirect:/veiculos";
    }
}