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

    private JPanel panelLateral;
    private JPanel panelCentral;
    private JLabel lblBienvenida;
    private JLabel lblTituloUnidad;

    public VentanaMain(SesionController sesion) {
        this.sesion = sesion;
        configurarVentana();
        panelesMain();
        labelsMain();
        botonesMain();
        aplicarTema();
        frameMain.setVisible(true);
    }

    private void configurarVentana() {
        frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameMain.setSize(1200, 800);
        frameMain.setLocationRelativeTo(null);
        frameMain.setLayout(null);
        frameMain.setResizable(false);
        frameMain.setIconImage(herramientas.getIcono());
    }

    private void panelesMain() {
        panelLateral = herramientas.crearPanel(tema.getBoton(), 0, 0, 220, 800);
        frameMain.add(panelLateral);

        panelCentral = herramientas.crearPanel(tema.getFondo(), 220, 100, 980, 700);
        frameMain.add(panelCentral);
    }

    private void labelsMain() {
        lblBienvenida = herramientas.crearLabels(48, "Bienvenid@ a SCA", 470, 25, 800, 55);
        lblBienvenida.setForeground(tema.getBotonTexto());
        frameMain.add(lblBienvenida);

        String nombreUsuario = sesion.getUsuarioActual().getNombre();
        lblTituloUnidad = herramientas.crearLabels(30, "Mi Unidad: " + nombreUsuario, 20, 10, 800, 40);
        lblTituloUnidad.setForeground(tema.getTexto());
        panelCentral.add(lblTituloUnidad);

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

        JLabel lblAsignaturas = herramientas.crearLabels(26, "Asignaturas", 35, 25, 200, 40);
        lblAsignaturas.setForeground(tema.getBotonTexto());
        panelLateral.add(lblAsignaturas);
    }

    private void botonesMain() {
        String[] asignaturas = archivosController.listarAsignaturas();

        if (asignaturas.length == 0) {
            JLabel lblVacio = herramientas.crearLabels(20, "No hay asignaturas", 30, 90, 200, 25);
            lblVacio.setForeground(tema.getBotonTexto());
            panelLateral.add(lblVacio);
            return;
        }

        int y = 60;
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

            panelLateral.add(btn);
            y += 30;
        }
    }

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
                        } catch (Exception ignored) {}
                    }
                });

        panelCentral.add(lblArchivo);
                y += 30;
            }
        }

        panelCentral.revalidate();
        panelCentral.repaint();
    }

    private void aplicarTema() {
        frameMain.getContentPane().setBackground(tema.getBoton());
        lblBienvenida.setForeground(tema.getBotonTexto());
        lblTituloUnidad.setForeground(tema.getTexto());
        panelCentral.setBackground(tema.getFondo());
        panelLateral.setBackground(tema.getBoton());
        frameMain.repaint();

    }


    public static void main(String[] args) {
        Controladores.SesionController sesion = new Controladores.SesionController();
        sesion.iniciarSesion("22074033124", "1234");
        new VentanaMain(sesion);
}}
