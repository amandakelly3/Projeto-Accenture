package ProcessamentoAPI.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemProdutoDTO {
    private Long idProduto;
    private Integer quantidade;
}