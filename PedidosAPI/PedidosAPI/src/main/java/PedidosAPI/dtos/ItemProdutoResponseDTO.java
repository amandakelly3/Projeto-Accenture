package PedidosAPI.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemProdutoResponseDTO {
    private Integer idProduto;
    private Integer quantidade;
}
