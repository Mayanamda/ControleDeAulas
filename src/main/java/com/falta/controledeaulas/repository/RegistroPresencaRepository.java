package com.falta.controledeaulas.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.falta.controledeaulas.entity.RegistroPresenca;

public interface RegistroPresencaRepository extends JpaRepository<RegistroPresenca, Integer> {
	List<RegistroPresenca> findByAlunoId(int alunoId);


	    @Query("SELECT COUNT(rp) FROM RegistroPresenca rp WHERE rp.alunoId = :alunoId AND rp.participou = true")
	    int countPresencasPorAluno(int alunoId);

	    @Query("SELECT COUNT(rp) FROM RegistroPresenca rp WHERE rp.alunoId = :alunoId AND rp.participou = false")
	    int countFaltasPorAluno(int alunoId);

	    @Query("SELECT rp.data FROM RegistroPresenca rp WHERE rp.alunoId = :alunoId AND rp.participou = false")
	    List<Date> listarDatasFaltasPorAluno(int alunoId);

	    @Query("SELECT rp.data FROM RegistroPresenca rp WHERE rp.alunoId = :alunoId AND rp.participou = true")
	    List<Date> listarDatasPresencasPorAluno(int alunoId);
	
}