package negocio;

import interfaz.Interfaz;

public class Set {
    private Jugador jugador1;
    private Jugador jugador2;
    private int cantGamesJ1;
    private int cantGamesJ2;
    private boolean tiebreak;
    private Game[] games;

    public Set() {}

    public Set(Jugador jugador1, Jugador jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.cantGamesJ1 = 0;
        this.cantGamesJ2 = 0;
        this.games = new Game[13];
        for (int i = 0; i < 13; i++) {
            if (i == 12) {
                games[i] = new Game(jugador1, jugador2, true);
            }
            else {
                games[i] = new Game(jugador1, jugador2, false);
            }
        }
    }

    public Jugador getJugador1() {
        return jugador1;
    }

    public void setJugador1(Jugador jugador1) {
        this.jugador1 = jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }

    public void setJugador2(Jugador jugador2) {
        this.jugador2 = jugador2;
    }

    public int getCantGamesJ1() {
        return cantGamesJ1;
    }

    public void setCantGamesJ1(int cantGamesJ1) {
        this.cantGamesJ1 = cantGamesJ1;
    }

    public int getCantGamesJ2() {
        return cantGamesJ2;
    }

    public void setCantGamesJ2(int cantGamesJ2) {
        this.cantGamesJ2 = cantGamesJ2;
    }

    public boolean isTiebreak() {
        return tiebreak;
    }

    public void setTiebreak(boolean tiebreak) {
        this.tiebreak = tiebreak;
    }

    public Game[] getGames() {
        return games;
    }

    public void setGames(Game[] games) {
        this.games = games;
    }

    public Jugador simularSet(Interfaz interfaz) {
        Jugador ganadorSet = null;
        Jugador ganadorGame;

        for (int i = 0; i < 13; i++) {
            // DEFINIENDO SI ES TIEBREAK
            tiebreak = games[i].isTiebreak();

            // SETEANDO JUGADOR AL SAQUE
            games[i].setSaque(i%2);
            interfaz.mostrarInicioGame(games[i]);

            ganadorGame = games[i].simularGame(interfaz);

            // DETERMINANDO GANADOR DEL SET
            if (ganoSet(ganadorGame)) {
                ganadorSet = ganadorGame;
                interfaz.mostrarResultadoFinalSet(this, ganadorSet);
                break;
            }

            interfaz.mostrarResultadoParcialSet(this, ganadorGame);
        }
        return ganadorSet;
    }

    private boolean ganoSet(Jugador ganadorGame) {
        boolean gano = false;
        if (ganadorGame == jugador1) {
            cantGamesJ1++;
            gano = (cantGamesJ1 == 6 && cantGamesJ1 - cantGamesJ2 >= 2) || cantGamesJ1 == 7;
        } else if (ganadorGame == jugador2) {
            cantGamesJ2++;
            gano = (cantGamesJ2 == 6 && cantGamesJ2 - cantGamesJ1 >= 2) || cantGamesJ2 == 7;
        }
        return gano;
    }

    @Override
    public String toString() {
        String stringJ1 = String.format("%-15s", jugador1);
        String stringJ2 = String.format("%-15s", jugador2);

        // SI FUE TIEBREAK QUE MUESTRE LOS PUNTOS
        if (tiebreak) {
            stringJ1 += String.format("%-5s", cantGamesJ1 + "(" + games[12].getPuntosJ1() + ")");
            stringJ2 += String.format("%-5s", cantGamesJ2 + "(" + games[12].getPuntosJ2() + ")");
        }
        else {
            stringJ1 += String.format("%-5s", cantGamesJ1);
            stringJ2 += String.format("%-5s", cantGamesJ2);
        }

        return stringJ1 + "\n" + stringJ2;
    }
}
