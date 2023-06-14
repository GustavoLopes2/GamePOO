package ifpr.paranavai.jogo.principal;

import javax.swing.JFrame;
import ifpr.paranavai.jogo.modelo.Fase;
public class Main extends JFrame {
    private Fase fase = new Fase();
    public Main() {
        fase.setFocusable(true);
        fase.addKeyListener(fase);
        fase.repaint();
    }
    public void addSettings() {
         super.add(fase);
        super.setTitle("Jogo IFPR POO - Gustavo");
        super.setSize(1536,960);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null);
        super.setResizable(false);
        super.setVisible(true);
    }
    public static void main(String[] args) throws InterruptedException {
        Main mainSc = new Main();
        mainSc.addSettings();
        while (true) {
            mainSc.fase.moveEntities();
            //mainSc.fase.collision();
            mainSc.repaint();
            Thread.sleep(10);
        }
    }
}