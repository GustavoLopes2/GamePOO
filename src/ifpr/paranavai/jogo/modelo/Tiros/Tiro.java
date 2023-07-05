package ifpr.paranavai.jogo.modelo.Tiros;

import ifpr.paranavai.jogo.modelo.ElementoGrafico;

import javax.swing.*;
import java.awt.*;

public class Tiro extends ElementoGrafico {
    private static final int VELOCIDADE = 12;
    public boolean destroid;
    public Tiro(int posX, int posY) {
        super.setPosicaoEmX(posX);
        super.setPosicaoEmY(posY);
        super.setVisivel(true);
    }
    @Override
    public void carregar() {
        ImageIcon carregando = new ImageIcon("src/resources/municaoTeste.png");
        super.setImagem(carregando.getImage());
        super.setAlturaImagem(super.getImagem().getHeight(null));
        super.setLarguraImagem(super.getImagem().getWidth(null));
    }

    @Override
    public void atualizar() {
        this.setPosicaoEmX(super.getPosicaoEmX() + VELOCIDADE);
    }

    public Rectangle getRectangle() {
        return new Rectangle(super.getPosicaoEmX(), super.getPosicaoEmY(), super.getLarguraImagem(), super.getAlturaImagem());
    }
}
