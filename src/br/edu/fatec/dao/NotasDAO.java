package br.edu.fatec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.fatec.model.Aluno;
import br.edu.fatec.model.Notas;
import br.edu.fatec.util.ConnectionFactory;

public class NotasDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	private Notas notas;
	
	public NotasDAO() throws Exception{
		try {
			this.conn = ConnectionFactory.getConnection();
		}catch(Exception e) {
			throw new Exception("erro: \n" + e.getMessage());
		}
	}
	
	public void salvar(Notas notas) throws Exception {
	    double notaValue = notas.getNota();
	    
	    // Verifica se a nota está no intervalo permitido
	    if (notaValue < 0 || notaValue > 10) {
	        throw new Exception("O valor não pode ser menor que zero ou maior que 10");
	    }

	    // Usa try-with-resources para gerenciar recursos automaticamente
	    String SQL = "INSERT INTO notas(disciplina, semestre, nota, faltas, raAluno) VALUES(?,?,?,?,?)";
	    try (Connection conn = this.conn; 
	         PreparedStatement ps = conn.prepareStatement(SQL)) {
	         
	        // Define os parâmetros da consulta
	        ps.setString(1, notas.getDisciplina());
	        ps.setString(2, notas.getSemestre());
	        ps.setDouble(3, notaValue);
	        ps.setInt(4, notas.getFaltas());
	        ps.setInt(5, notas.getRaAluno());
	        
	        // Executa a atualização
	        ps.executeUpdate();
	    } catch (SQLException sqle) {
	        throw new Exception("Erro ao inserir dados: " + sqle.getMessage(), sqle);
	    }
	}

	public void excluir(Notas notas) throws Exception{
		if(notas == null)
			throw new Exception("O valor passado não pode ser nulo");
		try {
			String SQL = "DELETE FROM notas WHERE idNota = ?";
			conn = this.conn;
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, notas.getIdNota());
			ps.executeUpdate();
		}catch(SQLException sqle) {
			throw new Exception("Erro ao excluir dados " + sqle);
		}finally {
			ConnectionFactory.closeConnection(conn, ps);
		}
	}
	
	public Notas consultar(int idNota) throws Exception{
		if(notas == null)
			throw new Exception("O valor passado não pode ser nulo");
		
		try{
			String SQL = "SELECT notas WHERE idNota=?";
			conn = this.conn;
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, idNota);
			rs = ps.executeQuery();
			if(rs.next()) {
				int id = rs.getInt(1);
				String disciplina = rs.getString("disciplina");
				String semestre = rs.getString("semestre");
				double nota = rs.getDouble("nota");
				int faltas = rs.getInt("faltas");
				int raAluno = rs.getInt("raAluno");
				notas = new Notas(id, disciplina, semestre, nota, faltas, raAluno);
			}
			return notas;
		}catch(SQLException sqle) {
			throw new Exception(sqle);
		}finally {
			ConnectionFactory.closeConnection(conn, ps, rs);
		}
	}
	public void editar(Notas notas) throws Exception{
		try {
			if(notas == null)
				throw new Exception("O valor passado não pode ser nulo");
			
			String SQL = "UPDATE notas SET disciplina=?,semestre=?,nota=?,faltas=?,raAluno=? WHERE idNota=?";
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, notas.getIdNota());
			ps.setString(2, notas.getDisciplina());
			ps.setString(3, notas.getSemestre());
			ps.setDouble(4, notas.getNota());
			ps.setInt(4, notas.getFaltas());
			ps.setInt(5, notas.getRaAluno());
			ps.executeUpdate();
		}catch(Exception e) {
			throw new Exception("Erro ao atualizar os dados " + e.getMessage());
		}
	}
}
