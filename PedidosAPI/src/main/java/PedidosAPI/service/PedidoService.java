package PedidosAPI.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import PedidosAPI.dtos.AtualizarPedidoDTO;
import PedidosAPI.dtos.CriarPedidoDTO;
import PedidosAPI.entity.Pedido;
import PedidosAPI.entity.enums.Status;
import PedidosAPI.repository.PedidoRepository;

@Service
public class PedidoService {

	private final Logger logger = LoggerFactory.getLogger(PedidoService.class);
    private final PedidoRepository pedidoRepository;
    private final RabbitTemplate rabbitTemplate;

    public PedidoService(PedidoRepository pedidoRepository, RabbitTemplate rabbitTemplate) {
        this.pedidoRepository = pedidoRepository;
        this.rabbitTemplate = rabbitTemplate;
    }
    
    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;
    
    @Value("${rabbitmq.routing.key.name}")
    private String routingKey;
	
    public Pedido enfileirarPedido(CriarPedidoDTO criarPedidoDTO) {
        Pedido pedido = new Pedido();
        pedido.setDescricao(criarPedidoDTO.getDescricao());
        pedido.setValor(criarPedidoDTO.getValor());
        pedido.setStatus(Status.EM_PROCESSAMENTO);
        pedido.setQuantidadePedido(criarPedidoDTO.getQuantidadePedido());
        pedidoRepository.save(pedido);
        rabbitTemplate.convertAndSend(exchangeName, routingKey, pedido);
        logger.info("Pedido enfileirado: {}", pedido.toString());
        return pedido;
    }
    
    public void excluirPedido(Long id) {
        if (!pedidoRepository.existsById(id)) {
            logger.info("Pedido com ID {} não encontrado.", id);
            throw new IllegalArgumentException("Pedido não encontrado");
        }
        pedidoRepository.deleteById(id);
        logger.info("Pedido com ID {} excluído.", id);
    }
    
    public Pedido atualizarPedido(Long id, AtualizarPedidoDTO atualizarPedidoDTO) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        pedido.setDescricao(atualizarPedidoDTO.getDescricao());
        pedido.setValor(atualizarPedidoDTO.getValor());
        pedido.setQuantidadePedido(atualizarPedidoDTO.getQuantidadePedido());
        pedidoRepository.save(pedido);
        rabbitTemplate.convertAndSend(exchangeName, routingKey, atualizarPedidoDTO);
        return pedido;

    }
    
    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido com ID " + id + " não encontrado."));
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }
}
