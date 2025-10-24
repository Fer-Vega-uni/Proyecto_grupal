package vista;

import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class ConsolaVista {

    private final Scanner scan = new Scanner(System.in);

    public void mostrarMenu(String carpetaActual) {
        System.out.println("Carpeta Actual: " + carpetaActual);
        System.out.println("1. Ver archivos de la carpeta actual");
        System.out.println("2. Buscar por nombre dentro de la carpeta actual");
        System.out.println("3. Crear nueva carpeta");
        System.out.println("4. Cambiar Carpeta");
        System.out.println("5. Añadir Archivo a lista de favoritos");
        System.out.println("6. Copiar Archivo de la Carpeta actual a otra carpeta");
        System.out.println("7. Salir");
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
                } else { mostrarError("Opción fuera de rango");}
            } catch (Exception e) {
                mostrarError("Ingrese un número valido");
                scan.nextLine();
            }
        }
    }
    public String leerTexto(String mensaje){
        System.out.println(mensaje + ": ");
        return scan.nextLine();
    }

    public void mostrarArchivos(List<String> archivos) {
        if (archivos.isEmpty()){System.out.println("No se encontraron archivos.");}
        else{
            System.out.println("Archivos Encontrados: ");
            int cont=1;
            for (String a:archivos){
                System.out.println(cont+".-"+ Paths.get(a).getFileName().toString());
                cont++;
            }
        }
    }
    public void mostrarMensaje(String mensaje) {System.out.println(mensaje);}
    public void mostrarError(String mensaje) {System.err.println("!! ERROR: " + mensaje);}
}
