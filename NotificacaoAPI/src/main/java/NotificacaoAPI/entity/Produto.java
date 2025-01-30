package NotificacaoAPI.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Produto {

    private Integer id;
	private String descricao;
	
	private BigDecimal valor;
	
	private List<PedidoTemProdutos> pedidos = new ArrayList<>();
}
