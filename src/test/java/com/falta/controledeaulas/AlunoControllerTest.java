package com.falta.controledeaulas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.falta.controledeaulas.controller.AlunoController;
import com.falta.controledeaulas.entity.Aluno;
import com.falta.controledeaulas.repository.AlunoRepository;

public class AlunoControllerTest {

    @InjectMocks
    private AlunoController alunoController;

    @Mock
    private AlunoRepository alunoRepository;

    @BeforeEach
    public void listarAlunos() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dataNascimento = null;
        Date dataNascimento2 = null;
        try {
            dataNascimento = sdf.parse("1991-01-02");
            dataNascimento2 = sdf.parse("1991-02-06");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Aluno aluno = new Aluno(1, "João", "12345", dataNascimento);
        Aluno aluno2 = new Aluno(2, "Maria", "12346", dataNascimento2);

        when(alunoRepository.findById(1)).thenReturn(Optional.of(aluno));
        when(alunoRepository.findById(2)).thenReturn(Optional.of(aluno2));

        when(alunoRepository.save(aluno)).thenReturn(aluno);
        when(alunoRepository.save(aluno2)).thenReturn(aluno2);

        List<Aluno> alunos = new ArrayList<>();
        alunos.add(aluno);
        alunos.add(aluno2);

        when(alunoRepository.findAll()).thenReturn(alunos);
    }


    @Test
    public void testCadastrarAluno() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dataNascimento = null;
        try {
            dataNascimento = sdf.parse("1991-01-02");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Aluno aluno = new Aluno(1, "Maria", "54321", dataNascimento);

        when(alunoRepository.save(any(Aluno.class))).thenReturn(aluno);

        ResponseEntity<Aluno> response = alunoController.cadastrarAluno(aluno);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Maria", response.getBody().getNome());
    }

    @Test
    public void testListarAlunos() {
        ResponseEntity<List<Aluno>> response = alunoController.listarAlunos();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    public void testObterAlunoPorId() {
        ResponseEntity<Aluno> response = alunoController.obterAlunoPorId(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("João", response.getBody().getNome());
    }

    @Test
    public void testAtualizarAluno() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dataNascimento = null;
        try {
            dataNascimento = sdf.parse("1992-02-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Aluno novoAluno = new Aluno(1, "Carlos", "99999", dataNascimento);

        when(alunoRepository.existsById(1)).thenReturn(true);
        when(alunoRepository.save(any(Aluno.class))).thenReturn(novoAluno);

        ResponseEntity<Aluno> response = alunoController.atualizarAluno(1, novoAluno);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Carlos", response.getBody().getNome());
    }

    @Test
    public void testExcluirAluno() {
        ResponseEntity<Void> response = alunoController.excluirAluno(1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}