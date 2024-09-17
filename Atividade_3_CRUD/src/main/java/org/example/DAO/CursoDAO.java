package org.example.DAO;

import org.example.Config.conexaoBD;
import org.example.Model.Curso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO implements ICursoDAO {

    private Connection connection;

    // Construtor para estabelecer a conexão
    public CursoDAO() {
        try {
            this.connection = conexaoBD.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Curso curso) {
        String sql = "INSERT INTO curso (nome_curso, sigla_curso, area_curso) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getSigla());
            stmt.setString(3, curso.getArea().name());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Curso curso) {
        String sql = "UPDATE curso SET nome_curso = ?, sigla_curso = ?, area_curso = ? WHERE cod_curso = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getSigla());
            stmt.setString(3, curso.getArea().name());
            stmt.setLong(4, curso.getCodigo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long codigo) {
        String sql = "DELETE FROM curso WHERE cod_curso = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, codigo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Curso> findAll() {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT * FROM curso";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Curso curso = new Curso(
                        rs.getLong("cod_curso"),
                        rs.getString("nome_curso"),
                        rs.getString("sigla_curso"),
                        Curso.Area.valueOf(rs.getString("area_curso"))
                );
                cursos.add(curso);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursos;
    }

    @Override
    public Curso findById(Long codigo) {
        String sql = "SELECT * FROM curso WHERE cod_curso = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, codigo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Curso(
                        rs.getLong("cod_curso"),
                        rs.getString("nome_curso"),
                        rs.getString("sigla_curso"),
                        Curso.Area.valueOf(rs.getString("area_curso"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Curso> findByArea(Curso.Area area) {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT * FROM curso WHERE area_curso = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, area.name());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Curso curso = new Curso(
                        rs.getLong("cod_curso"),
                        rs.getString("nome_curso"),
                        rs.getString("sigla_curso"),
                        Curso.Area.valueOf(rs.getString("area_curso"))
                );
                cursos.add(curso);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursos;
    }

    @Override
    public Curso findBySigla(String sigla) {
        String sql = "SELECT * FROM curso WHERE sigla_curso = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, sigla);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Curso(
                        rs.getLong("cod_curso"),
                        rs.getString("nome_curso"),
                        rs.getString("sigla_curso"),
                        Curso.Area.valueOf(rs.getString("area_curso"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
