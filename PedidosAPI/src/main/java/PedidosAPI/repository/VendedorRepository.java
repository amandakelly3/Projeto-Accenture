package PedidosAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import PedidosAPI.entity.Vendedor;

public interface VendedorRepository extends JpaRepository<Vendedor, Integer>{

}
