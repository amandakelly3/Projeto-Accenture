package ProcessamentoAPI.dtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusPedidoDTO {
    private Integer idStatusPedido;
    private String statusPedidoDescricao;
}
