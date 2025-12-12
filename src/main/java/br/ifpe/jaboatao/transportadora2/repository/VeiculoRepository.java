package br.ifpe.jaboatao.transportadora2.repository;

import br.ifpe.jaboatao.transportadora2.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    List<Veiculo> findByPlacaContainingIgnoreCase(String placa);
    List<Veiculo> findByMarcaContainingIgnoreCase(String marca);
}