package NotificacaoAPI.listener;

import org.slf4j.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import NotificacaoAPI.entity.Pedido;

@Component
public class PedidoListener {
	
	private final Logger logger = LoggerFactory.getLogger(PedidoListener.class);
	
	@RabbitListener(queues = "pedidos.pedido-criado.gerar-notificacao")
    public void enviarNotificacao(Pedido pedido) {
        logger.info("Notificação gerada: {}", pedido.toString());

	}
}
