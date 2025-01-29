package PedidosAPI.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import PedidosAPI.entity.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pedido")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPedido")
    private Integer id;

	@Column(name = "pedidoDescricao")
    private String descricao;

	@Column(name = "pedidoValor")
    private BigDecimal valor;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "produtoStatus")
    private Status status;
	
	@Column(name = "pedidoQuantidade")
	private Integer quantidadePedido;

	@Column(name = "pedidoDataHora")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataHora = LocalDateTime.now();

}