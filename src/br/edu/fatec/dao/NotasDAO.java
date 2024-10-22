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
	    if (notas == null) {
	        throw new Exception("O valor passado não pode ser nulo");
	    }

	    String checkDisciplinaSQL = "SELECT idDisciplina FROM disciplinas WHERE idDisciplina = ?";
	    try (PreparedStatement psCheck = this.conn.prepareStatement(checkDisciplinaSQL)) {
	        psCheck.setInt(1, notas.getIdDisciplina());
	        ResultSet rsCheck = psCheck.executeQuery();
	        if (!rsCheck.next()) {
	            throw new Exception("idDisciplina não existe: " + notas.getIdDisciplina());
	        }
	    }

	    // Verifica se a nota já existe
	    if (notasExistem(notas.getRaAluno(), notas.getIdDisciplina())) {
	        // Atualiza a nota existente
	        String updateSQL = "UPDATE notas SET disciplina = ?, semestre = ?, nota = ?, nota2 = ?, media = ?, faltas = ? WHERE raAluno = ? AND idDisciplina = ?";
	        try (PreparedStatement psUpdate = this.conn.prepareStatement(updateSQL)) {
	            psUpdate.setString(1, notas.getDisciplina());
	            psUpdate.setString(2, notas.getSemestre());
	            psUpdate.setDouble(3, notas.getNota() != null ? notas.getNota() : 0.0);
	            psUpdate.setDouble(4, notas.getNota2() != null ? notas.getNota2() : 0.0);
	            psUpdate.setDouble(5, notas.getMedia() != null ? notas.getMedia() : 0.0);
	            psUpdate.setInt(6, notas.getFaltas());
	            psUpdate.setInt(7, notas.getRaAluno());
	            psUpdate.setInt(8, notas.getIdDisciplina());
	            psUpdate.executeUpdate();
	        } catch (SQLException sqle) {
	            throw new Exception("Erro ao atualizar dados: " + sqle.getMessage(), sqle);
	        }
	    } else {
	        // Insere a nova nota
	        String insertSQL = "INSERT INTO notas(idNotas, disciplina, semestre, nota, nota2, media, faltas, raAluno, idDisciplina) VALUES(?,?,?,?,?,?,?,?,?)";
	        try (PreparedStatement psInsert = this.conn.prepareStatement(insertSQL)) {
	            psInsert.setInt(1, notas.getIdNota());
	            psInsert.setString(2, notas.getDisciplina());
	            psInsert.setString(3, notas.getSemestre());
	            psInsert.setDouble(4, notas.getNota() != null ? notas.getNota() : 0.0);
	            psInsert.setDouble(5, notas.getNota2() != null ? notas.getNota2() : 0.0);
	            psInsert.setDouble(6, notas.getMedia() != null ? notas.getMedia() : 0.0);
	            psInsert.setInt(7, notas.getFaltas());
	            psInsert.setInt(8, notas.getRaAluno());
	            psInsert.setInt(9, notas.getIdDisciplina());
	            psInsert.executeUpdate();
	        } catch (SQLException sqle) {
	            throw new Exception("Erro ao inserir dados: " + sqle.getMessage(), sqle);
	        }
	    }
	}

	private boolean notasExistem(int raAluno, int idDisciplina) throws SQLException {
	    String SQL = "SELECT COUNT(*) FROM notas WHERE raAluno = ? AND idDisciplina = ?";
	    conn = this.conn;
	    ps = conn.prepareStatement(SQL);
	    ps.setInt(1, raAluno);
	    ps.setInt(2, idDisciplina);
	    ResultSet rs = ps.executeQuery();
	    if (rs.next()) {
	        return rs.getInt(1) > 0;
	    }
	    return false;
	}
	public Notas consultarNotasPorDisciplina(int raAluno, String nomeDisciplina) throws Exception {
	    Notas notas = null;
	    Connection connection = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        
	        connection = ConnectionFactory.getConnection(); 

	        
	        String sql = "SELECT * FROM notas WHERE raAluno = ? AND disciplina = ?";
	        stmt = connection.prepareStatement(sql);
	        stmt.setInt(1, raAluno);
	        stmt.setString(2, nomeDisciplina);

	        rs = stmt.executeQuery();

	        if (rs.next()) {
	            // Criar um objeto Notas com os dados do ResultSet
	            notas = new Notas();
	            notas.setRaAluno(rs.getInt("raAluno"));
	            notas.setDisciplina(rs.getString("disciplina"));
	            notas.setNota(rs.getFloat("nota"));
	            notas.setNota2(rs.getFloat("nota2"));
	            notas.setFaltas(rs.getInt("faltas"));
	            notas.setSemestre(rs.getString("semestre"));
	            notas.setMedia(rs.getFloat("media"));
	        }
	    } catch (SQLException e) {
	        System.out.println("Erro ao consultar notas: " + e.getMessage());
	    } finally {
	        // Fechar recursos
	        try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            System.out.println("Erro ao fechar recursos: " + e.getMessage());
	        }
	    }

	    return notas;
	}

	//================================ DELETE ======================================//
	public void excluir(Notas notas) throws Exception {
	    if (notas == null) {
	        throw new Exception("O valor passado não pode ser nulo");
	    }
	    
	    Connection conn = null;
	    PreparedStatement ps = null;
	    
	    try {
	        
	        conn = ConnectionFactory.getConnection(); 
	        
	        String SQL = "DELETE FROM notas WHERE idNotas = ?";
	        ps = conn.prepareStatement(SQL);
	        ps.setInt(1, notas.getIdNota());
	        
	        ps.executeUpdate();
	        
	        System.out.println("Nota excluída com sucesso: ID " + notas.getIdNota());
	    } catch (SQLException sqle) {
	        throw new Exception("Erro ao excluir dados " + sqle);
	    } finally {
	        
	        ConnectionFactory.closeConnection(conn, ps);
	    }
	}
	
	public String calcularMedia(List<Notas> listaNotas) {
	    double soma = 0;
	    int count = 0;

	    for (Notas nota : listaNotas) {
	    	System.out.println("Notas encontradas: " + listaNotas.size());
	        // Verifica se a nota1 é válida
	        if (nota.getNota() != null) {
	        	System.out.println("Nota 1: " + nota.getNota());
	            soma += nota.getNota();
	            count++;
	        }
	        // Verifica se a nota2 é válida
	        if (nota.getNota2() != null) {
	        	System.out.println("Nota 2: " + nota.getNota2());
	            soma += nota.getNota2();
	            count++;
	        }
	    }
	    System.out.println("Soma das notas: " + soma);
	    System.out.println("Número de notas válidas: " + count);
	    if (count == 0) {
	        return "Nenhuma nota disponível para cálculo";
	    }

	    double media = soma / count; // Cálculo da média
	    String situacao = media >= 6.0 ? "Aprovado" : "Reprovado";

	    return String.format("%.2f  -  %s", media, situacao); // Formata a saída
	}


	//============================= CONSULTAR ===================================//
	public List<Notas> consultar(int raAluno) throws Exception {
	    List<Notas> listaNotas = new ArrayList<>();

	    if (raAluno <= 0) 
	        throw new Exception("O RA do aluno deve ser maior que zero.");

	    try {
	        String SQL = "SELECT * FROM notas WHERE raAluno = ?";
	        conn = this.conn;
	        ps = conn.prepareStatement(SQL);
	        ps.setInt(1, raAluno);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            int id = rs.getInt("idNotas");
	            String disciplina = rs.getString("disciplina");
	            String semestre = rs.getString("semestre");
	            Double nota = rs.getObject("nota") != null ? rs.getDouble("nota") : null;
	            Double nota2 = rs.getObject("nota2") != null ? rs.getDouble("nota2") : null;
	            Double media = rs.getObject("media") != null ? rs.getDouble("media") : null; 
	            int faltas = rs.getInt("faltas");
	            int idDisciplina = rs.getInt("idDisciplina");

	            Notas notaObj = new Notas(id, disciplina, semestre, nota, nota2, media, faltas, raAluno, idDisciplina);
	            listaNotas.add(notaObj);
	        }
	    } catch (SQLException sqle) {
	        throw new Exception("Erro ao consultar notas: " + sqle.getMessage());
	    } finally {
	        ConnectionFactory.closeConnection(conn, ps, rs);
	    }

	    return listaNotas;
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
