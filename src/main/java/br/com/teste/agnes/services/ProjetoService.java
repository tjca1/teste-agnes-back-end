package br.com.teste.agnes.services;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.teste.agnes.dtos.ProjetoDTO;
import br.com.teste.agnes.entities.Cliente;
import br.com.teste.agnes.entities.Projeto;
import br.com.teste.agnes.enuns.StatusProjeto;
import br.com.teste.agnes.repositories.ClienteRepository;
import br.com.teste.agnes.repositories.ProjetoRepository;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<ProjetoDTO> findAll() {
        return projetoRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public ProjetoDTO findById(Long id) {
        return projetoRepository.findById(id)
            .map(this::convertToDTO)
            .orElse(null);
    }

    public ProjetoDTO save(ProjetoDTO projetoDTO) {
        Projeto projeto = convertToEntity(projetoDTO);
        Optional<Cliente> cliente = clienteRepository.findById(projetoDTO.getClienteId());
        if (cliente.isPresent()) {
            projeto.setCliente(cliente.get());
        } else {
            throw new RuntimeException("Cliente não encontrado");
        }
        Projeto savedProjeto = projetoRepository.save(projeto);
        return convertToDTO(savedProjeto);
    }

    public void delete(Long id) {
        projetoRepository.deleteById(id);
    }

    private ProjetoDTO convertToDTO(Projeto projeto) {
        ProjetoDTO projetoDTO = new ProjetoDTO();
        projetoDTO.setId(projeto.getId());
        projetoDTO.setNome(projeto.getNome());
        projetoDTO.setStatus(projeto.getStatus().name());
        projetoDTO.setClienteId(projeto.getCliente().getId());
        projetoDTO.setClienteNome(projeto.getClienteNome());
        return projetoDTO;
    }

    private Projeto convertToEntity(ProjetoDTO projetoDTO) {
        Projeto projeto = new Projeto();
        projeto.setId(projetoDTO.getId());
        projeto.setNome(projetoDTO.getNome());
        projeto.setStatus(StatusProjeto.valueOf(projetoDTO.getStatus()));
        return projeto;
    }
}


