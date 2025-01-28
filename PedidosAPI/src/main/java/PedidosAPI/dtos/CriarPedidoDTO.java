package PedidosAPI.dtos;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CriarPedidoDTO {
    private String descricao;
    private BigDecimal valor;
}