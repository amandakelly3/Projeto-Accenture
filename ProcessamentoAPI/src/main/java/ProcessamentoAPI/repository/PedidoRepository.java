package ProcessamentoAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ProcessamentoAPI.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}