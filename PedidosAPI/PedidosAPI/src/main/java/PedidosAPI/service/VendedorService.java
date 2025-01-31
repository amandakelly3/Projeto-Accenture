package PedidosAPI.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import PedidosAPI.dtos.AtualizarVendedorDTO;
import PedidosAPI.dtos.CriarVendedorDTO;
import PedidosAPI.entity.Vendedor;
import PedidosAPI.repository.VendedorRepository;

@Service
public class VendedorService {

    private final VendedorRepository repository;
    private final Logger logger = LoggerFactory.getLogger(VendedorService.class);

    public VendedorService(VendedorRepository repository) {
        this.repository = repository;
    }

    public List<Vendedor> listarVendedores() {
        return repository.findAll();
    }

    public Vendedor criarVendedor(CriarVendedorDTO criarVendedorDTO) {
        Vendedor vendedor = new Vendedor();
        vendedor.setNome(criarVendedorDTO.getNome());
        vendedor.setSetor(criarVendedorDTO.getSetor());
        repository.save(vendedor);
        logger.info("Vendedor criado: {}", vendedor.toString());
        return vendedor;
    }

    public Vendedor atualizarVendedor(Integer id, AtualizarVendedorDTO atualizarVendedorDTO) {
        Vendedor vendedor = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendedor não encontrado"));
        vendedor.setNome(atualizarVendedorDTO.getNome());
        vendedor.setSetor(atualizarVendedorDTO.getSetor());
        repository.save(vendedor);
        logger.info("Vendedor atualizado: {}", vendedor.toString());
        return vendedor;
    }

    public void excluirVendedor(Integer id) {
        repository.deleteById(id);
    }

    public Vendedor buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendedor não encontrado"));
    }
}