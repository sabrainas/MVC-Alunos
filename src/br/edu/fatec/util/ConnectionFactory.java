package br.edu.fatec.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JOptionPane;
import java.sql.Statement;

public class ConnectionFactory {
	public static Connection getConnection() throws Exception{
		try {
			//indica o banco de dados e aponta para o driver
			Class.forName("com.mysql.jdbc.Driver");
			//conexão com o DB
			String login = "root";
			String senha = "";
			String url = "jdbc:mysql://localhost:3306/alunos_db";
			return DriverManager.getConnection(url,login,senha);
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		try {
			Connection conn = ConnectionFactory.getConnection();
			System.out.println("Deu certo");
		}catch(Exception el) {
			System.out.println(el.getMessage());
		}
	}
	
	//fecha uma conexão com conn, stmt, rs
	public static void closeConnection(Connection conn, Statement stmt,
			ResultSet rs) throws Exception {
		close(conn, stmt, rs);
	}

	public static void closeConnection(Connection conn, Statement stmt)
			throws Exception {
		close(conn, stmt, null);
	}

	public static void closeConnection(Connection conn) throws Exception {
		close(conn, null, null);
	}

	private static void close(Connection conn, Statement stmt, ResultSet rs)
			throws Exception {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}

