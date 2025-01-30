package PedidosAPI.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import PedidosAPI.dtos.AtualizarPedidoDTO;
import PedidosAPI.dtos.CriarPedidoDTO;
import PedidosAPI.entity.Pedido;
import PedidosAPI.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Pedidos", description = "Contém a operação para realização de pedidos")
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    
    private final Logger logger = LoggerFactory.getLogger(PedidoController.class);

    private final PedidoService service;
    
    public PedidoController(PedidoService service){
        this.service = service;
    }
    
    @Operation(summary = "Busca todos os pedidos", description = "Recurso para buscar pedido",
            responses = {@ApiResponse(responseCode = "201", description = "Busca feita com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pedido.class)))
    })
    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos() {
        logger.info("Listando todos os pedidos");
        List<Pedido> pedidos = service.listarPedidos();
        return ResponseEntity.ok(pedidos);
    }
    
    @Operation(summary = "Cria um novo pedido", description = "Recurso para criar um novo pedido",
            responses = {@ApiResponse(responseCode = "201", description = "Recurso criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pedido.class)))
    })
    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody CriarPedidoDTO criarPedidoDTO) {
        logger.info("Criando novo pedido: {}", criarPedidoDTO.toString());
        Pedido pedido = new Pedido();
        pedido.setDescricao(criarPedidoDTO.getDescricao());
        
        Pedido pedidoCriado = service.enfileirarPedido(criarPedidoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoCriado);
    }
  
    @Operation(summary = "Atualiza um pedido", description = "Recurso para atualizar um pedido existente",
            responses = {@ApiResponse(responseCode = "200", description = "Recurso atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pedido.class)))}
    )
    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizarPedido(@PathVariable Long id, @RequestBody AtualizarPedidoDTO atualizarPedidoDTO) {
        logger.info("Atualizando pedido com ID: {}", id);
        Pedido pedidoAtualizado = new Pedido();
        pedidoAtualizado.setDescricao(atualizarPedidoDTO.getDescricao());
        pedidoAtualizado.setValor(atualizarPedidoDTO.getValor());
        Pedido pedido = service.atualizarPedido(id, atualizarPedidoDTO);
        logger.info("Pedido atualizado: {}", atualizarPedidoDTO.getDescricao(),atualizarPedidoDTO.getValor());

        return ResponseEntity.ok(pedido);
    }

    @Operation(summary = "Exclui um pedido", description = "Recurso para excluir um pedido existente",
            responses = {@ApiResponse(responseCode = "204", description = "Recurso excluído com sucesso")}
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPedido(@PathVariable Long id) {
        logger.info("Excluindo pedido com ID: {}", id);
        service.excluirPedido(id);
        return ResponseEntity.noContent().build();
    }
    
    @Operation(summary = "Busca pedido por ID", description = "Recupera um pedido específico pelo seu ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pedido encontrado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pedido.class))),
                    @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
            })
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
        logger.info("Buscando pedido com ID: {}", id);
        Pedido pedido = service.buscarPorId(id);
        logger.info("Pedido: {}", pedido.getId(), pedido.getDescricao(), pedido.getStatus(), pedido.getValor());
        return ResponseEntity.ok(pedido);
    }
}