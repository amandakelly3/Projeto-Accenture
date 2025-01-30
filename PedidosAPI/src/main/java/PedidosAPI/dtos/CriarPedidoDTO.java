package PedidosAPI.dtos;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CriarPedidoDTO {
    private String descricao;
    private BigDecimal valor;
	private List<ItemProdutoDTO> produtos;
}