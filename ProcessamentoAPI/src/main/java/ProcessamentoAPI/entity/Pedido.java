package ProcessamentoAPI.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import ProcessamentoAPI.entity.enums.Status;
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
	
	private Status status;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dataHora = LocalDateTime.now();

// Getters
public Integer getId() {
    return id;
}

public String getDescricao() {
    return descricao;
}

public Double getValor() {
    return valor;
}

public Status getStatus() {
    return status;
}

public LocalDateTime getDataHora() {
    return dataHora;
}

// Setters
public void setId(Integer id) {
    this.id = id;
}

public void setDescricao(String descricao) {
    this.descricao = descricao;
}

public void setValor(Double valor) {
    this.valor = valor;
}

public void setStatus(Status status) {
    this.status = status;
}

public void setDataHora(LocalDateTime dataHora) {
    this.dataHora = dataHora;
}
}