package Visual;

import java.awt.*;

/**
 * Clase que gestiona el tema visual de la aplicación, permitiendo alternar entre
 * modo claro y modo oscuro.
 * <p>
 * Implementa el patrón Singleton para asegurar que solo exista una instancia del tema
 * en toda la aplicación. Proporciona los colores principales para el fondo, texto
 * y botones, de acuerdo con el modo activo.
 * </p>
 *
 * @author Marcelo Orellana - [@morellana09-dotcom]
 * @version 1.0 - Creación inicial (02/11/2025)
 */
public class Tema {
    /** Instancia única del tema (patrón Singleton). */
    private static Tema modo;

    /** Indica si el tema actual es oscuro. */
    private boolean modoOscuro;
    /** Color de fondo principal. */
    private Color fondo;
    /** Color del texto principal. */
    private Color texto;
    /** Color de fondo de los botones. */
    private Color boton;
    /** Color del texto en los botones. */
    private Color botonTexto;

    /**
     * Constructor privado para restringir la creación directa de instancias.
     * Inicializa el tema por defecto en modo claro.
     */
    private Tema() {
        modoOscuro = false;
        aplicarTema();
    }

    /**
     * Devuelve la instancia única del tema.
     * <p>
     * Si no existe una instancia previa, se crea automáticamente.
     * </p>
     *
     * @return la instancia única de {@link Tema}
     */
    public static Tema getModo() {
        if (modo == null) {
            modo = new Tema();
        }
        return modo;
    }

    /**
     * Alterna entre modo claro y modo oscuro, aplicando los colores correspondientes.
     */
    public void alternarTema() {
        modoOscuro = !modoOscuro;
        aplicarTema();
    }

    /**
     * Aplica los colores correspondientes según el modo actual.
     * <p>
     * Este método se encarga de actualizar los valores de fondo, texto y botones
     * dependiendo de si el modo activo es oscuro o claro.
     * </p>
     */
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

    /**
     * Indica si el modo actual es oscuro.
     *
     * @return {@code true} si está activo el modo oscuro, {@code false} en caso contrario
     */
    public boolean getModoOscuro() {
        return modoOscuro;
    }

    /**
     * Devuelve el color de fondo actual.
     *
     * @return color del fondo
     */
    public Color getFondo() {
        return fondo;
    }

    /**
     * Devuelve el color principal del texto.
     *
     * @return color del texto
     */
    public Color getTexto() {
        return texto;
    }

    /**
     * Devuelve el color de fondo de los botones.
     *
     * @return color del botón
     */
    public Color getBoton() {
        return boton;
    }

    /**
     * Devuelve el color del texto de los botones.
     *
     * @return color del texto de los botones
     */
    public Color getBotonTexto() {
        return botonTexto;
    }
}



