import java.util.Scanner;

public class Menu {
    public static int opcion = 0;
    public static Scanner scan = new Scanner(System.in);
    private static final ControladorArchivos archivos =new ControladorArchivos();

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        System.out.println("Visualización prototipo, parte funcional sin grafica");
        do {
            archivos.mostrarCarpetaActual();
            mostrarMenu();
            seleccionarOpcion(leerOpcion());
        } while (opcion > 0 && opcion < 6);
    }

    public static void mostrarMenu() {
        System.out.println("1. Ver archivos de la carpeta actual");
        System.out.println("2. Buscar por nombre dentro de la carpeta actual");
        System.out.println("3. ");
        System.out.println("4. Cambiar Carpeta");
        System.out.println("5. Añadir Archivo a lista de favoritos");
        System.out.println("6. Copiar Archivo de la Carpeta actual a otra carpeta");
        System.out.println("7. Salir");
    }

    public static int leerOpcion(){
        opcion = scan.nextInt();
        scan.nextLine();
        return opcion;
    }

    public static void seleccionarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> archivos.verArchivos();
            case 2 -> archivos.buscarArchivos();
            case 3 -> archivos.crearCarpeta();
            case 4 -> archivos.cambiarCarpetaActual();
            case 5 -> archivos.agregarFavorito();
            case 6 -> archivos.copiarArchivo();
            case 7 -> System.out.println("Saliendo...");
            default -> System.out.println("Opción invalida");
        }
    }
}
