package com.falta.controledeaulas.entity;

public class ConsolidadoPresencaFalta {
    private int presencas;
    private int faltas;
    private int alunoId;
    private int totalAulas;

  
    public ConsolidadoPresencaFalta(int presencas, int faltas, int alunoId, int totalAulas) {
		super();
		this.presencas = presencas;
		this.faltas = faltas;
		this.alunoId = alunoId;
		this.totalAulas = totalAulas;
	}

	public int getPresencas() {
        return presencas;
    }

    public void setPresencas(int presencas) {
        this.presencas = presencas;
    }

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

	public int getAlunoId() {
		return alunoId;
	}

	public void setAlunoId(int alunoId) {
		this.alunoId = alunoId;
	}

	public int getTotalAulas() {
		return totalAulas;
	}

	public void setTotalAulas(int totalAulas) {
		this.totalAulas = totalAulas;
	}
    
    
}