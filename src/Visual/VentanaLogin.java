package Visual;

import Controladores.SesionController;
import javax.swing.*;
import java.awt.*;

/**
 * Ventana principal de inicio de sesión del sistema SCA.
 * <p>
 * Permite al usuario ingresar sus credenciales para acceder al sistema o registrarse
 * si aún no tiene una cuenta. Integra el control de sesión a través de
 * {@link SesionController}, la configuración visual mediante {@link Tema} y la
 * creación de componentes gráficos reutilizables mediante {@link Herramientas}.
 * </p>
 *
 * @author Marcelo Orellana - [@morellana09-dotcom]
 * @version 1.0
 */
public class VentanaLogin {
    /** Ventana principal del login. */
    private final JFrame frameLogin = new JFrame("Login - SCA");
    /** Herramienta auxiliar para crear componentes gráficos. */
    private final Herramientas herramientas = new Herramientas();
    /** Tema actual de la interfaz. */
    private final Tema tema = Tema.getModo();
    /** Controlador de sesión del sistema. */
    private SesionController sesion;

    // Componentes gráficos
    private JLabel labelMatricula;
    private JLabel labelContraseña;
    private JTextField txtMatricula;
    private JPasswordField txtContraseña;
    private JButton btnIngresar;
    private JButton btnRegistrar;
    private JButton btnModo;
    private JLabel imaUFRO;
    private JLabel imaSCA;

    /**
     * Constructor de la ventana de login.
     *
     * @param sesion instancia del controlador de sesión utilizada para validar usuarios
     */
    public VentanaLogin(SesionController sesion) {
        this.sesion = sesion;
        frameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameLogin.setSize(700, 350);
        frameLogin.setLocationRelativeTo(null);
        frameLogin.setLayout(null);
        frameLogin.setIconImage(herramientas.getIcono());
        frameLogin.setResizable(false);
    }

    /**
     * Crea y configura las etiquetas del formulario de inicio de sesión.
     */
    private void labelsLogin() {
        labelMatricula = herramientas.crearLabels(14, "Matricula", 250, 120, 170, 25);
        labelContraseña = herramientas.crearLabels(14, "Contraseña", 250, 170, 100, 25);
    }

    /**
     * Crea los campos de texto para el ingreso de credenciales.
     */
    private void textosLogin() {
        txtMatricula = herramientas.crearTextos(250, 140, 200, 25);
        txtContraseña = herramientas.crearTextosClave(250, 190, 200, 25);
    }

    /**
     * Crea los botones principales de la ventana:
     * ingresar, registrar y cambiar tema.
     */
    private void botonesLogin() {
        btnIngresar = herramientas.crearBoton( "Ingresar",210, 235, 120, 25, e -> verificarIngreso());
        btnRegistrar = herramientas.crearBoton("Registrar", 360, 235, 120, 25, e -> mostrarRegistro());
        btnModo = herramientas.crearBoton("Tema", 570, 10, 100, 25, e -> cambiarTema());
    }

    /**
     * Crea y carga las imágenes del logo institucional y del sistema SCA.
     */
    private void imaLogin() {
        imaUFRO = herramientas.crearImagen("/Recursos/Icono_UFRO.png", 10, 10, 120, 104);
        imaSCA = herramientas.crearImagen("/Recursos/Logo_SCA.png", 290, 0, 130, 130);
    }

    /**
     * Muestra la ventana de inicio de sesión con todos sus componentes configurados.
     * <p>
     * Incluye la configuración del tema visual y la funcionalidad de los botones.
     * </p>
     */
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

    /**
     * Cierra la ventana actual y abre la ventana de registro de usuario.
     */
    private void mostrarRegistro() {
        frameLogin.dispose();
        VentanaRegistro registro = new VentanaRegistro(sesion);
        registro.mostrarVentanaRegistro();
    }

    /**
     * Verifica las credenciales ingresadas por el usuario.
     * <p>
     * Si son correctas, se muestra un mensaje de bienvenida y se abre la ventana principal
     * del sistema. En caso contrario, se muestra un mensaje de error.
     * </p>
     */
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

    /**
     * Cambia el tema visual de la aplicación (claro/oscuro).
     */
    private void cambiarTema() {
        tema.alternarTema();
        aplicarTema();
    }

    /**
     * Aplica el tema visual actual a todos los componentes de la ventana.
     */
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
