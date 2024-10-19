package br.edu.fatec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.fatec.model.Aluno;
import br.edu.fatec.model.Curso;
import br.edu.fatec.util.ConnectionFactory;

public class CursoDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	private Curso curso;
	
	public CursoDAO() throws Exception{
		try {
			this.conn = ConnectionFactory.getConnection();
		}catch(Exception e) {
			throw new Exception("erro: \n" + e.getMessage());
		}
	}
	

	public void excluir(Curso curso) throws Exception{
		if(curso == null)
			throw new Exception("O valor passado não pode ser nulo");
		try {
			String SQL = "DELETE FROM cursos WHERE idCurso = ?";
			conn = this.conn;
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, curso.getIdCurso());
			ps.executeUpdate();
		}catch(SQLException sqle) {
			throw new Exception("Erro ao excluir dados " + sqle);
		}finally {
			ConnectionFactory.closeConnection(conn, ps);
		}
	}
	public Curso consultar(String nomeCurso, String campus, String periodo) throws Exception{
		Curso curso = null;
		
		try{
			String SQL = "SELECT idCurso FROM cursos WHERE curso=? AND campus=? AND periodo=?";
			conn = this.conn;
			ps = conn.prepareStatement(SQL);
			ps.setString(1, nomeCurso);
			ps.setString(2, campus);
			ps.setString(3, periodo);
			rs = ps.executeQuery();
			if(rs.next()) {
				curso = new Curso();
				curso.setIdCurso(rs.getInt("idCurso"));
			}
		}catch(SQLException sqle) {
			throw new Exception(sqle);
		}finally {
			ConnectionFactory.closeConnection(conn, ps, rs);
		}
		return curso;
	}
	public Curso consultar(int raAluno) throws Exception{
		Curso curso = null;
		try {
			String SQL = "SELECT c.* FROM cursos c INNER JOIN alunos a ON c.idCurso = a.idCurso WHERE a.raAluno=?";
			conn = this.conn;
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, raAluno);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				int idCurso = rs.getInt("idCurso");
				String nomeCurso = rs.getString("curso");
				String campus = rs.getString("campus");
				String periodo = rs.getString("periodo");
				curso = new Curso(idCurso, nomeCurso, campus, periodo);
			}
		}catch(SQLException sqle) {
			throw new Exception(sqle);
		}finally {
			ConnectionFactory.closeConnection(conn, ps, rs);
		}
		return curso;
	}
	public void editar(Curso curso) throws Exception{
		try {
			if(curso == null)
				throw new Exception("O valor passado não pode ser nulo");
			
			String SQL = "UPDATE cursos SET curso=?,campus=?,periodo=? WHERE idCurso=?";
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, curso.getIdCurso());
			ps.setString(2, curso.getCurso());
			ps.setString(3, curso.getCampus());
			ps.setString(4, curso.getPeriodo());
			ps.executeUpdate();
		}catch(Exception e) {
			throw new Exception("Erro ao atualizar os dados " + e.getMessage());
		}
	}
}