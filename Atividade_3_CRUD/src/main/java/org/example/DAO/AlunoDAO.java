// DAO/AlunoDAO.java
package org.example.DAO;

import org.example.Config.conexaoBD;
import org.example.Model.Aluno;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO implements IAlunoDAO {

    private Connection connection;

    public AlunoDAO() {
        try {
            this.connection = conexaoBD.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Aluno aluno) {
        String sql = "INSERT INTO alunos (nome_aluno, sexo_aluno, maioridade_aluno, cod_curso_aluno) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getSexo());
            stmt.setBoolean(3, aluno.isMaioridade());

            // Buscar o código do curso pela sigla usando o CursoDAO
            CursoDAO cursoDAO = new CursoDAO();
            stmt.setInt(4, cursoDAO.findBySigla(aluno.getCurso()).getCodigo().intValue());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Aluno aluno) {
        String sql = "UPDATE alunos SET nome_aluno = ?, sexo_aluno = ?, maioridade_aluno = ?, cod_curso_aluno = ? WHERE matricula_aluno = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getSexo());
            stmt.setBoolean(3, aluno.isMaioridade());

            // Buscar o código do curso pela sigla usando o CursoDAO
            CursoDAO cursoDAO = new CursoDAO();
            stmt.setInt(4, cursoDAO.findBySigla(aluno.getCurso()).getCodigo().intValue());

            stmt.setLong(5, aluno.getMatricula());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long matricula) {
        String sql = "DELETE FROM alunos WHERE matricula_aluno = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, matricula);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Aluno> findAll() {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT a.*, c.sigla_curso FROM alunos a JOIN curso c ON a.cod_curso_aluno = c.cod_curso";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Aluno aluno = new Aluno(
                        rs.getLong("matricula_aluno"),
                        rs.getString("nome_aluno"),
                        rs.getString("sexo_aluno"),
                        rs.getBoolean("maioridade_aluno"),
                        rs.getString("sigla_curso") // Obter a sigla do curso
                );
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alunos;
    }

    @Override
    public Aluno findById(Long matricula) {
        String sql = "SELECT a.*, c.sigla_curso FROM alunos a JOIN curso c ON a.cod_curso_aluno = c.cod_curso WHERE matricula_aluno = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, matricula);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Aluno(
                        rs.getLong("matricula_aluno"),
                        rs.getString("nome_aluno"),
                        rs.getString("sexo_aluno"),
                        rs.getBoolean("maioridade_aluno"),
                        rs.getString("sigla_curso") // Obter a sigla do curso
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
