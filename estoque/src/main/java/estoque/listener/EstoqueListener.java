
package estoque.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import estoque.entity.Produto;
import estoque.service.EstoqueService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EstoqueListener {

    private final EstoqueService estoqueService;
    private final Logger log = LoggerFactory.getLogger(EstoqueListener.class);
    
    public EstoqueListener(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @RabbitListener(queues = "${rabbitmq.exchange.name}")
    public void atualizarEstoque(Produto produto) {
        try {
            log.info("Produto recebido: {}", produto);
            estoqueService.atualizarEstoque(produto);
            log.info("Estoque atualizado: {}", produto);
        } catch (Exception e) {
            log.error("Erro ao atualizar estoque: {}", e.getMessage(), e);
            throw e;
        }
    }
}

