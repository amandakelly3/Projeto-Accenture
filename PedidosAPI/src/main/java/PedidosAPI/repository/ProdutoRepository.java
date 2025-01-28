package PedidosAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import PedidosAPI.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
