package PedidosAPI.mapper;

import PedidosAPI.dtos.ItemProdutoResponseDTO;
import PedidosAPI.dtos.PedidoResponseDTO;
import PedidosAPI.entity.Pedido;

public class PedidoMapper {

    public static PedidoResponseDTO toPedidoResponseDTO(Pedido pedidoCriado) {
        PedidoResponseDTO responseDTO = new PedidoResponseDTO();
        responseDTO.setDescricao(pedidoCriado.getDescricao());
        responseDTO.setProdutos(
                pedidoCriado.getProdutos().stream().map(item ->
                        new ItemProdutoResponseDTO(
                                item.getProduto().getId(),
                                item.getQuantidade()
                        )
                ).toList()
        );

        return responseDTO;
    }
}
