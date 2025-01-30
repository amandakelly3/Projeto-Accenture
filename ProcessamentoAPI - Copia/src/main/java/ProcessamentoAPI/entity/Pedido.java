package ProcessamentoAPI.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import ProcessamentoAPI.entity.enums.Status;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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

	@Column(name = "pedidoDataHora")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataHora = LocalDateTime.now();
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<PedidoTemProdutos> produtos = new ArrayList<>();
}