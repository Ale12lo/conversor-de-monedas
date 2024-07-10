import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExchangeGenerate exchangeGenerate = new ExchangeGenerate();

        System.out.println("Bienvenido al conversor de monedas.");
        System.out.println("Monedas disponibles para conversión:");

        String[] monedasDisponibles = exchangeGenerate.obtenerMonedasDisponibles();
        for (String moneda : monedasDisponibles) {
            System.out.print(moneda + " ");
        }
        System.out.println();

        while (true) {
            try {
                System.out.println("\nIngrese el monto a convertir:");
                double monto = scanner.nextDouble();
                scanner.nextLine(); // consume the newline character

                System.out.println("Ingrese la moneda desde la cual desea convertir (ej. USD):");
                String monedaDesde = scanner.nextLine().toUpperCase();

                System.out.println("Ingrese la moneda a la que desea convertir (ej. EUR):");
                String monedaHacia = scanner.nextLine().toUpperCase();

                double resultado = exchangeGenerate.convertirMoneda(monto, monedaDesde, monedaHacia);
                System.out.printf("%.2f %s equivale a %.2f %s%n", monto, monedaDesde, resultado, monedaHacia);

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println("\n¿Desea realizar otra conversión? (S/N)");
            String continuar = scanner.nextLine().toUpperCase();
            if (!continuar.equals("S")) {
                break;
            }
        }

        System.out.println("¡Gracias por usar el conversor de monedas!");
        scanner.close();
    }
}
