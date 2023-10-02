package com.falta.controledeaulas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.falta.controledeaulas.entity.RegistroPresenca;

public interface RegistroPresencaRepository extends JpaRepository<RegistroPresenca, Long> {
	List<RegistroPresenca> findByAlunoId(Long id);
	
	@Query("SELECT COUNT(rp) FROM RegistroPresenca rp WHERE rp.aluno.id = :alunoId AND rp.participou = false")
    int countFaltasPorAluno(@Param("alunoId") Long alunoId);
}