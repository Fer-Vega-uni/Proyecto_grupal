package controlador;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ControladorRutas {
    public static String getRutaBase() {
        String home = System.getProperty("user.home");
        Path rutaCompleta = Paths.get(home)
                .resolve("Escritorio")
                .resolve("proyecto final progra")
                .resolve("Materias");
        return rutaCompleta.toString();
    }
}
