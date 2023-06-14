package ifpr.paranavai.jogo.modelo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;

public class Fase extends JPanel implements ActionListener, KeyListener {
    private Image fundo;
    private Personagem personagem;
    private ArrayList<Projectile> projectileList = new ArrayList<>();
    private static final int DELAY = 5;
    private Timer timer;
    public Fase(){                     // Linha adicionada (+)
        setFocusable(true);            // + define o foco inicial do jogo
        setDoubleBuffered(true);       // + Otimização computacional
        ImageIcon carregando = new ImageIcon("src/resources/fundoArvore.jpg");
        fundo = carregando.getImage();
        personagem = new Personagem(); // + Criação do objeto Personagem
        personagem.carregar();         // + Carregando as informações do nosso personagem
        addKeyListener(this);          // + Definindo que a própria classe irá controlar os eventos do teclado
        timer = new Timer(1000, cleanUpEntities);
        timer = new Timer(DELAY,this);    // + Criação do objeto Timer
        timer.start();                 // + Iniciando o nosso jogo
    }
    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(fundo, 0,0, null);
        projectileList.stream().forEach(p ->{
            graficos.drawImage(p.getImagem(), p.getPosicaoEmX(), p.getPosicaoEmY(), this);
        });
        graficos.drawImage(personagem.getImagem(), personagem.getPosicaoEmX(), personagem.getPosicaoEmY(), this);
        g.dispose();
    }

    public void fireProjectile() {
        Projectile p = new Projectile(personagem.getPosicaoEmX(), personagem.getPosicaoEmY());
        p.carregar();
        projectileList.add(p);
    }
    public void moveEntities() {
        if(projectileList.size() > 0) {
            projectileList.stream().forEach(p -> {
                p.setPosicaoEmX(p.getPosicaoEmX() + 4);
            });
        }
    }
    ActionListener cleanUpEntities = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            for(int i = 0;i<projectileList.size();i++) {
                if(projectileList.get(i).getPosicaoEmX() > 1000) {
                    projectileList.remove(i);
                }
            }
        }
    };
    @Override
    public void actionPerformed(ActionEvent e) {
        personagem.atualizar();
        repaint();
    }
    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("zé da manga");// TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }
    @Override
    public void keyPressed(KeyEvent e) {
        personagem.mover(e);
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            this.fireProjectile();
        }
        personagem.parar(e);
    }
}