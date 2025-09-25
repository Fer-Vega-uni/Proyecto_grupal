package Logica;

import java.util.ArrayList;

public class Login {
    public final ArrayList<Usuario> USUARIOS = new ArrayList<>();

    public Login() {
        USUARIOS.add(new Usuario("22074033124", "1234", "Marcelo Orellana"));
        USUARIOS.add(new Usuario("21876574224", "1234", "Fernanda Vega"));
        USUARIOS.add(new Usuario("22162796224", "1234", "Pablo Ramos"));

    }
    public String validarLogin(String m, String c ) {
        for (Usuario i : USUARIOS) {
            if (i.validarCredenciales(m, c)) {
                return i.getNombre();
            }
        }
        return "";
    }
}