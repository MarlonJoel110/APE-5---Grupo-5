
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ParqueAventuras {
    public static void main(String[] args) {
        int total = 0;
        int puntajeMayor = -1;
        String participanteMayor = "";

        try {
            BufferedReader lector = new BufferedReader(new FileReader("Primera Parte\\Ejercicio 4\\resultados.txt"));
            String linea;

            System.out.println("=== RESULTADOS DE PARTICIPANTES ===");

            while ((linea = lector.readLine()) != null) {

                int separador = linea.indexOf(",");

                if (separador != -1) {
                    String nombre = linea.substring(0, separador).trim();
                    String textoPuntaje = linea.substring(separador + 1).trim();

                    int puntaje = Integer.parseInt(textoPuntaje);

                    System.out.println(nombre + " - " + puntaje + " puntos");

                    total = total + puntaje;

                    if (puntaje > puntajeMayor) {
                        puntajeMayor = puntaje;
                        participanteMayor = nombre;
                    }
                }
            }

            lector.close();

            System.out.println("\nPuntaje total: " + total);

            if (puntajeMayor != -1) {
                System.out.println("Puntaje mas alto: " + puntajeMayor);
                System.out.println("Participante con mayor puntaje: " + participanteMayor);
            } else {
                System.out.println("No hay participantes registrados.");
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo resultados.txt");
        } catch (NumberFormatException e) {
            System.out.println("Error: revisa que los puntajes sean numeros.");
        }
    }
}
