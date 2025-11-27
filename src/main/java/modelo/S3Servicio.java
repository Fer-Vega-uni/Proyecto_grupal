package modelo;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import java.nio.file.Path;

public class S3Servicio {
    private final String BUCKET_NAME ="sistema-colaborativo-apuntes-bucket";
    private final S3Client s3;

    public S3Servicio(){
        this.s3 = S3Client.builder().region(Region.US_EAST_1).credentialsProvider(EnvironmentVariableCredentialsProvider.create()).build();
    }

    public String subirArchivo(Path archivoLocal, String carpetaDestino){
        try{
            String nombreArchivo = archivoLocal.getFileName().toString();
            String key = carpetaDestino + "/" + nombreArchivo;
            PutObjectRequest request = PutObjectRequest.builder().bucket(BUCKET_NAME).key(key).build();
            s3.putObject(request, RequestBody.fromFile(archivoLocal));
            return "https://" + BUCKET_NAME + ".s3.amazonaws.com/" + key;
        } catch (S3Exception e) {
            throw new RuntimeException("Error en subida a AWS: " + e.getMessage());
        }
    }
}
