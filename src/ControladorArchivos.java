import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ControladorArchivos {

    private Scanner scan = new Scanner(System.in);
    private final String rutaBase= "/Escritorio/probando/proyecto final progra/"; //esta era la ruta en mi local... se cambiará cuando ya implementemos lo de un servidor
    private ArrayList<String> favoritos = new ArrayList<>();
    private String carpetaActual = "No hay carpeta Seleccionada";



    public boolean verificarExistenciaCarpeta(String rutaString){
        Path ruta = Paths.get(rutaString);
        return Files.exists(ruta) && Files.isDirectory(ruta);
    }

    public void crearCarpeta(){
        System.out.println("Ingrese el nombre de la carpeta a crear");
        Path ruta = Paths.get(rutaBase).resolve(scan.nextLine());
        try {
            Files.createDirectories(ruta);
            System.out.println("Carpeta creada o ya en existencia: " + ruta);
    } catch (IOException e) {
            System.err.println("Error al crear la carpeta: " + e.getMessage());
        }
    }

    public void mostrarCarpetaActual(){System.out.println("Carpeta actual: " + carpetaActual);}

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

    public List<String> buscarArchivos() {
        Path rutaPadre = Paths.get(rutaBase).resolve(carpetaActual);
        System.out.println("Buscando archivos en: " + carpetaActual);
        try (Stream<Path> stream = Files.list(rutaPadre)){
            return stream.filter(Files::isRegularFile).filter(path -> path.toString().toLowerCase().endsWith(".pdf")).map(Path::toString).collect(Collectors.toList());
        } catch (IOException e){
            System.err.println("Error al listar archivos: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public void cambiarCarpetaActual() {
        System.out.println("Escriba el nombre de la carpeta: ");
        String nombreCarpeta =scan.nextLine();
        Path nuevaRuta = Paths.get(rutaBase).resolve(nombreCarpeta);
        if (verificarExistenciaCarpeta(nuevaRuta.toString())){
            carpetaActual=nombreCarpeta;
            System.out.println("Cambiando a carpeta: " + carpetaActual);
        } else {System.err.println("La carpeta '"+nombreCarpeta+"' no existe.");}
    }

    public void copiarArchivo() {
        System.out.println("Ingrese el nombre del archivo a copiar");
        Path origen = Paths.get(rutaBase).resolve(carpetaActual).resolve(scan.nextLine());
        System.out.println("Ingrese la carpeta a la que irá el archivo: ");
        Path destino = Paths.get(rutaBase).resolve(scan.nextLine());
        if (!Files.exists(origen)|| !Files.isRegularFile(origen)){
            System.err.println("El archivo de origen no existe" + origen);
            return;
        }
        try{Path deestinoFinal= destino.resolve(origen.getFileName());
            Files.copy(origen,deestinoFinal, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Archivo copiado exitosamente a: " + deestinoFinal);
        } catch (IOException e) {
            System.err.println("Error al copiar el archivo: " + e.getMessage());
        }
    }

    public void verArchivos(){
        List<String> archivos= buscarArchivos();
        if (archivos.isEmpty()){System.out.println("No se encontaron archivos en esta carpeta"); return;}
        int cont=1;
        for (String a:archivos){
            System.out.println(cont+".-"+Paths.get(a).getFileName().toString());
            cont++;
        }


    }

    public void agregarArchivo(){
        //aquí va a ir el tema de enviar un archivo a Gemini, que sigue en proceso desafortunadamente
    }

    public void eliminarArchivo(){
        //
    }

    public void agregarFavorito(){
        System.out.println("Ingrese el nombre del archivo a añadir a favoritos: ");

        }

    public int leerOpcion( int limiteInf, int limiteSup){
        int opcion;
        while (true){
            try {
                System.out.println("Ingrese una opción");
                opcion = scan.nextInt();
                scan.nextLine();
                if (opcion >= limiteInf && opcion <= limiteSup) {
                    return opcion;
                } else { System.err.println("Ingrese un número valido");}
            } catch (Exception e) {
                System.err.println("Ingrese un número valido");
                scan.nextLine();
            }
        }

    }


}
