package PedidosAPI.dtos;

import java.time.LocalDateTime;

import PedidosAPI.entity.Vendedor;
import PedidosAPI.entity.enums.Status;

public record PedidoRegisteredPayload (Integer id, String descricao, Double valor, String emailNotificacao, Status status, LocalDateTime dataHora, Vendedor vendedor){

}
