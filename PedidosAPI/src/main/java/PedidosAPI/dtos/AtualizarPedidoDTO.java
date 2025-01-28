package PedidosAPI.dtos;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtualizarPedidoDTO {
    private String descricao;
    private BigDecimal valor;
    
    
}
