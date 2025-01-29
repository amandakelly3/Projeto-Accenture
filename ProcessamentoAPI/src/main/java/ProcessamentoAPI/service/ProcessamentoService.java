package ProcessamentoAPI.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ProcessamentoAPI.entity.Pedido;
import ProcessamentoAPI.entity.enums.Status;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProcessamentoService {
	
	private final Logger logger = LoggerFactory.getLogger(ProcessamentoService.class);
    private final RabbitTemplate rabbitTemplate;

    public void processaPagamento(Pedido pedido) {
        Pedido pagamento = new Pedido();
        pagamento.setId(pedido.getId());
        pagamento.setDescricao(pedido.getDescricao());
        pagamento.setValor(pedido.getValor());
        pagamento.setDataHora(LocalDateTime.now());

        if (pedido.getValor() <= 0) {
            pagamento.setStatus(Status.NEGADO);
            logger.info("Pagamento negado: {}", pedido.toString());
            rabbitTemplate.convertAndSend("E5.pagamento.exchange", "pagamento.RoutingKey", pagamento);
        } else {
            pagamento.setStatus(Status.PROCESSADO);
            rabbitTemplate.convertAndSend("E5.pagamento.exchange", "pagamento.RoutingKey", pagamento);
        }
    }
}
