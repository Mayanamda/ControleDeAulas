package com.falta.controledeaulas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.falta.controledeaulas.entity.Aluno;
import com.falta.controledeaulas.entity.RegistroPresenca;
import com.falta.controledeaulas.service.ControleAulasService;

import java.util.List;

@RestController
@RequestMapping("/api/controle-aulas")
public class ControleAulasController {

    @Autowired
    private ControleAulasService controleAulasService;

    @PostMapping("/alunos")
    public ResponseEntity<Aluno> cadastrarAluno(@RequestBody Aluno aluno) {
        Aluno alunoCadastrado = controleAulasService.cadastrarAluno(aluno);
        return ResponseEntity.ok(alunoCadastrado);
    }
    @PostMapping("/registro-presenca")
    public ResponseEntity<RegistroPresenca> registrarPresenca(@RequestBody RegistroPresenca registroPresenca) {
        Aluno aluno = registroPresenca.getAluno(); 
        boolean participou = registroPresenca.isParticipou();
        
        RegistroPresenca registro = controleAulasService.registrarPresenca(aluno.getId(), participou);
        return ResponseEntity.ok(registro);
    }

    @GetMapping("/alunos")
    public ResponseEntity<List<Aluno>> encontrarAlunosPorNome(@RequestParam String nome) {
        List<Aluno> alunos = controleAulasService.encontrarAlunosPorNome(nome);
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/alunos/{alunoId}/registros-presenca")
    public ResponseEntity<List<RegistroPresenca>> obterRegistrosPorAluno(@PathVariable Long alunoId) {
        List<RegistroPresenca> registros = controleAulasService.obterRegistrosPorAluno(alunoId);
        return ResponseEntity.ok(registros);
    }

    @GetMapping("/alunos/{alunoId}/faltas")
    public ResponseEntity<Integer> contarFaltasPorAluno(@PathVariable Long alunoId) {
        int faltas = controleAulasService.contarFaltasPorAluno(alunoId);
        return ResponseEntity.ok(faltas);
    }

}