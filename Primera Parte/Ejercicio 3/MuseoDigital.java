
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MuseoDigital {
    public static void main(String[] args) {
        int pinturas = 0;
        int esculturas = 0;

        try {
            BufferedReader lector = new BufferedReader(new FileReader("Primera Parte\\Ejercicio 3\\obras.txt"));
            String linea;

            System.out.println("=== REGISTRO DE OBRAS ===");

            while ((linea = lector.readLine()) != null) {

                int separador = linea.indexOf(";");

                if (separador != -1) {
                    String nombre = linea.substring(0, separador).trim();
                    String categoria = linea.substring(separador + 1).trim();

                    System.out.println("Obra: " + nombre +
                            " | Categoria: " + categoria);

                    if (categoria.equalsIgnoreCase("Pintura")) {
                        pinturas++;
                    } else if (categoria.equalsIgnoreCase("Escultura")) {
                        esculturas++;
                    }
                }
            }

            lector.close();

            System.out.println("\n=== CANTIDAD POR CATEGORIA ===");
            System.out.println("Pintura: " + pinturas + " obras");
            System.out.println("Escultura: " + esculturas + " obras");

        } catch (IOException e) {
            System.out.println("Error al leer el archivo obras.txt");
        }
    }
}
