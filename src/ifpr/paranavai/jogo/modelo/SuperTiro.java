package ifpr.paranavai.jogo.modelo;

import javax.swing.*;
import java.awt.*;

public class SuperTiro {
    private int posicaoEmX;
    private int posicaoEmY;
    private Image imagem;
    private int larguraImagem;
    private int alturaImagem;
    public boolean destroid;
    private Personagem personagem;
    private long tempoInicial;
    private boolean isVisivel;

    public SuperTiro(int posicaoEmX, int posicaoEmY, Personagem personagem) {
        this.posicaoEmX = posicaoEmX;
        this.posicaoEmY = posicaoEmY;
        this.setVisivel(true);
        this.personagem = personagem;
        this.tempoInicial = System.currentTimeMillis();
    }
    public void carregar() {
        ImageIcon carregando = new ImageIcon("src/resources/superTiro42.png");
        this.imagem = carregando.getImage();
        this.alturaImagem = this.imagem.getWidth(null);
        this.larguraImagem = this.imagem.getHeight(null);
    }

    public void atualizar() {
        long tempoAtual = System.currentTimeMillis();
        long tempoDecorrido = tempoAtual - tempoInicial;
        if (tempoDecorrido >= 8000) {
            this.setVisivel(false);
        } else {
            double angulo = (tempoDecorrido / 1000.0) * Math.PI * 2;
            int raio = 142;

            int posX = (int) (Math.cos(angulo) * raio) + personagem.getPosicaoEmX() + (personagem.getLarguraImagem() / 2);
            int posY = (int) (Math.sin(angulo) * raio) + personagem.getPosicaoEmY() + (personagem.getAlturaImagem() / 2);

            this.setPosicaoEmX(posX);
            this.setPosicaoEmY(posY);
        }
    }
    public Rectangle getRectangle() {
        return new Rectangle(this.posicaoEmX, this.posicaoEmY, this.larguraImagem, this.alturaImagem);
    }

    public int getPosicaoEmX() {
        return posicaoEmX;
    }

    public void setPosicaoEmX(int posicaoEmX) {
        this.posicaoEmX = posicaoEmX;
    }

    public int getPosicaoEmY() {
        return posicaoEmY;
    }

    public void setPosicaoEmY(int posicaoEmY) {
        this.posicaoEmY = posicaoEmY;
    }

    public Image getImagem() {
        return imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

    public int getLarguraImagem() {
        return larguraImagem;
    }

    public void setLarguraImagem(int larguraImagem) {
        this.larguraImagem = larguraImagem;
    }

    public int getAlturaImagem() {
        return alturaImagem;
    }

    public void setAlturaImagem(int alturaImagem) {
        this.alturaImagem = alturaImagem;
    }

    public boolean isDestroid() {
        return destroid;
    }

    public void setDestroid(boolean destroid) {
        this.destroid = destroid;
    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

    public long getTempoInicial() {
        return tempoInicial;
    }

    public void setTempoInicial(long tempoInicial) {
        this.tempoInicial = tempoInicial;
    }

    public boolean isVisivel() {
        return isVisivel;
    }

    public void setVisivel(boolean visivel) {
        isVisivel = visivel;
    }
}
