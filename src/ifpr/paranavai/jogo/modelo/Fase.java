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
    private Inimigo inimigo;
    private Projectile projectile;
    private ArrayList<Projectile> projectileList = new ArrayList<>();
    private ArrayList<Inimigo> inimigos = new ArrayList<>();
    private static final int DELAY = 5;
    private Timer timer;
    public Fase(){
        setFocusable(true);
        setDoubleBuffered(true);
        ImageIcon carregando = new ImageIcon("src/resources/fundoArvore.jpg");
        fundo = carregando.getImage();
        personagem = new Personagem();
        personagem.carregar();
        addKeyListener(this);
        timer = new Timer(1000, cleanUpEntities);
        timer = new Timer(DELAY,this);
        timer.start();
        spawnEnemy();
    }
    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(fundo, 0,0, null);
        projectileList.stream().forEach(p ->{
            graficos.drawImage(p.getImagem(), p.getPosicaoEmX(), p.getPosicaoEmY(), this);
        });
        inimigos.stream().forEach(o -> {
            graficos.drawImage(o.getImagem(), o.getPosicaoEmX(), o.getPosicaoEmY(), this);
        });
        graficos.drawImage(personagem.getImagem(), personagem.getPosicaoEmX(), personagem.getPosicaoEmY(), this);
        g.dispose();
    }

    public void spawnEnemy() {
        Inimigo i = new Inimigo();
        i.carregar();
        inimigos.add(i);
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
        if(inimigos.size() > 0) {
            inimigos.stream().forEach(i -> {
                i.setPosicaoEmX(i.getPosicaoEmX() - 4);
            });
        }
        this.spawnEnemy();
    }

    public void collision() {
        personagem.getRectangle();

        for (int i = 0; i < inimigos.size(); i++) {
                for (int l = 0; l < projectileList.size(); l++) {
                    if (projectileList.get(l).getRectangle().intersects(inimigos.get(i).getRectangle())) {
                        this.inimigos.get(i).destroid = true;
                }
            }
        }
        for (int j = 0; j < inimigos.size(); j++) {
            if (this.inimigos.get(j).destroid) {
                inimigos.remove(j);
            }
        }
    }
    ActionListener cleanUpEntities = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            for (int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i).getPosicaoEmX() > 1000) {
                    projectileList.remove(i);
                }
            }
            //Colisão
            for (int l = 0; l < projectileList.size(); l++) {
                if (projectileList.get(l).getRectangle().intersects(inimigos.get(l).getRectangle())) {
                    for (int i = 0; i < inimigos.size(); i++) {
                        inimigos.remove(i);
                        System.out.println("Colisão com item");
                    }
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