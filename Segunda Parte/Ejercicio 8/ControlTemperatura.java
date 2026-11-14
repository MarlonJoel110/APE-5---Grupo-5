import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ControlTemperatura {
    public static void main(String[] args) {
        String archivoNombre = "Segunda Parte\\Ejercicio 8\\temperaturas.txt";
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la temperatura registrada (°C): ");
        double nuevaTemp = scanner.nextDouble();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoNombre, true))) {
            bw.write(String.valueOf(nuevaTemp));
            bw.newLine();
            System.out.println("Temperatura guardada exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir la temperatura: " + e.getMessage());
        }

        File archivo = new File(archivoNombre);
        if (!archivo.exists() || archivo.length() == 0) {
            System.out.println("No hay suficientes registros de temperatura.");
            scanner.close();
            return;
        }

        int totalRegistros = 0;
        double sumaTemperaturas = 0;
        double temperaturaMasAlta = -Double.MAX_VALUE;
        int mayoresA30 = 0;

        System.out.println("\n=== ANÁLISIS METEOROLÓGICO ===");
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                try {
                    double temp = Double.parseDouble(linea.trim());
                    totalRegistros++;
                    sumaTemperaturas += temp;

                    if (temp > temperaturaMasAlta) {
                        temperaturaMasAlta = temp;
                    }

                    if (temp > 30.0) {
                        mayoresA30++;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("[Error de Formato] Se omitió un valor no numérico: " + linea);
                }
            }

            if (totalRegistros > 0) {
                double promedio = sumaTemperaturas / totalRegistros;
                System.out.println("Total de registros válidos: " + totalRegistros);
                System.out.println("Temperatura promedio: " + promedio + " °C");
                System.out.println("Temperatura más alta: " + temperaturaMasAlta + " °C");
                System.out.println("Registros que superan los 30 °C: " + mayoresA30);
            } else {
                System.out.println("No se encontraron valores numéricos válidos.");
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        scanner.close();
    }
}