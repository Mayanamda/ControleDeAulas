package com.falta.controledeaulas.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.falta.controledeaulas.entity.Aluno;
import com.falta.controledeaulas.entity.ConsolidadoPresencaFalta;
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
    public RegistroPresenca registrarPresenca(Aluno aluno, boolean participou) {
        RegistroPresenca registroPresenca = new RegistroPresenca();
        registroPresenca.setAlunoId(aluno.getId());
        registroPresenca.setData(new Date());
        registroPresenca.setParticipou(participou);

        return registroPresencaRepository.save(registroPresenca);
    }

    public List<Aluno> encontrarAlunosPorNome(String nome) {
        return alunoRepository.findByNome(nome);
    }

    public List<RegistroPresenca> obterRegistrosPorAluno(int alunoId) {
        return registroPresencaRepository.findByAlunoId(alunoId);
    }

    public int contarFaltasPorAluno(int alunoId) {
        return registroPresencaRepository.countFaltasPorAluno(alunoId);
    }

    public List<Aluno> listarAlunos() {
        return alunoRepository.findAll();
    }

    public List<RegistroPresenca> listarRegistrosPresenca() {
        return registroPresencaRepository.findAll();
    }

    public ConsolidadoPresencaFalta calcularConsolidadoPresencaFalta(int alunoId) {
        List<RegistroPresenca> registros = registroPresencaRepository.findByAlunoId(alunoId);

        int totalAulas = registros.size();
        int totalPresencas = 0;
        int totalFaltas = 0;

        for (RegistroPresenca registro : registros) {
            if (registro.isParticipou()) {
                totalPresencas++;
            } else {
                totalFaltas++;
            }
        }

        ConsolidadoPresencaFalta consolidado = new ConsolidadoPresencaFalta(totalPresencas, totalAulas, alunoId, totalFaltas);
        consolidado.setAlunoId(alunoId);
        consolidado.setTotalAulas(totalAulas);
        consolidado.setPresencas(totalPresencas);
        consolidado.setFaltas(totalFaltas);

        return consolidado;
    }

}