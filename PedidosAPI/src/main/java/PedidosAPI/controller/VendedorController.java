package PedidosAPI.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import PedidosAPI.dtos.AtualizarVendedorDTO;
import PedidosAPI.dtos.CriarVendedorDTO;
import PedidosAPI.entity.Produto;
import PedidosAPI.entity.Vendedor;
import PedidosAPI.service.VendedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Vendedores", description = "Contém a operação para realização de Vendedores")
@RestController
@RequestMapping("/vendedor")
public class VendedorController {

    private final VendedorService vendedorService;

    public VendedorController(VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }
    @Operation(summary = "Busca todos os Vendedores", description = "Recurso para buscar vendedores",
            responses = {@ApiResponse(responseCode = "200", description = "Busca feita com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Vendedor.class)))
    })
    @GetMapping
    public List<Vendedor> listarVendedores() {
        return vendedorService.listarVendedores();
    }
    @Operation(summary = "Busca vendedores por ID", description = "Recurso para buscar vendedores por ID",
            responses = {@ApiResponse(responseCode = "200", description = "Busca feita com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Vendedor.class)))
    })
    @GetMapping("/{id}")
    public Vendedor buscarPorId(@PathVariable Integer id) {
        return vendedorService.buscarPorId(id);
    }
    @Operation(summary = "Cria um novo vendedor", description = "Recurso para criar um novo vendedor",
            responses = {@ApiResponse(responseCode = "201", description = "Recurso criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Vendedor.class)))
    })
    @PostMapping
    public Vendedor criarVendedor(@RequestBody CriarVendedorDTO criarVendedorDTO) {
        return vendedorService.criarVendedor(criarVendedorDTO);
    }
    
    @Operation(summary = "Atualiza um vendedor", description = "Recurso para atualizar um vendedor existente",
            responses = {@ApiResponse(responseCode = "200", description = "Recurso atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Vendedor.class)))}
    )
    @PutMapping("/{id}")
    public Vendedor atualizarVendedor(
            @PathVariable Integer id,
            @RequestBody AtualizarVendedorDTO atualizarVendedorDTO
    ) {
        return vendedorService.atualizarVendedor(id, atualizarVendedorDTO);
    }
    @Operation(summary = "Exclui um vendedor", description = "Recurso para excluir um vendedor existente",
            responses = {@ApiResponse(responseCode = "204", description = "Recurso excluído com sucesso")}
    )
    @DeleteMapping("/{id}")
    public void excluirVendedor(@PathVariable Integer id) {
        vendedorService.excluirVendedor(id);
    }
}