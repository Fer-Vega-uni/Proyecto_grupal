package Visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Herramientas {

    public JLabel crearLabels(int letra, String Texto, int x, int y, int largo, int alto, Color color) {
        JLabel label = new JLabel(Texto);
        label.setBounds(x, y, largo, alto);
        label.setFont(new Font("Arial", Font.BOLD, letra));
        label.setForeground(color);
        return label;
    }

    public JTextField crearTextos(int x, int y, int largo, int alto) {
        JTextField txt = new JTextField();
        txt.setBounds(x, y, largo, alto);
        txt.setHorizontalAlignment(JTextField.CENTER);
        return txt;
    }

    public JPasswordField crearTextosClave(int x, int y, int largo, int alto) {
        JPasswordField clave = new JPasswordField();
        clave.setBounds(x, y, largo, alto);
        clave.setHorizontalAlignment(JPasswordField.CENTER);
        return clave;
    }

    public JButton crearBoton(Color colorFondo, Color colorLetra, String Texto, int x, int y, int largo, int alto, ActionListener accion) {
        JButton boton = new JButton(Texto);
        boton.setBackground(colorFondo);
        boton.setForeground(colorLetra);
        boton.setBounds(x, y, largo, alto);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setFocusPainted(false);
        boton.addActionListener(accion);
        return boton;
    }

    //creo que no estaba subido al develop porque al hacer "git pull origin develop" para que este lo actualizado no salia, pero segun pablo lo subio
    public JLabel crearImagen(String ruta, int x, int y, int largo, int alto) {
        ImageIcon icon = new ImageIcon(getClass().getResource(ruta));
        Image img = icon.getImage().getScaledInstance(largo, alto, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        JLabel imagen = new JLabel(icon);
        imagen.setBounds(x, y, largo, alto);
        return imagen;
    }

    public Image getIcono() {
        return new ImageIcon(getClass().getResource("/Recursos/Logo_SCA.png")).getImage();
    }
}
