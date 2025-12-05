package Modelo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class UsuariosDAO {

    private static final Path RUTA = Path.of("/home/ec2-user/sca/data/usuarios.json");
    private static final Gson gson = new Gson();

    public static List<Usuario> cargarUsuarios() throws IOException {
        if (!Files.exists(RUTA)) {
            return new ArrayList<>();
        }

        String json = Files.readString(RUTA);

        Type tipoLista = new TypeToken<List<Usuario>>() {}.getType();
        return gson.fromJson(json, tipoLista);
    }

    public static void guardarUsuarios(List<Usuario> usuarios) throws IOException {
        String json = gson.toJson(usuarios);
        Files.createDirectories(RUTA.getParent());
        Files.writeString(RUTA, json);
    }
}
