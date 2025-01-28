package PedidosAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import PedidosAPI.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>  {

}
