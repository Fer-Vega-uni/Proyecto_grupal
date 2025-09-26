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

    public boolean validarCredenciales(String m, String p) {
        return this.matricula.equals(m) && this.contraseña.equals(p);
    }

    public boolean verficarDuplicado(String m) {
        return this.matricula.equals(m);
    }
    public boolean verficarNombreDuplicado(String n) {
        return this.nombre.toLowerCase().equals(n.toLowerCase());
    }


    public String getNombre() {
        return nombre;
    }

    public String getCarrera(){
        return carrera;
    }
}

