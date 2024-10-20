package br.edu.fatec.model;

public class Notas {
	private int idNota;
	private int idDisciplina;
	private String disciplina;
	private String semestre;
	private Double nota;
	private Double nota2;
	private Double media;
	private int faltas;
	private int raAluno;
	
	public Notas(int idNota, String disciplina, String semestre, Double nota, Double nota2, Double media, int faltas, int raAluno, int idDisciplina) {
		super();
		this.idNota = idNota;
		this.disciplina = disciplina;
		this.semestre = semestre;
		this.nota = nota;
		this.nota2 = nota2;
		this.media = media;
		this.faltas = faltas;
		this.raAluno = raAluno;
		this.idDisciplina = idDisciplina;
	}
	
	public Double getNota2() {
		return (nota2 != null) ? nota2 : 0.0;
	}

	public void setNota2(double nota2) {
		this.nota2 = nota2;
	}

	public Double getMedia() {
		return media;
	}

	public void setMedia(double media) {
		this.media = media;
	}

	public int getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(int idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

	public int getRaAluno() {
		return raAluno;
	}

	public void setRaAluno(int raAluno) {
		this.raAluno = raAluno;
	}

	public Notas() {
		
	}

	public int getIdNota() {
		return idNota;
	}

	public void setIdNota(int idNota) {
		this.idNota = idNota;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public Double getNota() {
		return (nota != null) ? nota : 0.0;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public int getFaltas() {
		return faltas;
	}

	public void setFaltas(int faltas) {
		this.faltas = faltas;
	}
		
}
