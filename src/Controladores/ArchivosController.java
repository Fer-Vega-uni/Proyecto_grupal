package Controladores;

import java.io.File;


/**
 * Controlador encargado de gestionar las operaciones relacionadas con archivos y directorios
 * del sistema, como listar asignaturas, unidades y crear carpetas de usuario.
 * <p>
 * Esta clase facilita la interacción entre la aplicación y el sistema de archivos,
 * permitiendo la obtención de listas de asignaturas, archivos y unidades personalizadas
 * según el usuario activo.
 * </p>
 *
 * @author Marcelo Orellana - [@morellana09-dotcom]
 * @version 1.0
 */

public class ArchivosController {
    /** Ruta base donde se almacenan las carpetas de asignaturas. */
    private final String rutaAsignaturas = System.getProperty("user.dir") + "/recursos/asignaturas";
    /** Ruta base donde se almacenan las carpetas personales de los usuarios. */
    private final String rutaUnidades = System.getProperty("user.dir") + "/Recursos/Unidades";

    /**
     * Obtiene la ruta actual de la carpeta de asignaturas.
     *
     * @return ruta absoluta del directorio de asignaturas
     */
    public String getRutaAsignaturas() {
        return rutaAsignaturas;
    }

    /**
     * Lista los nombres de todas las asignaturas disponibles dentro de la carpeta principal.
     *
     * @return un arreglo con los nombres de las carpetas de asignaturas;
     *         un arreglo vacío si no existen o la ruta no es válida
     */
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

    /**
     * Lista los archivos de una asignatura específica.
     *
     * @param asignatura nombre de la asignatura cuya carpeta se desea listar
     * @return un arreglo con los nombres de los archivos dentro de la carpeta;
     *         un arreglo vacío si la ruta no existe o no es válida
     */
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

    /**
     * Crea una carpeta de unidad personalizada para un usuario.
     * <p>
     * Si la carpeta no existe, se genera automáticamente bajo la ruta configurada.
     * Si ya existe, se notifica mediante un mensaje en consola.
     * </p>
     *
     * @param nombreUsuario nombre del usuario para asociar la carpeta
     * @return objeto {@link File} que representa la carpeta creada o existente
     */
    public File crearUnidadUsuario(String nombreUsuario) {
        File carpetaUsuario = new File(rutaUnidades + "/Unidad " + nombreUsuario);

        if (!carpetaUsuario.exists()) {
            boolean creada = carpetaUsuario.mkdirs();
            if (creada) {
                System.out.println("Carpeta creada: " + carpetaUsuario.getAbsolutePath());
            } else {
                System.out.println("No se pudo crear la carpeta: " + carpetaUsuario.getAbsolutePath());
            }
        } else {
            System.out.println("Carpeta ya existente: " + carpetaUsuario.getAbsolutePath());
        }

        return carpetaUsuario;
    }

    /**
     * Lista los archivos dentro de la carpeta de unidad correspondiente a un usuario.
     * Si la carpeta no existe, se crea automáticamente mediante {@link #crearUnidadUsuario(String)}.
     *
     * @param nombreUsuario nombre del usuario cuya unidad se desea listar
     * @return un arreglo con los nombres de los archivos de la unidad;
     *         un arreglo vacío si no contiene archivos
     */
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
