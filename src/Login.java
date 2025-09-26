import java.util.ArrayList;

public class Login {
    public static final ArrayList<Usuario> USUARIOS = new ArrayList<>();

    public Login() {
        USUARIOS.add(new Usuario("22074033124", "1234", "Marcelo Orellana", "Ing. civil informatica"));
        USUARIOS.add(new Usuario("21876574224", "1234", "Fernanda Vega", "Ing. civil informatica"));
        USUARIOS.add(new Usuario("22162796224", "1234", "Pablo Ramos","Ing. civil informatica"));

    }
    public String validarLogin(String m, String p) {
        for (Usuario i : USUARIOS) {
            if (i.validarCredenciales(m, p)) {
                return i.getNombre();
            }
        }
        return "";
    }
}
