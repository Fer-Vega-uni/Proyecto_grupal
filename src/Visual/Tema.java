package Visual;

import java.awt.*;

public class Tema {
    private static Tema modo;

    private boolean modoOscuro;
    private Color fondo;
    private Color texto;
    private Color boton;
    private Color botonTexto;

    private Tema() {
        modoOscuro = false;
        aplicarTema();
    }

    public static Tema getModo() {
        if (modo == null) {
            modo = new Tema();
        }
        return modo;
    }
    public void alternarTema() {
        modoOscuro = !modoOscuro;
        aplicarTema();
    }

    private void aplicarTema() {
        if (modoOscuro) {
            fondo = new Color(30, 30, 30);
            texto = Color.WHITE;
            boton = new Color(60, 60, 60);
            botonTexto = Color.WHITE;
        } else {
            fondo = new Color(199, 236, 252);
            texto = Color.BLACK;
            boton = new Color(1, 53, 110);
            botonTexto = Color.WHITE;
        }
    }

    public boolean getModoOscuro() {
        return modoOscuro;
    }

    public Color getFondo() {
        return fondo;
    }

    public Color getTexto() {
        return texto;
    }

    public Color getBoton() {
        return boton;
    }

    public Color getBotonTexto() {
        return botonTexto;
    }
}

//empiezan con el tema normal

