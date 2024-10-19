package br.edu.fatec.model;

public class Curso {
	private int idCurso;
	private String curso;
	private String campus;
	private String periodo;
	public Curso(int idCurso, String curso, String campus, String periodo) {
		super();
		this.idCurso = idCurso;
		this.curso = curso;
		this.campus = campus;
		this.periodo = periodo;
	}
	
	public Curso() {
		
	}

	public int getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
}
