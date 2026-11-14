import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AnalisisPistas {
    public static void main(String[] args) {
        String nombreArchivo = "Primera Parte\\Ejercicio 1\\pistas.txt";
        int contador = 0;
      
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            System.out.println("=== REGISTRO DE PISTAS ===");
            
            while ((linea = br.readLine()) != null) {
                contador++;
                System.out.println("Pista " + contador + ": " + linea);
            }
            
            System.out.println("==========================");
            System.out.println("El archivo contiene un total de: " + contador + " pistas.");
            
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
