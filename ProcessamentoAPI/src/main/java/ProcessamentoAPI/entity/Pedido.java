package ProcessamentoAPI.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import ProcessamentoAPI.entity.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = "produtos")
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
	
	@OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER,
    cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Anotação para configurar a serialização da lista
    private List<PedidoTemProdutos> produtos = new ArrayList<>();
}