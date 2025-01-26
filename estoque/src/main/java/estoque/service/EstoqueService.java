package estoque.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import estoque.entity.Produto;
import estoque.repository.ProdutoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstoqueService {

    private final ProdutoRepository produtoRepository;

    @Transactional
    public void atualizarEstoque(Produto produto) {
        Produto produtoExistente = produtoRepository.findById(produto.getId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produtoExistente.setQuantidade(produtoExistente.getQuantidade() - produto.getQuantidade());
        produtoRepository.save(produtoExistente);
    }

    public Produto obterProduto(Integer id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }
}