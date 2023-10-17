package com.falta.controledeaulas.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.falta.controledeaulas.entity.ConsolidadoPresencaFalta;
import com.falta.controledeaulas.entity.RegistroPresenca;
import com.falta.controledeaulas.repository.RegistroPresencaRepository;
import com.falta.controledeaulas.service.ControleAulasService;

@RestController
@RequestMapping("/api/registros-aula")
public class ControleAulasController {

    @Autowired
    private RegistroPresencaRepository controleAulaRepository;
    @Autowired
    private ControleAulasService controleAulasService;

    @PostMapping("/")
    public ResponseEntity<RegistroPresenca> registrarPresenca(@RequestBody RegistroPresenca registroPresenca) {
    	RegistroPresenca registroPresencaSalvo = controleAulaRepository.save(registroPresenca);
        return new ResponseEntity<>(registroPresencaSalvo, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<RegistroPresenca>> listarRegistrosPresenca() {
        List<RegistroPresenca> registrosAula = controleAulaRepository.findAll();
        return ResponseEntity.ok(registrosAula);
    }

    @GetMapping("/alunos/{alunoId}/registros-presenca")
    public ResponseEntity<List<RegistroPresenca>> listarPresencasPorAluno(@PathVariable int alunoId) {
        List<RegistroPresenca> todosOsRegistros = controleAulaRepository.findByAlunoId(alunoId);
        
        List<RegistroPresenca> presencas = todosOsRegistros
                .stream()
                .filter(registro -> registro.isParticipou())
                .collect(Collectors.toList());

        return ResponseEntity.ok(presencas);
    }
    
    @GetMapping("/alunos/{alunoId}/faltas")
    public ResponseEntity<List<RegistroPresenca>> listarFaltasPorAluno(@PathVariable int alunoId) {
        List<RegistroPresenca> todosOsRegistros = controleAulaRepository.findByAlunoId(alunoId);
        
        List<RegistroPresenca> faltas = todosOsRegistros
                .stream()
                .filter(registro -> !registro.isParticipou())
                .collect(Collectors.toList());

        return ResponseEntity.ok(faltas);
    }
    
    @GetMapping("/alunos/{alunoId}/consolidado-presenca-falta")
    public ResponseEntity<ConsolidadoPresencaFalta> obterConsolidadoPresencaFalta(@PathVariable int alunoId) {
        ConsolidadoPresencaFalta consolidadoDto = controleAulasService.calcularConsolidadoPresencaFalta(alunoId);
        return ResponseEntity.ok(consolidadoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistroPresenca> atualizarRegistroPresenca(@PathVariable int id, @RequestBody RegistroPresenca novoRegistroAula) {
        if (!controleAulaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        novoRegistroAula.setId(id);
        RegistroPresenca registroAulaAtualizado = controleAulaRepository.save(novoRegistroAula);
        return ResponseEntity.ok(registroAulaAtualizado);
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirRegistroPresenca(@PathVariable int id) {
        if (!controleAulaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        controleAulaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}