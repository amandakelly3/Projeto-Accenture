package PedidosAPI.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import PedidosAPI.entity.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "statusPedido")
public class StatusPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
    private Integer id;
	
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@Column(name = "pedidoDescricao")
    private LocalDateTime descricao;
	
	 @Enumerated(EnumType.STRING) 
	 @Column(name = "produtoStatus")
	 private Status status;
}