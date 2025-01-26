package estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import estoque.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}