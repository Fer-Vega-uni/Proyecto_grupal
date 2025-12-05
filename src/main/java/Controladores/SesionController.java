package Controladores;

import Modelo.Usuario;
import Modelo.Seguridad;
import java.util.ArrayList;

public class SesionController {

    private ArrayList<Usuario> USUARIOS;
    private Usuario UsuarioActual;

    public SesionController() {
        USUARIOS = new ArrayList<>();
        UsuarioActual = null;

        // Usuarios con contraseñas encriptadas automáticamente
        USUARIOS.add(new Usuario("Marcelo", "1234", "22074033124", "Ing. Civil Informatica"));
        USUARIOS.add(new Usuario("Fernanda", "1234", "21876574224", "Ing. Civil Informatica"));
        USUARIOS.add(new Usuario("Pablo", "1234", "22162796224", "Ing. Civil Informatica"));
    }

    public Usuario getUsuarioActual() {
        return UsuarioActual;
    }

    public void cerrarSesion() {
        UsuarioActual = null;
    }

    public boolean iniciarSesion(String m, String c) {
        if (m == null || m.isBlank() || c == null || c.isBlank()) {
            throw new IllegalArgumentException("Por favor rellene todos los campos");
        }

        for (Usuario usuario : USUARIOS) {
            if (usuario.getMatricula().equals(m) &&
                    usuario.getContraseña().equals(Seguridad.encriptar(c))) {

                UsuarioActual = usuario;
                return true;
            }
        }
        return false;
    }

    public boolean Registro(String m, String c, String n, String i) {
        if (n == null || n.isBlank() ||
                c == null || c.isBlank() ||
                m == null || m.isBlank() ||
                i == null || i.isBlank()) {

            throw new IllegalArgumentException("Por favor rellene todos los campos");
        }

        if (verifcarDuplicado(m, n)) {
            throw new IllegalArgumentException("La matricula y/o nombre ya están registrados");
        }

        Usuario nuevoUsuario = new Usuario(n, c, m, i);
        USUARIOS.add(nuevoUsuario);
        return true;
    }

    public boolean verifcarDuplicado(String m, String n) {
        for (Usuario usuario : USUARIOS) {
            if (usuario.getMatricula().equals(m) || usuario.getNombre().equals(n)) {
                return true;
            }
        }
        return false;
    }
}


