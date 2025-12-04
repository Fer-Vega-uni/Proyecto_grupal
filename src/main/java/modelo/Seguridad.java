package modelo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Seguridad {
    public String encriptarPassword(String password){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length()==1) hexString.append('=');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(NoSuchAlgorithmException e){
            throw new RuntimeException("Error al encriptar contraseña",e);
        }
    }

}
