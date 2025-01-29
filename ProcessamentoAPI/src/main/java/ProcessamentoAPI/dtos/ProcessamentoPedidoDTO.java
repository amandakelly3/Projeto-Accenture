package ProcessamentoAPI.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import ProcessamentoAPI.entity.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProcessamentoPedidoDTO {
    private Integer id; // Identificador do pedido
    private BigDecimal valor; // Valor do pedido
    private String descricao; // Descrição do pedido
    private Status status;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataHora = LocalDateTime.now();
}