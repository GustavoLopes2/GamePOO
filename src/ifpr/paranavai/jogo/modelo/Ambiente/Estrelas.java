package ifpr.paranavai.jogo.modelo.Ambiente;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Estrelas {
    private int posicaoEmX;
    private int posicaoEmY;
    private Image imagem;
    private int larguraImagem;
    private int alturaImagem;
    private static int VELOCIDADE = 7;
    private boolean isVisivel;

    public Estrelas(int xAleatorio, int yAleatorio) {
        this.posicaoEmX = xAleatorio;
        this.posicaoEmY = yAleatorio;
        isVisivel = true;
    }
    public void carregar() {
        ImageIcon carregando = new ImageIcon("src\\resources\\estrela.png");
        this.imagem = carregando.getImage();
        this.alturaImagem = this.imagem.getWidth(null);
        this.larguraImagem = this.imagem.getHeight(null);
    }

    public void atualizar() {
        if(this.posicaoEmX < 0) {
            this.posicaoEmX = larguraImagem;
            Random a = new Random();
            int m = a.nextInt(500);
            this.posicaoEmX = m + 1600;
            Random r = new Random();
            int n = r.nextInt(900);
            this.posicaoEmY = n;
        } else {
            this.posicaoEmX -= VELOCIDADE;
        }
    }

    public boolean isVisivel() {
        return isVisivel;
    }

    public void setVisivel(boolean visivel) {
        isVisivel = visivel;
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
        Estrelas.VELOCIDADE = VELOCIDADE;
    }
}
