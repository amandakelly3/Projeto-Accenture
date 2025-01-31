package PedidosAPI.entity;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pedidoHistoricoStatus")
public class PedidoHistoricoStatus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@ManyToOne
    @JoinColumn(name = "idPedido", nullable = false)
    private Pedido pedido;
    
	@ManyToOne
	@Column(name = "pedidoDataHora",nullable = false)
    private StatusPedido statusPedido;
    
	@Column(name = "pedidoDataHora")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataHoraStatus;

    private LocalDateTime dataHoraPagamento;
}
