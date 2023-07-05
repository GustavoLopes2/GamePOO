package ifpr.paranavai.jogo.modelo;

import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Personagem extends ElementoGrafico{
    private int deslocamentoEmX;
    private int deslocamentoEmY;
    private ArrayList<Tiro> tiros;
    private ArrayList<SuperTiro> superTiros;
    private static final int DESLOCAMENTO = 6;
    private static final int POSICAO_INICIAL_EM_X = 100;
    private static final int POSICAO_INICIAL_EM_Y = 100;
    private long tempoUltimoTiro;
    private long tempoUltimoSuperTiro;
    private long tempoAtual;
    private long delayTiro;
    private long delayTiroSuper;
    public int life;
    public boolean destroid;

    public Personagem() {
        super.setPosicaoEmX(POSICAO_INICIAL_EM_X);
        super.setPosicaoEmY(POSICAO_INICIAL_EM_Y);
        super.setVisivel(true);
        this.tiros = new ArrayList<Tiro>();
        this.superTiros = new ArrayList<SuperTiro>();
        this.delayTiro = 150;
        this.delayTiroSuper = 15000;

    }
    @Override
    public void carregar() {
        ImageIcon carregando = new ImageIcon("src/resources/nave128Teste.png");
        super.setImagem(carregando.getImage());
        super.setAlturaImagem(super.getImagem().getHeight(null));
        super.setLarguraImagem(super.getImagem().getWidth(null));
    }

    @Override
    public void atualizar() {
        super.setPosicaoEmX(super.getPosicaoEmX() + this.deslocamentoEmX);
        super.setPosicaoEmY(super.getPosicaoEmY() + this.deslocamentoEmY);
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(super.getPosicaoEmX(), super.getPosicaoEmY(), super.getLarguraImagem(), super.getAlturaImagem());
    }

    public void atirar() {
        this.tempoAtual = System.currentTimeMillis();

        if (tempoAtual - tempoUltimoTiro < delayTiro) {
            return;
        }

        int frenteDaNave = super.getPosicaoEmX() + super.getLarguraImagem();
        int meioDaNave = super.getPosicaoEmY() + (super.getAlturaImagem() / 2)-4;

        Tiro tiro = new Tiro(frenteDaNave, meioDaNave);
        this.tiros.add(tiro);
        tempoUltimoTiro = tempoAtual;
    }
    public void superAtirar() {
        this.tempoAtual = System.currentTimeMillis();

        if (tempoAtual - tempoUltimoSuperTiro < delayTiroSuper) {
            return;
        }

        int frenteDaNave = super.getPosicaoEmX() + super.getLarguraImagem();
        int meioDaNave = super.getPosicaoEmY() + (super.getAlturaImagem() / 2)-4;

        SuperTiro superTiro = new SuperTiro(frenteDaNave, meioDaNave, this);
        this.superTiros.add(superTiro);
        tempoUltimoSuperTiro = tempoAtual;
    }

    public void mover(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();
        switch (codigo) {
            case KeyEvent.VK_UP, KeyEvent.VK_W:
                this.deslocamentoEmY = -DESLOCAMENTO;
                break;
            case KeyEvent.VK_DOWN, KeyEvent.VK_S:
                this.deslocamentoEmY = DESLOCAMENTO;
                break;
            case KeyEvent.VK_LEFT, KeyEvent.VK_A:
                this.deslocamentoEmX = -DESLOCAMENTO;
                break;
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D:
                this.deslocamentoEmX = DESLOCAMENTO;
                break;
            default:
                break;
        }
    }
    public void parar(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();
        switch (codigo) {
            case KeyEvent.VK_UP, KeyEvent.VK_W:
                deslocamentoEmY = 0;
                break;
            case KeyEvent.VK_DOWN, KeyEvent.VK_S:
                deslocamentoEmY = 0;
                break;
            case KeyEvent.VK_LEFT, KeyEvent.VK_A:
                deslocamentoEmX = 0;
                break;
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D:
                deslocamentoEmX = 0;
                break;
            default:
                break;
        }
    }
    public int getDeslocamentoEmX() {
        return deslocamentoEmX;
    }

    public void setDeslocamentoEmX(int deslocamentoEmX) {
        this.deslocamentoEmX = deslocamentoEmX;
    }

    public int getDeslocamentoEmY() {
        return deslocamentoEmY;
    }

    public void setDeslocamentoEmY(int deslocamentoEmY) {
        this.deslocamentoEmY = deslocamentoEmY;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public boolean isDestroid() {
        return destroid;
    }

    public void setDestroid(boolean destroid) {
        this.destroid = destroid;
    }

    public ArrayList<Tiro> getTiros() {
        return tiros;
    }

    public ArrayList<SuperTiro> getSuperTiros() {
        return superTiros;
    }

    public void setSuperTiros(ArrayList<SuperTiro> superTiros) {
        this.superTiros = superTiros;
    }

    public long getTempoUltimoSuperTiro() {
        return tempoUltimoSuperTiro;
    }

    public void setTempoUltimoSuperTiro(long tempoUltimoSuperTiro) {
        this.tempoUltimoSuperTiro = tempoUltimoSuperTiro;
    }

    public long getDelayTiroSuper() {
        return delayTiroSuper;
    }

    public void setDelayTiroSuper(long delayTiroSuper) {
        this.delayTiroSuper = delayTiroSuper;
    }

    public void setTiros(ArrayList<Tiro> tiros) {
        this.tiros = tiros;
    }

    public long getTempoUltimoTiro() {
        return tempoUltimoTiro;
    }

    public void setTempoUltimoTiro(long tempoUltimoTiro) {
        this.tempoUltimoTiro = tempoUltimoTiro;
    }

    public long getTempoAtual() {
        return tempoAtual;
    }

    public void setTempoAtual(long tempoAtual) {
        this.tempoAtual = tempoAtual;
    }

    public long getDelayTiro() {
        return delayTiro;
    }

    public void setDelayTiro(long delayTiro) {
        this.delayTiro = delayTiro;
    }
}
