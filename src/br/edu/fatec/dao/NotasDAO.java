package br.edu.fatec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	public int buscarIdDisciplina(String nomeDisciplina) throws Exception {
	    int idDisciplina = 11;
	    String sql = "SELECT idDisciplina FROM disciplinas WHERE nomeDisciplina = ?";

	    try {
	        PreparedStatement ps = this.conn.prepareStatement(sql);
	        ps.setString(1, nomeDisciplina);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            idDisciplina = rs.getInt("idDisciplina");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return idDisciplina;
	}
	//============================= UPDATE ============================//
	public void salvar(Notas notas) throws Exception {
	    double notaValue = notas.getNota();
	    
	    // Verifica se a nota está no intervalo permitido
	    if (notaValue < 0 || notaValue > 10) {
	        throw new Exception("O valor não pode ser menor que zero ou maior que 10");
	    }
	    String checkDisciplinaSQL = "SELECT idDisciplina FROM disciplinas WHERE idDisciplina = ?";
	    try (PreparedStatement psCheck = this.conn.prepareStatement(checkDisciplinaSQL)) {
	        psCheck.setInt(1, notas.getIdDisciplina());
	        ResultSet rsCheck = psCheck.executeQuery();
	        if (!rsCheck.next()) {
	            throw new Exception("idDisciplina não existe: " + notas.getIdDisciplina());
	        }
	    }

	    String SQL = "INSERT INTO notas(disciplina, semestre, nota, faltas, raAluno, idDisciplina) VALUES(?,?,?,?,?,?)";
	    try (PreparedStatement ps = this.conn.prepareStatement(SQL)) {
	        ps.setString(1, notas.getDisciplina());
	        ps.setString(2, notas.getSemestre());
	        ps.setDouble(3, notaValue);
	        ps.setInt(4, notas.getFaltas());
	        ps.setInt(5, notas.getRaAluno());
	        ps.setInt(6, notas.getIdDisciplina());
	        ps.executeUpdate();
	    } catch (SQLException sqle) {
	        throw new Exception("Erro ao inserir dados: " + sqle.getMessage(), sqle);
	    }
	}
	//================================ DELETE ======================================//
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
	
	//============================= CONSULTAR ===================================//
	public Notas consultar(int idNota) throws Exception{
	    Notas notas = null;
	    
	    if (idNota <= 0) // Validação do ID
	        throw new Exception("O valor passado não pode ser nulo ou negativo.");
	    
	    String SQL = "SELECT * FROM notas WHERE idNotas = ?";
	    
	    try (PreparedStatement ps = conn.prepareStatement(SQL)) {  // Usando try-with-resources
	        ps.setInt(1, idNota);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                int id = rs.getInt("idNotas");
	                String disciplina = rs.getString("disciplina");
	                String semestre = rs.getString("semestre");
	                double nota = rs.getDouble("nota");
	                int faltas = rs.getInt("faltas");
	                int raAluno = rs.getInt("raAluno");
	                int idDisciplina = rs.getInt("idDisciplina");
	                notas = new Notas(id, disciplina, semestre, nota, faltas, raAluno, idDisciplina);
	            }
	        }
	    } catch (SQLException sqle) {
	        throw new Exception("Erro ao consultar nota: " + sqle.getMessage(), sqle);
	    }
	    
	    return notas;
	}

	
	//================================== CONSULTA - BOLETIM ==============================//
	public Notas consultar(int raAluno, int idDisciplina) throws Exception {
	    Notas notas = null;  
	    Connection conn = null;  
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	    	System.out.println("RA Aluno: " + raAluno + ", ID Disciplina: " + idDisciplina);
	        String SQL = "SELECT * FROM notas WHERE raAluno = ? AND idDisciplina = ?";
	        conn = ConnectionFactory.getConnection(); // Garantir que a conexão seja aberta corretamente
	        ps = conn.prepareStatement(SQL);
	        ps.setInt(1, raAluno);
	        ps.setInt(2, idDisciplina);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            int id = rs.getInt("idNotas");
	            String disciplina = rs.getString("disciplina"); // Verificar se isso faz sentido ou precisa buscar de outra tabela
	            String semestre = rs.getString("semestre");
	            double nota = rs.getDouble("nota");
	            int faltas = rs.getInt("faltas");
	            notas = new Notas(id, disciplina, semestre, nota, faltas, raAluno, idDisciplina);
	        }
	        
	        return notas;
	    } catch (SQLException sqle) {
	        throw new Exception("Erro ao consultar notas: " + sqle.getMessage());
	    } finally {
	        ConnectionFactory.closeConnection(conn, ps, rs); // Fechar corretamente a conexão
	    }
	}

	
	//================================ EDITAR ==================================//
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
