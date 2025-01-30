package PedidosAPI.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import PedidosAPI.dtos.AtualizarProdutoDTO;
import PedidosAPI.dtos.CriarProdutoDTO;
import PedidosAPI.entity.Produto;
import PedidosAPI.repository.ProdutoRepository;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Service
public class ProdutoService {
	
	private final ProdutoRepository repository;
	private final Logger logger = LoggerFactory.getLogger(ProdutoService.class);
    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public List<Produto> listarProdutos() {
        return repository.findAll();
    }

    public Produto criarProduto(@RequestBody CriarProdutoDTO criarProdutoDTO) {
        Produto produto = new Produto();
        produto.setDescricao(criarProdutoDTO.getDescricao());
        produto.setValor(criarProdutoDTO.getValor());
        repository.save(produto);
        logger.info("Produto criado: {}", produto.toString());
        return produto;
    }
    

    public Produto atualizarProduto(Long id, AtualizarProdutoDTO atualizarProdutoDTO) {
        Produto produto = repository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        produto.setDescricao(atualizarProdutoDTO.getDescricao());
        produto.setValor(atualizarProdutoDTO.getValor());
        repository.save(produto);
        logger.info("Produto atualizado: {}", produto.toString());
        return produto;
    }

    public void excluirProduto(Long id) {
        repository.deleteById(id);
    }

    public Produto buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }
}

