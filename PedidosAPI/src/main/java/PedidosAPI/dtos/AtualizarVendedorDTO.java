package PedidosAPI.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtualizarVendedorDTO {
    private Integer id;
    private String nome;
    private String setor;
}
