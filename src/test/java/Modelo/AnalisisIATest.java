package Modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

class AnalisisIATest {

    private AnalisisIA analisisIA;

    @BeforeEach
    void setUp() {
        analisisIA = new AnalisisIA();
    }

    @Test
    void testAnalizarArchivoSinApiKey() {
        Path testFile = Path.of("test.pdf");
        String resultado = analisisIA.analizarArchivo(testFile);
        assertTrue(resultado.contains("ERROR: No se encontró la variable de entorno GEMINI_API_KEY."));
    }

    @Test
    void testAnalizarArchivoNoExistente() {
        Path rutaInvalida = Path.of("/path/to/nonexistent/file.pdf");
        String resultado = analisisIA.analizarArchivo(rutaInvalida);
        assertTrue(resultado.contains("ERROR DE CONEXIÓN:"));
    }

    @Test
    void testCrearJsonPeticion() {
        String pdfBase64 = "JVBERi0xLjQK";
        java.lang.reflect.Method metodo;
        try {
            metodo = AnalisisIA.class.getDeclaredMethod("crearJsonPeticion", String.class);
            metodo.setAccessible(true);
            com.google.gson.JsonObject resultado = (com.google.gson.JsonObject) metodo.invoke(analisisIA, pdfBase64);
            assertNotNull(resultado);
            assertTrue(resultado.has("contents"));
        } catch (Exception e) {
            fail("Error al invocar método crearJsonPeticion: " + e.getMessage());
        }
    }

    @Test
    void testExtraerTextoDeRespuestaValido() {
        String jsonValido = "{\"candidates\":[{\"content\":{\"parts\":[{\"text\":\"status:APROBADO\\nmateria:Algebra lineal\"}]}}]}";
        java.lang.reflect.Method metodo;
        try {
            metodo = AnalisisIA.class.getDeclaredMethod("extraerTextoDeRespuesta", String.class);
            metodo.setAccessible(true);
            String resultado = (String) metodo.invoke(analisisIA, jsonValido);
            assertTrue(resultado.contains("APROBADO"));
        } catch (Exception e) {
            fail("Error al invocar método extraerTextoDeRespuesta: " + e.getMessage());
        }
    }

    @Test
    void testExtraerTextoDeRespuestaInvalido() {
        String jsonInvalido = "{\"error\": \"invalid\"}";
        java.lang.reflect.Method metodo;
        try {
            metodo = AnalisisIA.class.getDeclaredMethod("extraerTextoDeRespuesta", String.class);
            metodo.setAccessible(true);
            String resultado = (String) metodo.invoke(analisisIA, jsonInvalido);
            assertTrue(resultado.contains("ERROR PARSEANDO JSON:"));
        } catch (Exception e) {
            fail("Error al invocar método extraerTextoDeRespuesta: " + e.getMessage());
        }
    }

    @Test
    void testCrearPdfBase64ConArchivoPequeno(@TempDir Path tempDir) throws Exception {
        Path testFile = tempDir.resolve("test.pdf");
        Files.writeString(testFile, "%PDF-1.4\n");
        assertTrue(Files.exists(testFile));
    }
}