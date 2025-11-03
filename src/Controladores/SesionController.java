package Controladores;

import Modelo.Usuario;

import java.util.ArrayList;


/**
 * Controlador encargado de gestionar la sesión de usuarios dentro del sistema.
 * <p>
 * Esta clase permite iniciar y cerrar sesión, registrar nuevos usuarios
 * y verificar duplicados. Mantiene una lista de usuarios registrados
 * y una referencia al usuario actualmente autenticado.
 * </p>
 *
 * @author Pablo Ramos - [@PabloRamos02]
 * @version 1.0
 */
public class SesionController {

    /** Lista de usuarios registrados en el sistema. */
    private ArrayList<Usuario> USUARIOS;
    /** Usuario actualmente autenticado. */
    private Usuario UsuarioActual;

    /**
     * Constructor que inicializa la sesión con una lista de usuarios predeterminada.
     * <p>
     * Estos usuarios se cargan por defecto para facilitar las pruebas del sistema.
     * </p>
     */
    public SesionController(){
        USUARIOS = new ArrayList<>();
        UsuarioActual = null;

        USUARIOS.add(new Usuario("Marcelo", "1234", "22074033124", "Ing. Civil Informatica"));
        USUARIOS.add(new Usuario("Fernanda", "1234", "21876574224", "Ing. Civil Informatica"));
        USUARIOS.add(new Usuario("Pablo", "1234", "22162796224", "Ing. Civil Informatica"));

    }

    /**
     * Devuelve el usuario actualmente autenticado.
     *
     * @return el usuario activo en la sesión, o {@code null} si no hay sesión iniciada
     */
    public Usuario getUsuarioActual() {
        return UsuarioActual;
    }

    /**
     * Cierra la sesión actual, eliminando la referencia del usuario autenticado.
     */
    public void cerrarSesion() {
        UsuarioActual = null;
    }

    /**
     * Inicia sesión verificando las credenciales del usuario.
     * <p>
     * Si las credenciales son válidas, el usuario queda establecido como sesión activa.
     * </p>
     *
     * @param m matrícula del usuario
     * @param c contraseña del usuario
     * @return {@code true} si las credenciales son correctas y la sesión se inicia exitosamente,
     *         {@code false} en caso contrario
     * @throws IllegalArgumentException si alguno de los campos está vacío o nulo
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

    /**
     * Registra un nuevo usuario en el sistema.
     * <p>
     * Antes de agregarlo, valida que los campos no estén vacíos y que no exista
     * otro usuario con la misma matrícula o nombre.
     * </p>
     *
     * @param m matrícula del nuevo usuario
     * @param c contraseña del nuevo usuario
     * @param n nombre del nuevo usuario
     * @param i carrera del nuevo usuario
     * @return {@code true} si el registro se realiza exitosamente
     * @throws IllegalArgumentException si algún campo está vacío o si la matrícula/nombre ya existen
     */
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

    /**
     * Verifica si ya existe un usuario con la matrícula o el nombre proporcionado.
     *
     * @param m matrícula a verificar
     * @param n nombre a verificar
     * @return {@code true} si existe un usuario con los mismos datos, {@code false} en caso contrario
     */
    public boolean verifcarDuplicado(String m, String n) {
        for (Usuario usuario : USUARIOS) {
            if (usuario.getMatricula().equals(m) || usuario.getNombre().equals(n)) {
                return true;
            }
        } return false;
    }
}
