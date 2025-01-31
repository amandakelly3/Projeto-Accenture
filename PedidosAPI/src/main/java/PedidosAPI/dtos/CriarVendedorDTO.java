package PedidosAPI.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class CriarVendedorDTO {
    private String nome;
    private String setor;
}