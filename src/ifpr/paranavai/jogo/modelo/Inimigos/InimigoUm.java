package ifpr.paranavai.jogo.modelo.Inimigos;

import javax.swing.*;
import java.awt.*;

public class InimigoUm {
    private int posicaoEmX;
    private int posicaoEmY;
    private Image imagem;
    private int larguraImagem;
    private int alturaImagem;
    private static int VELOCIDADE = 8;
    public boolean destroid;
    public int life;

    public InimigoUm(int xAleatorio, int yAleatorio) {
        this.posicaoEmX = xAleatorio;
        this.posicaoEmY = yAleatorio;
    }
    public void carregar() {
        ImageIcon carregando = new ImageIcon("src/resources/inimigoTeste.png");
        this.imagem = carregando.getImage();
        this.alturaImagem = this.imagem.getWidth(null);
        this.larguraImagem = this.imagem.getHeight(null);
    }

    public void atualizar() {
        this.posicaoEmX = this.posicaoEmX - VELOCIDADE;
    }

    public Rectangle getRectangle() {
        return new Rectangle(this.posicaoEmX, this.posicaoEmY, this.larguraImagem, this.alturaImagem);
    }

    public int getLife() {
        return life;
    }

    public boolean isDestroid() {
        return destroid;
    }

    public void setDestroid(boolean destroid) {
        this.destroid = destroid;
    }

    public void setLife(int life) {
        this.life = life;
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

    public static int getVELOCIDADE() {
        return VELOCIDADE;
    }

    public static void setVELOCIDADE(int VELOCIDADE) {
        InimigoUm.VELOCIDADE = VELOCIDADE;
    }
}
