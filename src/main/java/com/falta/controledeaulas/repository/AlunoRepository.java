package com.falta.controledeaulas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.falta.controledeaulas.entity.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
	    List<Aluno> findByNome(String nome);
	    Optional<Aluno> findByMatricula(String matricula);
	
}