import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class tela_calculadora {
    private JTextField textField1;
    private JTextField textField2;
    private JRadioButton soma;
    private JRadioButton subtracao;
    private JRadioButton multiplicacao;
    private JRadioButton divisao;
    private JButton btn_calcula;
    public JPanel painelPrincipal;
    private JLabel LabelResultado;

    public tela_calculadora() {


        btn_calcula.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double num1 = Double.parseDouble(textField1.getText());
                    double num2 = Double.parseDouble(textField2.getText());
                    double resultado = 0;

                    if (soma.isSelected()) {
                        resultado = num1 + num2;
                    } else if (subtracao.isSelected()) {
                        resultado = num1 - num2;
                    } else if (multiplicacao.isSelected()) {
                        resultado = num1 * num2;
                    } else if (divisao.isSelected()) {
                        if (num2 != 0) {
                            resultado = num1 / num2;
                        } else {
                            LabelResultado.setText("Erro: Divisão por zero");
                            return;
                        }
                    }


                    LabelResultado.setText("Resultado: " + resultado);
                } catch (NumberFormatException ex) {
                    LabelResultado.setText("Erro: Entrada inválida");
                }
            }
        });
    }




}
