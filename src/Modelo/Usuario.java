package Modelo;


public class Usuario {
    private String Nombre;
    private String Contraseña;
    private String Matricula;
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



    // Esto esta con la exepciones para que las capte las ventanas (cambienlo si quieren)
    public void setNombre(String Nombre) {
        if (Nombre == null || Nombre.isBlank()) {
            throw new IllegalArgumentException("Su nombre no puede estar vacio");
        }
        this.Nombre = Nombre;
    }

    public void setContraseña(String Contraseña) {
        if (Contraseña == null || Contraseña.isBlank()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacia");
        }
        this.Contraseña = Contraseña;
    }

    public void setMatricula(String Matricula) {
        if (Matricula == null || Matricula.isBlank()) {
            throw new IllegalArgumentException("Su matricula no puede estar vacia");
        }
        this.Matricula = Matricula;
    }

    public void setCarrera(String Carrera) {
        if (Carrera == null || Carrera.isBlank()) {
            throw new IllegalArgumentException("Su carrera no puede estar vacia");
        }
        this.Carrera = Carrera;
    }
}

