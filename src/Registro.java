public class Registro {
    Login login = new Login();


    public boolean verifcarMatriculaRegistrada(String m) {
        for ( Usuario i : login.USUARIOS){
            if (i.verficarDuplicado(m)) {
                return true;
            }
        }
        return false;
    }
    public boolean verifcarNombreRegistrado(String n) {
        for (Usuario i : login.USUARIOS){
            if (i.verficarNombreDuplicado(n)) {
                return true;
            }
        }
        return false;
    }
    public boolean varificacionMatricula(String m){
        return m.length() == 11;
    }
}
