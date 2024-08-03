package br.com.teste.agnes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.teste.agnes.dtos.ProjetoDTO;
import br.com.teste.agnes.services.ProjetoService;

@RestController
@RequestMapping("/projetos")
@CrossOrigin(origins = "*") 
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @GetMapping
    public ResponseEntity<List<ProjetoDTO>> findAll() {
        List<ProjetoDTO> projetos = projetoService.findAll();
        if (projetos != null && !projetos.isEmpty() ) {
            return ResponseEntity.ok(projetos);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetoDTO> findById(@PathVariable Long id) {
        ProjetoDTO projetoDTO = projetoService.findById(id);
        return projetoDTO != null ? new ResponseEntity<>(projetoDTO, HttpStatus.OK)
                                  : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<ProjetoDTO> save(@RequestBody ProjetoDTO projetoDTO) {
        ProjetoDTO savedProjetoDTO = projetoService.save(projetoDTO);
        return new ResponseEntity<>(savedProjetoDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        projetoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ProjetoDTO> updateCliente(@PathVariable Long id, @RequestBody ProjetoDTO projetoDTO) {
    	projetoDTO.setId(id);
    	ProjetoDTO updatedProjeto = projetoService.save(projetoDTO);
        if (updatedProjeto != null) {
            return ResponseEntity.ok(updatedProjeto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
