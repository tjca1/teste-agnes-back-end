package br.com.teste.agnes.dtos;

import lombok.Data;

@Data
public class AtividadeDTO {
    private Long id;
    private String nome;
    private String descricao;
    private Long projetoId;
    private Long clienteId;
    private String projetoNome;  
    private String clienteNome; 
}
