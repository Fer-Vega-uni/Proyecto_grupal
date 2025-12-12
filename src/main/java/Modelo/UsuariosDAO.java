package Modelo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class UsuariosDAO {

    private static final Path RUTA = Path.of(System.getProperty("user.dir"), "Recursos", "Usuarios.json");

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static List<Usuario> cargarUsuarios() throws IOException {
        if (!Files.exists(RUTA)) {
            return new ArrayList<>();
        }

        String json = Files.readString(RUTA);
        Type tipoLista = new TypeToken<List<Usuario>>() {}.getType();
        List<Usuario> usuarios = gson.fromJson(json, tipoLista);

        if (usuarios == null) {
            return new ArrayList<>();
        }
        return usuarios;
    }

    public static void guardarUsuarios(List<Usuario> usuarios) throws IOException {
        String json = gson.toJson(usuarios);   // ahora lo escribe formateado
        Files.createDirectories(RUTA.getParent());
        Files.writeString(RUTA, json);
    }
}
