package PedidosAPI.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import PedidosAPI.entity.PedidoTemProdutos;
import PedidosAPI.entity.Produto;

public interface PedidoTemProdutosRepository extends JpaRepository<PedidoTemProdutos, Long>{
	List<PedidoTemProdutos> findByPedidoId(Integer pedidoId);
}
  