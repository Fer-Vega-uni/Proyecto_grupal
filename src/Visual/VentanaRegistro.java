package Visual;

import javax.swing.*;

import Controladores.SesionController;

import java.awt.*;

public class VentanaRegistro {
    private final JFrame frameRegistro = new JFrame("Registro - SCA");
    private final Herramientas herramientas = new Herramientas();
    private SesionController sesion;

    private JLabel lblMatricula;
    private JTextField txtMatricula;
    private JLabel lblContraseña;
    private JPasswordField txtContraseña;
    private JLabel lblNombre;
    private JTextField txtNombre;
    private JLabel lblCarrera;
    private JTextField txtCarrera;
    private JButton btnRegistrar;
    private JButton btnVolver;
    private JLabel imaUFRO;
    private JLabel imaSCA;

    public VentanaRegistro(SesionController sesion) {
        this.sesion = sesion;
        frameRegistro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameRegistro.setSize(700, 350);
        frameRegistro.setLocationRelativeTo(null);
        frameRegistro.setLayout(null);
        frameRegistro.getContentPane().setBackground(new Color(199, 236, 252));
        frameRegistro.setIconImage(herramientas.getIcono());
        frameRegistro.setResizable(false);
    }

    private void labelsRegistro() {
        lblMatricula = herramientas.crearLabels(15, "Matricula", 250, 30, 170, 25,Color.BLACK);
        lblContraseña = herramientas.crearLabels(15, "Contraseña", 250, 80, 100, 25,Color.BLACK);
        lblNombre = herramientas.crearLabels(15, "Nombre", 250, 130, 170, 25, Color.BLACK);
        lblCarrera = herramientas.crearLabels(15, "Carrera", 250, 180, 170, 25,Color.BLACK);
    }

    private void txtRegistro() {
        txtMatricula = herramientas.crearTextos(250, 50, 200, 25);
        txtContraseña = herramientas.crearTextosClave(250, 100, 200, 25);
        txtNombre = herramientas.crearTextos(250, 150, 200, 25);
        txtCarrera = herramientas.crearTextos(250, 200, 200, 25);
    }

    private void btnRegistro() {
        btnRegistrar = herramientas.crearBoton(new Color(1, 53, 110),Color.WHITE ,"Registrar", 210, 245, 120, 25, e -> verificarRegistro());
        btnVolver = herramientas.crearBoton(new Color(1, 53, 110),Color.WHITE ,"Volver", 360, 245, 120, 25, e -> volverLogin());


    }

    private void imaRegistro() {
        imaUFRO = herramientas.crearImagen("/Recursos/Icono_UFRO.png", 10, 10, 120, 104);
        imaSCA = herramientas.crearImagen("/Recursos/Logo_SCA.png", 560, 0, 120, 120);
    }

    public void mostrarVentanaRegistro() {
        labelsRegistro();
        txtRegistro();
        btnRegistro();
        imaRegistro();

        frameRegistro.add(lblMatricula);
        frameRegistro.add(txtMatricula);
        frameRegistro.add(lblContraseña);
        frameRegistro.add(txtContraseña);
        frameRegistro.add(lblNombre);
        frameRegistro.add(txtNombre);
        frameRegistro.add(lblCarrera);
        frameRegistro.add(txtCarrera);
        frameRegistro.add(btnRegistrar);
        frameRegistro.add(btnVolver);
        frameRegistro.add(imaUFRO);
        frameRegistro.add(imaSCA);

        frameRegistro.getRootPane().setDefaultButton(btnRegistrar);
        frameRegistro.setVisible(true);
    }

    private void volverLogin() {
        frameRegistro.dispose();
        VentanaLogin login = new VentanaLogin(sesion);
        login.mostrarVentanaLogin();
    }

    private void verificarRegistro() {
        String m = txtMatricula.getText().trim();
        String c = new String(txtContraseña.getPassword()).trim();
        String n = txtNombre.getText().trim();
        String i = txtCarrera.getText().trim();

        try {
            if (sesion.Registro(m, c, n, i)) {
                JOptionPane.showMessageDialog(frameRegistro,
                        "Registro exitoso del usuario: " + n,
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);

                volverLogin();
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(frameRegistro,
                    ex.getMessage(),
                    "Error al registrar",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}