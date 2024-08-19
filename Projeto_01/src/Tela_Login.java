import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tela_Login {
    public JPanel painelPrincipal;
    private JLabel titulo_Login;
    private JTextField field_num_01;
    private JPasswordField field_num_02;
    private JLabel label_num_01;
    private JButton button_calcula;
    private JLabel label_num_02;
    private JRadioButton soma;
    private JRadioButton multiplicacao;
    private JRadioButton subtracao;
    private JRadioButton divisao;

    public Tela_Login() {
        button_calcula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

}
