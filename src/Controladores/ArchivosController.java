package Controladores;

import java.io.File;

public class ArchivosController {

    private final String rutaAsignaturas = System.getProperty("user.dir") + "/recursos/asignaturas";
    private final String rutaUnidades = System.getProperty("user.dir") + "/Recursos/Unidades";



    public String getRutaAsignaturas() {
        return rutaAsignaturas;
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

        // Si no existe, la crea
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
}
