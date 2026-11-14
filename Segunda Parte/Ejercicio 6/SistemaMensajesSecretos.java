import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SistemaMensajesSecretos {
    public static void main(String[] args) {
        String archivoNombre = "Segunda Parte\\Ejercicio 6\\secreto.txt";
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el nuevo mensaje secreto a guardar: ");
        String nuevoMensaje = scanner.nextLine();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoNombre, true))) {
            bw.write(nuevoMensaje);
            bw.newLine();
            System.out.println("Mensaje agregado correctamente al archivo.");
        } catch (IOException e) {
            System.out.println("Error de E/S al escribir el mensaje: " + e.getMessage());
        }

        File archivo = new File(archivoNombre);
        if (archivo.exists()) {
            int totalLineas = 0;
            System.out.println("\n--- MENSAJES SECRETOS ALMACENADOS ---");
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    totalLineas++;
                    System.out.println("Mensaje [" + totalLineas + "]: " + linea);
                }
                System.out.println("-------------------------------------");
                System.out.println("Número total de líneas registradas: " + totalLineas);
            } catch (IOException e) {
                System.out.println("Error de E/S al leer el archivo: " + e.getMessage());
            }
        } else {
            System.out.println("El archivo secreto.txt aún no contiene registros.");
        }
        scanner.close();
    }
}