package Visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Clase utilitaria que facilita la creación y configuración de componentes gráficos
 * para las ventanas del sistema.
 * <p>
 * Proporciona métodos preconfigurados para crear etiquetas, campos de texto,
 * botones, paneles e imágenes, asegurando una apariencia y comportamiento
 * uniforme en toda la interfaz.
 * </p>
 *
 * @author Pablo Ramos - [@PabloRamos02]
 * @version 1.0
 */
public class Herramientas {

    /**
     * Crea una etiqueta personalizada con posición, tamaño y fuente especificada.
     *
     * @param letra tamaño de la fuente en puntos
     * @param Texto texto a mostrar en la etiqueta
     * @param x     posición horizontal (en píxeles)
     * @param y     posición vertical (en píxeles)
     * @param largo ancho de la etiqueta
     * @param alto  alto de la etiqueta
     * @return un objeto {@link JLabel} configurado
     */
    public JLabel crearLabels(int letra, String Texto, int x, int y, int largo, int alto) {
        JLabel label = new JLabel(Texto);
        label.setBounds(x, y, largo, alto);
        label.setFont(new Font("Arial", Font.BOLD, letra));
        return label;
    }

    /**
     * Crea un campo de texto centrado.
     *
     * @param x     posición horizontal (en píxeles)
     * @param y     posición vertical (en píxeles)
     * @param largo ancho del campo
     * @param alto  alto del campo
     * @return un objeto {@link JTextField} configurado
     */
    public JTextField crearTextos(int x, int y, int largo, int alto) {
        JTextField txt = new JTextField();
        txt.setBounds(x, y, largo, alto);
        txt.setHorizontalAlignment(JTextField.CENTER);
        return txt;
    }

    /**
     * Crea un campo de texto para contraseñas centrado.
     *
     * @param x     posición horizontal (en píxeles)
     * @param y     posición vertical (en píxeles)
     * @param largo ancho del campo
     * @param alto  alto del campo
     * @return un objeto {@link JPasswordField} configurado
     */
    public JPasswordField crearTextosClave(int x, int y, int largo, int alto) {
        JPasswordField clave = new JPasswordField();
        clave.setBounds(x, y, largo, alto);
        clave.setHorizontalAlignment(JPasswordField.CENTER);
        return clave;
    }

    /**
     * Crea un botón con fuente, posición y acción especificada.
     *
     * @param Texto   texto a mostrar en el botón
     * @param x       posición horizontal (en píxeles)
     * @param y       posición vertical (en píxeles)
     * @param largo   ancho del botón
     * @param alto    alto del botón
     * @param accion  acción que se ejecutará al presionar el botón
     * @return un objeto {@link JButton} configurado
     */
    public JButton crearBoton(String Texto, int x, int y, int largo, int alto, ActionListener accion) {
        JButton boton = new JButton(Texto);
        boton.setBounds(x, y, largo, alto);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setFocusPainted(false);
        boton.addActionListener(accion);
        return boton;
    }

    /**
     * Crea una etiqueta con una imagen escalada a las dimensiones indicadas.
     *
     * @param ruta  ruta relativa de la imagen desde el directorio del proyecto
     * @param x     posición horizontal (en píxeles)
     * @param y     posición vertical (en píxeles)
     * @param largo ancho deseado de la imagen
     * @param alto  alto deseado de la imagen
     * @return un objeto {@link JLabel} que contiene la imagen escalada
     */
    public JLabel crearImagen(String ruta, int x, int y, int largo, int alto) {
        String path = System.getProperty("user.dir") + ruta;
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage().getScaledInstance(largo, alto, Image.SCALE_SMOOTH);
        JLabel imagen = new JLabel(new ImageIcon(img));
        imagen.setBounds(x, y, largo, alto);
        return imagen;
    }

    /**
     * Obtiene el icono principal de la aplicación desde la carpeta de recursos.
     *
     * @return un objeto {@link Image} con el icono del sistema
     */
    public Image getIcono() {
        String path = System.getProperty("user.dir") + "/Recursos/Logo_SCA.png";
        return new ImageIcon(path).getImage();
    }

    /**
     * Crea un panel de color y dimensiones específicas.
     *
     * @param color color de fondo del panel
     * @param x     posición horizontal (en píxeles)
     * @param y     posición vertical (en píxeles)
     * @param ancho ancho del panel
     * @param alto  alto del panel
     * @return un objeto {@link JPanel} configurado con el color y dimensiones especificadas
     */
    public JPanel crearPanel(Color color, int x, int y, int ancho, int alto) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(color);
        panel.setBounds(x, y, ancho, alto);
        return panel;
    }
}
