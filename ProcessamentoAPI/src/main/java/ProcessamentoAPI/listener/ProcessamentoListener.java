package ProcessamentoAPI.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ProcessamentoAPI.dtos.ProcessamentoPedidoDTO;
import ProcessamentoAPI.service.ProcessamentoService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProcessamentoListener {

	private final ProcessamentoService processamentoService;
    private final Logger log = LoggerFactory.getLogger(ProcessamentoListener.class);


    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void pedido(ProcessamentoPedidoDTO pedido) {
        try {
        	
            log.info("pedido recebido: {}", pedido);
            processamentoService.processaPagamento(pedido);
            log.info("Pagamento Processado: {}", pedido);

        } catch (Exception e) {
            log.error("Erro ao processar pagamento: {}", e.getMessage(), e);
            throw e;
        }
    }
}