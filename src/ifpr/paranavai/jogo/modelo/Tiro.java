package ifpr.paranavai.jogo.modelo;

import javax.swing.*;
import java.awt.*;

public class Tiro {
    private int posicaoEmX;
    private int posicaoEmY;
    private Image imagem;
    private int larguraImagem;
    private int alturaImagem;
    private static final int VELOCIDADE = 2;
    public boolean destroid;
    public Tiro(int posX, int posY) {
        this.posicaoEmX = posX;
        this.posicaoEmY = posY;
    }
    public void carregar() {
        ImageIcon carregando = new ImageIcon("src/resources/municao.png");
        this.imagem = carregando.getImage();
        this.alturaImagem = this.imagem.getWidth(null);
        this.larguraImagem = this.imagem.getHeight(null);
    }

    public void atualizar() {
        this.posicaoEmX = this.posicaoEmX + VELOCIDADE;
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
}
