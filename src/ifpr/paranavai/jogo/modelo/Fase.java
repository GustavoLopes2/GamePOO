package ifpr.paranavai.jogo.modelo;

import ifpr.paranavai.jogo.modelo.Inimigos.InimigoUm;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public abstract class Fase extends JPanel implements ActionListener, KeyListener {
    public static final int DELAY = 5;
    public static final int LARGURA_DA_JANELA = 1536;
    public static final int QTDE_DE_INIMIGOS = 40;

    protected Image fundo;
    protected Personagem personagem;
    private ArrayList<InimigoUm> inimigosUm;
    protected Timer timer;

    public Fase() {
        setFocusable(true);
        setDoubleBuffered(true);
        addKeyListener(this);
    }

    public abstract void inicializaInimigos();
    public abstract void inicializaEstrelas();
    public abstract void colisao();

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public abstract void keyPressed(KeyEvent e);

    @Override
    public abstract void keyReleased(KeyEvent e);

    @Override
    public abstract void actionPerformed(ActionEvent e);
}
