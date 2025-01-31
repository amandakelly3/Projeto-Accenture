package PedidosAPI.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import PedidosAPI.repository.PedidoTemProdutosRepository;
import com.fasterxml.jackson.annotation.JsonFormat;

import PedidosAPI.dtos.ItemProdutoDTO;
import PedidosAPI.dtos.ProdutoDTO;
import PedidosAPI.entity.enums.Status;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = "produtos")
@Table(name = "pedido")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPedido",nullable = false)
    private Integer id;

	@Column(name = "pedidoDescricao")
    private String descricao;

	@Column(name = "pedidoValor")
    private BigDecimal valor;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "produtoStatus")
    private Status status;

	@Column(name = "pedidoDataHora")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataHora = LocalDateTime.now();
	
	@OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER,
    cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference 
    private List<PedidoTemProdutos> produtos = new ArrayList<>();

    public PedidoTemProdutos addProduto(Produto produto, int quantidade) {
        PedidoTemProdutos pedidoTemProduto = new PedidoTemProdutos();
        pedidoTemProduto.setPedido(this); // Define o pedido atual
        pedidoTemProduto.setProduto(produto); // Define o produto
        pedidoTemProduto.setQuantidade(quantidade); // Define a quantidade
        pedidoTemProduto.setValor(produto.getValor());

        return pedidoTemProduto;
        
        
}}