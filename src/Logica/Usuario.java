package Logica;

public class Usuario {
    private String matricula;
    private String contraseña;
    private String nombre;
    private String carrera;

    public Usuario(String username, String password, String nombre, String carrera) {
        this.matricula = username;
        this.contraseña = password;
        this.nombre = nombre;
        this.carrera = carrera;
    }

    public boolean validarCredenciales(String m, String c) {
        return this.matricula.equals(m) && this.contraseña.equals(c);
    }

    public String getNombre() {
        return nombre;
    }

    public String getCarrera(){
        return carrera;
    }
}

