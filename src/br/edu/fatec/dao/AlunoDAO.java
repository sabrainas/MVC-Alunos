package br.edu.fatec.dao;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.edu.fatec.model.Aluno;
import br.edu.fatec.util.ConnectionFactory;

public class AlunoDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	private Aluno aluno;
	
	public AlunoDAO() throws Exception{
		try {
			this.conn = ConnectionFactory.getConnection();
		}catch(Exception e) {
			throw new Exception("erro: \n" + e.getMessage());
		}
	}
	
	public void salvar(Aluno aluno, int idCurso) throws Exception{
		if(aluno == null)
			throw new Exception("O valor passado não pode ser nulo");
		
		try {
			String dataNascTexto = aluno.getDataNascimento();
	        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	        Date dataNasc = format.parse(dataNascTexto);

	        SimpleDateFormat sqlFormat = new SimpleDateFormat("yyyy-MM-dd");
	        String dataSql = sqlFormat.format(dataNasc);
			
			String SQL = "INSERT INTO alunos(raAluno, nomeAluno, emailAluno, dataNascimento, enderecoAluno, celularAluno, municipioAluno, ufAluno, cpfAluno, idCurso) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			conn = this.conn;
			ps = conn.prepareStatement(SQL);
	        ps.setInt(1, aluno.getRaAluno());
	        ps.setString(2, aluno.getNomeAluno());
	        ps.setString(3, aluno.getEmailAluno());
	        ps.setString(4, dataSql); 
	        ps.setString(5, aluno.getEnderecoAluno());
	        ps.setString(6, aluno.getCelularAluno());
	        ps.setString(7, aluno.getMunicipioAluno());
	        ps.setString(8, aluno.getUfAluno());
	        ps.setString(9, aluno.getCpfAluno());
	        ps.setInt(10, idCurso);
			ps.executeUpdate();
		}catch(SQLException sqle) {
			throw new Exception("Erro ao inserir dados " + sqle);
		}finally {
			ConnectionFactory.closeConnection(conn, ps);
		}
	}
	
	public void excluir(int raAluno) throws Exception {
	    if(raAluno <= 0) {
	        throw new Exception("O RA passado não pode ser nulo ou negativo");
	    }
	    Connection conn = null;
	    PreparedStatement ps = null;
	    try {
	        conn = ConnectionFactory.getConnection();
	        String SQL = "DELETE FROM alunos WHERE raAluno = ?";
	        ps = conn.prepareStatement(SQL);
	        ps.setInt(1, raAluno);  
	        ps.executeUpdate();
	    } catch(SQLException sqle) {
	        throw new Exception("Erro ao excluir dados: " + sqle);
	    } finally {
	        ConnectionFactory.closeConnection(conn, ps);
	    }
	}
	
	public Aluno consultar(int raAluno) throws Exception {

	    if (raAluno <= 0) {
	        throw new Exception("O RA passado não pode ser nulo ou negativo");
	    }

	    Aluno aluno = null;
	    try {
	    		        
			String SQL = "SELECT * FROM alunos WHERE raAluno=?";
	        conn = this.conn;
	        ps = conn.prepareStatement(SQL);
	        ps.setInt(1, raAluno);
	        rs = ps.executeQuery();
	        
	        if (rs.next()) {
	            int ra = rs.getInt("raAluno"); 
	            String nome = rs.getString("nomeAluno");
	            String email = rs.getString("emailAluno");
	            String dataNascimentoSql = rs.getString("dataNascimento");
	            SimpleDateFormat sqlFormat = new SimpleDateFormat("yyyy-MM-dd");
	            Date dataNasc = sqlFormat.parse(dataNascimentoSql);

	            SimpleDateFormat displayFormat = new SimpleDateFormat("dd/MM/yyyy");
	            String dataNascimento = displayFormat.format(dataNasc);
	            String endereco = rs.getString("enderecoAluno");
	            String celular = rs.getString("celularAluno");
	            String municipio = rs.getString("municipioAluno");
	            String uf = rs.getString("ufAluno");
	            String cpf = rs.getString("cpfAluno");
	            int idCurso = rs.getInt("idCurso");
	            aluno = new Aluno(ra, nome, email, dataNascimento, endereco, celular, municipio, uf, cpf, idCurso);
	        }
	    } catch (SQLException sqle) {
	        throw new Exception("Erro ao consultar o aluno: " + sqle.getMessage());
	    } finally {
	        ConnectionFactory.closeConnection(conn, ps, rs);
	    }

	    return aluno; 
	}

	public void atualizar(Aluno aluno) throws Exception{
		try {
			if(aluno == null)
				throw new Exception("O valor passado não pode ser nulo");
			
			if (conn == null || conn.isClosed()) {
	            conn = ConnectionFactory.getConnection(); 
	        }
			String dataNascTexto = aluno.getDataNascimento();
	        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	        Date dataNasc = format.parse(dataNascTexto);

	        SimpleDateFormat sqlFormat = new SimpleDateFormat("yyyy-MM-dd");
	        String dataSql = sqlFormat.format(dataNasc);
			String SQL = "UPDATE alunos SET nomeAluno=?, emailAluno=?, dataNascimento=?, enderecoAluno=?, celularAluno=?, municipioAluno=?, ufAluno=?, cpfAluno=? WHERE raAluno=?";
			ps = conn.prepareStatement(SQL);
			ps.setString(1, aluno.getNomeAluno());
			ps.setString(2, aluno.getEmailAluno());
			ps.setString(3, dataSql);
			ps.setString(4, aluno.getEnderecoAluno());
			ps.setString(5, aluno.getCelularAluno());
			ps.setString(6, aluno.getMunicipioAluno());
			ps.setString(7, aluno.getUfAluno());
			ps.setString(8, aluno.getCpfAluno());
			ps.setInt(9, aluno.getRaAluno());
			// Executa a atualização
	        ps.executeUpdate();

		}catch(SQLException e) {
			throw new Exception("Erro ao atualizar os dados " + e.getMessage());
		}finally {
	        ConnectionFactory.closeConnection(conn, ps, rs);
	    }
	}
}
