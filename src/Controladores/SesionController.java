package Controladores;

import Modelo.Usuario;

import java.util.ArrayList;

public class SesionController {
    private ArrayList<Usuario> USUARIOS;
    private Usuario UsuarioActual;

    public SesionController(){
        USUARIOS = new ArrayList<>();
        UsuarioActual = null;

        USUARIOS.add(new Usuario("Marcelo", "1234", "22074033124", "Ing. Civil Informatica"));
        USUARIOS.add(new Usuario("Fernanda", "1234", "21876574224", "Ing. Civil Informatica"));
        USUARIOS.add(new Usuario("desempleado (pablo)", "1234", "22162796224", "Ing. Civil Informatica"));

    }

    public Usuario getUsuarioActual() {
        return UsuarioActual;
    }

    public void cerrarSesion() {
        UsuarioActual = null;
    }


    /* String n = Nombre
       String c = Contraseña
       String m = Matricula
       String i = Carrera (ingenieria)
     */

    public boolean iniciarSesion(String m, String c) {
        if (m == null || m.isBlank() || c == null || c.isBlank()) {
            throw new IllegalArgumentException("Porfavor rellene todos los campos");
        }
        for (Usuario usuario : USUARIOS) {
            if (usuario.getMatricula().equals(m) && usuario.getContraseña().equals(c)) {
                UsuarioActual = usuario;
                return true;
            }
        } return false;
    }


    public boolean Registro(String m, String c, String n, String i) {
        if (n == null || n.isBlank() || c == null || c.isBlank() || m == null || m.isBlank() || i == null || i.isBlank()) {
            throw new IllegalArgumentException("Porfavor rellene todos los campos");
        } else if (verifcarDuplicado(m, n)) {
            throw new IllegalArgumentException("La matricula y/o nombre ya estan registrados");
        } else {
            Usuario nuevoUsuario = new Usuario(n,c,m,i);
            USUARIOS.add(nuevoUsuario);
            return true;
        }
    }

    public boolean verifcarDuplicado(String m, String n) {
        for (Usuario usuario : USUARIOS) {
            if (usuario.getMatricula().equals(m) || usuario.getNombre().equals(n)) {
                return true;
            }
        } return false;
    }
}
