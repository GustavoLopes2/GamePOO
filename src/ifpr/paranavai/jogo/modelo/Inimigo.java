package ifpr.paranavai.jogo.modelo;

import javax.swing.*;
import java.awt.*;

public class Inimigo {
    private int posicaoEmX;
    private int posicaoEmY;
    private int deslocamentoEmX;
    private int deslocamentoEmY;
    private Image imagem;
    private int larguraImagem;
    private int alturaImagem;
    public boolean destroid;

    public boolean isDestroid() {
        return destroid;
    }

    public void setDestroid(boolean destroid) {
        this.destroid = destroid;
    }

    public Inimigo() {
        int posX = (int)(Math.random() * 5 + 1700);
        int posY = (int)(Math.random() * 850 + 0);
        this.posicaoEmX = posX;
        this.posicaoEmY = posY;
    }
    public void carregar() {
        ImageIcon carregando = new ImageIcon("src/resources/inimigo.png");
        this.imagem = carregando.getImage();
        this.alturaImagem = this.imagem.getWidth(null);
        this.larguraImagem = this.imagem.getHeight(null);
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
}
