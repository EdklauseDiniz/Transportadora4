package br.ifpe.jaboatao.transportadora2.repository;

import br.ifpe.jaboatao.transportadora2.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByNomeContainingIgnoreCase(String nome);
    List<Cliente> findByDocumentoContaining(String documento);
}