package com.falta.controledeaulas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.falta.controledeaulas.entity.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
	List<Aluno> findByNome(String nome);
	Optional<Aluno> findByMatricula(String matricula);
}