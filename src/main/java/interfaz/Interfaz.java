package interfaz;

import negocio.*;

public class Interfaz {
    public void mostrarPresentacionTorneo(Torneo torneo) {
        System.out.println(torneo);
    }

    public void mostrarGanadorTorneo(Jugador ganadorTorneo, Torneo torneo) {
        System.out.println("\nFELICITACIONES");
        System.out.println("GANADOR DEL TORNEO: \"" + ganadorTorneo.toString().toUpperCase() + "\"");
        System.out.println(torneo);
    }

    public void mostrarGanadorPartido(Jugador ganadorPartido) {
        System.out.println("\nGANADOR DEL PARTIDO: " + ganadorPartido.toString().toUpperCase());
    }

    public void mostrarGanadorRevancha(Jugador ganadorRevancha) {
        System.out.println("\nGANADOR DE LA REVANCHA: " + ganadorRevancha.toString().toUpperCase());
    }

    public void mostrarPresentacionRevancha(Partido revancha) {
        System.out.println("\nREVANCHA\n" + revancha);
    }

    public void mostrarComienzoPartido() {
        System.out.println("\nCOMIENZA EL PARTIDO");
    }

    public void mostrarResultadoFinalPartido(Partido partido) {
        System.out.println("\nRESULTADO FINAL DEL PARTIDO");
        System.out.println(partido);
    }

    public void mostrarResultadoParcialPartido(Partido partido) {
        System.out.println("\nRESULTADO PARCIAL DEL PARTIDO");
        System.out.println(partido);
    }

    public void mostrarResultadoFinalSet(Set set, Jugador ganadorSet) {
        System.out.println("\nGANADOR DEL SET: " + ganadorSet);
        System.out.println("\nRESULTADO FINAL DEL SET");
        System.out.println(set);
    }

    public void mostrarResultadoParcialSet(Set set, Jugador ganadorGame) {
        System.out.println("\nGANADOR DEL GAME: " + ganadorGame);
        System.out.println("\nRESULTADO PARCIAL DEL SET");
        System.out.println(set);
    }

    public void mostrarResultadoParcialGame(Game game, Jugador ganadorPunto) {
        System.out.println("\nGANADOR DEL PUNTO: " + ganadorPunto);
        System.out.println("\nRESULTADO PARCIAL DEL GAME");
        System.out.println(game);
    }

    public void mostrarInicioGame(Game game) {
        if (game.isTiebreak()) {
            System.out.println("\nINICIO DEL TIEBREAK");
        }
        else {
            System.out.println("\nINICIO DEL GAME");
        }
        System.out.println(game);
    }
}
