package ifpr.paranavai.jogo.principal;

import javax.swing.JFrame;
import ifpr.paranavai.jogo.modelo.Fase;
public class Main extends JFrame {
    public Main() {
        Fase fase = new Fase();
        super.add(fase);
        super.setTitle("Jogo IFPR POO - Gustavo");
        super.setSize(1000,1500);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null);
        super.setResizable(false);
        super.setVisible(true);
    }
    public static void main(String[] args) {
        new Main();
    }
}