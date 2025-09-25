package Ventanas;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistro {
    private final JFrame frameRegistro = new JFrame("Registro - SCA.");
    private final JLabel lbllogoUFRO = new JLabel(new ImageIcon("C:/Users/malej/OneDrive/Escritorio/POO/Visual Proyecto/Logo_Nuevo_Ufro.png"));
    private final JLabel lblMatrucla = new JLabel("Matricula");
    private final JTextField txtMatricula = new JTextField();
    private final JLabel lblContraseña = new JLabel("Contraseña");
    private final JPasswordField txtContraseña = new JPasswordField();
    private final JLabel lblCarrera = new JLabel("Carrera");
    private final JTextField txtCarrera = new JPasswordField();
    private final JLabel lblNombre = new JLabel("Nombre");
    private final JTextField txtNombre = new JPasswordField();

    private final JButton btnRegistrar = new JButton("Registrarse");




    public VentanaRegistro(){
        frameRegistro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameRegistro.setSize(700, 350);
        frameRegistro.setLocationRelativeTo(null);
        frameRegistro.setLayout(null);
        frameRegistro.getContentPane().setBackground(new Color(199, 236, 252));
        ImageIcon Icono = LogoUfro();
        frameRegistro.setIconImage(Icono.getImage());
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


}
