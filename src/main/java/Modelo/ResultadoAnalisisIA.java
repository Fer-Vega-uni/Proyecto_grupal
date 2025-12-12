package Modelo;

public class ResultadoAnalisisIA {
    private String status = "DESCONOCIDO";
    private String materia = "General";
    private String razon = "";

    public ResultadoAnalisisIA(String textoRaw) {
        parsearTexto(textoRaw);
    }

    private void parsearTexto(String texto) {
        String[] lineas = texto.split("\n");
        for (String linea : lineas) {
            linea = linea.trim();
            if (linea.startsWith("status:")) {
                this.status = linea.substring("status:".length()).trim();
            } else if (linea.startsWith("materia:")) {
                this.materia = linea.substring("materia:".length()).trim();
                if (this.materia.isEmpty()) this.materia = "General";
            } else if (linea.startsWith("razon:")) {
                this.razon = linea.substring("razon:".length()).trim();
            }
        }
    }

    public boolean esAprobado() {
        return "APROBADO".equalsIgnoreCase(status);
    }

    public boolean esRechazado() {
        return "RECHAZADO".equalsIgnoreCase(status);
    }

    public String getMateria() {
        return materia; }

    public String getRazon() {
        return razon; }
}
