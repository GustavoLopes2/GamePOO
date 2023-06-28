package ifpr.paranavai.jogo.modelo;

import ifpr.paranavai.jogo.principal.Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.JFrame;
import java.awt.event.*;

public class Fase extends JPanel implements ActionListener, KeyListener {
    private final Image fundo;
    private final Personagem personagem;
    private Inimigo inimigo;
    private Projectile projectile;
    private final ArrayList<Projectile> projectileList = new ArrayList<>();
    private final ArrayList<Inimigo> inimigos = new ArrayList<>();
    private static final int DELAY = 5;
    private Timer timer;
    private Image imagem;
    private int larguraImagem;
    private int alturaImagem;

    public Fase(){
        setFocusable(true);
        setDoubleBuffered(true);
        ImageIcon carregando = new ImageIcon("src/resources/fundoArvore.jpg");
        fundo = carregando.getImage();
        personagem = new Personagem();
        //JLabel projectileSuper = new JLabel(new ImageIcon(getClass().getResource("src/resources/municao.png")));
        personagem.carregar();
        addKeyListener(this);
        timer = new Timer(1000, cleanUpEntities);
        //timer = new Timer(1500, spawnEnemy());
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
    public void fireProjectileEspecial() {
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
        //this.spawnEnemy();
    }

    public void collision() {

        for (int i = 0; i < inimigos.size(); i++) {
                for (int l = 0; l < projectileList.size(); l++) {
                    if (projectileList.get(l).getRectangle().intersects(inimigos.get(i).getRectangle())) {
                        this.inimigos.get(i).life++;
                        if(this.inimigos.get(i).life == 4)
                            this.inimigos.get(i).destroid = true;
                        this.projectileList.get(l).destroid = true;
                }
            }
        }

        for (int k = 0; k < inimigos.size(); k++) {
            if(personagem.getRectangle().intersects(inimigos.get(k).getRectangle())) {
                //this.personagem.life++;
                //this.inimigos.get(k).destroid = true;
                //if(this.personagem.life == 8)
                    this.personagem.setImagem(null);
                    this.inimigos.get(k).destroid = true;
                    this.carregarImagem();
                    Main.controle = true;
            }
        }
        for (int j = 0; j < inimigos.size(); j++) {
            if (this.inimigos.get(j).destroid) {
                inimigos.remove(j);
            }
        }

        for (int j = 0; j < projectileList.size(); j++) {
            if (this.projectileList.get(j).destroid) {
                projectileList.remove(j);
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
        }
    };

    public void carregarImagem() {
        ImageIcon carregando = new ImageIcon("src/resources/gitDeath.gif");
        this.imagem = carregando.getImage();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(personagem != null) {
            personagem.atualizar();
            repaint();
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("zé da manga");// TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(personagem != null)
            personagem.mover(e);
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if (personagem != null) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                this.fireProjectile();
            }
            if (e.getKeyCode() == KeyEvent.VK_Z) {
                this.fireProjectileEspecial();
            }
            personagem.parar(e);
        }
    }
}