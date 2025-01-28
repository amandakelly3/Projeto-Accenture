package PedidosAPI.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonFormat;

import PedidosAPI.entity.Pedido;
import PedidosAPI.entity.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PedidoDTO {

	private Integer id;
    private String descricao;
    private BigDecimal valor;
    private Status status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataHora = LocalDateTime.now();
    
}
