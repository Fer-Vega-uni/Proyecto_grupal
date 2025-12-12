package Controladores;

import Modelo.Usuario;
import Modelo.UsuariosDAO;
import Modelo.Seguridad;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SesionController {

    private ArrayList<Usuario> USUARIOS;
    private Usuario UsuarioActual;

    public SesionController() {
        UsuarioActual = null;

        try {
            List<Usuario> lista = UsuariosDAO.cargarUsuarios();
            USUARIOS = new ArrayList<>(lista);
        } catch (IOException e) {
            e.printStackTrace();
            USUARIOS = new ArrayList<>();
        }

        boolean huboCambios = false;
        for (Usuario u : USUARIOS) {
            String pass = u.getContraseña();
            if (pass != null && pass.length() != 64) {
                String hash = Seguridad.encriptar(pass);
                u.setContraseña(hash);
                huboCambios = true;
            }
        }

        if (huboCambios) {
            try {
                UsuariosDAO.guardarUsuarios(USUARIOS);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

        String hashIngresada = Seguridad.encriptar(c);

        for (Usuario usuario : USUARIOS) {
            if (usuario.getMatricula().equals(m) &&
                    usuario.getContraseña().equals(hashIngresada)) {

                UsuarioActual = usuario;
                return true;
            }
        }
        return false;
    }


    public boolean Registro(String m, String c, String n, String i) {
        if (n == null || n.isBlank() || c == null || c.isBlank() || m == null || m.isBlank() || i == null || i.isBlank()) {

            throw new IllegalArgumentException("Por favor rellene todos los campos");
        }

        if (verifcarDuplicado(m, n)) {
            throw new IllegalArgumentException("La matrícula y/o nombre ya están registrados");
        }

        String hash = Seguridad.encriptar(c);
        Usuario nuevoUsuario = new Usuario(n, hash, m, i);
        USUARIOS.add(nuevoUsuario);

        try {
            UsuariosDAO.guardarUsuarios(USUARIOS);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("No se pudo guardar el usuario en el archivo JSON");
        }

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
