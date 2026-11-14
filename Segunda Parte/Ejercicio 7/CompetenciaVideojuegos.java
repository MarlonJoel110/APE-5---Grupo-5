import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CompetenciaVideojuegos {
    public static void main(String[] args) {
        String archivoNombre = "Segunda Parte\\Ejercicio 7\\jugadores.txt";
        File archivo = new File(archivoNombre);

        if (!archivo.exists()) {
            System.out.println("El archivo " + archivoNombre + " no existe. Créelo con el formato: Nombre,Puntuacion");
            return;
        }

        int cantidadJugadores = 0;
        int sumaPuntajes = 0;
        int mayorPuntaje = -1;
        String mejorJugador = "";

        System.out.println("=== REPORTE DE COMPETENCIA DE VIDEOJUEGOS ===");
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                int comaIndex = linea.indexOf(',');
                if (comaIndex == -1) {
                    System.out.println("[Advertencia] Formato inválido omitido: " + linea);
                    continue;
                }

                String nombre = linea.substring(0, comaIndex).trim();
                String puntajeStr = linea.substring(comaIndex + 1).trim();

                try {
                    int puntaje = Integer.parseInt(puntajeStr);
                    cantidadJugadores++;
                    sumaPuntajes += puntaje;

                    if (puntaje > mayorPuntaje) {
                        mayorPuntaje = puntaje;
                        mejorJugador = nombre;
                    }

                    if (puntaje > 70) {
                        System.out.println("-> [Filtrado > 70 pts] " + nombre + " con " + puntaje + " puntos.");
                    }

                } catch (NumberFormatException e) {
                    System.out.println("[Error] La puntuación no es un número válido en la línea: " + linea);
                }
            }

            if (cantidadJugadores > 0) {
                double promedio = (double) sumaPuntajes / cantidadJugadores;
                System.out.println("\n----------------------------------------");
                System.out.println("Total de jugadores evaluados: " + cantidadJugadores);
                System.out.println("Puntaje promedio general: " + promedio);
                System.out.println("Jugador con mayor puntaje: " + mejorJugador + " (" + mayorPuntaje + " pts)");
            } else {
                System.out.println("No se encontraron registros numéricos válidos en el archivo.");
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}