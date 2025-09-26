import java.util.ArrayList;


public class Usuario {
        private String matricula;
        private String contraseña;
        private String nombre;
        private String carrera;
        private static ArrayList<String> favoritos = new ArrayList<>();

        public Usuario(String username, String password, String nombre, String carrera) {
            this.matricula = username;
            this.contraseña = password;
            this.nombre = nombre;
            this.carrera = carrera;
        }

    public static ArrayList<String> getFavoritos() {
        return favoritos;
    }

    public static void editarFavoritos(String nombreArchivo) {
        if (favoritos.contains(nombreArchivo)){
            favoritos.remove(nombreArchivo);
        } else {
            favoritos.add(nombreArchivo);
        }

    }

    public boolean validarCredenciales(String m, String p) {return this.matricula.equals(m) && this.contraseña.equals(p);}

        public boolean verficarDuplicado(String m) {return this.matricula.equals(m);}
        public boolean verficarNombreDuplicado(String n) {return this.nombre.equalsIgnoreCase(n);}


        public String getNombre() {return nombre;}

        public String getCarrera(){return carrera;}
}
