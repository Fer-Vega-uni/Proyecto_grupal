package Ventanas;

import javax.swing.*;
import java.awt.*;

public class VentanaLogin {
    private final JFrame frameLogin = new JFrame("Login - SCA.");
    private final JLabel lbllogoUFRO = new JLabel(new ImageIcon("C:/Users/malej/OneDrive/Escritorio/POO/Visual Proyecto/Logo_Nuevo_Ufro.png"));
    private final JLabel lblMatrucla = new JLabel("Matricula");
    private final JTextField txtMatricula = new JTextField();
    private final JLabel lblContraseña = new JLabel("Contraseña");
    private final JPasswordField txtContraseña = new JPasswordField();
    private final JButton btnIngresar = new JButton("Ingresar");
    private final JButton btnRegistrar = new JButton("Registrarse");



    public VentanaLogin(){
        frameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameLogin.setSize(700, 350);
        frameLogin.setLocationRelativeTo(null);
        frameLogin.setLayout(null);
        frameLogin.getContentPane().setBackground(new Color(199, 236, 252));
        ImageIcon Icono = LogoUfro();
        frameLogin.setIconImage(Icono.getImage());
        frameLogin.getRootPane().setDefaultButton(btnIngresar);

    }

    public void mostrarVentana(){
        imagenesLogin();
        labelsVentanaLogin();
        textosVentanaLogin();
        botonesVentanaLogin();
        agregarVentanaLogin();
        frameLogin.setVisible(true);
    }

    public void agregarVentanaLogin(){
        frameLogin.add(lbllogoUFRO);
        frameLogin.add(lblMatrucla);
        frameLogin.add(txtMatricula);
        frameLogin.add(lblContraseña);
        frameLogin.add(txtContraseña);
        frameLogin.add(btnIngresar);
        frameLogin.add(btnRegistrar);
    }

    public void labelsVentanaLogin(){
        lblMatrucla.setBounds(250,120,170,25);
        lblMatrucla.setFont(new Font("Arial", Font.BOLD, 15));
        lblContraseña.setBounds(250,170,100,25);
        lblContraseña.setFont(new Font("Arial", Font.BOLD, 15));
    }

    public void textosVentanaLogin(){
        txtMatricula.setBounds(250,140,200,25);
        txtMatricula.setHorizontalAlignment(JTextField.CENTER);
        txtContraseña.setBounds(250,190,200,25);
        txtContraseña.setHorizontalAlignment(JTextField.CENTER);
    }

    public void botonesVentanaLogin(){
        btnIngresar.setBounds(210,235,120,25);
        btnIngresar.setFont(new Font("Arial", Font.BOLD, 15));
        btnIngresar.setBackground(new Color(1, 53, 110));
        btnIngresar.setForeground(Color.WHITE);
        btnRegistrar.setBounds(360,235,120,25);
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 15));
        btnRegistrar.setBackground(new Color(1, 53, 110));
        btnRegistrar.setForeground(Color.WHITE);

    }

    public void imagenesLogin(){
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

