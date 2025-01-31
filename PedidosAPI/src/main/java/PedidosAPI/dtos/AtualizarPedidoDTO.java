package PedidosAPI.dtos;

import java.math.BigDecimal;
import java.util.List;

import PedidosAPI.entity.Vendedor;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtualizarPedidoDTO {
    private String descricao;
    private BigDecimal valor;
	private List<ItemProdutoDTO> produtos;

}
