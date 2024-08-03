package br.com.teste.agnes.dtos;

import lombok.Data;

@Data
public class ProjetoDTO {
    private Long id;
    private String nome;
    private String status;
    private Long clienteId;
    private String clienteNome;
}
