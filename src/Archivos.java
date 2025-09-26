import java.util.ArrayList;
import java.util.Scanner;

public class Archivos {

    private static ArrayList<String> archivos = new ArrayList<>();
    public static Scanner scan = new Scanner(System.in);

    static {archivos.add("lineal.pdf");
            archivos.add("multi.pdf");
            archivos.add("aaaaa.pdf");
            archivos.add("edo.pdf");}


    public static void verArchivos(){
        for (String a:archivos){
            System.out.println(a);
        }

    }
    public static void buscarArchivos(String nombreArchivo){
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
