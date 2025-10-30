package Visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Herramientas {

    //ya hace falta los colores pablo XD
    public JLabel crearLabels(int letra, String Texto, int x, int y, int largo, int alto) {
        JLabel label = new JLabel(Texto);
        label.setBounds(x, y, largo, alto);
        label.setFont(new Font("Arial", Font.BOLD, letra));
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

    //ya no hace falta los coleres
    public JButton crearBoton(String Texto, int x, int y, int largo, int alto, ActionListener accion) {
        JButton boton = new JButton(Texto);
        boton.setBounds(x, y, largo, alto);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setFocusPainted(false);
        boton.addActionListener(accion);
        return boton;
    }

    //creo que no estaba subido al develop porque al hacer "git pull origin develop" para que este lo actualizado no salia, pero segun pablo lo subio
    public JLabel crearImagen(String ruta, int x, int y, int largo, int alto) {
        String path = System.getProperty("user.dir") + ruta;
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage().getScaledInstance(largo, alto, Image.SCALE_SMOOTH);
        JLabel imagen = new JLabel(new ImageIcon(img));
        imagen.setBounds(x, y, largo, alto);
        return imagen;
    }

    public Image getIcono() {
        String path = System.getProperty("user.dir") + "/Recursos/Logo_SCA.png";
        return new ImageIcon(path).getImage();
    }

    public JPanel crearPanel(Color color, int x, int y, int ancho, int alto) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(color);
        panel.setBounds(x, y, ancho, alto);
        return panel;
    }
}
