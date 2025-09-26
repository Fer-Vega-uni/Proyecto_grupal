package Ventanas;

import Logica.Login;
import Logica.Registro;
import Logica.Usuario;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistro {
    Login login = new Login();
    Registro registro = new Registro();
    private final JFrame frameRegistro = new JFrame("Registro - SCA.");
    private final JLabel lbllogoUFRO = new JLabel(new ImageIcon("C:/Users/malej/OneDrive/Escritorio/POO/Visual Proyecto/Logo_Nuevo_Ufro.png"));
    private final JLabel lblMatrucla = new JLabel("Matricula");
    private final JTextField txtMatricula = new JTextField();
    private final JLabel lblContraseña = new JLabel("Contraseña");
    private final JPasswordField txtContraseña = new JPasswordField();
    private final JLabel lblCarrera = new JLabel("Carrera");
    private final JTextField txtCarrera = new JTextField();
    private final JLabel lblNombre = new JLabel("Nombre");
    private final JTextField txtNombre = new JTextField();
    private final JButton btnRegistrar = new JButton("Registrarse");




    public VentanaRegistro(){
        frameRegistro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameRegistro.setSize(700, 350);
        frameRegistro.setLocationRelativeTo(null);
        frameRegistro.setLayout(null);
        frameRegistro.getContentPane().setBackground(new Color(199, 236, 252));
        ImageIcon Icono = LogoUfro();
        frameRegistro.setIconImage(Icono.getImage());
        frameRegistro.getRootPane().setDefaultButton(btnRegistrar);
    }
    public void mostrarVentanaRegistro(){
        imagenesRegistro();
        labelsVentanaRegsitro();
        textosVentanaRegistro();
        botonesVentanaRegistro();
        agregarVentanaRegistro();
        frameRegistro.setVisible(true);
    }

    public void agregarVentanaRegistro(){
        frameRegistro.add(lbllogoUFRO);
        frameRegistro.add(lblMatrucla);
        frameRegistro.add(txtMatricula);
        frameRegistro.add(lblContraseña);
        frameRegistro.add(txtContraseña);
        frameRegistro.add(lblCarrera);
        frameRegistro.add(txtCarrera);
        frameRegistro.add(lblNombre);
        frameRegistro.add(txtNombre);
        frameRegistro.add(btnRegistrar);
    }

    public void labelsVentanaRegsitro(){
        lblMatrucla.setBounds(250,30,170,25);
        lblMatrucla.setFont(new Font("Arial", Font.BOLD, 15));
        lblContraseña.setBounds(250,80,100,25);
        lblContraseña.setFont(new Font("Arial", Font.BOLD, 15));
        lblCarrera.setBounds(250,130,170,25);
        lblCarrera.setFont(new Font("Arial", Font.BOLD, 15));
        lblNombre.setBounds(250,180,170,25);
        lblNombre.setFont(new Font("Arial", Font.BOLD, 15));
    }

    public void textosVentanaRegistro(){
        txtMatricula.setBounds(250,50,200,25);
        txtMatricula.setHorizontalAlignment(JTextField.CENTER);
        txtContraseña.setBounds(250,100,200,25);
        txtContraseña.setHorizontalAlignment(JTextField.CENTER);
        txtCarrera.setBounds(250,150,200,25);
        txtCarrera.setHorizontalAlignment(JTextField.CENTER);
        txtNombre.setBounds(250,200,200,25);
        txtNombre.setHorizontalAlignment(JTextField.CENTER);
    }

    public void botonesVentanaRegistro(){
        btnRegistrar.setBounds(290,240,120,25);
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 15));
        btnRegistrar.setBackground(new Color(1, 53, 110));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.addActionListener(e -> ValidarRegistro());
    }

    public void imagenesRegistro(){
        ImageIcon logoUfro = LogoUfro();
        lbllogoUFRO.setIcon(logoUfro);
        lbllogoUFRO.setBounds(10,10,110,94);
    }
    public ImageIcon LogoUfro(){
        ImageIcon logoUfroActual = (ImageIcon) lbllogoUFRO.getIcon();
        Image logoUfroReEscalado = logoUfroActual.getImage().getScaledInstance(110, 94, Image.SCALE_SMOOTH);
        return new ImageIcon(logoUfroReEscalado);
    }

    public void ValidarRegistro() {
        String m = txtMatricula.getText();
        String p = txtContraseña.getText();
        String n = txtNombre.getText();
        String c = txtCarrera.getText();

        if (m.isEmpty() || p.isEmpty() || n.isEmpty() || c.isEmpty()) {
            JOptionPane.showMessageDialog(frameRegistro, "Porfavor, rellene todas las casillas", "Registro invalido", JOptionPane.INFORMATION_MESSAGE);
        } else if (registro.verifcarMatriculaRegistrada(m)) {
            JOptionPane.showMessageDialog(frameRegistro, "Matricula ya registrada", "Registro invalido", JOptionPane.INFORMATION_MESSAGE);
        } else if (registro.verifcarNombreRegistrado(n)) {
            JOptionPane.showMessageDialog(frameRegistro, "Alumno ya registrado", "Registro invalido", JOptionPane.INFORMATION_MESSAGE);
        } else if (!registro.varificacionMatricula(m)) {
            JOptionPane.showMessageDialog(frameRegistro, "Matricula no valida", "Registro invalido", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(frameRegistro, "Alumno: " + n +"\n" +
                                                                 "Matricula: " + m + "\n" +
                                                                 "Registrado correctamente", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);

            login.USUARIOS.add(new Usuario(m,p,n,c));
            volverLogin();

        }
    }

    public void volverLogin(){
        frameRegistro.dispose();
        VentanaLogin mostrarLogin = new VentanaLogin();
        mostrarLogin.mostrarVentanaLogin();
    }
}
