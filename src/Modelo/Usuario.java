package Modelo;

/**
 * Representa a un usuario dentro del sistema.
 * <p>
 * Esta clase almacena los datos personales y académicos de un usuario, incluyendo su nombre,
 * matrícula, contraseña y carrera. Además, provee métodos para acceder y modificar dicha
 * información, con validaciones básicas para evitar valores vacíos o nulos.
 * </p>
 *
 * @author Pablo Ramos - [@PabloRamos02]
 * @version 1.0
 */
public class Usuario {
    private String Nombre;
    private String Contraseña;
    private String Matricula;
    private String Carrera;

    /**
     * Crea un nuevo usuario con los datos proporcionados.
     *
     * @param Nombre     nombre completo del usuario
     * @param Contraseña contraseña del usuario
     * @param Matricula  matrícula universitaria del usuario
     * @param Carrera    carrera a la que pertenece el usuario
     */
    public Usuario(String Nombre, String Contraseña, String Matricula, String Carrera) {
        this.Nombre = Nombre;
        this.Contraseña = Contraseña;
        this.Matricula = Matricula;
        this.Carrera = Carrera;
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return nombre completo del usuario
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return contraseña actual del usuario
     */
    public String getContraseña() {
        return Contraseña;
    }

    /**
     * Obtiene la matrícula del usuario.
     *
     * @return número de matrícula del usuario
     */
    public String getMatricula() {
        return Matricula;
    }

    /**
     * Obtiene la carrera del usuario.
     *
     * @return nombre de la carrera del usuario
     */
    public String getCarrera() {
        return Carrera;
    }

    /**
     * Establece un nuevo nombre para el usuario.
     *
     * @param Nombre nuevo nombre a registrar
     * @throws IllegalArgumentException si el nombre es nulo o está vacío
     */
    public void setNombre(String Nombre) {
        if (Nombre == null || Nombre.isBlank()) {
            throw new IllegalArgumentException("Su nombre no puede estar vacio");
        }
        this.Nombre = Nombre;
    }

    /**
     * Establece una nueva contraseña para el usuario.
     *
     * @param Contraseña nueva contraseña a registrar
     * @throws IllegalArgumentException si la contraseña es nula o está vacía
     */
    public void setContraseña(String Contraseña) {
        if (Contraseña == null || Contraseña.isBlank()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacia");
        }
        this.Contraseña = Contraseña;
    }

    /**
     * Establece una nueva matrícula para el usuario.
     *
     * @param Matricula nueva matrícula a registrar
     * @throws IllegalArgumentException si la matrícula es nula o está vacía
     */
    public void setMatricula(String Matricula) {
        if (Matricula == null || Matricula.isBlank()) {
            throw new IllegalArgumentException("Su matricula no puede estar vacia");
        }
        this.Matricula = Matricula;
    }

    /**
     * Establece una nueva carrera para el usuario.
     *
     * @param Carrera nueva carrera a registrar
     * @throws IllegalArgumentException si la carrera es nula o está vacía
     */
    public void setCarrera(String Carrera) {
        if (Carrera == null || Carrera.isBlank()) {
            throw new IllegalArgumentException("Su carrera no puede estar vacia");
        }
        this.Carrera = Carrera;
    }
}
