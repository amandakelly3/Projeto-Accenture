package PedidosAPI.dtos;

import java.math.BigDecimal;
import java.util.List;

import PedidosAPI.entity.Vendedor;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CriarPedidoDTO {
    private String descricao;
	private List<ItemProdutoDTO> produtos;

}