package Visual;

import Controladores.ArchivosController;
import Controladores.SesionController;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class VentanaSubirArchivo {

    private final JFrame frame = new JFrame("Subir archivo - SCA");
    private final Herramientas herramientas = new Herramientas();
    private final Tema tema = Tema.getModo();

    private final ArchivosController archivosController;
    private final SesionController sesion;

    private JPanel panelBase;
    private JPanel panelContenido;

    private JTextField txtRutaArchivo;
    private JLabel lblEstado;
    private File archivoSeleccionado;

    public VentanaSubirArchivo(SesionController sesion, ArchivosController archivosController, Frame padre) {
        this.sesion = sesion;
        this.archivosController = archivosController;

        frame.setSize(600, 350);
        frame.setLocationRelativeTo(padre);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setIconImage(herramientas.getIcono());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        construirUI();
        aplicarTema();
    }

    private void construirUI() {
        panelBase = herramientas.crearPanel(tema.getBoton(), 0, 0, 600, 350);
        panelBase.setLayout(null);
        frame.add(panelBase);


        panelContenido = herramientas.crearPanel(tema.getFondo(), 30, 60, 540, 250);
        panelContenido.setLayout(null);
        panelBase.add(panelContenido);


        JLabel lblTituloVentana = herramientas.crearLabels(
                26, "Subir archivo PDF", 170, 15, 400, 35);
        lblTituloVentana.setForeground(tema.getBotonTexto());
        panelBase.add(lblTituloVentana);


        JLabel lblDescripcion = herramientas.crearLabels(
                16, "Selecciona un archivo PDF para subir a tu unidad.", 30, 15, 480, 25);
        lblDescripcion.setForeground(tema.getTexto());
        panelContenido.add(lblDescripcion);

        txtRutaArchivo = new JTextField();
        txtRutaArchivo.setBounds(30, 60, 360, 30);
        txtRutaArchivo.setEditable(false);
        txtRutaArchivo.setBackground(Color.WHITE);
        panelContenido.add(txtRutaArchivo);


        JButton btnBuscar = herramientas.crearBoton(
                "Buscar...", 410, 60, 100, 30, e -> seleccionarArchivo());
        estiloBoton(btnBuscar);
        panelContenido.add(btnBuscar);


        JButton btnSubir = herramientas.crearBoton(
                "Subir a pendientes", 170, 120, 200, 40, e -> subirArchivo());
        estiloBoton(btnSubir);
        panelContenido.add(btnSubir);

        lblEstado = herramientas.crearLabels(
                14, "Selecciona un archivo PDF.", 30, 190, 480, 25);
        lblEstado.setForeground(tema.getTexto());
        panelContenido.add(lblEstado);
    }

    private void estiloBoton(JButton boton) {
        boton.setBackground(tema.getBoton());
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
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

    private void seleccionarArchivo() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Archivos PDF", "pdf"));
        int resultado = chooser.showOpenDialog(frame);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            archivoSeleccionado = chooser.getSelectedFile();
            txtRutaArchivo.setText(archivoSeleccionado.getAbsolutePath());
            lblEstado.setText("Archivo listo para subir.");
        }
    }

    private void subirArchivo() {
        if (archivoSeleccionado == null) {
            JOptionPane.showMessageDialog(frame,
                    "Primero debes seleccionar un archivo PDF.",
                    "Sin archivo",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String nombreUsuario = sesion.getUsuarioActual().getNombre();

        try {

            File destino = archivosController.guardarEnPendientes(archivoSeleccionado, nombreUsuario);

            lblEstado.setText("Archivo copiado a pendientes: " + destino.getName());


            String resultado = archivosController.analizarYSubirPendienteConIA(destino);


            JOptionPane.showMessageDialog(
                    frame,
                    resultado,
                    "Resultado del análisis",
                    JOptionPane.INFORMATION_MESSAGE
            );

            frame.dispose();

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    frame,
                    "Ocurrió un error al copiar el archivo.\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    private void aplicarTema() {
        if (panelBase != null) {
            panelBase.setBackground(tema.getBoton());
        }
        if (panelContenido != null) {
            panelContenido.setBackground(tema.getFondo());
        }
    }

    public void mostrar() {
        frame.setVisible(true);
    }
}
