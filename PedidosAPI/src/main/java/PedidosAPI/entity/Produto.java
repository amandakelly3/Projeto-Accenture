package PedidosAPI.entity;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dataHoraSaida;

	private String descricao;

	private Double valor;
	
	private Integer quantidade;
}
