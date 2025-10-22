import Controladores.SesionController;
import Visual.VentanaLogin;

public class Launcher {
    public static void main(String[] args) {
        SesionController sesion = new SesionController();
        new VentanaLogin(sesion).mostrarVentanaLogin();
    }
}
