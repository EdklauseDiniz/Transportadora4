package br.ifpe.jaboatao.transportadora2.repository;

import br.ifpe.jaboatao.transportadora2.model.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, Long> {
    List<Motorista> findByNomeContainingIgnoreCase(String nome);
    List<Motorista> findByCnhContaining(String cnh);
}
