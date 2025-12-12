package br.ifpe.jaboatao.transportadora2.service;

import br.ifpe.jaboatao.transportadora2.model.Motorista;
import br.ifpe.jaboatao.transportadora2.repository.MotoristaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MotoristaService {
    private final MotoristaRepository repo;

    public MotoristaService(MotoristaRepository repo) {
        this.repo = repo;
    }

    public List<Motorista> listar() {
        return repo.findAll();
    }

    public Motorista salvar(Motorista m) {
        return repo.save(m);
    }

    public Motorista buscar(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        repo.deleteById(id);
    }

    public List<Motorista> buscarPorNome(String nome) {
        return repo.findByNomeContainingIgnoreCase(nome);
    }

    public List<Motorista> buscarPorCnh(String cnh) {
        return repo.findByCnhContaining(cnh);
    }
}