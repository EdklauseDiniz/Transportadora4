package br.ifpe.jaboatao.transportadora2.web.controller;

import br.ifpe.jaboatao.transportadora2.model.Cliente;
import br.ifpe.jaboatao.transportadora2.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("clientes", service.listar());
        return "clientes-list";
    }

    @GetMapping("/buscar")
    public String buscar(@RequestParam(required = false) String tipo,
                @RequestParam(required = false) String valor,
                Model model) {
        List<Cliente> clientes;
        
        if (tipo != null && valor != null && !valor.trim().isEmpty()) {
            if ("nome".equals(tipo)) {
                clientes = service.buscarPorNome(valor);
            } else if ("documento".equals(tipo)) {
                clientes = service.buscarPorDocumento(valor);
            } else {
                clientes = service.listar();
            }
        } else {
            clientes = service.listar();
        }
        
        model.addAttribute("clientes", clientes);
        return "clientes-list";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cliente-form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Cliente cliente) {
        service.salvar(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Cliente cliente = service.buscar(id);
        if (cliente == null) {
            return "redirect:/clientes";
        }
        model.addAttribute("cliente", cliente);
        return "cliente-form";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        service.excluir(id);
        return "redirect:/clientes";
    }
}