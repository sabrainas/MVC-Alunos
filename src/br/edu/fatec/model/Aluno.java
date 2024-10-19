package br.edu.fatec.model;

public class Aluno {
	private int raAluno;
	private String nomeAluno;
	private String emailAluno;
	private String dataNascimento;
	private String enderecoAluno;
	private String celularAluno;
	private String municipioAluno;
	private String ufAluno;
	private String cpfAluno;
	private int idCurso;
	
	public Aluno(int raAluno, String nomeAluno, String emailAluno, String dataNascimento, String enderecoAluno,
			String celularAluno, String municipioAluno, String ufAluno, String cpfAluno, int idCurso) {
		super();
		this.raAluno = raAluno;
		this.nomeAluno = nomeAluno;
		this.emailAluno = emailAluno;
		this.dataNascimento = dataNascimento;
		this.enderecoAluno = enderecoAluno;
		this.celularAluno = celularAluno;
		this.municipioAluno = municipioAluno;
		this.ufAluno = ufAluno;
		this.cpfAluno = cpfAluno;
		this.idCurso = idCurso;
	}
	public Aluno() {
		
	}
	
	public int getIdCurso() {
		return idCurso;
	}
	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}
	public int getRaAluno() {
		return raAluno;
	}
	public void setRaAluno(int raAluno) {
		this.raAluno = raAluno;
	}
	public String getNomeAluno() {
		return nomeAluno;
	}
	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}
	public String getEmailAluno() {
		return emailAluno;
	}
	public void setEmailAluno(String emailAluno) {
		this.emailAluno = emailAluno;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getEnderecoAluno() {
		return enderecoAluno;
	}
	public void setEnderecoAluno(String enderecoAluno) {
		this.enderecoAluno = enderecoAluno;
	}
	public String getCelularAluno() {
		return celularAluno;
	}
	public void setCelularAluno(String celularAluno) {
		this.celularAluno = celularAluno;
	}
	public String getMunicipioAluno() {
		return municipioAluno;
	}
	public void setMunicipioAluno(String municipioAluno) {
		this.municipioAluno = municipioAluno;
	}
	public String getUfAluno() {
		return ufAluno;
	}
	public void setUfAluno(String ufAluno) {
		this.ufAluno = ufAluno;
	}
	public String getCpfAluno() {
		return cpfAluno;
	}
	public void setCpfAluno(String cpfAluno) {
		this.cpfAluno = cpfAluno;
	}
	
}
