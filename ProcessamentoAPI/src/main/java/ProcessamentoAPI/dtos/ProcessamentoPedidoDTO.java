package ProcessamentoAPI.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ProcessamentoAPI.entity.PedidoTemProdutos;
import ProcessamentoAPI.entity.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProcessamentoPedidoDTO {
    private Integer id; // Identificador do pedido
    private BigDecimal valor = BigDecimal.ZERO; // Valor do pedido
    private String descricao; // Descrição do pedido
    private Status status;
    private LocalDateTime dataHora = LocalDateTime.now();
    private List<PedidoTemProdutos> produtos = new ArrayList<>();
}