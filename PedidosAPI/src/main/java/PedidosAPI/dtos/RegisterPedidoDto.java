package PedidosAPI.dtos;

import java.time.LocalDateTime;

import PedidosAPI.entity.Vendedor;
import PedidosAPI.entity.enums.Status;

public record RegisterPedidoDto (String descricao, Double valor, String emailNotificacao) { 

	}

