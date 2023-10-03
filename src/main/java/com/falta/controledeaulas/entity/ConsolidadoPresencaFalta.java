package com.falta.controledeaulas.entity;

public class ConsolidadoPresencaFalta {
    private int presencas;
    private int faltas;
    private Long alunoId;
    private int totalAulas;

  
    public ConsolidadoPresencaFalta(int presencas, int faltas, Long alunoId, int totalAulas) {
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

	public Long getAlunoId() {
		return alunoId;
	}

	public void setAlunoId(Long alunoId) {
		this.alunoId = alunoId;
	}

	public int getTotalAulas() {
		return totalAulas;
	}

	public void setTotalAulas(int totalAulas) {
		this.totalAulas = totalAulas;
	}
    
    
}