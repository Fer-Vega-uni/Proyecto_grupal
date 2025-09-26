import java.util.Scanner;

public class Menu {
    public static int opcion = 0;
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        //No voy a emular el login aca, ya que eso ya está hecho he implementado en la versión gráfica :)
        menu();
    }

    public static void menu() {
        System.out.println("Visualización prototipo, parte funcional sin grafica");
        do {
            mostrarMenu();
            seleccionarOpcion(leerOpcion());
        } while (opcion > 0 && opcion < 6);
        System.out.println("Saliendo.....");
    }

    public static void mostrarMenu() {
        System.out.println("1. Ver archivos por carpeta");
        System.out.println("2. Buscar por nombre");
        System.out.println("3. Agregar Archivo");
        System.out.println("4. Borrar Archivo");
        System.out.println("5. Añadir Archivo a lista de favoritos");
        System.out.println("6. Salir");
    }

    public static int leerOpcion(){
        opcion = scan.nextInt();
        scan.nextLine();
        return opcion;
    }

    public static void seleccionarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> {Archivos.verArchivos();}
            case 2 -> {
                System.out.println("Ingrese el nombre del archivo a buscar");
                Archivos.buscarArchivos(scan.nextLine());
            }
            case 3 -> {Archivos.agregarArchivo();}
            case 4 -> {Archivos.eliminarArchivo();}
            case 5 -> {Archivos.agregarFavorito();}
            default -> {System.out.println("Opción invalida");}
        }
    }
}
