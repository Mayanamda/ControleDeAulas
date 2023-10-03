package com.falta.controledeaulas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.falta.controledeaulas.entity.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    
}