package Visual;

import Controladores.ArchivosController;
import Controladores.SesionController;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana principal del sistema SCA (Sistema de Carpeta Académica).
 * <p>
 * Permite al usuario navegar por sus unidades personales y asignaturas, visualizar
 * archivos disponibles y acceder al menú lateral con opciones de personalización,
 * subida de archivos y cierre de sesión.
 * </p>
 * <p>
 * Esta clase integra controladores de sesión ({@link SesionController}) y archivos
 * ({@link ArchivosController}), junto con la configuración visual proporcionada por
 * {@link Tema} y {@link Herramientas}.
 * </p>
 *
 * @author Marcelo Orellana - [@morellana09-dotcom]
 * @version 1.0
 */
public class VentanaMain {
    /** Ventana principal del sistema. */
    private final JFrame frameMain = new JFrame("Main - SCA");
    /** Utilidad para crear componentes gráficos preconfigurados. */
    private final Herramientas herramientas = new Herramientas();
    /** Tema visual actual (claro u oscuro). */
    private final Tema tema = Tema.getModo();
    /** Controlador encargado de la gestión de archivos. */
    private final ArchivosController archivosController = new ArchivosController();
    /** Controlador de sesión que gestiona el usuario autenticado. */
    private final SesionController sesion;

    // Componentes principales
    private JLayeredPane capas;
    private JPanel panelIzquierdo;
    private JPanel panelCentral;
    private JPanel panelDerecho;
    private JLabel lblBienvenida;
    private JLabel lblTituloUnidad;
    private JLabel lblAsignaturas;
    private JLabel lblMenuHola;
    private JLabel lblMenuNombre;
    private JLabel lblMenuCarrera;
    private JButton btnMenuDerecho;
    private JButton btnMenuTema;
    private JButton btnMenuSubir;
    private JButton btnMenuCerrarSesion;
    private JButton btnVolverUnidad;

    // Propiedades de animación y control del menú
    private boolean menuVisible = false;
    private Timer animacionMenu;
    private int anchoMenu = 300;
    private int velocidad = 10;

    /**
     * Constructor de la ventana principal.
     *
     * @param sesion controlador de sesión que contiene los datos del usuario autenticado
     */
    public VentanaMain(SesionController sesion) {
        this.sesion = sesion;

        frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameMain.setSize(1200, 800);
        frameMain.setLocationRelativeTo(null);
        frameMain.setLayout(null);
        frameMain.setResizable(false);
        frameMain.setIconImage(herramientas.getIcono());
    }

    /**
     * Crea y configura los paneles principales de la interfaz:
     * lateral izquierdo, central y menú lateral derecho.
     */
    private void panelesMain() {
        capas = new JLayeredPane();
        capas.setBounds(0, 0, 1200, 800);
        frameMain.add(capas);

        panelIzquierdo = herramientas.crearPanel(tema.getBoton(), 0, 0, 220, 800);
        panelCentral = herramientas.crearPanel(tema.getFondo(), 220, 100, 980, 700);
        panelDerecho = herramientas.crearPanel(tema.getBoton(), 1200, 0, anchoMenu, 800);
        panelDerecho.setLayout(null);


        capas.add(panelIzquierdo, Integer.valueOf(0));
        capas.add(panelCentral, Integer.valueOf(1));
        capas.add(panelDerecho, Integer.valueOf(2));
        contenidoPanelDerecho();
    }

    /**
     * Crea y configura las etiquetas principales de la ventana, incluyendo la bienvenida,
     * el título de la unidad personal y la sección de asignaturas.
     */
    private void labelsMain() {
        lblBienvenida = herramientas.crearLabels(48, "Bienvenid@ a SCA", 470, 25, 800, 55);
        lblBienvenida.setForeground(tema.getBotonTexto());
        frameMain.add(lblBienvenida);

        String nombreUsuario = sesion.getUsuarioActual().getNombre();
        lblTituloUnidad = herramientas.crearLabels(30, "Mi Unidad: " + nombreUsuario, 20, 10, 800, 40);
        lblTituloUnidad.setForeground(tema.getTexto());
        panelCentral.add(lblTituloUnidad);

        lblAsignaturas = herramientas.crearLabels(26, "Asignaturas", 35, 25, 200, 40);
        lblAsignaturas.setForeground(tema.getBotonTexto());
        panelIzquierdo.add(lblAsignaturas);

        mostrarArchivosUnidad(nombreUsuario);
    }

    /**
     * Genera los botones de navegación de asignaturas y el botón del menú lateral.
     * <p>
     * Si no hay asignaturas registradas, muestra un mensaje informativo.
     * </p>
     */
    private void botonesMain() {
        btnMenuDerecho = herramientas.crearBoton("☰", 1100, 15, 80, 60, e -> toggleMenu());
        btnMenuDerecho.setFont(new Font("Segoe UI Symbol", Font.BOLD, 30));
        btnMenuDerecho.setForeground(tema.getBotonTexto());
        btnMenuDerecho.setFocusPainted(false);
        btnMenuDerecho.setBorderPainted(false);
        btnMenuDerecho.setContentAreaFilled(false);
        capas.add(btnMenuDerecho, Integer.valueOf(3));

        String[] asignaturas = archivosController.listarAsignaturas();
        if (asignaturas.length == 0) {
            JLabel lblVacio = herramientas.crearLabels(20, "No hay asignaturas", 30, 90, 200, 25);
            lblVacio.setForeground(tema.getBotonTexto());
            panelIzquierdo.add(lblVacio);
            return;
        }

        int y = 70;
        for (String nombre : asignaturas) {
            JButton btn = herramientas.crearBoton(nombre, 20, y, 180, 35, e -> mostrarAsignatura(nombre));
            btn.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            btn.setForeground(tema.getBotonTexto());
            btn.setBackground(tema.getBoton());
            btn.setBorderPainted(false);
            btn.setFocusPainted(false);
            btn.setContentAreaFilled(false);
            btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            btn.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    btn.setForeground(Color.LIGHT_GRAY);
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
                    btn.setForeground(tema.getBotonTexto());
                }
            });

            panelIzquierdo.add(btn);
            y += 30;
        }
    }

    /**
     * Configura el saludo y la información del usuario en el menú lateral derecho.
     */
    private void labelsMenuSaludo() {
        String nombre = sesion.getUsuarioActual().getNombre();
        String carrera = sesion.getUsuarioActual().getCarrera();

        lblMenuHola = herramientas.crearLabels(28, "Hola,", 30, 100, 200, 40);
        lblMenuHola.setForeground(Color.WHITE);
        panelDerecho.add(lblMenuHola);

        lblMenuNombre = herramientas.crearLabels(32, nombre, 30, 140, 250, 45);
        lblMenuNombre.setForeground(Color.WHITE);
        lblMenuNombre.setFont(new Font("Segoe UI", Font.BOLD, 28));
        panelDerecho.add(lblMenuNombre);

        lblMenuCarrera = herramientas.crearLabels(18, carrera, 30, 185, 240, 25);
        lblMenuCarrera.setHorizontalAlignment(SwingConstants.CENTER);
        lblMenuCarrera.setForeground(Color.WHITE);
        panelDerecho.add(lblMenuCarrera);
    }

    /**
     * Configura los botones del menú lateral derecho:
     * volver a unidad, cambiar tema, subir archivo y cerrar sesión.
     */
    private void botonesMenuOpciones() {
        btnVolverUnidad = herramientas.crearBoton("Volver a Mi Unidad", 40, 500, 200, 40, e -> mostrarUnidadPersonal());
        estiloBotonLateral(btnVolverUnidad);
        panelDerecho.add(btnVolverUnidad);

        btnMenuTema = herramientas.crearBoton("Cambiar tema", 40, 550, 200, 40, e -> {
            tema.alternarTema();
            aplicarTema();
        });
        estiloBotonLateral(btnMenuTema);
        panelDerecho.add(btnMenuTema);

        btnMenuSubir = herramientas.crearBoton("Subir archivo", 40, 600, 200, 40, e ->
                JOptionPane.showMessageDialog(frameMain,
                        ";_; " ,
                        "Aun no hecho....",
                        JOptionPane.INFORMATION_MESSAGE));
        estiloBotonLateral(btnMenuSubir);
        panelDerecho.add(btnMenuSubir);

        btnMenuCerrarSesion = herramientas.crearBoton("Cerrar sesión", 40, 650, 200, 40, e -> {
            frameMain.dispose();
            new VentanaLogin(sesion).mostrarVentanaLogin();
        });
        estiloBotonLateral(btnMenuCerrarSesion);
        panelDerecho.add(btnMenuCerrarSesion);
    }

    /**
     * Inicializa el contenido del panel lateral derecho (saludo y opciones).
     */
    private void contenidoPanelDerecho() {
        labelsMenuSaludo();
        botonesMenuOpciones();
    }

    /**
     * Aplica un estilo común a los botones del menú lateral derecho.
     *
     * @param boton botón a personalizar
     */
    private void estiloBotonLateral(JButton boton) {
        boton.setBackground(tema.getBoton());
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                boton.setForeground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                boton.setForeground(Color.WHITE);
            }
        });
    }

    /**
     * Muestra u oculta el menú lateral derecho mediante una animación.
     */
    private void toggleMenu() {
        if (animacionMenu != null && animacionMenu.isRunning()) return;
        menuVisible = !menuVisible;
        int destino = menuVisible ? 900 : 1200;

        animacionMenu = new Timer(5, e -> {
            int x = panelDerecho.getX();
            if (menuVisible && x > destino) {
                panelDerecho.setLocation(x - velocidad, 0);
            } else if (!menuVisible && x < destino) {
                panelDerecho.setLocation(x + velocidad, 0);
            } else {
                ((Timer) e.getSource()).stop();
            }
            frameMain.repaint();
        });
        animacionMenu.start();
    }

    /**
     * Muestra los archivos personales del usuario en el panel central.
     *
     * @param nombreUsuario nombre del usuario cuya carpeta se va a mostrar
     */
    private void mostrarArchivosUnidad(String nombreUsuario) {
        String[] archivos = archivosController.listarUnidadUsuario(nombreUsuario);
        int y = 50;

        if (archivos.length == 0) {
            JLabel lblVacio = herramientas.crearLabels(20, "Tu unidad está vacía.", 20, y, 400, 25);
            lblVacio.setForeground(tema.getTexto());
            panelCentral.add(lblVacio);
        } else {
            for (String archivo : archivos) {
                JLabel lblArchivo = herramientas.crearLabels(20, "• " + archivo, 120, y, 400, 25);
                lblArchivo.setForeground(tema.getTexto());
                panelCentral.add(lblArchivo);
                y += 30;
            }
        }
    }

    /**
     * Muestra los archivos de una asignatura específica en el panel central.
     *
     * @param nombre nombre de la asignatura seleccionada
     */
    private void mostrarAsignatura(String nombre) {
        panelCentral.removeAll();

        JLabel lblTitulo = herramientas.crearLabels(40, nombre.toUpperCase(), 20, 10, 800, 50);
        lblTitulo.setForeground(tema.getTexto());
        panelCentral.add(lblTitulo);

        JLabel lblInfo = herramientas.crearLabels(25, "Archivos en esta asignatura:", 20, 60, 500, 25);
        lblInfo.setForeground(tema.getTexto());
        panelCentral.add(lblInfo);

        String rutaCarpeta = System.getProperty("user.dir") + "/Recursos/asignaturas/" + nombre;
        String[] archivos = archivosController.listarArchivos(nombre);
        int y = 90;

        if (archivos.length == 0) {
            JLabel lblVacio = herramientas.crearLabels(14, "Carpeta vacía", 20, y, 300, 25);
            lblVacio.setForeground(tema.getTexto());
            panelCentral.add(lblVacio);
        } else {
            for (String archivo : archivos) {
                JLabel lblArchivo = herramientas.crearLabels(14, "• " + archivo, 20, y, 400, 25);
                lblArchivo.setForeground(tema.getTexto());
                lblArchivo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                lblArchivo.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        try {
                            java.awt.Desktop.getDesktop().open(new java.io.File(rutaCarpeta + "/" + archivo));
                        } catch (Exception ignored) {
                        }
                    }
                });
                panelCentral.add(lblArchivo);
                y += 30;
            }
        }

        panelCentral.revalidate();
        panelCentral.repaint();
    }

    /**
     * Muestra la ventana principal del sistema completamente configurada.
     */
    public void mostrarVentanaMain() {
        panelesMain();
        labelsMain();
        botonesMain();
        aplicarTema();
        frameMain.setVisible(true);
        panelDerecho.setLocation(1200, 0);
    }

    /**
     * Restaura el panel central mostrando la carpeta personal del usuario.
     */
    private void mostrarUnidadPersonal() {
        panelCentral.removeAll();
        String nombreUsuario = sesion.getUsuarioActual().getNombre();

        JLabel lblTitulo = herramientas.crearLabels(30, "Mi Unidad: " + nombreUsuario, 20, 10, 800, 40);
        lblTitulo.setForeground(tema.getTexto());
        panelCentral.add(lblTitulo);

        mostrarArchivosUnidad(nombreUsuario);

        panelCentral.revalidate();
        panelCentral.repaint();
    }

    /**
     * Aplica el tema visual actual a los paneles, etiquetas y botones de la interfaz.
     */
    private void aplicarTema() {
        frameMain.getContentPane().setBackground(tema.getBoton());
        panelCentral.setBackground(tema.getFondo());
        panelIzquierdo.setBackground(tema.getBoton());
        panelDerecho.setBackground(tema.getBoton());

        lblBienvenida.setForeground(tema.getBotonTexto());
        lblTituloUnidad.setForeground(tema.getTexto());
        lblAsignaturas.setForeground(tema.getBotonTexto());
        lblMenuHola.setForeground(tema.getBotonTexto());
        lblMenuNombre.setForeground(tema.getBotonTexto());
        lblMenuCarrera.setForeground(tema.getBotonTexto());

       actualizarLabelsPanelCentral();

        actualizarColorBoton(btnVolverUnidad);
        actualizarColorBoton(btnMenuTema);
        actualizarColorBoton(btnMenuSubir);
        actualizarColorBoton(btnMenuCerrarSesion);

        frameMain.repaint();
    }

    /**
     * Actualiza el color de fondo y texto de un botón según el tema activo.
     *
     * @param boton botón a actualizar
     */
    private void actualizarColorBoton(JButton boton) {
        boton.setBackground(tema.getBoton());
        boton.setForeground(tema.getBotonTexto());
    }

    /**
     * Actualiza el color de todas las etiquetas contenidas en el panel central.
     */
    private void actualizarLabelsPanelCentral() {
        for (Component comp : panelCentral.getComponents()) {
            if (comp instanceof JLabel label) {
                label.setForeground(tema.getTexto());
            }
        }
    }
}


