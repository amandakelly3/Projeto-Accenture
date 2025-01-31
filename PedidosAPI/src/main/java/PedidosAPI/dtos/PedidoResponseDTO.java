package PedidosAPI.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import PedidosAPI.entity.Vendedor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoResponseDTO {
    private String descricao;
    private List<ItemProdutoResponseDTO> produtos;
    private Integer idVendedor; 
}
