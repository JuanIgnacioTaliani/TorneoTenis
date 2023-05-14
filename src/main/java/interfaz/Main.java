package interfaz;

import negocio.Torneo;
import negocio.Jugador;
import negocio.Partido;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Interfaz interfaz = new Interfaz();

        // INGRESO DE DATOS
        System.out.print("Nombre del torneo: ");
        String nombreTorneo = scanner.nextLine();

        System.out.print("\nNombre del Jugador 1: ");
        String nombreJ1 = scanner.nextLine();
        int probGanarJ1 = validarIngresoProbabilidad(scanner, nombreJ1);
        System.out.print("\nNombre del Jugador 2: ");
        String nombreJ2 = scanner.nextLine();
        int probGanarJ2 = validarIngresoProbabilidad(scanner, nombreJ2);

        // VALIDACION DE CANTIDAD DE SETS
        int cantSets = validarCantSets(scanner);

        // INICIALIZACION DE JUGADORES
        Jugador jugador1 = new Jugador(nombreJ1, probGanarJ1);
        Jugador jugador2 = new Jugador(nombreJ2, probGanarJ2);

        // MOSTRANDO INFORMACION INGRESADA

        // INICIALIZACION DE PARTIDO
        Partido partido = new Partido(jugador1, jugador2, cantSets);
        Torneo torneo = new Torneo(nombreTorneo, partido);

        interfaz.mostrarPresentacionTorneo(torneo);
        interfaz.mostrarComienzoPartido();

        // SIMULANDO PARTIDO
        Jugador ganadorPartido = partido.simularPartido(interfaz);

        interfaz.mostrarGanadorPartido(ganadorPartido);
        interfaz.mostrarGanadorTorneo(ganadorPartido, torneo);

        // REVANCHA
        scanner = new Scanner(System.in);
        System.out.print("\nDesea jugar la revancha? (S/N): ");
        String quiereRevancha = scanner.nextLine();

        if (quiereRevancha.equalsIgnoreCase("s")) {
            Partido revancha = new Partido(jugador1, jugador2, cantSets);

            interfaz.mostrarPresentacionRevancha(revancha);

            Jugador ganadorRevancha = revancha.simularPartido(interfaz);

            interfaz.mostrarGanadorRevancha(ganadorRevancha);
        }
    }

    private static int validarIngresoProbabilidad(Scanner scanner, String nombreJugador) {
        int probGanar = -1;
        while (probGanar < 0 || probGanar > 100) {
            System.out.print("Probabilidad de ganar de " + nombreJugador + " (1-100): ");
            probGanar = Integer.parseInt(scanner.nextLine());
            if (probGanar < 0 || probGanar > 100) {
                System.out.println("ERROR! La probabilidad debe estar entre 1 y 100");
            }
        }
        return probGanar;
    }

    private static int validarCantSets(Scanner scanner) {
        int cantSets = 0;

        while (cantSets != 3 && cantSets != 5) {
            System.out.print("Cantidad de sets: ");
            cantSets = scanner.nextInt();
            if (cantSets != 3 && cantSets != 5) {
                System.out.println("ERROR! La cantidad de sets debe ser 3 o 5");
            }
        }
        return cantSets;
    }
}
