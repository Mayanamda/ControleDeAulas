package com.falta.controledeaulas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.falta.controledeaulas.entity.Aluno;
import com.falta.controledeaulas.entity.RegistroPresenca;
import com.falta.controledeaulas.service.ControleAulasService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DataJpaTest
@Transactional
public class ControleAulasServiceTest {

    @Autowired
    private ControleAulasService controleAulasService;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testCadastrarAluno() {
        Aluno aluno = new Aluno("João", "12345", parseDate("1990-01-01"));
        Aluno alunoCadastrado = controleAulasService.cadastrarAluno(aluno);
        assertEquals(aluno.getNome(), alunoCadastrado.getNome());
    }

    @Test
    @Sql("/data.sql")
    public void testRegistrarPresenca() {
        RegistroPresenca registroPresenca = new RegistroPresenca(1L, parseDate("2023-09-28"), true);
        Aluno aluno = new Aluno("Maria", "Matricula123", new Date());
        aluno.setId(registroPresenca.getAlunoId());
        RegistroPresenca registroPresencaSalvo = controleAulasService.registrarPresenca(aluno, registroPresenca.isParticipou());
        assertEquals(registroPresenca.isParticipou(), registroPresencaSalvo.isParticipou());
    }

    @Test
    public void testListarAlunos() {
        List<Aluno> alunos = controleAulasService.listarAlunos();
        assertEquals(0, alunos.size());
    }

    @Test
    public void testListarRegistrosPresenca() {
        List<RegistroPresenca> registrosPresenca = controleAulasService.listarRegistrosPresenca();
        assertEquals(0, registrosPresenca.size());
    }

    private Date parseDate(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(dateString);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}