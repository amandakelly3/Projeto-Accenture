package PedidosAPI.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import PedidosAPI.dtos.AtualizarPedidoDTO;
import PedidosAPI.dtos.CriarPedidoDTO;
import PedidosAPI.dtos.ItemProdutoDTO;
import PedidosAPI.entity.Pedido; 
import PedidosAPI.entity.PedidoTemProdutos;
import PedidosAPI.entity.Produto;
import PedidosAPI.entity.Vendedor;
import PedidosAPI.entity.enums.Status;
import PedidosAPI.repository.PedidoRepository;
import PedidosAPI.repository.PedidoTemProdutosRepository;
import PedidosAPI.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import lombok.Data;

@Data
@Service
public class PedidoService {

	private final Logger logger = LoggerFactory.getLogger(PedidoService.class);
    private final PedidoRepository pedidoRepository;
    private final RabbitTemplate rabbitTemplate;
    private final PedidoTemProdutosRepository pedidoTemProdutosRepository;
    private final ProdutoRepository produtoRepository;
    
    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;
    
    @Value("${rabbitmq.routing.key.name}")
    private String routingKey;
	
    @Transactional
    public Pedido enfileirarPedido(CriarPedidoDTO criarPedidoDTO) {
    	Vendedor vendedor = new Vendedor();
        Pedido pedido = new Pedido();
        pedido.setDescricao(criarPedidoDTO.getDescricao());
        pedido.setStatus(Status.EM_PROCESSAMENTO);
        BigDecimal valorTotal = BigDecimal.ZERO;

        List<PedidoTemProdutos> itensPedido = new ArrayList<>();

        for (ItemProdutoDTO item : criarPedidoDTO.getProdutos()) {
            Produto produto = produtoRepository.findById(item.getIdProduto())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            BigDecimal subtotal = produto.getValor().multiply(BigDecimal.valueOf(item.getQuantidade()));
            valorTotal = valorTotal.add(subtotal);

            PedidoTemProdutos ptp = new PedidoTemProdutos();
            ptp.setPedido(pedido); 
            ptp.setProduto(produto);
            ptp.setQuantidade(item.getQuantidade());
            ptp.setValor(valorTotal);

            itensPedido.add(ptp);
        }

        pedido.setValor(valorTotal);
        pedido = pedidoRepository.save(pedido);

        for (PedidoTemProdutos ptp : itensPedido) {
            pedidoTemProdutosRepository.save(ptp);
        }

        rabbitTemplate.convertAndSend(exchangeName, routingKey, pedido);
        logger.info("Pedido enfileirado: {}", pedido);

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
