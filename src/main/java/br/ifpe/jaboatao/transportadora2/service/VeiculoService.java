package br.ifpe.jaboatao.transportadora2.service;

import br.ifpe.jaboatao.transportadora2.model.Veiculo;
import br.ifpe.jaboatao.transportadora2.repository.VeiculoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VeiculoService {
    private final VeiculoRepository repo;

    public VeiculoService(VeiculoRepository repo) {
        this.repo = repo;
    }

    public List<Veiculo> listar() {
        return repo.findAll();
    }

    public Veiculo salvar(Veiculo v) {
        return repo.save(v);
    }

    public Veiculo buscar(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        repo.deleteById(id);
    }

    public List<Veiculo> buscarPorPlaca(String placa) {
        return repo.findByPlacaContainingIgnoreCase(placa);
    }

    public List<Veiculo> buscarPorMarca(String marca) {
        return repo.findByMarcaContainingIgnoreCase(marca);
    }
}