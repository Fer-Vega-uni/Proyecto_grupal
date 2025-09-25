package Logica;

public class Usuario {
    private String matricula;
    private String contraseña;
    private String nombre;

    public Usuario(String username, String password, String nombre) {
        this.matricula = username;
        this.contraseña = password;
        this.nombre = nombre;
    }

    public boolean validarCredenciales(String m, String c) {
        return this.matricula.equals(m) && this.contraseña.equals(c);
    }

    public String getNombre() {
        return nombre;
    }
}

