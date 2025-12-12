package Controladores;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class ArchivosController {

    private final String rutaAsignaturas = System.getProperty("user.dir") + "/Recursos/asignaturas";
    private final String rutaUnidades    = System.getProperty("user.dir") + "/Recursos/Unidades";

    public String getRutaAsignaturas() {
        return rutaAsignaturas;
    }

    public String getRutaUnidades() {
        return rutaUnidades;
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

    public File copiarArchivoA(File archivoOrigen, File carpetaDestino) throws IOException {
        if (!carpetaDestino.exists()) {
            carpetaDestino.mkdirs();
        }

        Path origen = archivoOrigen.toPath();
        Path destino = carpetaDestino.toPath().resolve(archivoOrigen.getName());

        Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);

        return destino.toFile();
    }
}


