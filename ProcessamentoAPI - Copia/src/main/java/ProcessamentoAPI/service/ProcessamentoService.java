package ProcessamentoAPI.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import ProcessamentoAPI.dtos.PedidoDTO;
import ProcessamentoAPI.dtos.ProcessamentoPedidoDTO;
import ProcessamentoAPI.entity.Pedido;
import ProcessamentoAPI.entity.StatusPedido;
import ProcessamentoAPI.entity.enums.Status;
import ProcessamentoAPI.repository.PedidoRepository;
import ProcessamentoAPI.repository.StatusPedidoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProcessamentoService {

	private final Logger logger = LoggerFactory.getLogger(ProcessamentoService.class);
    private final RabbitTemplate rabbitTemplate;
    private final PedidoRepository pedidoRepository;
    private final StatusPedidoRepository statusPedidoRepository;

    public void processaPagamento(ProcessamentoPedidoDTO pedidoDTO) {
        Pedido pedido = pedidoRepository.findById(pedidoDTO.getId())
            .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

        if (pedido.getValor() == null) {
            logger.error("Valor do pedido é nulo. ID: {}", pedido.getId());
            throw new IllegalArgumentException("Valor do pedido não pode ser nulo");
        }

        Pedido pagamento = new Pedido();
        pagamento.setId(pedido.getId());
        pagamento.setValor(pedido.getValor());  // Agora sempre terá um valor válido
        pagamento.setDescricao(pedido.getDescricao());
        pagamento.setDataHora(LocalDateTime.now());

        StatusPedido statusPedido = new StatusPedido();
        statusPedido.setPedidoHistorico(pedido.getDataHora());
        statusPedido.setIdpedidohistorico(pedido.getId());

        if (pedido.getValor().compareTo(BigDecimal.ZERO) <= 0) {
            statusPedido.setStatus(Status.NEGADO);
            pagamento.setStatus(Status.NEGADO);
            logger.info("Pagamento negado: {}", pagamento.toString());
        } else {
            statusPedido.setStatus(Status.PROCESSADO);
            pagamento.setStatus(Status.PROCESSADO);
            rabbitTemplate.convertAndSend("E5.pagamento.exchange", "pagamento.RoutingKey", pagamento);
            logger.info("Pagamento aprovado: {}", pagamento.toString());
        }

        pedidoRepository.save(pagamento);
        statusPedidoRepository.save(statusPedido);
    }
    public Pedido reverterProcessamento(Integer id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        
        StatusPedido novoStatus = new StatusPedido();
        novoStatus.setIdpedidohistorico(id);
        novoStatus.setStatus(Status.EM_PROCESSAMENTO);
        novoStatus.setPedidoHistorico(LocalDateTime.now());
        
        pedido.setStatus(Status.EM_PROCESSAMENTO);
        
        statusPedidoRepository.save(novoStatus);
        return pedidoRepository.save(pedido);
    }
}
