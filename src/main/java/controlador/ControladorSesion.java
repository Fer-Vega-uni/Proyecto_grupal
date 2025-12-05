package controlador;

import modelo.GestorArchivos;
import modelo.Seguridad;
import modelo.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ControladorSesion {

    private ArrayList<Usuario> USUARIOS;
    private Usuario UsuarioActual;

    // Dependencias para persistencia
    private final GestorArchivos gestorArchivos;
    private final Gson gson;
    private final String ARCHIVO_DB = "usuarios.json";

    public ControladorSesion(GestorArchivos gestorArchivos){
        this.gestorArchivos = gestorArchivos;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.UsuarioActual = null;
        this.USUARIOS = new ArrayList<>();
        try {
            cargarUsuarios();
        } catch (Exception e) {
            this.USUARIOS = new ArrayList<>();
            cargarUsuariosPorDefecto();
        }
    }

    private void cargarUsuarios() throws IOException {
        String json = gestorArchivos.leerArchivoTexto(ARCHIVO_DB);

        if (json.isBlank()) {
            cargarUsuariosPorDefecto();
        } else {
            Type listaTipo = new TypeToken<ArrayList<Usuario>>() {}.getType();
            USUARIOS = gson.fromJson(json, listaTipo);
            if (USUARIOS == null) USUARIOS = new ArrayList<>();
        }
    }

    private void cargarUsuariosPorDefecto() {
        USUARIOS.add(new Usuario("Marcelo", Seguridad.encriptar("1234"), "22074033124", "Ing. Civil Informatica"));
        USUARIOS.add(new Usuario("Fernanda", Seguridad.encriptar("1234"), "21876574224", "Ing. Civil Informatica"));
        USUARIOS.add(new Usuario("Pablo", Seguridad.encriptar("1234"), "22162796224", "Ing. Civil Informatica"));
        USUARIOS.add(new Usuario("1", Seguridad.encriptar("1"), "1", "1"));
        guardarCambios();
    }

    private void guardarCambios() {
        try {
            String json = gson.toJson(USUARIOS);
            gestorArchivos.guardarArchivoTexto(ARCHIVO_DB, json);
        } catch (IOException e) {
            throw new RuntimeException("Error crítico al guardar la base de datos de usuarios: " + e.getMessage());
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
        String hashIngresado = Seguridad.encriptar(c);
        for (Usuario usuario : USUARIOS) {
            if (usuario.getMatricula().equals(m) && usuario.getContraseña().equals(hashIngresado)) {
                UsuarioActual = usuario;
                return true;
            }
        }
        return false;
    }

    public boolean Registro(String m, String c, String n, String i) {
        if (n == null || n.isBlank() || c == null || c.isBlank() || m == null || m.isBlank() || i == null || i.isBlank()) {
            throw new IllegalArgumentException("Por favor rellene todos los campos");
        } else if (verifcarDuplicado(m, n)) {
            throw new IllegalArgumentException("La matrícula y/o nombre ya están registrados");
        } else {
            String contraSegura = Seguridad.encriptar(c);
            Usuario nuevoUsuario = new Usuario(n, contraSegura, m, i);
            USUARIOS.add(nuevoUsuario);
            guardarCambios();
            return true;
        }
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
