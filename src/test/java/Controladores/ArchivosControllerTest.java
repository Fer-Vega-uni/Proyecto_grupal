package Controladores;

import org.junit.jupiter.api.*;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;




class ArchivosControllerTest {

    private ArchivosController controller;
    private File tempFile;

    @BeforeEach
    void setUp() throws IOException {
        controller = new ArchivosController();
        // Create a temporary file for testing
        tempFile = File.createTempFile("testfile", ".txt");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("contenido de prueba");
        }
    }

    @AfterEach
    void tearDown() {
        if (tempFile != null && tempFile.exists()) {
            tempFile.delete();
        }
    }

    @Test
    void testGetRutaAsignaturas() {
        String ruta = controller.getRutaAsignaturas();
        assertNotNull(ruta);
        assertTrue(ruta.endsWith("/Recursos/asignaturas"));
    }
    // esta prueba verifica que el metodo guardarEnPendientes copia el archivo correctamente


    @Test
    void testListarAsignaturasReturnsEmptyIfNone() {
        String[] asignaturas = controller.listarAsignaturas();
        assertNotNull(asignaturas);
    }
    // esta prueba verifica que listarArchivos devuelve un array no nulo

    @Test
    void testListarArchivosReturnsEmptyIfNone() {
        String[] archivos = controller.listarArchivos("asignaturaInexistente");
        assertNotNull(archivos);
        assertEquals(0, archivos.length);
    }
    // esta prueba verifica que crearUnidadUsuario crea el directorio correctamente

    @Test
    void testCrearUnidadUsuarioCreatesDirectory() {
        String usuario = "testUnidad";
        File carpeta = controller.crearUnidadUsuario(usuario);
        assertTrue(carpeta.exists());
        assertTrue(carpeta.isDirectory());
        carpeta.delete();
    }
    // esta prueba verifica que listarUnidadUsuario devuelve un array no nulo


    @Test
    void testListarUnidadUsuarioReturnsEmptyIfNone() {
        String usuario = "usuarioUnidadVacia";
        String[] archivos = controller.listarUnidadUsuario(usuario);
        assertNotNull(archivos);
        assertEquals(0, archivos.length);
        File carpeta = new File(controller.getRutaAsignaturas().replace("asignaturas", "Unidades") + "/Unidad " + usuario);
        carpeta.delete();
    }
    // esta prueba verifica que eliminarArchivoUnidadUsuario devuelve false si el archivo no existe

    @Test
    void testEliminarArchivoUnidadUsuarioReturnsFalseIfNotExists() {
        String usuario = "usuarioEliminar";
        boolean eliminado = controller.eliminarArchivoUnidadUsuario(usuario, "archivoInexistente.txt");
        assertFalse(eliminado);
    }
    // esta prueba verifica que copiarDesdeAsignaturaAUnidad devuelve false si el archivo origen no existe

    @Test
    void testCopiarDesdeAsignaturaAUnidadReturnsFalseIfSourceNotExists() {
        boolean resultado = controller.copiarDesdeAsignaturaAUnidad("asignaturaFalsa", "archivoFalso.txt", "usuarioTest");
        assertFalse(resultado);
    }
    // esta prueba verifica que analizarYSubirPendienteConIA maneja correctamente un archivo no existente

}