package br.com.teste.agnes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.teste.agnes.entities.Projeto;
import br.com.teste.agnes.enuns.StatusProjeto;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
    List<Projeto> findByStatus(StatusProjeto status);
}