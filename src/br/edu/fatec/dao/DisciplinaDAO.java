package br.edu.fatec.dao;

import br.edu.fatec.model.Disciplina;
import br.edu.fatec.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO {

    public List<Disciplina> getDisciplinas(int idCurso) throws Exception {
        List<Disciplina> disciplinas = new ArrayList<>();

        if (idCurso <= 0) {
            throw new IllegalArgumentException("ID do curso deve ser positivo.");
        }

        String SQL = "SELECT * FROM disciplinas WHERE idCurso = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {

            ps.setInt(1, idCurso);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Disciplina disciplina = new Disciplina();
                    disciplina.setIdDisciplina(rs.getInt("idDisciplina"));
                    disciplina.setNomeDisciplina(rs.getString("nomeDisciplina"));
                    disciplinas.add(disciplina);
                }
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao consultar disciplinas: " + e.getMessage(), e);
        }

        return disciplinas;
    }

    public void inserirDisciplina(Disciplina disciplina) throws Exception {
        String SQL = "INSERT INTO disciplinas (nomeDisciplina, idCurso) VALUES (?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {
            ps.setString(1, disciplina.getNomeDisciplina());
            ps.setInt(2, disciplina.getIdCurso()); // Assumindo que Disciplina tem idCurso

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Erro ao inserir disciplina: " + e.getMessage(), e);
        }
    }

    public void atualizarDisciplina(Disciplina disciplina) throws Exception {
        String SQL = "UPDATE disciplinas SET nomeDisciplina = ?, idCurso = ? WHERE idDisciplina = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {
            ps.setString(1, disciplina.getNomeDisciplina());
            ps.setInt(2, disciplina.getIdCurso());
            ps.setInt(3, disciplina.getIdDisciplina());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Erro ao atualizar disciplina: " + e.getMessage(), e);
        }
    }

    public void deletarDisciplina(int idDisciplina) throws Exception {
        String SQL = "DELETE FROM disciplinas WHERE idDisciplina = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {
            ps.setInt(1, idDisciplina);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Erro ao deletar disciplina: " + e.getMessage(), e);
        }
    }

    public Disciplina consultarDisciplina(int idDisciplina) throws Exception {
        Disciplina disciplina = null;

        String SQL = "SELECT * FROM disciplinas WHERE idDisciplina = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {
            ps.setInt(1, idDisciplina);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    disciplina = new Disciplina();
                    disciplina.setIdDisciplina(rs.getInt("idDisciplina"));
                    disciplina.setNomeDisciplina(rs.getString("nomeDisciplina"));
                    disciplina.setIdCurso(rs.getInt("idCurso")); // Assumindo que a Disciplina também tem idCurso
                }
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao consultar disciplina: " + e.getMessage(), e);
        }

        return disciplina;
    }
}
