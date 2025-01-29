package NotificacaoAPI.entity;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import NotificacaoAPI.entity.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {


	private Integer id;
	
	private String descricao;
	
	private Double valor;
	
	private String emailNotificacao;
	
	private Status status;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dataHora = LocalDateTime.now();
	
	private Vendedor vendedor;
}
