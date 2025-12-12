package br.ifpe.jaboatao.transportadora2.service;

import br.ifpe.jaboatao.transportadora2.model.Cliente;
import br.ifpe.jaboatao.transportadora2.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository repo;

    public ClienteService(ClienteRepository repo) {
        this.repo = repo;
    }

    public List<Cliente> listar() {
        return repo.findAll();
    }

    public Cliente salvar(Cliente c) {
        return repo.save(c);
    }

    public Cliente buscar(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        repo.deleteById(id);
    }

    public List<Cliente> buscarPorNome(String nome) {
        return repo.findByNomeContainingIgnoreCase(nome);
    }

    public List<Cliente> buscarPorDocumento(String documento) {
        return repo.findByDocumentoContaining(documento);
    }
}