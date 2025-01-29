package NotificacaoAPI.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Vendedor {

	private Long id;
	
    private String nome;

    private String setor;

}
