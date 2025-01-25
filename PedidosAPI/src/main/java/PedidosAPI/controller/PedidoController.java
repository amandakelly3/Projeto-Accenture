package PedidosAPI.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    
    @GetMapping
    public Map<String, String> getData() {
        Map<String, String> data = new HashMap<>();
        data.put("message", "Hello from Spring Boot!");
        return data;
    }
    @Operation(summary = "Cria um novo pedido", description = "Recurso para criar um novo pedido",
            responses = {@ApiResponse(responseCode = "201", description = "Recurso criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pedido.class)))
    })
    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) {
        logger.info("Pedido recebido: {}", pedido.toString());
        pedido = service.enfileirarPedido(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }
}
