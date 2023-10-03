package com.falta.controledeaulas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.falta.controledeaulas.entity.Aluno;
import com.falta.controledeaulas.entity.RegistroPresenca;
import com.falta.controledeaulas.exception.AlunoNotFoundException;
import com.falta.controledeaulas.repository.AlunoRepository;
import com.falta.controledeaulas.repository.RegistroPresencaRepository;
import com.falta.controledeaulas.service.ControleAulasService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/registros-aula")
public class ControleAulasController {

    @Autowired
    private RegistroPresencaRepository controleAulaRepository;

    @PostMapping
    public ResponseEntity<RegistroPresenca> registrarPresenca(@RequestBody RegistroPresenca registroPresenca) {
    	RegistroPresenca registroPresencaSalvo = controleAulaRepository.save(registroPresenca);
        return new ResponseEntity<>(registroPresencaSalvo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RegistroPresenca>> listarRegistrosPresenca() {
        List<RegistroPresenca> registrosAula = controleAulaRepository.findAll();
        return ResponseEntity.ok(registrosAula);
    }

    // Endpoint para obter detalhes de um registro de presença/falta pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<RegistroPresenca> obterRegistroPresencaPorId(@PathVariable Long id) {
        Optional<RegistroPresenca> registroAulaOptional = controleAulaRepository.findById(id);
        return registroAulaOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistroPresenca> atualizarRegistroPresenca(@PathVariable Long id, @RequestBody RegistroPresenca novoRegistroAula) {
        if (!controleAulaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        novoRegistroAula.setId(id);
        RegistroPresenca registroAulaAtualizado = controleAulaRepository.save(novoRegistroAula);
        return ResponseEntity.ok(registroAulaAtualizado);
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirRegistroPresenca(@PathVariable Long id) {
        if (!controleAulaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        controleAulaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}