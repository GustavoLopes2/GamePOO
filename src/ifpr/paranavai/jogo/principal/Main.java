package ifpr.paranavai.jogo.principal;

import javax.swing.JFrame;
import ifpr.paranavai.jogo.modelo.FaseUm;
public class Main extends JFrame {
    public Main() {
        FaseUm fase = new FaseUm();
        super.add(fase);
        super.setTitle("Jogo IFPR POO - Gustavo");
        super.setSize(1600,900);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null);
        super.setResizable(false);
        super.setVisible(true);
    }
    public static void main(String[] args) throws InterruptedException {
        new Main();
    }
}