package ifpr.paranavai.jogo.modelo.Ambiente;

import ifpr.paranavai.jogo.modelo.ElementoGrafico;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Estrelas extends ElementoGrafico {
    private static int VELOCIDADE = 7;
    private boolean isVisivel;

    public Estrelas(int xAleatorio, int yAleatorio) {
        super.setPosicaoEmX(xAleatorio);
        super.setPosicaoEmY(yAleatorio);
        super.setVisivel(true);
    }

    @Override
    public void carregar() {
        ImageIcon carregando = new ImageIcon("src\\resources\\estrela.png");
        super.setImagem(carregando.getImage());
        super.setAlturaImagem(super.getImagem().getHeight(null));
        super.setLarguraImagem(super.getImagem().getWidth(null));
    }

    @Override
    public void atualizar() {
        if(super.getPosicaoEmX() < 0) {
            super.setPosicaoEmX(super.getLarguraImagem());
            Random a = new Random();
            int m = a.nextInt(500);
            super.setPosicaoEmX(m + 1600);
            Random r = new Random();
            int n = r.nextInt(900);
            super.setPosicaoEmY(n);
        } else {
            super.setPosicaoEmX(super.getPosicaoEmX() - VELOCIDADE);
        }
    }
}
