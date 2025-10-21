import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Archivos {

    private static ArrayList<String> archivos = new ArrayList<>();
    public static Scanner scan = new Scanner(System.in);

    static {archivos.add("lineal.pdf");
            archivos.add("multi.pdf");
            archivos.add("aaaaa.pdf");
            archivos.add("edo.pdf");}


    public boolean verificarExistenciaCarpeta(String rutaString){
        Path ruta = Paths.get(rutaString);
        return Files.exists(ruta) && Files.isDirectory(ruta);
    }

    public void crearCarpeta(String rutaString){
        Path ruta = Paths.get(rutaString);
        try {
            Files.createDirectories(ruta);
            System.out.println("Carpeta creada o ya en existencia: " + ruta);
    } catch (IOException e) {
            System.err.println("Error al crear la carpeta: " + e.getMessage());
        }
    }

    public List<String> buscarSubcarpetas (String carpetaPadre) {
        Path rutaPadre = Paths.get(carpetaPadre);
        if (!verificarExistenciaCarpeta(carpetaPadre)){
            System.err.println("La capreta padre no existe: " + carpetaPadre);
            return Collections.emptyList();}
        System.out.println("Buscando susbcarpetass en: " + rutaPadre);

        try (Stream<Path> stream = Files.list(rutaPadre)){
            return stream .filter(Files::isDirectory).map(Path::toString).collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Error al listar carpetas: " + e.getMessage());
            return Collections.emptyList();
        }}

    public List<String> buscarArchivos(String carpetaPadre) {
        Path rutaPadre = Paths.get(carpetaPadre);
        if (!verificarExistenciaCarpeta(carpetaPadre)){
            System.err.println("La capreta padre no existe: " + carpetaPadre);
            return Collections.emptyList();}
        System.out.println("Buscando archivos en: " + rutaPadre);
        try (Stream<Path> stream = Files.list(rutaPadre)){
            return stream.filter(Files::isRegularFile).filter(path -> path.toString().toLowerCase().endsWith(".pdf")).map(Path::toString).collect(Collectors.toList());
        } catch (IOException e){
            System.err.println("Error al listar archivos: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public void copiarArchivo(String archivoOrigen, String carpetaDestino) {
        Path origen = Paths.get(archivoOrigen);
        Path destino = Paths.get(carpetaDestino);
        if (!Files.exists(origen)|| !Files.isRegularFile(origen)){
            System.err.println("El archivo de origen no existe" + origen);
            return;
        }
        try{Path deestinoFinal= destino.resolve(origen.getFileName());
            Files.copy(origen,deestinoFinal, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Archivo copiado exitosamente a: " + deestinoFinal);
        } catch (IOException e) {
            System.out.println("Error al copiar el archivo: " + e.getMessage());
        }
    }

    public static void verArchivos(){
        for (String a:archivos){
            System.out.println(a);
        }

    }
    public static void buscarArchivos(){
        System.out.println("Ingrese el nombre del archivo a buscar");
        String nombreArchivo=scan.nextLine();
        for (String a:archivos){
            String archivo = a.split("\\.")[0];
            if (archivo.contains(nombreArchivo.trim().split("\\.")[0])){
                System.out.println("Coincidencia encontrada");
                System.out.println(a);
                return;
            }
        }
        System.out.println("No se encontraron coincidencias");
    }

    public static void agregarArchivo(){
        System.out.println("Ingrese el nombre del archivo a subir ");
        String arch= scan.nextLine();
        if (arch.contains(".pdf")){
            archivos.add(arch);
        } else {archivos.add(arch+".pdf");}
    }

    public static void eliminarArchivo(){
        System.out.println("Ingrese el nombre del archivo a borrar ");
        String arch= scan.nextLine();
        if (!(arch.contains(".pdf"))){ arch += ".pdf";}
        if (archivos.contains(arch)){
            System.out.println("Coincidencia encontrada");
            System.out.println(arch);
            archivos.remove(arch);
        } else{System.out.println("No se encontraron coincidencias");}
    }

    public static void agregarFavorito(){
        System.out.println("Ingrese el nombre del archivo a añadir a favoritos");
        String arch= scan.nextLine();
        if (!(arch.contains(".pdf"))){ arch += ".pdf";}
        if (archivos.contains(arch)){
            System.out.println("Coincidencia encontrada");
            System.out.println(arch);
            Usuario.editarFavoritos(arch);
        } else{System.out.println("No se encontraron coincidencias");}
    }

}
