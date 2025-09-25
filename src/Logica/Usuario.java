package Logica;

public class Usuario {
    private String matricula;
    private String password;
    private String nombre;

    public Usuario(String username, String password, String nombre){
        this.matricula = username;
        this.password = password;
        this.nombre = nombre;
    }

    public boolean validarCredenciales(String u, String p){
        return this.matricula.equals(u) && this.password.equals(p);
    }

    public String getNombre(){
        return nombre;
    }

    public boolean validarRegistro(String u){
        return this.matricula.equals(u);
    }
}
