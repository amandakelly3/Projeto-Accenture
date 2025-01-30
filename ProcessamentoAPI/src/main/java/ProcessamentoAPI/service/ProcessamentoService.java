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

    public void processaPagamento(ProcessamentoPedidoDTO pedido) {
        
        Pedido pagamento = new Pedido();
        pagamento.setId(pedido.getId());
        pagamento.setDescricao(pedido.getDescricao());
        pagamento.setValor(pedido.getValor());
        pagamento.setDataHora(LocalDateTime.now());

        StatusPedido statusPedido = new StatusPedido();
        statusPedido.setPedidoHistorico(pedido.getDataHora());
        statusPedido.setIdpedidohistorico(pedido.getId());
        statusPedido.setStatus(pedido.getStatus());
        statusPedidoRepository.save(statusPedido);
        
        if (pedido.getValor().compareTo(BigDecimal.ZERO) <= 0) {
            statusPedido.setStatus(Status.NEGADO);
            pagamento.setStatus(Status.NEGADO);
            pedidoRepository.save(pagamento);
            statusPedidoRepository.save(statusPedido);
            logger.info("Pagamento negado: {}", pagamento.toString());
            
        } else {
            pagamento.setStatus(Status.PROCESSADO);
            statusPedido.setStatus(Status.PROCESSADO);
            pedidoRepository.save(pagamento);
            statusPedidoRepository.save(statusPedido);
            logger.info("Pagamento Aprovado: {}", pagamento.toString());
            rabbitTemplate.convertAndSend("E5.pagamento.exchange", "pagamento.RoutingKey", pagamento);
        }
    }
}
