package br.com.teste.agnes.services;

import br.com.teste.agnes.dtos.AtividadeDTO;
import br.com.teste.agnes.entities.Atividade;
import br.com.teste.agnes.entities.Cliente;
import br.com.teste.agnes.entities.Projeto;
import br.com.teste.agnes.repositories.AtividadeRepository;
import br.com.teste.agnes.repositories.ClienteRepository;
import br.com.teste.agnes.repositories.ProjetoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AtividadeServiceTest {

    @InjectMocks
    private AtividadeService atividadeService;

    @Mock
    private AtividadeRepository atividadeRepository;

    @Mock
    private ProjetoRepository projetoRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Atividade atividade1 = new Atividade();
        atividade1.setId(1L);
        atividade1.setNome("Atividade 1");
        atividade1.setDescricao("Descrição 1");
        
        Atividade atividade2 = new Atividade();
        atividade2.setId(2L);
        atividade2.setNome("Atividade 2");
        atividade2.setDescricao("Descrição 2");

        when(atividadeRepository.findAll()).thenReturn(Stream.of(atividade1, atividade2).collect(Collectors.toList()));

        List<AtividadeDTO> atividadesDTO = atividadeService.findAll();

        assertEquals(2, atividadesDTO.size());
        assertEquals("Atividade 1", atividadesDTO.get(0).getNome());
        assertEquals("Atividade 2", atividadesDTO.get(1).getNome());
    }

    @Test
    void testFindById() {
        Atividade atividade = new Atividade();
        atividade.setId(1L);
        atividade.setNome("Atividade 1");
        atividade.setDescricao("Descrição 1");

        when(atividadeRepository.findById(1L)).thenReturn(Optional.of(atividade));

        AtividadeDTO atividadeDTO = atividadeService.findById(1L);

        assertNotNull(atividadeDTO);
        assertEquals("Atividade 1", atividadeDTO.getNome());
    }

    @Test
    void testSave() {
        AtividadeDTO atividadeDTO = new AtividadeDTO();
        atividadeDTO.setId(1L);
        atividadeDTO.setNome("Atividade 1");
        atividadeDTO.setDescricao("Descrição 1");
        atividadeDTO.setProjetoId(1L);
        atividadeDTO.setClienteId(1L);

        Projeto projeto = new Projeto();
        projeto.setId(1L);
        projeto.setNome("Projeto 1");

        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Cliente 1");

        Atividade atividade = new Atividade();
        atividade.setId(1L);
        atividade.setNome("Atividade 1");
        atividade.setDescricao("Descrição 1");
        atividade.setProjeto(projeto);
        atividade.setCliente(cliente);

        when(projetoRepository.findById(1L)).thenReturn(Optional.of(projeto));
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(atividadeRepository.save(any(Atividade.class))).thenReturn(atividade);

        AtividadeDTO savedAtividadeDTO = atividadeService.save(atividadeDTO);

        assertNotNull(savedAtividadeDTO);
        assertEquals("Atividade 1", savedAtividadeDTO.getNome());
    }

    @Test
    void testUpdate() {
        AtividadeDTO atividadeDTO = new AtividadeDTO();
        atividadeDTO.setNome("Atividade Atualizada");
        atividadeDTO.setDescricao("Descrição Atualizada");
        atividadeDTO.setProjetoId(1L);
        atividadeDTO.setClienteId(1L);

        Projeto projeto = new Projeto();
        projeto.setId(1L);
        projeto.setNome("Projeto 1");

        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Cliente 1");

        Atividade atividade = new Atividade();
        atividade.setId(1L);
        atividade.setNome("Atividade Original");
        atividade.setDescricao("Descrição Original");
        atividade.setProjeto(projeto);
        atividade.setCliente(cliente);

        when(atividadeRepository.findById(1L)).thenReturn(Optional.of(atividade));
        when(projetoRepository.findById(1L)).thenReturn(Optional.of(projeto));
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(atividadeRepository.save(any(Atividade.class))).thenReturn(atividade);

        AtividadeDTO updatedAtividadeDTO = atividadeService.update(1L, atividadeDTO);

        assertNotNull(updatedAtividadeDTO);
        assertEquals("Atividade Atualizada", updatedAtividadeDTO.getNome());
    }

    @Test
    void testDelete() {
        doNothing().when(atividadeRepository).deleteById(1L);
        assertDoesNotThrow(() -> atividadeService.delete(1L));
    }

    @Test
    void testFindByProjeto() {
        Atividade atividade1 = new Atividade();
        atividade1.setId(1L);
        atividade1.setNome("Atividade 1");
        atividade1.setDescricao("Descrição 1");
        Projeto projeto = new Projeto();
        projeto.setId(1L);
        atividade1.setProjeto(projeto);

        Atividade atividade2 = new Atividade();
        atividade2.setId(2L);
        atividade2.setNome("Atividade 2");
        atividade2.setDescricao("Descrição 2");
        Projeto projeto2 = new Projeto();
        projeto2.setId(2L);
        atividade2.setProjeto(projeto2);

        when(atividadeRepository.findAll()).thenReturn(Stream.of(atividade1, atividade2).collect(Collectors.toList()));

        List<AtividadeDTO> atividadesDTO = atividadeService.findByProjeto(1L);

        assertEquals(1, atividadesDTO.size());
        assertEquals("Atividade 1", atividadesDTO.get(0).getNome());
    }
}
