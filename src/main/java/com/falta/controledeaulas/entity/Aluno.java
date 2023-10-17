package com.falta.controledeaulas.entity;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Aluno {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
    private int id;
    private String nome;
    private String matricula;
    @Column(name = "DATA_NASCIMENTO")
    private Date dataNascimento;
    
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;	
	}
	
	 public Aluno() {
	        
	}
	 
	public Aluno(int id, String nome, String matricula, Date dataNascimento) {
	    this.id = id;
	    this.nome = nome;
	    this.matricula = matricula;
	    this.dataNascimento = dataNascimento;
	}


       
    
}