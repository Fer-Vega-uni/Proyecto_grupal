package Visual;

import Controladores.ArchivosController;
import Controladores.SesionController;

import javax.swing.*;
import java.awt.*;

public class VentanaMain {

    private final JFrame frameMain = new JFrame("Main - SCA");
    private final Herramientas herramientas = new Herramientas();
    private final Tema tema = Tema.getModo();
    private final ArchivosController archivosController = new ArchivosController();
    private final SesionController sesion;

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

    private boolean menuVisible = false;
    private Timer animacionMenu;
    private int anchoMenu = 300;
    private int velocidad = 10;

    public VentanaMain(SesionController sesion) {
        this.sesion = sesion;

        frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameMain.setSize(1200, 800);
        frameMain.setLocationRelativeTo(null);
        frameMain.setLayout(null);
        frameMain.setResizable(false);
        frameMain.setIconImage(herramientas.getIcono());
    }

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

        btnMenuSubir = herramientas.crearBoton("Subir archivo", 40, 600, 200, 40, e -> {
            VentanaSubirArchivo ventanaSubir =
                    new VentanaSubirArchivo(sesion, archivosController, frameMain);
            ventanaSubir.mostrar();
        });
        estiloBotonLateral(btnMenuSubir);
        panelDerecho.add(btnMenuSubir);

        btnMenuCerrarSesion = herramientas.crearBoton("Cerrar sesión", 40, 650, 200, 40, e -> {
            frameMain.dispose();
            new VentanaLogin(sesion).mostrarVentanaLogin();
        });
        estiloBotonLateral(btnMenuCerrarSesion);
        panelDerecho.add(btnMenuCerrarSesion);
    }

    private void contenidoPanelDerecho() {
        labelsMenuSaludo();
        botonesMenuOpciones();
    }

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

    private void mostrarArchivosUnidad(String nombreUsuario) {
        java.io.File carpetaUsuario = archivosController.crearUnidadUsuario(nombreUsuario);

        String[] archivos = archivosController.listarUnidadUsuario(nombreUsuario);
        int y = 60;

        if (archivos.length == 0) {
            JLabel lblVacio = herramientas.crearLabels(20, "Tu unidad está vacía.", 20, y, 400, 25);
            lblVacio.setForeground(tema.getTexto());
            panelCentral.add(lblVacio);
        } else {
            for (String nombreArchivo : archivos) {
                final String archivoFinal = nombreArchivo;

                JLabel lblArchivo = herramientas.crearLabels(15, "• " + archivoFinal, 40, y, 320, 25);
                lblArchivo.setForeground(tema.getTexto());
                lblArchivo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                lblArchivo.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        try {
                            java.io.File archivo = new java.io.File(carpetaUsuario, archivoFinal);
                            java.awt.Desktop.getDesktop().open(archivo);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(
                                    frameMain,
                                    "No se pudo abrir el archivo.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    }
                });
                panelCentral.add(lblArchivo);

                JButton btnEliminar = herramientas.crearBoton("Eliminar", 480, y, 120, 25, e -> {
                    int opcion = JOptionPane.showConfirmDialog(
                            frameMain,
                            "¿Seguro que quieres eliminar este archivo de tu unidad?\n" + archivoFinal,
                            "Confirmar eliminación",
                            JOptionPane.YES_NO_OPTION
                    );
                    if (opcion == JOptionPane.YES_OPTION) {
                        boolean ok = archivosController.eliminarArchivoUnidadUsuario(nombreUsuario, archivoFinal);
                        if (ok) {
                            JOptionPane.showMessageDialog(
                                    frameMain,
                                    "Archivo eliminado correctamente.",
                                    "Eliminado",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                            mostrarUnidadPersonal();
                        } else {
                            JOptionPane.showMessageDialog(
                                    frameMain,
                                    "No se pudo eliminar el archivo.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    }
                });
                actualizarColorBoton(btnEliminar);
                panelCentral.add(btnEliminar);

                y += 35;
            }
        }
    }

    private void mostrarAsignatura(String nombre) {
        panelCentral.removeAll();

        String nombreUsuario = sesion.getUsuarioActual().getNombre();
        String rutaCarpeta = System.getProperty("user.dir") + "/Recursos/asignaturas/" + nombre;

        JLabel lblTitulo = herramientas.crearLabels(40, nombre.toUpperCase(), 20, 10, 800, 50);
        lblTitulo.setForeground(tema.getTexto());
        panelCentral.add(lblTitulo);

        JLabel lblInfo = herramientas.crearLabels(25, "Archivos en esta asignatura:", 20, 60, 500, 25);
        lblInfo.setForeground(tema.getTexto());
        panelCentral.add(lblInfo);

        String[] archivos = archivosController.listarArchivos(nombre);
        int y = 100;

        if (archivos.length == 0) {
            JLabel lblVacio = herramientas.crearLabels(14, "Carpeta vacía", 20, y, 300, 25);
            lblVacio.setForeground(tema.getTexto());
            panelCentral.add(lblVacio);
        } else {
            for (String nombreArchivo : archivos) {
                JLabel lblArchivo = herramientas.crearLabels(14, "• " + nombreArchivo, 20, y, 320, 25);
                lblArchivo.setForeground(tema.getTexto());
                lblArchivo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                lblArchivo.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        try {
                            java.awt.Desktop.getDesktop().open(
                                    new java.io.File(rutaCarpeta + "/" + nombreArchivo)
                            );
                        } catch (Exception ignored) {
                        }
                    }
                });
                panelCentral.add(lblArchivo);

                JButton btnGuardar = herramientas.crearBoton("Guardar en Mi Unidad", 360, y - 2, 200, 25, e -> {
                    boolean ok = archivosController.copiarDesdeAsignaturaAUnidad(nombre, nombreArchivo, nombreUsuario);
                    if (ok) {
                        JOptionPane.showMessageDialog(
                                frameMain,
                                "Archivo guardado en tu unidad personal.",
                                "Guardado",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    } else {
                        JOptionPane.showMessageDialog(
                                frameMain,
                                "No se pudo copiar el archivo.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                });
                actualizarColorBoton(btnGuardar);
                panelCentral.add(btnGuardar);

                y += 30;
            }
        }

        panelCentral.revalidate();
        panelCentral.repaint();
    }

    public void mostrarVentanaMain() {
        panelesMain();
        labelsMain();
        botonesMain();
        aplicarTema();
        frameMain.setVisible(true);
        panelDerecho.setLocation(1200, 0);
    }

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
        actualizarBotonesPanelCentral();

        actualizarColorBoton(btnVolverUnidad);
        actualizarColorBoton(btnMenuTema);
        actualizarColorBoton(btnMenuSubir);
        actualizarColorBoton(btnMenuCerrarSesion);

        frameMain.repaint();
    }

    private void actualizarColorBoton(JButton boton) {
        boton.setBackground(tema.getBoton());
        boton.setForeground(tema.getBotonTexto());
    }

    private void actualizarLabelsPanelCentral() {
        for (Component comp : panelCentral.getComponents()) {
            if (comp instanceof JLabel label) {
                label.setForeground(tema.getTexto());
            }
        }
    }

    private void actualizarBotonesPanelCentral() {
        for (Component comp : panelCentral.getComponents()) {
            if (comp instanceof JButton boton) {
                actualizarColorBoton(boton);
            }
        }
    }
}
