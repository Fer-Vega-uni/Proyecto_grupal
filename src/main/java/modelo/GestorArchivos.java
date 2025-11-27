package modelo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GestorArchivos {
    private final String rutaBase;
    private String carpetaActual;
    private final ArrayList<String> favoritos = new ArrayList<>();

    public GestorArchivos(String rutaBase) {
        this.rutaBase=rutaBase;
        this.carpetaActual="";
    }

    public String getRutaCompletaActual() {return Paths.get(rutaBase).resolve(carpetaActual).toString();}

    public boolean verificarExistenciaCarpeta(String rutaString){
        Path ruta = Paths.get(rutaString);
        return Files.exists(ruta) && Files.isDirectory(ruta);
    }

    public boolean crearCarpeta(String nombreCarpeta){
        Path ruta = Paths.get(rutaBase).resolve(nombreCarpeta);
        try {
            Files.createDirectories(ruta);
            return true;
        } catch (IOException e) {return false;}
    }

    public List<String> buscarSubcarpetas () {
        Path rutaPadre = Paths.get(getRutaCompletaActual());
        if (!verificarExistenciaCarpeta(rutaPadre.toString())){
            return Collections.emptyList();}
        try (Stream<Path> stream = Files.list(rutaPadre)){
            return stream .filter(Files::isDirectory).map(Path::toString).collect(Collectors.toList());
        } catch (IOException e) {return Collections.emptyList();}
    }

    public List<String> buscarArchivosPdf() {
        Path rutaPadre = Paths.get(getRutaCompletaActual());
        try (Stream<Path> stream = Files.list(rutaPadre)){
            return stream.filter(Files::isRegularFile).filter(path -> path.toString().toLowerCase().endsWith(".pdf")).map(Path::toString).collect(Collectors.toList());
        } catch (IOException e){return Collections.emptyList();}
    }

    public List<String> buscarArchivosPorNombre(String busqueda){
        List<String> pdfsCarpetaActual=buscarArchivosPdf();
        return pdfsCarpetaActual.stream().filter((nombre-> nombre.toLowerCase().contains(busqueda.toLowerCase()))).collect(Collectors.toList());
    }

    public boolean cambiarCarpetaActual( String nombreCarpeta) {
        Path nuevaRuta = Paths.get(rutaBase).resolve(nombreCarpeta);
        if (verificarExistenciaCarpeta(nuevaRuta.toString())){
            carpetaActual=nombreCarpeta;
            return true;
        } else {return false;}
    }

    public boolean copiarArchivo(String nombreArchivo, String carpetaDestino) {
        Path origen = Paths.get(getRutaCompletaActual()).resolve( nombreArchivo);
        Path destino = Paths.get(rutaBase).resolve(carpetaDestino);
        if (!Files.exists(origen)|| !Files.isRegularFile(origen)){return false;
        } else if (!verificarExistenciaCarpeta(destino.toString())) {crearCarpeta(carpetaDestino);}
        try{Path destinoFinal= destino.resolve(origen.getFileName());
            Files.copy(origen,destinoFinal, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {return false;}
    }
    
    public boolean agregarFavorito(String nombreArchivo) {
        Path archivo = Paths.get(getRutaCompletaActual()).resolve(nombreArchivo);
        if (!Files.exists(archivo)){ return false;}
        String rutaCompletaArchivo= archivo.toString();
        if (!favoritos.contains(rutaCompletaArchivo)){
            favoritos.add((rutaCompletaArchivo));
            return true;
        } else {return false;}
    }

    public List<String> getFavoritos(){return favoritos;}

    public String agregarArchivoConIA(String rutaOrigenString) {
        Path rutaOrigen = Paths.get(rutaOrigenString);
        if (!Files.exists(rutaOrigen)) return "Error: El archivo no existe.";
        if (!rutaOrigen.toString().toLowerCase().endsWith(".pdf")) return "Error: Solo archivos PDF.";

        try {
            AnalisisIA servicioIA = new AnalisisIA();
            String respuestaRaw = servicioIA.analizarArchivo(rutaOrigen);

            // --- INICIO DE CODIGO DE DEBUG (Solo para pruebas) ---
            System.out.println("\n💀💀💀 DEBUG - LO QUE REALMENTE DIJO LA IA: 💀💀💀");
            System.out.println("--------------------------------------------------");
            System.out.println(respuestaRaw);
            System.out.println("--------------------------------------------------\n");
            // --- FIN DE CODIGO DE DEBUG ---

            ResultadoAnalisisIA resultado = new ResultadoAnalisisIA(respuestaRaw);
            if (resultado.esAprobado()) {
                subirArchivoS3(rutaOrigen, resultado.getMateria());
                return "Archivo APROBADO.\n Clasificado en: " + resultado.getMateria() + "\n Subido exitosamente.";
            }
            else if (resultado.esRechazado()) { return "Archivo RECHAZADO.\nMotivo: " + resultado.getRazon();}
            else {return " Respuesta ambigua de la IA.";}
        } catch (IOException e) {
            return "Error de sistema al guardar el archivo: " + e.getMessage();
        } catch (Exception e) {
            return "Error inesperado: " + e.getMessage();
        }
    }

    public void eliminarArchivo(){
        //
    }

    private void subirArchivoS3(Path origen, String materia) throws IOException {
        try {
            S3Servicio servicio = new S3Servicio();
            String urlNube = servicio.subirArchivo(origen,materia);
            //para debug, dsps lo borro
            System.out.println("funciona" + urlNube);
        } catch (Exception e) {
            
        }
        }

}
