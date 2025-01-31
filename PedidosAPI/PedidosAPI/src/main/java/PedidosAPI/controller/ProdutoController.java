package PedidosAPI.controller;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import PedidosAPI.dtos.CriarProdutoDTO;
import PedidosAPI.dtos.AtualizarProdutoDTO;
import PedidosAPI.entity.Produto;
import PedidosAPI.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Produtos", description = "Contém a operação para realização de produtos")
@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    
    private final Logger logger = LoggerFactory.getLogger(ProdutoController.class);

    private final ProdutoService service;
    
    public ProdutoController(ProdutoService service){
        this.service = service;
    }
    
    @Operation(summary = "Busca todos os produtos", description = "Recurso para buscar produtos",
            responses = {@ApiResponse(responseCode = "200", description = "Busca feita com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class)))
    })
    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {
        logger.info("Listando todos os produtos");
        List<Produto> produtos = service.listarProdutos();
        return ResponseEntity.ok(produtos);
    }
    
    @Operation(summary = "Cria um novo produto", description = "Recurso para criar um novo produto",
            responses = {@ApiResponse(responseCode = "201", description = "Recurso criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class)))
    })
    @PostMapping
    public ResponseEntity<Produto> criarProduto(@RequestBody CriarProdutoDTO criarProdutoDTO) {
        logger.info("Criando novo produto: {}", criarProdutoDTO.toString());
        Produto produtoCriado = service.criarProduto(criarProdutoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoCriado);
    }
  
    @Operation(summary = "Atualiza um produto", description = "Recurso para atualizar um produto existente",
            responses = {@ApiResponse(responseCode = "200", description = "Recurso atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class)))}
    )
    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarProduto(@PathVariable Long id, @RequestBody AtualizarProdutoDTO atualizarProdutoDTO) {
        logger.info("Atualizando produto com ID: {}", id);
        Produto produtoAtualizado = service.atualizarProduto(id, atualizarProdutoDTO);
        String mensagem = String.format("Produto atualizado: ID=%d, Nome=%s, Preço=%s", 
                                        produtoAtualizado.getId(), 
                                        produtoAtualizado.getDescricao(), 
                                        produtoAtualizado.getValor().toString());
        return ResponseEntity.ok(mensagem);
    }

    @Operation(summary = "Exclui um produto", description = "Recurso para excluir um produto existente",
            responses = {@ApiResponse(responseCode = "204", description = "Recurso excluído com sucesso")}
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirProduto(@PathVariable Long id) {
        logger.info("Excluindo produto com ID: {}", id);
        service.excluirProduto(id);
        return ResponseEntity.noContent().build();
    }
    
    @Operation(summary = "Busca produto por ID", description = "Recupera um produto específico pelo seu ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Produto encontrado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class))),
                    @ApiResponse(responseCode = "404", description = "Produto não encontrado")
            })
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        logger.info("Buscando produto com ID: {}", id);
        Produto produto = service.buscarPorId(id);
        return ResponseEntity.ok(produto);
    }
}