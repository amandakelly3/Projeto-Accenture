package PedidosAPI.dtos;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class CriarProdutoDTO {
	private String descricao;
	private BigDecimal valor;
	private Integer quantidadeEstoque;
}
