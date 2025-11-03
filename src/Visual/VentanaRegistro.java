package Visual;

import javax.swing.*;

import Controladores.SesionController;

import java.awt.*;

/**
 * Ventana de registro de nuevos usuarios en el sistema SCA (Sistema de Carpeta Académica).
 * <p>
 * Permite crear una nueva cuenta ingresando matrícula, contraseña, nombre y carrera.
 * Una vez completado el registro, el usuario puede volver a la ventana de inicio de sesión.
 * </p>
 * <p>
 * Esta clase utiliza el controlador {@link SesionController} para registrar usuarios,
 * y las clases {@link Herramientas} y {@link Tema} para gestionar los componentes
 * visuales y la apariencia del sistema.
 * </p>
 *
 * @author Marcelo Orellana - [@morellana09-dotcom]
 * @version 1.0
 */
public class VentanaRegistro {
    /** Ventana principal del registro. */
    private final JFrame frameRegistro = new JFrame("Registro - SCA");
    /** Herramienta auxiliar para crear componentes gráficos. */
    private final Herramientas herramientas = new Herramientas();
    /** Tema visual actual (claro u oscuro). */
    private final Tema tema = Tema.getModo();
    /** Controlador de sesión que gestiona los usuarios registrados. */
    private SesionController sesion;

    // Componentes gráficos
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

    /**
     * Constructor de la ventana de registro.
     *
     * @param sesion controlador de sesión utilizado para gestionar el registro de nuevos usuarios
     */
    public VentanaRegistro(SesionController sesion) {
        this.sesion = sesion;
        frameRegistro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameRegistro.setSize(700, 350);
        frameRegistro.setLocationRelativeTo(null);
        frameRegistro.setLayout(null);
        frameRegistro.setIconImage(herramientas.getIcono());
        frameRegistro.setResizable(false);
    }

    /**
     * Crea y configura las etiquetas del formulario de registro.
     */
    private void labelsRegistro() {
        lblMatricula = herramientas.crearLabels(15, "Matricula", 250, 30, 170, 25);
        lblContraseña = herramientas.crearLabels(15, "Contraseña", 250, 80, 100, 25);
        lblNombre = herramientas.crearLabels(15, "Nombre", 250, 130, 170, 25);
        lblCarrera = herramientas.crearLabels(15, "Carrera", 250, 180, 170, 25);
    }

    /**
     * Crea los campos de texto del formulario de registro.
     */
    private void txtRegistro() {
        txtMatricula = herramientas.crearTextos(250, 50, 200, 25);
        txtContraseña = herramientas.crearTextosClave(250, 100, 200, 25);
        txtNombre = herramientas.crearTextos(250, 150, 200, 25);
        txtCarrera = herramientas.crearTextos(250, 200, 200, 25);
    }

    /**
     * Crea los botones principales del formulario de registro:
     * <ul>
     *     <li>Registrar</li>
     *     <li>Volver al login</li>
     * </ul>
     */
    private void btnRegistro() {
        btnRegistrar = herramientas.crearBoton("Registrar", 210, 245, 120, 25, e -> verificarRegistro());
        btnVolver = herramientas.crearBoton("Volver", 360, 245, 120, 25, e -> volverLogin());
    }

    /**
     * Crea y carga las imágenes decorativas (logo UFRO y logo SCA).
     */
    private void imaRegistro() {
        imaUFRO = herramientas.crearImagen("/Recursos/Icono_UFRO.png", 10, 10, 120, 104);
        imaSCA = herramientas.crearImagen("/Recursos/Logo_SCA.png", 560, 0, 120, 120);
    }

    /**
     * Muestra la ventana de registro completamente configurada.
     * <p>
     * Incluye todos los campos, botones e imágenes del formulario.
     * </p>
     */
    public void mostrarVentanaRegistro() {
        labelsRegistro();
        txtRegistro();
        btnRegistro();
        imaRegistro();
        aplicartema();

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

    /**
     * Cierra la ventana de registro y vuelve a la ventana de inicio de sesión.
     */
    private void volverLogin() {
        frameRegistro.dispose();
        VentanaLogin login = new VentanaLogin(sesion);
        login.mostrarVentanaLogin();
    }

    /**
     * Verifica la validez de los datos ingresados y registra un nuevo usuario.
     * <p>
     * Si el registro es exitoso, muestra un mensaje de confirmación y redirige al login.
     * Si ocurre un error (campos vacíos o duplicados), se muestra un mensaje de advertencia.
     * </p>
     */
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

    /**
     * Aplica el tema visual actual a todos los componentes de la ventana.
     */
    private void aplicartema() {
        frameRegistro.getContentPane().setBackground(tema.getFondo());
        lblMatricula.setForeground(tema.getTexto());
        lblContraseña.setForeground(tema.getTexto());
        lblNombre.setForeground(tema.getTexto());
        lblCarrera.setForeground(tema.getTexto());
        btnRegistrar.setBackground(tema.getBoton());
        btnRegistrar.setForeground(tema.getBotonTexto());
        btnVolver.setBackground(tema.getBoton());
        btnVolver.setForeground(tema.getBotonTexto());

    }
}