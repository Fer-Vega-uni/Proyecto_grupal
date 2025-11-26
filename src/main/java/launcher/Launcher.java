package launcher;

import controlador.ControladorAplicacion;
import controlador.ControladorRutas;
import modelo.GestorArchivos;
import vista.ConsolaVista;

public class Launcher {

    public static void main(String[] args) {
        String ruta_base =ControladorRutas.getRutaBase();
        GestorArchivos modelo = new GestorArchivos(ruta_base);
        ConsolaVista vista = new ConsolaVista();
        ControladorAplicacion controlador = new ControladorAplicacion(modelo,vista);
        controlador.iniciar();
    }
}
