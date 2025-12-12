package Modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ResultadoAnalisisIATest {

    @Test
    public void testParsingStatus() {
        ResultadoAnalisisIA resultado = new ResultadoAnalisisIA("status: APROBADO");
        assertTrue(resultado.esAprobado());
        assertFalse(resultado.esRechazado());
    }

    @Test
    public void testParsingMateria() {
        ResultadoAnalisisIA resultado = new ResultadoAnalisisIA("materia: Matemáticas");
        assertEquals("Matemáticas", resultado.getMateria());
    }

    @Test
    public void testParsingRazon() {
        ResultadoAnalisisIA resultado = new ResultadoAnalisisIA("razon: Excelente desempeño");
        assertEquals("Excelente desempeño", resultado.getRazon());
    }

    @Test
    public void testParsingMultipleLines() {
        String texto = "status: RECHAZADO\nmateria: Física\nrazon: Insuficiente";
        ResultadoAnalisisIA resultado = new ResultadoAnalisisIA(texto);
        assertTrue(resultado.esRechazado());
        assertEquals("Física", resultado.getMateria());
        assertEquals("Insuficiente", resultado.getRazon());
    }

    @Test
    public void testDefaultValues() {
        ResultadoAnalisisIA resultado = new ResultadoAnalisisIA("status: DESCONOCIDO");
        assertEquals("General", resultado.getMateria());
        assertEquals("", resultado.getRazon());
    }

    @Test
    public void testMateriaEmptyDefaultsToGeneral() {
        ResultadoAnalisisIA resultado = new ResultadoAnalisisIA("materia: ");
        assertEquals("General", resultado.getMateria());
    }

    @Test
    public void testStatusCaseInsensitive() {
        ResultadoAnalisisIA resultado = new ResultadoAnalisisIA("status: aprobado");
        assertTrue(resultado.esAprobado());
    }

    @Test
    public void testWhitespaceHandling() {
        ResultadoAnalisisIA resultado = new ResultadoAnalisisIA("  status:   APROBADO  \n  materia:   Historia  ");
        assertTrue(resultado.esAprobado());
        assertEquals("Historia", resultado.getMateria());
    }
}