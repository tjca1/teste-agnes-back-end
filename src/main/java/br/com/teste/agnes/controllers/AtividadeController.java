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

import br.com.teste.agnes.dtos.AtividadeDTO;
import br.com.teste.agnes.services.AtividadeService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/atividades")
public class AtividadeController {

    @Autowired
    private AtividadeService atividadeService;

    @GetMapping
    public ResponseEntity<List<AtividadeDTO>> findAll() {
        List<AtividadeDTO> atividades = atividadeService.findAll();
        if (atividades != null && !atividades.isEmpty()) {
            return ResponseEntity.ok(atividades);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtividadeDTO> findById(@PathVariable Long id) {
        AtividadeDTO atividadeDTO = atividadeService.findById(id);
        return atividadeDTO != null ? new ResponseEntity<>(atividadeDTO, HttpStatus.OK)
                                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<AtividadeDTO> save(@RequestBody AtividadeDTO atividadeDTO) {
        try {
            AtividadeDTO savedAtividadeDTO = atividadeService.save(atividadeDTO);
            return new ResponseEntity<>(savedAtividadeDTO, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        atividadeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/projeto/{projetoId}")
    public ResponseEntity<List<AtividadeDTO>> findByProjeto(@PathVariable Long projetoId) {
        List<AtividadeDTO> atividades = atividadeService.findByProjeto(projetoId);
        if (atividades != null && !atividades.isEmpty()) {
            return ResponseEntity.ok(atividades);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<AtividadeDTO> update(@PathVariable Long id, @RequestBody AtividadeDTO atividadeDTO) {
        try {
            AtividadeDTO updatedAtividadeDTO = atividadeService.update(id, atividadeDTO);
            return new ResponseEntity<>(updatedAtividadeDTO, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
