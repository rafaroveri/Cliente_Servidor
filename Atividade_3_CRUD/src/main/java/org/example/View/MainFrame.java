// View/MainFrame.java
package org.example.View;

import org.example.DAO.AlunoDAO;
import org.example.DAO.CursoDAO;
import org.example.Model.Aluno;
import org.example.Model.Curso;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class MainFrame extends JFrame {

    private CursoDAO cursoDAO;
    private AlunoDAO alunoDAO;
    private JPanel buttonPanel;  // Painel para botões
    private JTextArea textArea;  // Área de texto para mensagens
    private JTable table;        // Tabela para listagens

    public MainFrame() {
        // Configurações da Janela Principal
        setTitle("Sistema de Gerenciamento de Alunos e Cursos");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setLayout(new BorderLayout());

        // Instanciando DAOs
        cursoDAO = new CursoDAO();
        alunoDAO = new AlunoDAO();

        // Configurando o Painel de Botões (Vertical à esquerda)
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setPreferredSize(new Dimension(200, 0)); // Largura fixa, altura ajustável

        // Configuração do JTable
        table = new JTable();
        JScrollPane tableScrollPane = new JScrollPane(table);

        // Botões de Cursos
        addButton("Adicionar Curso", e -> adicionarCurso());
        addButton("Listar Cursos", e -> listarCursos());
        addButton("Atualizar Curso", e -> atualizarCurso());
        addButton("Excluir Curso", e -> excluirCurso());

        // Botões de Alunos
        addButton("Adicionar Aluno", e -> adicionarAluno());
        addButton("Listar Alunos", e -> listarAlunos());
        addButton("Atualizar Aluno", e -> atualizarAluno());
        addButton("Excluir Aluno", e -> excluirAluno());

        // Configurando Área de Texto para Mensagens
        textArea = new JTextArea(5, 30);
        textArea.setEditable(false);
        JScrollPane textScrollPane = new JScrollPane(textArea);

        // Adiciona os componentes à Janela Principal
        add(buttonPanel, BorderLayout.WEST); // Botões na esquerda
        add(tableScrollPane, BorderLayout.CENTER); // Tabela no centro
        add(textScrollPane, BorderLayout.SOUTH); // Área de mensagem na parte inferior
    }

    // Método para adicionar botões com estilo ao painel
    private void addButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza o botão no painel
        button.setPreferredSize(new Dimension(180, 40));
        button.setMaximumSize(new Dimension(180, 40)); // Define um tamanho máximo
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.addActionListener(action);
        buttonPanel.add(button);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaço entre botões
    }

    // Método para Adicionar Curso
    private void adicionarCurso() {
        try {
            String nome = JOptionPane.showInputDialog(this, "Nome do Curso:");
            String sigla = JOptionPane.showInputDialog(this, "Sigla do Curso:");
            String areaInput = JOptionPane.showInputDialog(this, "Área do Curso (EXATAS, HUMANAS, BIOLOGICAS, ARTES):");
            Curso.Area area = Curso.Area.valueOf(areaInput.toUpperCase());

            Curso curso = new Curso(null, nome, sigla, area);
            cursoDAO.create(curso);
            textArea.setText("Curso adicionado com sucesso!");
        } catch (IllegalArgumentException e) {
            textArea.setText("Erro: Área inválida. Use EXATAS, HUMANAS, BIOLOGICAS, ARTES.");
        } catch (Exception e) {
            textArea.setText("Erro ao adicionar curso: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para Listar Cursos
    private void listarCursos() {
        try {
            List<Curso> cursos = cursoDAO.findAll();
            DefaultTableModel model = new DefaultTableModel(new Object[]{"Código", "Nome", "Sigla", "Área"}, 0);
            for (Curso curso : cursos) {
                model.addRow(new Object[]{curso.getCodigo(), curso.getNome(), curso.getSigla(), curso.getArea()});
            }
            table.setModel(model);
            textArea.setText("Listagem de cursos concluída.");
        } catch (Exception e) {
            textArea.setText("Erro ao listar cursos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para Atualizar Curso
    private void atualizarCurso() {
        try {
            Long codigo = Long.parseLong(JOptionPane.showInputDialog(this, "Código do Curso a ser atualizado:"));
            Curso curso = cursoDAO.findById(codigo);

            if (curso != null) {
                String nome = JOptionPane.showInputDialog(this, "Novo Nome do Curso:", curso.getNome());
                String sigla = JOptionPane.showInputDialog(this, "Nova Sigla do Curso:", curso.getSigla());
                String areaInput = JOptionPane.showInputDialog(this, "Nova Área do Curso (EXATAS, HUMANAS, BIOLOGICAS, ARTES):", curso.getArea().name());
                Curso.Area area = Curso.Area.valueOf(areaInput.toUpperCase());

                curso.setNome(nome);
                curso.setSigla(sigla);
                curso.setArea(area);

                cursoDAO.update(curso);
                textArea.setText("Curso atualizado com sucesso!");
            } else {
                textArea.setText("Curso não encontrado com o código fornecido.");
            }
        } catch (NumberFormatException e) {
            textArea.setText("Erro: Código inválido. Certifique-se de inserir um número.");
        } catch (IllegalArgumentException e) {
            textArea.setText("Erro: Área inválida. Use EXATAS, HUMANAS, BIOLOGICAS, ARTES.");
        } catch (Exception e) {
            textArea.setText("Erro ao atualizar curso: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para Excluir Curso
    private void excluirCurso() {
        try {
            Long codigo = Long.parseLong(JOptionPane.showInputDialog(this, "Código do Curso a ser excluído:"));
            cursoDAO.delete(codigo);
            textArea.setText("Curso excluído com sucesso!");
        } catch (NumberFormatException e) {
            textArea.setText("Erro: Código inválido. Certifique-se de inserir um número.");
        } catch (Exception e) {
            textArea.setText("Erro ao excluir curso: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para Adicionar Aluno
    private void adicionarAluno() {
        try {
            String nome = JOptionPane.showInputDialog(this, "Nome do Aluno:");
            String sexo = JOptionPane.showInputDialog(this, "Sexo do Aluno (M/F):");
            boolean maioridade = JOptionPane.showConfirmDialog(this, "Aluno é maior de idade?", "Maioridade", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            String siglaCurso = JOptionPane.showInputDialog(this, "Sigla do Curso:");

            Aluno aluno = new Aluno(null, nome, sexo, maioridade, siglaCurso);
            alunoDAO.create(aluno);
            textArea.setText("Aluno adicionado com sucesso!");
        } catch (Exception e) {
            textArea.setText("Erro ao adicionar aluno: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para Listar Alunos
    private void listarAlunos() {
        try {
            List<Aluno> alunos = alunoDAO.findAll();
            DefaultTableModel model = new DefaultTableModel(new Object[]{"Matrícula", "Nome", "Sexo", "Maioridade", "Curso"}, 0);
            for (Aluno aluno : alunos) {
                model.addRow(new Object[]{
                        aluno.getMatricula(),
                        aluno.getNome(),
                        aluno.getSexo(),
                        aluno.isMaioridade() ? "Sim" : "Não",
                        aluno.getCurso()
                });
            }
            table.setModel(model);
            textArea.setText("Listagem de alunos concluída.");
        } catch (Exception e) {
            textArea.setText("Erro ao listar alunos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para Atualizar Aluno
    private void atualizarAluno() {
        try {
            Long matricula = Long.parseLong(JOptionPane.showInputDialog(this, "Matrícula do Aluno a ser atualizado:"));
            Aluno aluno = alunoDAO.findById(matricula);

            if (aluno != null) {
                String nome = JOptionPane.showInputDialog(this, "Novo Nome do Aluno:", aluno.getNome());
                String sexo = JOptionPane.showInputDialog(this, "Novo Sexo do Aluno (M/F):", aluno.getSexo());
                boolean maioridade = JOptionPane.showConfirmDialog(this, "Aluno é maior de idade?", "Maioridade", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                String siglaCurso = JOptionPane.showInputDialog(this, "Nova Sigla do Curso:", aluno.getCurso());

                aluno.setNome(nome);
                aluno.setSexo(sexo);
                aluno.setMaioridade(maioridade);
                aluno.setCurso(siglaCurso);

                alunoDAO.update(aluno);
                textArea.setText("Aluno atualizado com sucesso!");
            } else {
                textArea.setText("Aluno não encontrado com a matrícula fornecida.");
            }
        } catch (NumberFormatException e) {
            textArea.setText("Erro: Matrícula inválida. Certifique-se de inserir um número.");
        } catch (Exception e) {
            textArea.setText("Erro ao atualizar aluno: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para Excluir Aluno
    private void excluirAluno() {
        try {
            Long matricula = Long.parseLong(JOptionPane.showInputDialog(this, "Matrícula do Aluno a ser excluído:"));
            alunoDAO.delete(matricula);
            textArea.setText("Aluno excluído com sucesso!");
        } catch (NumberFormatException e) {
            textArea.setText("Erro: Matrícula inválida. Certifique-se de inserir um número.");
        } catch (Exception e) {
            textArea.setText("Erro ao excluir aluno: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método principal para executar a interface
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
