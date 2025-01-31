package ProcessamentoAPI.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import ProcessamentoAPI.entity.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "status_pedido")
public class StatusPedido implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@JoinColumn(name = "idPedido")
	private Integer idpedidohistorico;
	
	@JoinColumn(name = "pedidoHistorico")
    private LocalDateTime pedidoHistorico = LocalDateTime.now();
	
	 @Enumerated(EnumType.STRING) 
	 @JoinColumn(name = "produtoStatus")
	 private Status status;
}