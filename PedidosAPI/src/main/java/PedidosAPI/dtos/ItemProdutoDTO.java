package PedidosAPI.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemProdutoDTO {
    private Long idProduto; // ID do produto
    private Integer valor; // Quantidade solicitada
}