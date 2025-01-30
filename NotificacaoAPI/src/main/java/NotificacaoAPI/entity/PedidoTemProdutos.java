package NotificacaoAPI.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoTemProdutos {

    private Long id;

    private Pedido pedido;

    private Produto produto;

    private Integer valor;
}
