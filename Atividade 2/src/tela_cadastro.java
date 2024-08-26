import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class tela_cadastro {
    public JPanel painelPrincipal;
    private JLabel labelTitulo;
    private JLabel labelNome;
    private JLabel labelCPF;
    private JLabel labelSexo;
    private JLabel labelDataNascimento;
    private JLabel labelEstadoCivil;
    private JLabel labelProfissao;

    private JTextField campoNome;
    private JFormattedTextField campoCPF;
    private JRadioButton radioMasculino;
    private JRadioButton radioFeminino;
    private JRadioButton radioOutro;
    private ButtonGroup grupoSexo;
    private JFormattedTextField campoDataNascimento;
    private JComboBox<String> comboEstadoCivil;
    private JTextField campoProfissao;
    private JButton botaoCadastrar;

    public tela_cadastro() {
        painelPrincipal = new JPanel();
        painelPrincipal.setBackground(Color.LIGHT_GRAY);
        painelPrincipal.setOpaque(true);
        painelPrincipal.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        painelPrincipal.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);


        labelTitulo = new JLabel("Cadastro");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        painelPrincipal.add(labelTitulo, gbc);


        labelNome = new JLabel("Nome:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        painelPrincipal.add(labelNome, gbc);

        campoNome = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.5;
        painelPrincipal.add(campoNome, gbc);


        labelCPF = new JLabel("CPF:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        painelPrincipal.add(labelCPF, gbc);

        try {
            campoCPF = new JFormattedTextField(new javax.swing.text.MaskFormatter("###.###.###-##"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        campoCPF.setColumns(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0.5;
        painelPrincipal.add(campoCPF, gbc);


        labelSexo = new JLabel("Sexo:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        painelPrincipal.add(labelSexo, gbc);

        JPanel painelSexo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        radioMasculino = new JRadioButton("Masculino");
        radioFeminino = new JRadioButton("Feminino");
        radioOutro = new JRadioButton("Outro");
        grupoSexo = new ButtonGroup();
        grupoSexo.add(radioMasculino);
        grupoSexo.add(radioFeminino);
        grupoSexo.add(radioOutro);
        painelSexo.add(radioMasculino);
        painelSexo.add(radioFeminino);
        painelSexo.add(radioOutro);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 0.5;
        painelPrincipal.add(painelSexo, gbc);


        labelDataNascimento = new JLabel("Data de Nascimento:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        painelPrincipal.add(labelDataNascimento, gbc);

        campoDataNascimento = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
        campoDataNascimento.setColumns(10);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weightx = 0.5;
        painelPrincipal.add(campoDataNascimento, gbc);


        labelEstadoCivil = new JLabel("Estado Civil:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        painelPrincipal.add(labelEstadoCivil, gbc);

        comboEstadoCivil = new JComboBox<>(new String[]{"Solteiro(a)", "Casado(a)", "Divorciado(a)", "Viúvo(a)"});
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.weightx = 0.5;
        painelPrincipal.add(comboEstadoCivil, gbc);


        labelProfissao = new JLabel("Profissão:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        painelPrincipal.add(labelProfissao, gbc);

        campoProfissao = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.weightx = 0.5;
        painelPrincipal.add(campoProfissao, gbc);


        botaoCadastrar = new JButton("Cadastrar");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        painelPrincipal.add(botaoCadastrar, gbc);


        botaoCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validarCampos()) {
                    preencherProfissaoSeVazia();
                    exibirInformacoes();
                }
            }
        });
    }

    private boolean validarCampos() {

        if (campoNome.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo Nome não pode estar em branco.", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }


        String cpf = campoCPF.getText().replace(".", "").replace("-", "").trim();
        if (!validarCPF(cpf)) {
            JOptionPane.showMessageDialog(null, "O CPF informado é inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }


    private boolean validarCPF(String cpf) {
        if (cpf.length() != 11 || !Pattern.matches("\\d{11}", cpf)) {
            return false;
        }


        return true;
    }

    private void preencherProfissaoSeVazia() {
        if (campoProfissao.getText().trim().isEmpty()) {
            campoProfissao.setText("Desempregado(a)");
        }
    }

    private int calcularIdade(Date dataNascimento) {
        Date hoje = new Date();
        int idade = hoje.getYear() - dataNascimento.getYear();
        if (hoje.getMonth() < dataNascimento.getMonth() ||
                (hoje.getMonth() == dataNascimento.getMonth() && hoje.getDate() < dataNascimento.getDate())) {
            idade--;
        }
        return idade;
    }

    // Método para exibir as informações cadastradas
    private void exibirInformacoes() {
        try {
            String nome = campoNome.getText().trim();
            Date dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse(campoDataNascimento.getText().trim());
            int idade = calcularIdade(dataNascimento);
            String sexo = radioMasculino.isSelected() ? "Masculino" :
                    radioFeminino.isSelected() ? "Feminino" : "Outro";
            String estadoCivil = comboEstadoCivil.getSelectedItem().toString();
            String profissao = campoProfissao.getText().trim();

            StringBuilder mensagem = new StringBuilder();
            mensagem.append("Nome: ").append(nome).append("\n");
            mensagem.append("Idade: ").append(idade).append(" anos\n");
            mensagem.append("Sexo: ").append(sexo).append("\n");
            mensagem.append("Estado Civil: ").append(estadoCivil).append("\n");
            mensagem.append("Profissão: ").append(profissao);

            if ("Engenheiro".equalsIgnoreCase(profissao) || "Analista de Sistemas".equalsIgnoreCase(profissao)) {
                mensagem.append("\nVagas disponíveis na área");
            }

            JOptionPane.showMessageDialog(null, mensagem.toString(), "Informações Cadastradas", JOptionPane.INFORMATION_MESSAGE);

        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Erro ao processar a data de nascimento.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
