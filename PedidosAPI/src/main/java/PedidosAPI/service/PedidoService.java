package PedidosAPI.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import PedidosAPI.config.RabbitmqConfig;
import PedidosAPI.entity.Pedido;
import PedidosAPI.entity.enums.Status;

@Service
public class PedidoService {

	private final Logger logger = LoggerFactory.getLogger(PedidoService.class);
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;
    
    @Value("${rabbitmq.routing.key.name}")
    private String routingKey;
    
    public PedidoService(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }
	
    public Pedido enfileirarPedido(Pedido pedido) {
        pedido.setStatus(Status.EM_PROCESSAMENTO);
        rabbitTemplate.convertAndSend(exchangeName, routingKey, pedido);
        logger.info("Pedido enfileirado: {}", pedido.toString());
        return pedido;
    }
}
