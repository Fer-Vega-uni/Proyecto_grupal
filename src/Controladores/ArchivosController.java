package Controladores;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ArchivosController {

    private final String rutaAsignaturas = System.getProperty("user.dir") + "/Recursos/asignaturas";
    private final String rutaUnidades    = System.getProperty("user.dir") + "/Recursos/Unidades";
    private final String rutaPendientes  = System.getProperty("user.dir") + "/Recursos/pendientes";

    public String getRutaAsignaturas() {
        return rutaAsignaturas;
    }

    public File getCarpetaPendientes() {
        File carpeta = new File(rutaPendientes);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
        return carpeta;
    }

    public File guardarEnPendientes(File archivoOrigen, String nombreUsuario) throws IOException {
        File carpetaPendientes = getCarpetaPendientes();

        String nombreOriginal = archivoOrigen.getName();
        String extension = "";
        int punto = nombreOriginal.lastIndexOf('.');
        if (punto != -1) {
            extension = nombreOriginal.substring(punto); // incluye el punto
            nombreOriginal = nombreOriginal.substring(0, punto);
        }

        String nombreLimpio = nombreOriginal.replaceAll("[^a-zA-Z0-9_\\-]", "_");
        String nombreDestino = nombreLimpio + "_" + nombreUsuario + "_" + System.currentTimeMillis() + extension;

        File destino = new File(carpetaPendientes, nombreDestino);
        Files.copy(archivoOrigen.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);

        return destino;
    }



    public String[] listarAsignaturas() {
        File carpeta = new File(rutaAsignaturas);

        if (!carpeta.exists() || !carpeta.isDirectory()) {
            return new String[0];
        }

        File[] carpetas = carpeta.listFiles(File::isDirectory);
        if (carpetas == null) return new String[0];

        String[] nombres = new String[carpetas.length];
        for (int i = 0; i < carpetas.length; i++) {
            nombres[i] = carpetas[i].getName();
        }
        return nombres;
    }

    public String[] listarArchivos(String asignatura) {
        File carpeta = new File(rutaAsignaturas + "/" + asignatura);

        if (!carpeta.exists() || !carpeta.isDirectory()) {
            return new String[0];
        }

        File[] archivos = carpeta.listFiles();
        if (archivos == null) return new String[0];

        String[] nombres = new String[archivos.length];
        for (int i = 0; i < archivos.length; i++) {
            nombres[i] = archivos[i].getName();
        }
        return nombres;
    }

    public File crearUnidadUsuario(String nombreUsuario) {
        File carpetaUsuario = new File(rutaUnidades + "/Unidad " + nombreUsuario);

        if (!carpetaUsuario.exists()) {
            boolean creada = carpetaUsuario.mkdirs();
            if (creada) {
                System.out.println("✅ Carpeta creada: " + carpetaUsuario.getAbsolutePath());
            } else {
                System.out.println("⚠️ No se pudo crear la carpeta: " + carpetaUsuario.getAbsolutePath());
            }
        } else {
            System.out.println("📂 Carpeta ya existente: " + carpetaUsuario.getAbsolutePath());
        }

        return carpetaUsuario;
    }

    public String[] listarUnidadUsuario(String nombreUsuario) {
        File carpetaUsuario = crearUnidadUsuario(nombreUsuario);
        File[] archivos = carpetaUsuario.listFiles();
        if (archivos == null || archivos.length == 0) return new String[0];

        String[] nombres = new String[archivos.length];
        for (int i = 0; i < archivos.length; i++) {
            nombres[i] = archivos[i].getName();
        }
        return nombres;
    }



    public boolean eliminarArchivoUnidadUsuario(String nombreUsuario, String nombreArchivo) {
        File carpetaUsuario = crearUnidadUsuario(nombreUsuario);
        File archivo = new File(carpetaUsuario, nombreArchivo);
        return archivo.exists() && archivo.delete();
    }


    public boolean copiarDesdeAsignaturaAUnidad(String asignatura, String nombreArchivo, String nombreUsuario) {
        File origen = new File(rutaAsignaturas + "/" + asignatura + "/" + nombreArchivo);
        if (!origen.exists() || !origen.isFile()) return false;

        File carpetaUsuario = crearUnidadUsuario(nombreUsuario);
        File destino = new File(carpetaUsuario, nombreArchivo);

        try {
            Files.copy(origen.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
