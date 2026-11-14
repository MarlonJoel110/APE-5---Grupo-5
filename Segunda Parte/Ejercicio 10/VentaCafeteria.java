import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class VentaCafeteria {
    public static void main(String[] args) {
        String archivoNombre = "Segunda Parte\\Ejercicio 10\\ventas.txt";
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el nombre del producto vendido: ");
        String producto = scanner.nextLine();
        System.out.print("Ingrese el precio del producto: ");
        double precio = scanner.nextDouble();

        if (precio < 0) {
            System.out.println("Error: El precio no puede ser negativo.");
        } else {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoNombre, true))) {
                bw.write(producto + "," + precio);
                bw.newLine();
                System.out.println("Venta registrada con éxito.");
            } catch (IOException e) {
                System.out.println("Error al registrar la venta: " + e.getMessage());
            }
        }

        File archivo = new File(archivoNombre);
        if (!archivo.exists()) {
            System.out.println("El archivo de ventas no existe todavía.");
            scanner.close();
            return;
        }

        double totalVentas = 0;
        double mayorPrecio = -1;
        String productoMasCaro = "";
        
        System.out.print("\nIngrese un valor límite para filtrar productos caros (>): ");
        double filtroValor = scanner.nextDouble();

        System.out.println("\n=== REPORTE DE VENTAS DE LA CAFETERÍA ===");
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                int comaIndex = linea.indexOf(',');
                if (comaIndex == -1) {
                    continue;
                }

                String prodNom = linea.substring(0, comaIndex).trim();
                String precioStr = linea.substring(comaIndex + 1).trim();

                try {
                    double prodPrecio = Double.parseDouble(precioStr);
                    totalVentas += prodPrecio;

                    if (prodPrecio > mayorPrecio) {
                        mayorPrecio = prodPrecio;
                        productoMasCaro = prodNom;
                    }

                    if (prodPrecio > filtroValor) {
                        System.out.println("-> [Supera " + filtroValor + "] " + prodNom + " - $" + prodPrecio);
                    }

                } catch (NumberFormatException e) {
                    System.out.println("[Error de Precio] Formato numérico inválido en: " + linea);
                }
            }

            System.out.println("----------------------------------------");
            System.out.println("Monto total de ventas acumuladas: $" + totalVentas);
            if (!productoMasCaro.isEmpty()) {
                System.out.println("Producto de mayor precio: " + productoMasCaro + " ($" + mayorPrecio + ")");
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo de ventas: " + e.getMessage());
        }
        scanner.close();
    }
}