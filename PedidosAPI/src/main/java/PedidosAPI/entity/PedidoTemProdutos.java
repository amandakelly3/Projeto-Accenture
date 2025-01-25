package PedidosAPI.entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoTemProdutos {
	
	  @ManyToOne
	    @JoinColumn(name = "pedido_id", nullable = false)
	    private Pedido pedido;

	    @ManyToOne
	    @JoinColumn(name = "produto_id", nullable = false)
	    private Produto produto;
	
}
