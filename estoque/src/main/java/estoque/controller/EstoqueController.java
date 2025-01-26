package estoque.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import estoque.entity.Produto;
import estoque.service.EstoqueService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/estoque")
@RequiredArgsConstructor
public class EstoqueController {

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }
    
    private final EstoqueService estoqueService;

    @PostMapping("/atualizar")
    public ResponseEntity<String> atualizarEstoque(@RequestBody Produto produto) {
        try {
            estoqueService.atualizarEstoque(produto);
            return ResponseEntity.ok("Estoque atualizado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao atualizar estoque: " + e.getMessage());
        }
    }

    @GetMapping("/produto/{id}")
    public ResponseEntity<Produto> obterProduto(@PathVariable Integer id) {
        try {
            Produto produto = estoqueService.obterProduto(id);
            return ResponseEntity.ok(produto);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }
}