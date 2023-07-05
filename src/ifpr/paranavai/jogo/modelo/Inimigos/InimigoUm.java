package ifpr.paranavai.jogo.modelo.Inimigos;

import ifpr.paranavai.jogo.modelo.ElementoGrafico;

import javax.swing.*;
import java.awt.*;

public class InimigoUm extends ElementoGrafico {
    private static int VELOCIDADE = 8;
    public boolean destroid;
    public int life;

    public InimigoUm(int xAleatorio, int yAleatorio) {
        super.setPosicaoEmX(xAleatorio);
        super.setPosicaoEmY(yAleatorio);
        super.setVisivel(true);
    }
    @Override
    public void carregar() {
        ImageIcon carregando = new ImageIcon("src/resources/inimigo.png");
        super.setImagem(carregando.getImage());
        super.setAlturaImagem(super.getImagem().getHeight(null));
        super.setLarguraImagem(super.getImagem().getWidth(null));
    }

    @Override
    public void atualizar() {
        this.setPosicaoEmX(super.getPosicaoEmX() - VELOCIDADE);
    }

    public Rectangle getRectangle() {
        return new Rectangle(super.getPosicaoEmX(), super.getPosicaoEmY(), super.getLarguraImagem(), super.getAlturaImagem());
    }
}
