package Modelo;

import com.google.gson.annotations.SerializedName;

public class Usuario {

    @SerializedName("nombre")
    private String Nombre;

    @SerializedName("contrasena")
    private String Contraseña;

    @SerializedName("matricula")
    private String Matricula;

    @SerializedName("carrera")
    private String Carrera;

    public Usuario(String Nombre, String Contraseña, String Matricula, String Carrera) {
        this.Nombre = Nombre;
        this.Contraseña = Contraseña;
        this.Matricula = Matricula;
        this.Carrera = Carrera;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public String getMatricula() {
        return Matricula;
    }

    public String getCarrera() {
        return Carrera;
    }

    // Setters con validación
    public void setNombre(String Nombre) {
        if (Nombre == null || Nombre.isBlank()) {
            throw new IllegalArgumentException("Su nombre no puede estar vacío");
        }
        this.Nombre = Nombre;
    }

    public void setContraseña(String Contraseña) {
        if (Contraseña == null || Contraseña.isBlank()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }
        this.Contraseña = Contraseña;
    }

    public void setMatricula(String Matricula) {
        if (Matricula == null || Matricula.isBlank()) {
            throw new IllegalArgumentException("Su matrícula no puede estar vacía");
        }
        this.Matricula = Matricula;
    }

    public void setCarrera(String Carrera) {
        if (Carrera == null || Carrera.isBlank()) {
            throw new IllegalArgumentException("Su carrera no puede estar vacía");
        }
        this.Carrera = Carrera;
    }
}


