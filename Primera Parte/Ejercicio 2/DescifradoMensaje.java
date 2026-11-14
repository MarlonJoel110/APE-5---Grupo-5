import java.io.FileReader;
import java.io.IOException;

public class DescifradoMensaje {
    public static void main(String[] args) {
        String nombreArchivo = "Primera Parte\\Ejercicio 2\\mensaje.txt";
        int contadorCaracteres = 0;
        int valorCaracter;

        try (FileReader lector = new FileReader(nombreArchivo)) {
            System.out.println("=== MENSAJE DE EMERGENCIA RECIBIDO ===");
            
            while ((valorCaracter = lector.read()) != -1) {
                System.out.print((char) valorCaracter);
                contadorCaracteres++;
            }
            
            System.out.println("\n======================================");
            System.out.println("Número total de caracteres: " + contadorCaracteres);
            
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de la estación espacial: " + e.getMessage());
        }
    }
}
