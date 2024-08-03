package br.com.teste.agnes.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.teste.agnes.dtos.AtividadeDTO;
import br.com.teste.agnes.entities.Atividade;
import br.com.teste.agnes.entities.Cliente;
import br.com.teste.agnes.entities.Projeto;
import br.com.teste.agnes.repositories.AtividadeRepository;
import br.com.teste.agnes.repositories.ClienteRepository;
import br.com.teste.agnes.repositories.ProjetoRepository;

@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<AtividadeDTO> findAll() {
        return atividadeRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public AtividadeDTO findById(Long id) {
        return atividadeRepository.findById(id)
            .map(this::convertToDTO)
            .orElse(null);
    }

    public AtividadeDTO save(AtividadeDTO atividadeDTO) {
        Atividade atividade = convertToEntity(atividadeDTO);
        
        Optional<Projeto> projeto = projetoRepository.findById(atividadeDTO.getProjetoId());
        if (projeto.isPresent()) {
            atividade.setProjeto(projeto.get());
        } else {
            throw new RuntimeException("Projeto não encontrado");
        }

        Optional<Cliente> cliente = clienteRepository.findById(atividadeDTO.getClienteId()); 
        if (cliente.isPresent()) {
            atividade.setCliente(cliente.get()); 
        } else {
            throw new RuntimeException("Cliente não encontrado");
        }

        Atividade savedAtividade = atividadeRepository.save(atividade);
        return convertToDTO(savedAtividade);
    }
    
    public AtividadeDTO update(Long id, AtividadeDTO atividadeDTO) {
        Atividade atividade = atividadeRepository.findById(id).orElse(null);
        if (atividade == null) {
            throw new RuntimeException("Atividade não encontrada");
        }
        
        if(null != atividadeDTO.getNome()) {
        	atividade.setNome(atividadeDTO.getNome());
        }
        
        if(null != atividadeDTO.getDescricao()) {
        	atividade.setDescricao(atividadeDTO.getDescricao());
        }
        

        Optional<Projeto> projeto = projetoRepository.findById(atividadeDTO.getProjetoId());
        if (projeto.isPresent()) {
            atividade.setProjeto(projeto.get());
        } else {
            throw new RuntimeException("Projeto não encontrado");
        }

        // Atualiza o cliente
        Optional<Cliente> cliente = clienteRepository.findById(atividadeDTO.getClienteId());
        if (cliente.isPresent()) {
            atividade.setCliente(cliente.get());
        } else {
            throw new RuntimeException("Cliente não encontrado");
        }

        Atividade updatedAtividade = atividadeRepository.save(atividade);
        return convertToDTO(updatedAtividade);
    }


    public void delete(Long id) {
        atividadeRepository.deleteById(id);
    }

    public List<AtividadeDTO> findByProjeto(Long projetoId) {
        return atividadeRepository.findAll().stream()
            .filter(atividade -> atividade.getProjeto().getId().equals(projetoId))
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    private AtividadeDTO convertToDTO(Atividade atividade) {
        AtividadeDTO atividadeDTO = new AtividadeDTO();
        atividadeDTO.setId(atividade.getId());
        atividadeDTO.setNome(atividade.getNome());
        atividadeDTO.setDescricao(atividade.getDescricao());
        
        if (atividade.getProjeto() != null) {
            atividadeDTO.setProjetoId(atividade.getProjeto().getId());
            atividadeDTO.setProjetoNome(atividade.getProjeto().getNome());  // Assumindo que o Projeto tem um método getNome()
        }

        if (atividade.getCliente() != null) {
            atividadeDTO.setClienteId(atividade.getCliente().getId());
            atividadeDTO.setClienteNome(atividade.getCliente().getNome());  // Assumindo que o Cliente tem um método getNome()
        }

        return atividadeDTO;
    }

    private Atividade convertToEntity(AtividadeDTO atividadeDTO) {
        Atividade atividade = new Atividade();
        atividade.setId(atividadeDTO.getId());
        atividade.setNome(atividadeDTO.getNome());
        atividade.setDescricao(atividadeDTO.getDescricao());

        Projeto projeto = projetoRepository.findById(atividadeDTO.getProjetoId())
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));
        atividade.setProjeto(projeto);

        Cliente cliente = clienteRepository.findById(atividadeDTO.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        atividade.setCliente(cliente);

        return atividade;
    }
}


