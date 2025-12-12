package Modelo;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

public class AnalisisIA {

    private static final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent";
    private final String apiKey = System.getenv("GEMINI_API_KEY");

    public String analizarArchivo(Path rutaArchivo) {
        if (apiKey == null || apiKey.isEmpty()) {
            return "ERROR: No se encontró la variable de entorno GEMINI_API_KEY.";
        }

        try {
            byte[] fileBytes = Files.readAllBytes(rutaArchivo);
            String pdfBase64 = Base64.getEncoder().encodeToString(fileBytes);
            JsonObject jsonBody = crearJsonPeticion(pdfBase64);
            String jsonString = new Gson().toJson(jsonBody);
            HttpClient client = HttpClient.newHttpClient();
            String urlConKey = API_URL + "?key=" + apiKey;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlConKey))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonString))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return extraerTextoDeRespuesta(response.body());
            } else {
                return "ERROR API (" + response.statusCode() + "): " + response.body();
            }

        } catch (IOException | InterruptedException e) {
            return "ERROR DE CONEXIÓN: " + e.getMessage();
        }
    }

    private JsonObject crearJsonPeticion(String pdfBase64) {
        JsonObject inlineData = new JsonObject();
        inlineData.addProperty("mime_type", "application/pdf");
        inlineData.addProperty("data", pdfBase64);

        JsonObject partPdf = new JsonObject();
        partPdf.add("inline_data", inlineData);

        JsonObject partText = getJsonObject();

        JsonArray parts = new JsonArray();
        parts.add(partPdf);
        parts.add(partText);

        JsonObject content = new JsonObject();
        content.add("parts", parts);

        JsonArray contents = new JsonArray();
        contents.add(content);

        JsonObject root = new JsonObject();
        root.add("contents", contents);

        return root;
    }

    private static JsonObject getJsonObject() {
        JsonObject partText = new JsonObject();
        String prompt = """
                Actúa como un clasificador académico. Analiza este documento PDF.
                1. Determina si el contenido es seguro y educativo.
                2. Si es APROBADO, clasifícalo en UNA de estas materias:
                   [Quimica, Fisica 1, Fisica 2, Fisica 3, Algebra lineal, Introducción al Algebra, 
                    Ecuaciones Diferenciales, Precalculo, Cálculo en una Variable, 
                    Cálculo Multivariable, Matemáticas para la computación]
                
                Responde ESTRICTAMENTE con este formato:
                status:APROBADO (o RECHAZADO)
                razon: (Solo si es rechazado explícalo)
                materia: (Solo si es aprobado, el nombre exacto de la lista)
                """;
        partText.addProperty("text", prompt);
        return partText;
    }

    private String extraerTextoDeRespuesta(String jsonResponse) {
        try {
            JsonObject json = new Gson().fromJson(jsonResponse, JsonObject.class);
            return json.getAsJsonArray("candidates")
                    .get(0).getAsJsonObject()
                    .getAsJsonObject("content")
                    .getAsJsonArray("parts")
                    .get(0).getAsJsonObject()
                    .get("text").getAsString();
        } catch (Exception e) {
            return "ERROR PARSEANDO JSON: " + jsonResponse;
        }
    }
}