package ProcessamentoAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ProcessamentoAPI.entity.StatusPedido;

public interface StatusPedidoRepository extends JpaRepository<StatusPedido, Integer> {
}
