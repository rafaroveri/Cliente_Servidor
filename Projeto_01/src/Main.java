import javax.swing.*;
import java.awt.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tela Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Tela_Login tela = new Tela_Login();
        frame.setContentPane(new Tela_Login().painelPrincipal);
        frame.setPreferredSize(new Dimension(500, 400));
        frame.pack();
        frame.setVisible(true);
    }

}