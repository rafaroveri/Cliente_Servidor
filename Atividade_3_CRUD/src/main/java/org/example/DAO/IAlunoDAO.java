package org.example.DAO;

import org.example.Model.Aluno;
import java.util.List;

public interface IAlunoDAO {
    void create(Aluno aluno);
    void update(Aluno aluno);
    void delete(Long matricula);
    List<Aluno> findAll();
    Aluno findById(Long matricula);
}
