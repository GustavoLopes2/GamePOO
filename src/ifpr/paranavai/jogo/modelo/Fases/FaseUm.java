package ifpr.paranavai.jogo.modelo.Fases;

import ifpr.paranavai.jogo.modelo.Ambiente.Estrelas;
import ifpr.paranavai.jogo.modelo.Inimigos.InimigoUm;
import ifpr.paranavai.jogo.modelo.Personagem;
import ifpr.paranavai.jogo.modelo.Tiros.SuperTiro;
import ifpr.paranavai.jogo.modelo.Tiros.Tiro;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.*;

public class FaseUm extends Fase {
    private final Image fundo;
    private final Personagem personagem;
    private ArrayList<Tiro> tiroList;
    private ArrayList<InimigoUm> inimigosUm;
    private ArrayList<Estrelas> estrelas;
    private static final int QTDE_DE_INIMIGOS = 40;
    private static final int QTDE_DE_ESTRELAS = 40;
    private static final int LARGURA_DA_JANELA = 1536;
    private static final int DELAY = 5;
    private boolean emJogo;
    private Timer timer;

    public FaseUm(){
        super();
        ImageIcon carregando = new ImageIcon("src/resources/background.png");
        fundo = carregando.getImage();

        personagem = new Personagem();
        personagem.carregar();

        this.inicializaInimigos();
        this.inicializaEstrelas();

        timer = new Timer(1000, cleanUpEntities);
        timer = new Timer(DELAY,this);
        timer.start();

        emJogo = true;
    }

    @Override
    public void inicializaInimigos(){
        inimigosUm = new ArrayList<InimigoUm>();

        for (int i = 0; i < QTDE_DE_INIMIGOS; i++) {
            int x = (int) (Math.random() * 8000 + 1600);
            int y = (int) (Math.random() * 900 + 30);
            InimigoUm inimigoUm = new InimigoUm(x, y);
            inimigosUm.add(inimigoUm);
        }
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        if(emJogo) {
            graficos.drawImage(fundo, 0, 0, null);

            //Estrelas
            for (Estrelas estrela : estrelas) {
                estrela.carregar();
                graficos.drawImage(estrela.getImagem(), estrela.getPosicaoEmX(), estrela.getPosicaoEmY(), this);
            }

            graficos.drawImage(personagem.getImagem(), personagem.getPosicaoEmX(), personagem.getPosicaoEmY(), this);
            //Tiro
            ArrayList<Tiro> tiros = personagem.getTiros();
            for (Tiro tiro : tiros) {
                tiro.carregar();
                graficos.drawImage(tiro.getImagem(), tiro.getPosicaoEmX(), tiro.getPosicaoEmY(), this);
            }
            //SuperTiro
            ArrayList<SuperTiro> superTiros = personagem.getSuperTiros();
            for (SuperTiro superTiro : superTiros) {
                superTiro.carregar();
                graficos.drawImage(superTiro.getImagem(), superTiro.getPosicaoEmX(), superTiro.getPosicaoEmY(), null);
            }

            //Inimigo
            for (InimigoUm inimigoUm : inimigosUm) {
                inimigoUm.carregar();
                graficos.drawImage(inimigoUm.getImagem(), inimigoUm.getPosicaoEmX(), inimigoUm.getPosicaoEmY(), this);
            }
        } else {
            ImageIcon fimJogo = new ImageIcon("src\\resources\\gameOver.png");
            graficos.drawImage(fimJogo.getImage(),0,0,null);
        }
            g.dispose();
    }

    @Override
    public void inicializaEstrelas() {
        estrelas = new ArrayList<Estrelas>();

        for (int i = 0; i < QTDE_DE_ESTRELAS; i++) {
            int x = (int) (Math.random() * 4000 + 1600);
            int y = (int) (Math.random() * 900 + 0);
            Estrelas estrelasUm = new Estrelas(x,y);
            estrelas.add(estrelasUm);
        }
    }

    @Override
    public void colisao() {
        ArrayList<Tiro> tiros = personagem.getTiros();
        for (int i = 0; i < inimigosUm.size(); i++) {
                for (int l = 0; l < tiros.size(); l++) {
                    if (tiros.get(l).getRectangle().intersects(inimigosUm.get(i).getRectangle())) {
                        this.inimigosUm.get(i).life++;
                        if(this.inimigosUm.get(i).life == 2)
                            this.inimigosUm.get(i).destroid = true;
                        tiros.get(l).destroid = true;
                }
            }
        }

        ArrayList<SuperTiro> superTiros = personagem.getSuperTiros();
        for (int i = 0; i < inimigosUm.size(); i++) {
            for (int l = 0; l < superTiros.size(); l++) {
                if (superTiros.get(l).getRectangle().intersects(inimigosUm.get(i).getRectangle())) {
                    this.inimigosUm.get(i).life++;
                    //if(this.inimigosUm.get(i).life == 2)
                    this.inimigosUm.get(i).destroid = true;
                    superTiros.get(l).destroid = true;
                }
            }
        }

        for (int k = 0; k < inimigosUm.size(); k++) {
            if(personagem.getRectangle().intersects(inimigosUm.get(k).getRectangle())) {
                this.personagem.life++;
                this.inimigosUm.get(k).destroid = true;
                if(this.personagem.life == 8) {
                    this.personagem.setVisivel(false);
                    this.inimigosUm.get(k).destroid = true;
                    emJogo = false;
                }
            }
        }

        for (int j = 0; j < inimigosUm.size(); j++) {
            if (this.inimigosUm.get(j).destroid) {
                inimigosUm.remove(j);
            }
        }

        for (int j = 0; j < tiros.size(); j++) {
            if (tiros.get(j).destroid) {
                tiros.remove(j);
            }
        }
    }
    ActionListener cleanUpEntities = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            for (int i = 0; i < tiroList.size(); i++) {
                if (tiroList.get(i).getPosicaoEmX() > 1000) {
                    tiroList.remove(i);
                }
            }
        }
    };

    @Override
    public void actionPerformed(ActionEvent e) {
        if(personagem != null) {
            personagem.atualizar();

            for (int p = 0; p < estrelas.size(); p++) {
                Estrelas on = estrelas.get(p);
                if(on.isVisivel()) {
                    on.atualizar();
                } else {
                    estrelas.remove(p);
                }
            }

            //Tiro
            ArrayList<Tiro> tiros = personagem.getTiros();
            for (int i = 0; i < tiros.size(); i++) {
                if (tiros.get(i).getPosicaoEmX() > LARGURA_DA_JANELA)
                    tiros.remove(i);
                else
                    tiros.get(i).atualizar();
            }

            //Super Tiro
            ArrayList<SuperTiro> superTiros = personagem.getSuperTiros();
            for (int i = 0; i < superTiros.size(); i++) {
                if (!superTiros.get(i).isVisivel()) {
                        superTiros.get(i).atualizar();
                        superTiros.remove(i);
                }
                else
                    superTiros.get(i).atualizar();
            }


            //Inimigo
            for (int i = 0; i < inimigosUm.size(); i++) {
                if (inimigosUm.get(i).getPosicaoEmX() < 0)
                    inimigosUm.remove(i);
                else
                    inimigosUm.get(i).atualizar();
            }
            colisao();
            repaint();
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(personagem != null) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE)
                personagem.atirar();

            if (e.getKeyCode() == KeyEvent.VK_F) {
                personagem.superAtirar();
            }
            else
                personagem.mover(e);
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if (personagem != null) {
            personagem.parar(e);
        }
    }
}