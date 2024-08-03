package br.com.teste.agnes.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.teste.agnes.entities.Atividade;
import br.com.teste.agnes.entities.Cliente;
import br.com.teste.agnes.entities.Projeto;
import br.com.teste.agnes.enuns.StatusProjeto;
import br.com.teste.agnes.repositories.AtividadeRepository;
import br.com.teste.agnes.repositories.ClienteRepository;
import br.com.teste.agnes.repositories.ProjetoRepository;

@Configuration
public class DataInitializer {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Bean
    public CommandLineRunner loadData() {
        return args -> {

        	Cliente cliente1 = new Cliente();
            cliente1.setNome("Cliente A");
            cliente1.setTelefone("1234-5678");
            cliente1.setEmail("clienteA@example.com");
            clienteRepository.save(cliente1);

            Cliente cliente2 = new Cliente();
            cliente2.setNome("Cliente B");
            cliente2.setTelefone("2345-6789");
            cliente2.setEmail("clienteB@example.com");
            clienteRepository.save(cliente2);

            Cliente cliente3 = new Cliente();
            cliente3.setNome("Cliente C");
            cliente3.setTelefone("3456-7890");
            cliente3.setEmail("clienteC@example.com");
            clienteRepository.save(cliente3);


            Projeto projeto1 = new Projeto();
            projeto1.setNome("Projeto X");
            projeto1.setStatus(StatusProjeto.EM_ANDAMENTO);
            projeto1.setCliente(cliente1);
            projetoRepository.save(projeto1);

            Projeto projeto2 = new Projeto();
            projeto2.setNome("Projeto Y");
            projeto2.setStatus(StatusProjeto.CONCLUIDO);
            projeto2.setCliente(cliente2);
            projetoRepository.save(projeto2);

            Projeto projeto3 = new Projeto();
            projeto3.setNome("Projeto Z");
            projeto3.setStatus(StatusProjeto.CANCELADO);
            projeto3.setCliente(cliente3);
            projetoRepository.save(projeto3);

            Projeto projeto4 = new Projeto();
            projeto4.setNome("Projeto W");
            projeto4.setStatus(StatusProjeto.EM_ANDAMENTO);
            projeto4.setCliente(cliente1);
            projetoRepository.save(projeto4);


            Atividade atividade1 = new Atividade();
            atividade1.setNome("Atividade 1");
            atividade1.setDescricao("Descrição da Atividade 1");
            atividade1.setProjeto(projeto1);
            atividadeRepository.save(atividade1);

            Atividade atividade2 = new Atividade();
            atividade2.setNome("Atividade 2");
            atividade2.setDescricao("Descrição da Atividade 2");
            atividade2.setProjeto(projeto1);
            atividadeRepository.save(atividade2);

            Atividade atividade3 = new Atividade();
            atividade3.setNome("Atividade 3");
            atividade3.setDescricao("Descrição da Atividade 3");
            atividade3.setProjeto(projeto2);
            atividadeRepository.save(atividade3);

            Atividade atividade4 = new Atividade();
            atividade4.setNome("Atividade 4");
            atividade4.setDescricao("Descrição da Atividade 4");
            atividade4.setProjeto(projeto3);
            atividadeRepository.save(atividade4);

            Atividade atividade5 = new Atividade();
            atividade5.setNome("Atividade 5");
            atividade5.setDescricao("Descrição da Atividade 5");
            atividade5.setProjeto(projeto4);
            atividadeRepository.save(atividade5);
        };
    }
}
