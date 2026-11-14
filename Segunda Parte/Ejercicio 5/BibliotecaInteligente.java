import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BibliotecaInteligente {
    public static void main(String[] args) {
        String archivoNombre = "Segunda Parte\\Ejercicio 5\\libros.txt";
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n--- BIBLIOTECA INTELIGENTE ---");
            System.out.println("1. Registrar nuevo libro");
            System.out.println("2. Leer y contar libros (con validación)");
            System.out.println("3. Buscar un libro específico");
            System.out.println("4. Salir");
            System.out.print("Elija una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el título del libro a registrar: ");
                    String nuevoLibro = scanner.nextLine();
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoNombre, true))) {
                        bw.write(nuevoLibro);
                        bw.newLine();
                        System.out.println("¡Libro registrado con éxito!");
                    } catch (IOException e) {
                        System.out.println("Error al escribir en el archivo: " + e.getMessage());
                    }
                    break;

                case 2:
                    File archivo = new File(archivoNombre);
                    if (!archivo.exists() || archivo.length() == 0) {
                        System.out.println("El archivo no existe o está vacío. Registre libros primero.");
                        break;
                    }

                    int contadorLibros = 0;
                    System.out.println("\n--- LISTA DE LIBROS ALMACENADOS ---");
                    try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                        String linea;
                        while ((linea = br.readLine()) != null) {
                            contadorLibros++;
                            System.out.println(contadorLibros + ". " + linea);
                        }
                        System.out.println("Total de libros registrados: " + contadorLibros);
                    } catch (IOException e) {
                        System.out.println("Error al leer el archivo: " + e.getMessage());
                    }
                    break;

                case 3:
                    File archivoBusqueda = new File(archivoNombre);
                    if (!archivoBusqueda.exists()) {
                        System.out.println("El archivo de libros aún no ha sido creado.");
                        break;
                    }
                    System.out.print("Ingrese el título exacto del libro que desea buscar: ");
                    String busqueda = scanner.nextLine();
                    boolean encontrado = false;

                    try (BufferedReader br = new BufferedReader(new FileReader(archivoBusqueda))) {
                        String linea;
                        while ((linea = br.readLine()) != null) {
                            if (linea.equalsIgnoreCase(busqueda)) {
                                encontrado = true;
                                break;
                            }
                        }
                        if (encontrado) {
                            System.out.println("¡El libro \"" + busqueda + "\" SÍ se encuentra en la biblioteca!");
                        } else {
                            System.out.println("El libro no fue encontrado en los registros.");
                        }
                    } catch (IOException e) {
                        System.out.println("Error en la búsqueda: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 4);
        scanner.close();
    }
}