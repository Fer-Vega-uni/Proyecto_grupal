package controlador;

import modelo.GestorArchivos;
import vista.ConsolaVista;

import java.io.IOException;
import java.util.List;

public class ControladorAplicacion {

    private final GestorArchivos modelo;
    private final ConsolaVista vista;

    public ControladorAplicacion(GestorArchivos modelo, ConsolaVista vista){
        this.modelo=modelo;
        this.vista=vista;
    }

    public void iniciar(){
        int opcion;
        do {
            vista.mostrarMenu(modelo.getRutaCompletaActual());
            opcion=vista.leerOpcion(1,7);
            seleccionarOpcion(opcion);
        } while (opcion!=7);
    }

    private void seleccionarOpcion(int opcion){
        try{
            switch (opcion){
                case 1 -> verArchivos();
                case 2 -> buscarPorNombre();
                case 3 -> crearCarpeta();
                case 4 -> cambiarCarpeta();
                case 5 -> agregarFavorito();
                case 6 -> copiarArchivo();
                case 7 -> vista.mostrarMensaje("Saliendo...");
            }
        } catch (Exception e){vista.mostrarError(e.getMessage());}
    }
    //boolean:agregarfav copiararch cambiarcarpeta crearcarpeta

    private void verArchivos() throws IOException{
        List<String> archivos =modelo.buscarArchivosPdf();
        vista.mostrarArchivos(archivos);
    }

    private void buscarPorNombre() throws IOException{
        String busqueda = vista.leerTexto("Ingrese el nombre a buscar");
        List<String> archivos =modelo.buscarArchivosPorNombre(busqueda);
        vista.mostrarArchivos(archivos);
    }

    private void crearCarpeta() throws IOException{
        String nombre = vista.leerTexto("Ingrese el nombre de la nueva carpeta");
        if (!modelo.crearCarpeta(nombre)){vista.mostrarError("Error al crear carpeta.");}
        vista.mostrarMensaje("Carpeta creada exitosamente.");
    }

    private void cambiarCarpeta(){
        String nombre = vista.leerTexto("Ingrese el nombre de la carpeta a cambiar: ");
        if (modelo.cambiarCarpetaActual(nombre)){
            vista.mostrarMensaje("Cambiando carpeta a " + nombre);
        } else{vista.mostrarError("La carpeta " + nombre + "no existe.");}
    }

    private void agregarFavorito() throws IOException{
        String nombre = vista.leerTexto("Ingrese el nombre del archivo PDF a añadir a favoritos");
        if (!modelo.agregarFavorito(nombre)){vista.mostrarError("Archivo no encontrado");}
        else{vista.mostrarMensaje("archivo '" + nombre+"' añadido a favoritos");}
    }

    private void copiarArchivo() throws IOException {
        String archivo = vista.leerTexto("Ingrese el nombre del archivo a copiar");
        String destino = vista.leerTexto("Ingrese la carpeta de destino");
        if (!modelo.copiarArchivo(archivo,destino)){vista.mostrarError("Archivo o carpeta no localizados.");
        }else{vista.mostrarMensaje("Archivo copiado exitosamente.");}
    }


}
