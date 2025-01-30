package NotificacaoAPI.entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    private Integer id;
    private String descricao;
    private BigDecimal valor;
    private List<PedidoTemProdutos> produtos = new ArrayList<>();
}