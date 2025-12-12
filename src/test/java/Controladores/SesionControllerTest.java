package Controladores;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class SesionControllerTest {

    private SesionController sesionController;

    @BeforeEach
    public void setUp() {
        sesionController = new SesionController();
    }

    @Test
    public void testConstructorInitializesUsers() {
        assertNotNull(sesionController.getUsuarioActual());
    }
    // esta prueba verifica que iniciarSesion funciona correctamente con credenciales válidas

    @Test
    public void testIniciarSesionExitoso() {
        boolean resultado = sesionController.iniciarSesion("22074033124", "1234");
        assertTrue(resultado);
        assertNotNull(sesionController.getUsuarioActual());
    }

    @Test
    public void testIniciarSesionFallo() {
        boolean resultado = sesionController.iniciarSesion("22074033124", "0000");
        assertFalse(resultado);
    }

    @Test
    public void testIniciarSesionMatriculaVacia() {
        assertThrows(IllegalArgumentException.class, () -> 
            sesionController.iniciarSesion("", "1234")
        );
    }

    @Test
    public void testCerrarSesion() {
        sesionController.iniciarSesion("22074033124", "1234");
        sesionController.cerrarSesion();
        assertNull(sesionController.getUsuarioActual());
    }

    @Test
    public void testRegistroExitoso() {
        boolean resultado = sesionController.Registro("22999999999", "5678", "Juan", "Ing. Industrial");
        assertTrue(resultado);
    }

    @Test
    public void testRegistroDuplicado() {
        assertThrows(IllegalArgumentException.class, () -> 
            sesionController.Registro("22074033124", "1234", "Marcelo", "Ing. Civil Informatica")
        );
    }

    @Test
    public void testVerificarDuplicadoMatricula() {
        boolean resultado = sesionController.verifcarDuplicado("22074033124", "Nuevo");
        assertTrue(resultado);
    }
}