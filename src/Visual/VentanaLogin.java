package Visual;


import Controladores.SesionController;

import javax.swing.*;
import java.awt.*;

public class VentanaLogin {
    private final JFrame frameLogin = new JFrame("Login - SCA");
    private final Herramientas herramientas = new Herramientas();
    private final Tema tema = Tema.getModo();
    private SesionController sesion;

    private JLabel labelMatricula;
    private JLabel labelContraseña;
    private JTextField txtMatricula;
    private JPasswordField txtContraseña;
    private JButton btnIngresar;
    private JButton btnRegistrar;
    private JButton btnModo;
    private JLabel imaUFRO;
    private JLabel imaSCA;

    public VentanaLogin(SesionController sesion) {
        this.sesion = sesion;
        frameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameLogin.setSize(700, 350);
        frameLogin.setLocationRelativeTo(null);
        frameLogin.setLayout(null);
        frameLogin.setIconImage(herramientas.getIcono());
        frameLogin.setResizable(false);
    }

    private void labelsLogin() {
        labelMatricula = herramientas.crearLabels(14, "Matricula", 250, 120, 170, 25);
        labelContraseña = herramientas.crearLabels(14, "Contraseña", 250, 170, 100, 25);
    }

    private void textosLogin() {
        txtMatricula = herramientas.crearTextos(250, 140, 200, 25);
        txtContraseña = herramientas.crearTextosClave(250, 190, 200, 25);
    }

    private void botonesLogin() {
        btnIngresar = herramientas.crearBoton( "Ingresar",210, 235, 120, 25, e -> verificarIngreso());
        btnRegistrar = herramientas.crearBoton("Registrar", 360, 235, 120, 25, e -> mostrarRegistro());
        btnModo = herramientas.crearBoton("Tema", 570, 10, 100, 25, e -> cambiarTema());
    }

    private void imaLogin() {
        imaUFRO = herramientas.crearImagen("/Recursos/Icono_UFRO.png", 10, 10, 120, 104);
        imaSCA = herramientas.crearImagen("/Recursos/Logo_SCA.png", 290, 0, 130, 130);
    }

    public void mostrarVentanaLogin() {
        labelsLogin();
        textosLogin();
        botonesLogin();
        imaLogin();
        aplicarTema();


        frameLogin.add(labelMatricula);
        frameLogin.add(labelContraseña);
        frameLogin.add(txtMatricula);
        frameLogin.add(txtContraseña);
        frameLogin.add(btnIngresar);
        frameLogin.add(btnRegistrar);
        frameLogin.add(btnModo);
        frameLogin.add(imaUFRO);
        frameLogin.add(imaSCA);

        frameLogin.getRootPane().setDefaultButton(btnIngresar);
        frameLogin.setVisible(true);
    }

    private void mostrarRegistro() {
        frameLogin.dispose();
        VentanaRegistro registro = new VentanaRegistro(sesion);
        registro.mostrarVentanaRegistro();
    }

    private void verificarIngreso() {
        String n = txtMatricula.getText().trim();
        String c = new String(txtContraseña.getPassword()).trim();

        try {
            if (sesion.iniciarSesion(n, c)) {
                JOptionPane.showMessageDialog(frameLogin,
                        "Bienvenido " + sesion.getUsuarioActual().getNombre(),
                        "Login correcto",
                        JOptionPane.INFORMATION_MESSAGE);

                frameLogin.dispose();

               VentanaMain Main = new VentanaMain(sesion);
               Main.mostrarVentanaMain();

            } else {
                JOptionPane.showMessageDialog(frameLogin,
                        "Matricula y/o clave incorrectos",
                        "Intente nuevamente",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(frameLogin,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }


    private void cambiarTema() {
        tema.alternarTema();
        aplicarTema();
    }

    private void aplicarTema() {
        frameLogin.getContentPane().setBackground(tema.getFondo());
        labelMatricula.setForeground(tema.getTexto());
        labelContraseña.setForeground(tema.getTexto());
        btnIngresar.setBackground(tema.getBoton());
        btnIngresar.setForeground(tema.getBotonTexto());
        btnRegistrar.setBackground(tema.getBoton());
        btnRegistrar.setForeground(tema.getBotonTexto());
        btnModo.setBackground(tema.getBoton());
        btnModo.setForeground(tema.getBotonTexto());
        frameLogin.repaint();
    }
}
