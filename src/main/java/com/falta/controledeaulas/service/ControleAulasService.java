package com.falta.controledeaulas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.falta.controledeaulas.entity.Aluno;
import com.falta.controledeaulas.entity.RegistroPresenca;
import com.falta.controledeaulas.repository.AlunoRepository;
import com.falta.controledeaulas.repository.RegistroPresencaRepository;

@Service
public class ControleAulasService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private RegistroPresencaRepository registroPresencaRepository;

    @Transactional
    public Aluno cadastrarAluno(Aluno aluno) {
        Optional<Aluno> alunoExistente = alunoRepository.findByMatricula(aluno.getMatricula());
        if (alunoExistente.isPresent()) {
            throw new IllegalArgumentException("Já existe um aluno com a mesma matrícula.");
        }
        
        return alunoRepository.save(aluno);
    }

    @Transactional
    public RegistroPresenca registrarPresenca(Long alunoId, boolean participou) {
        Optional<Aluno> alunoOptional = alunoRepository.findById(alunoId);
        if (!alunoOptional.isPresent()) {
            throw new IllegalArgumentException("Aluno não encontrado.");
        }

        Aluno aluno = alunoOptional.get();

        RegistroPresenca registroPresenca = new RegistroPresenca();
        registroPresenca.setAluno(aluno);
        registroPresenca.setParticipou(participou);

        return registroPresencaRepository.save(registroPresenca);
    }
    
    public List<Aluno> encontrarAlunosPorNome(String nome) {
        return alunoRepository.findByNome(nome);
    }

    public List<RegistroPresenca> obterRegistrosPorAluno(Long alunoId) {
        return registroPresencaRepository.findByAlunoId(alunoId);
    }

    public int contarFaltasPorAluno(Long alunoId) {
        return registroPresencaRepository.countFaltasPorAluno(alunoId);
    }
    

}