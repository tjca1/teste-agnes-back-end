package br.com.teste.agnes.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.teste.agnes.dtos.ProjetoDTO;
import br.com.teste.agnes.entities.Projeto;
import br.com.teste.agnes.entities.Cliente;
import br.com.teste.agnes.enuns.StatusProjeto;
import br.com.teste.agnes.repositories.ProjetoRepository;
import br.com.teste.agnes.repositories.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProjetoServiceTest {

    @Mock
    private ProjetoRepository projetoRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ProjetoService projetoService;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);

        Projeto projeto1 = new Projeto();
        projeto1.setId(1L);
        projeto1.setNome("Projeto 1");
        projeto1.setStatus(StatusProjeto.EM_ANDAMENTO);
        projeto1.setCliente(cliente);

        Projeto projeto2 = new Projeto();
        projeto2.setId(2L);
        projeto2.setNome("Projeto 2");
        projeto2.setStatus(StatusProjeto.CONCLUIDO);
        projeto2.setCliente(cliente);

        when(projetoRepository.findAll()).thenReturn(Arrays.asList(projeto1, projeto2));

        List<ProjetoDTO> result = projetoService.findAll();

        assertEquals(2, result.size());
        assertEquals("Projeto 1", result.get(0).getNome());
    }

    @Test
    public void testFindById() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);

        Projeto projeto = new Projeto();
        projeto.setId(1L);
        projeto.setNome("Projeto 1");
        projeto.setStatus(StatusProjeto.EM_ANDAMENTO);
        projeto.setCliente(cliente);

        when(projetoRepository.findById(1L)).thenReturn(Optional.of(projeto));

        ProjetoDTO result = projetoService.findById(1L);

        assertNotNull(result);
        assertEquals("Projeto 1", result.getNome());
    }

    @Test
    public void testSave() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);

        Projeto projeto = new Projeto();
        projeto.setNome("Projeto 1");
        projeto.setStatus(StatusProjeto.EM_ANDAMENTO);
        projeto.setCliente(cliente);

        Projeto savedProjeto = new Projeto();
        savedProjeto.setId(1L);
        savedProjeto.setNome("Projeto 1");
        savedProjeto.setStatus(StatusProjeto.EM_ANDAMENTO);
        savedProjeto.setCliente(cliente);

        when(projetoRepository.save(any(Projeto.class))).thenReturn(savedProjeto);
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        ProjetoDTO projetoDTO = new ProjetoDTO();
        projetoDTO.setNome("Projeto 1");
        projetoDTO.setStatus("EM_ANDAMENTO");
        projetoDTO.setClienteId(1L);

        ProjetoDTO result = projetoService.save(projetoDTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testDelete() {
        projetoService.delete(1L);
        verify(projetoRepository, times(1)).deleteById(1L);
    }
}
