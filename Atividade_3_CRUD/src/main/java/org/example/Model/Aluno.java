package org.example.Model;

public class Aluno {
    private Long matricula;
    private String nome;
    private String sexo;
    private boolean maioridade;
    private String curso;

    public Aluno() {}

    public Aluno(Long matricula, String nome, String sexo, boolean maioridade, String curso) {
        this.matricula = matricula;
        this.nome = nome;
        this.sexo = sexo;
        this.maioridade = maioridade;
        this.curso = curso;
    }

    public Long getMatricula() {
        return matricula;
    }

    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public boolean isMaioridade() {
        return maioridade;
    }

    public void setMaioridade(boolean maioridade) {
        this.maioridade = maioridade;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "matricula=" + matricula +
                ", nome='" + nome + '\'' +
                ", curso='" + curso + '\'' +
                '}';
    }
}
