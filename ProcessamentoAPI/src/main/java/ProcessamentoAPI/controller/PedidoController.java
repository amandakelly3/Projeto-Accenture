package ProcessamentoAPI.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ProcessamentoAPI.dtos.ProcessamentoPedidoDTO;
import ProcessamentoAPI.entity.Pedido;
import ProcessamentoAPI.entity.StatusPedido;
import ProcessamentoAPI.entity.enums.Status;
import ProcessamentoAPI.repository.PedidoRepository;
import ProcessamentoAPI.service.ProcessamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Processamento", description = "Operações relacionadas ao processamento de pedidos")
@RestController
@RequestMapping("/api/processamento")
@RequiredArgsConstructor
public class PedidoController {

    private final ProcessamentoService processamentoService;
    private final PedidoRepository pedidoRepository;
    private final Logger logger = LoggerFactory.getLogger(PedidoController.class);

    @Operation(summary = "Listar todos os pedidos processados", description = "Retorna todos os pedidos com status de processamento")
    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidosProcessados() {
        logger.info("Listando todos os pedidos processados");
        List<Pedido> pedidos = pedidoRepository.findByStatus(Status.PROCESSADO);
        return ResponseEntity.ok(pedidos);
    }

    @Operation(summary = "Buscar pedido processado por ID", description = "Retorna um pedido processado específico pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPedidoProcessado(@PathVariable Integer id) {
        logger.info("Buscando pedido processado com ID: {}", id);
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        return ResponseEntity.ok(pedido);
    }

    @Operation(summary = "Processar pagamento manualmente", description = "Endpoint para disparar processamento de pagamento manualmente")
    @PostMapping("/processar-pagamento")
    public ResponseEntity<String> processarPagamentoManual(@RequestBody ProcessamentoPedidoDTO dto) {
        logger.info("Processamento manual iniciado para pedido ID: {}", dto.getId());
        processamentoService.processaPagamento(dto);
        return ResponseEntity.ok("Processamento iniciado para o pedido ID: " + dto.getId());
    }


    @Operation(summary = "Reverter processamento", description = "Reverte o status de um pedido processado para em processamento")
    @PutMapping("/reverter/{id}")
    public ResponseEntity<Pedido> reverterProcessamento(@PathVariable Integer id) {
        logger.info("Revertendo processamento do pedido ID: {}", id);
        Pedido pedido = processamentoService.reverterProcessamento(id);
        return ResponseEntity.ok(pedido);
    }
}