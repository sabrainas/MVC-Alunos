package br.edu.fatec.model;

public class Disciplina {
	private int idDisciplina;
	private String nomeDisciplina;
	private int idCurso;
	
	public Disciplina(int idDisciplina, String nomeDisciplina, int idCurso) {
		super();
		this.idDisciplina = idDisciplina;
		this.nomeDisciplina = nomeDisciplina;
		this.idCurso = idCurso;
	}
	
	public Disciplina() {
		
	}

	public int getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(int idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	public int getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}
	
	
}
