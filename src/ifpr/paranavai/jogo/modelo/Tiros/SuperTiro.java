package ifpr.paranavai.jogo.modelo.Tiros;

import ifpr.paranavai.jogo.modelo.ElementoGrafico;
import ifpr.paranavai.jogo.modelo.Personagem;

import javax.swing.*;
import java.awt.*;

public class SuperTiro extends ElementoGrafico {
    public boolean destroid;
    private Personagem personagem;
    private long tempoInicial;

    public SuperTiro(int posicaoEmX, int posicaoEmY, Personagem personagem) {
        super.setPosicaoEmX(posicaoEmX);
        super.setPosicaoEmY(posicaoEmY);
        super.setVisivel(true);
        this.personagem = personagem;
        this.tempoInicial = System.currentTimeMillis();
    }

    @Override
    public void carregar() {
        ImageIcon carregando = new ImageIcon("src/resources/superTiro42.png");
        super.setImagem(carregando.getImage());
        super.setAlturaImagem(super.getImagem().getHeight(null));
        super.setLarguraImagem(super.getImagem().getWidth(null));
    }

    @Override
    public void atualizar() {
        long tempoAtual = System.currentTimeMillis();
        long tempoDecorrido = tempoAtual - tempoInicial;
        if (tempoDecorrido >= 8000) {
            super.setVisivel(false);
        } else {
            double angulo = (tempoDecorrido / 1000.0) * Math.PI * 2;
            int raio = 142;

            int posX = (int) (Math.cos(angulo) * raio) + personagem.getPosicaoEmX() + (personagem.getLarguraImagem() / 2);
            int posY = (int) (Math.sin(angulo) * raio) + personagem.getPosicaoEmY() + (personagem.getAlturaImagem() / 2);

            super.setPosicaoEmX(posX);
            super.setPosicaoEmY(posY);
        }
    }
    public Rectangle getRectangle() {
        return new Rectangle(super.getPosicaoEmX(), super.getPosicaoEmY(), super.getLarguraImagem(), super.getAlturaImagem());
    }
}
