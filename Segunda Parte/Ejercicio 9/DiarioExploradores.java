import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DiarioExploradores {
    public static void main(String[] args) {
        String archivoNombre = "Segunda Parte\\Ejercicio 9\\descubrimientos.txt";
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n--- DIARIO DE EXPLORADORES ---");
            System.out.println("1. Registrar nuevo descubrimiento");
            System.out.println("2. Leer y contar descubrimientos");
            System.out.println("3. Buscar descubrimiento por nombre");
            System.out.println("4. Salir");
            System.out.print("Elija una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre/descripción del descubrimiento: ");
                    String nuevoDescubrimiento = scanner.nextLine();
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoNombre, true))) {
                        bw.write(nuevoDescubrimiento);
                        bw.newLine();
                        System.out.println("¡Descubrimiento registrado al final del diario!");
                    } catch (IOException e) {
                        System.out.println("Excepción en la escritura: " + e.getMessage());
                    }
                    break;

                case 2:
                    File archivo = new File(archivoNombre);
                    if (!archivo.exists() || archivo.length() == 0) {
                        System.out.println("El diario está vacío o el archivo no existe.");
                        break;
                    }

                    int contador = 0;
                    System.out.println("\n--- REGISTROS DE DESCUBRIMIENTOS ---");
                    try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                        String linea;
                        while ((linea = br.readLine()) != null) {
                            contador++;
                            System.out.println(contador + ". " + linea);
                        }
                        System.out.println("Total de descubrimientos registrados: " + contador);
                    } catch (IOException e) {
                        System.out.println("Excepción en la lectura: " + e.getMessage());
                    }
                    break;

                case 3:
                    File archivoBusqueda = new File(archivoNombre);
                    if (!archivoBusqueda.exists() || archivoBusqueda.length() == 0) {
                        System.out.println("No hay datos para buscar.");
                        break;
                    }

                    System.out.print("Ingrese el término a buscar en los descubrimientos: ");
                    String criterio = scanner.nextLine().toLowerCase();
                    boolean encontrado = false;

                    try (BufferedReader br = new BufferedReader(new FileReader(archivoBusqueda))) {
                        String linea;
                        while ((linea = br.readLine()) != null) {
                            if (linea.toLowerCase().contains(criterio)) {
                                System.out.println("Encontrado: " + linea);
                                encontrado = true;
                            }
                        }
                        if (!encontrado) {
                            System.out.println("No se encontró ninguna coincidencia.");
                        }
                    } catch (IOException e) {
                        System.out.println("Excepción durante la búsqueda: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.println("Saliendo del diario...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
        scanner.close();
    }
}