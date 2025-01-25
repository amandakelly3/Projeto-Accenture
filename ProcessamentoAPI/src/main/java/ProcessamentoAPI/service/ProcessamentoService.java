package ProcessamentoAPI.service;

import java.time.LocalDateTime;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import ProcessamentoAPI.entity.Pedido;
import ProcessamentoAPI.entity.enums.Status;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProcessamentoService {
	
	 private final RabbitTemplate rabbitTemplate;
	 
	 public void processaPagamento(Pedido pedido) {
	        Pedido pagamento = new Pedido();
	        pagamento.setId(pagamento.getId());
	        pagamento.setDescricao(pagamento.getDescricao());
	        pagamento.setValor(pagamento.getValor());
	        pagamento.setDataHora(LocalDateTime.now());
	        pagamento.setStatus(Status.PROCESSADO);

	        rabbitTemplate.convertAndSend("E5.pagamento.exchange", "pagamento.RoutingKey", pedido);
}
}