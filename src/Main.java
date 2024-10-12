import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        API api = new API();
        ConversorMoneda conversor = new ConversorMoneda(api);
        Scanner scanner = new Scanner(System.in);

        int opcion = 0;

        do {
            System.out.println("************************************");
            System.out.println("1) Dólar => Peso Argentino");
            System.out.println("2) Peso Argentino => Dólar");
            System.out.println("3) Dólar => Real Brasileño");
            System.out.println("4) Real Brasileño => Dólar");
            System.out.println("5) Dólar => Peso Colombiano");
            System.out.println("6) Peso Colombiano => Dólar");
            System.out.println("7) Salir");
            System.out.print("Seleccione una opción válida: ");

            try {
                opcion = scanner.nextInt();

                if (opcion >= 1 && opcion <= 6) {
                    System.out.print("Ingrese el valor que deseas convertir: ");
                    double valor = scanner.nextDouble();
                    String monedaOrigen = "";
                    String monedaDestino = "";

                    switch (opcion) {
                        case 1 -> {
                            monedaOrigen = "USD";
                            monedaDestino = "ARS";
                        }
                        case 2 -> {
                            monedaOrigen = "ARS";
                            monedaDestino = "USD";
                        }
                        case 3 -> {
                            monedaOrigen = "USD";
                            monedaDestino = "BRL";
                        }
                        case 4 -> {
                            monedaOrigen = "BRL";
                            monedaDestino = "USD";
                        }
                        case 5 -> {
                            monedaOrigen = "USD";
                            monedaDestino = "COP";
                        }
                        case 6 -> {
                            monedaOrigen = "COP";
                            monedaDestino = "USD";
                        }
                    }

                    double resultado = conversor.convertir(valor, monedaOrigen, monedaDestino);
                    System.out.println("El valor " + valor + " [" + monedaOrigen + "] corresponde al valor final de => " + resultado + " [" + monedaDestino + "]");
                } else if (opcion != 7) {
                    System.out.println("Opción no válida. Por favor elija una opción entre 1 y 7.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor ingrese solo números.");
                scanner.next();
            }
        } while (opcion != 7);

        System.out.println("Programa terminado.");
        scanner.close();
    }
}
