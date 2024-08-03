package br.com.teste.agnes.repositories;

import br.com.teste.agnes.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}