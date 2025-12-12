package Modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class SeguridadTest {
    
    @Test
    public void testEncriptarBasic() {
        String result = Seguridad.encriptar("password123");
        assertNotNull(result);
        assertEquals(64, result.length());
    }
    
    @Test
    public void testEncriptarConsistency() {
        String input = "test";
        String result1 = Seguridad.encriptar(input);
        String result2 = Seguridad.encriptar(input);
        assertEquals(result1, result2);
    }
    
    @Test
    public void testEncriptarDifferentInputs() {
        String hash1 = Seguridad.encriptar("password1");
        String hash2 = Seguridad.encriptar("password2");
        assertNotEquals(hash1, hash2);
    }
    
    @Test
    public void testEncriptarEmpty() {
        String result = Seguridad.encriptar("");
        assertNotNull(result);
        assertEquals(64, result.length());
    }
    
    @Test
    public void testEncriptarHexFormat() {
        String result = Seguridad.encriptar("test");
        assertTrue(result.matches("[a-f0-9]+"));
    }
}