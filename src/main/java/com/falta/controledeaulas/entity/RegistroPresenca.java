package com.falta.controledeaulas.entity;


import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class RegistroPresenca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int alunoId;
    private Date data;
    private boolean participou;

   
    public RegistroPresenca() {
    }

    public RegistroPresenca(int alunoId, Date data, boolean participou) {
        this.alunoId = alunoId;
        this.data = data;
        this.participou = participou;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAlunoId() {
		return alunoId;
	}

	public void setAlunoId(int alunoId) {
		this.alunoId = alunoId;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public boolean isParticipou() {
		return participou;
	}

	public void setParticipou(boolean participou) {
		this.participou = participou;
	}
    
    
}