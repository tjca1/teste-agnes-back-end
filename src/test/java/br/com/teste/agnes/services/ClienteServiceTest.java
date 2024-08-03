package br.com.teste.agnes.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.teste.agnes.dtos.ClienteDTO;
import br.com.teste.agnes.entities.Cliente;
import br.com.teste.agnes.repositories.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() {
        Cliente cliente1 = new Cliente();
        cliente1.setId(1L);
        cliente1.setNome("Cliente 1");
        cliente1.setEmail("cliente1@example.com");

        Cliente cliente2 = new Cliente();
        cliente2.setId(2L);
        cliente2.setNome("Cliente 2");
        cliente2.setEmail("cliente2@example.com");

        when(clienteRepository.findAll()).thenReturn(Arrays.asList(cliente1, cliente2));

        List<ClienteDTO> result = clienteService.findAll();

        assertEquals(2, result.size());
        assertEquals("Cliente 1", result.get(0).getNome());
    }

    @Test
    public void testFindById() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Cliente 1");
        cliente.setEmail("cliente1@example.com");

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        ClienteDTO result = clienteService.findById(1L);

        assertNotNull(result);
        assertEquals("Cliente 1", result.getNome());
    }

    @Test
    public void testSave() {
        Cliente cliente = new Cliente();
        cliente.setNome("Cliente 1");
        cliente.setEmail("cliente1@example.com");

        Cliente savedCliente = new Cliente();
        savedCliente.setId(1L);
        savedCliente.setNome("Cliente 1");
        savedCliente.setEmail("cliente1@example.com");

        when(clienteRepository.save(any(Cliente.class))).thenReturn(savedCliente);

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNome("Cliente 1");
        clienteDTO.setEmail("cliente1@example.com");

        ClienteDTO result = clienteService.save(clienteDTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testDelete() {
        clienteService.delete(1L);
        verify(clienteRepository, times(1)).deleteById(1L);
    }
}
