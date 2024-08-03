package br.com.teste.agnes.repositories;

import br.com.teste.agnes.entities.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
}