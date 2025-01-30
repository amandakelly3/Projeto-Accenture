package ProcessamentoAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ProcessamentoAPI.entity.Pedido;
import ProcessamentoAPI.entity.enums.Status;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByStatus(Status status);

}