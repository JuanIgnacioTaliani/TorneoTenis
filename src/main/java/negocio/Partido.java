package negocio;

import interfaz.Interfaz;

public class Partido {
    private Jugador jugador1;
    private Jugador jugador2;
    private int cantSetsJ1;
    private int cantSetsJ2;
    private int cantSets;
    private Set[] resultadoSets;

    public Partido() {}

    public Partido(Jugador jugador1, Jugador jugador2, int cantSets) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.cantSets = cantSets;
        this.resultadoSets = new Set[cantSets];
        for (int i = 0; i < cantSets; i++) {
            resultadoSets[i] = new Set(jugador1, jugador2);
        }
        this.cantSetsJ1 = 0;
        this.cantSetsJ2 = 0;
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

    public int getCantSetsJ1() {
        return cantSetsJ1;
    }

    public void setCantSetsJ1(int cantSetsJ1) {
        this.cantSetsJ1 = cantSetsJ1;
    }

    public int getCantSetsJ2() {
        return cantSetsJ2;
    }

    public void setCantSetsJ2(int cantSetsJ2) {
        this.cantSetsJ2 = cantSetsJ2;
    }

    public int getCantSets() {
        return cantSets;
    }

    public void setCantSets(int cantSets) {
        this.cantSets = cantSets;
    }

    public Set[] getResultadoSets() {
        return resultadoSets;
    }

    public void setResultadoSets(Set[] resultadoSets) {
        this.resultadoSets = resultadoSets;
    }

    public Jugador simularPartido(Interfaz interfaz) {
        Jugador ganadorPartido = null;
        Jugador ganadorSet;

        for (int i = 0; i < cantSets; i++) {

            // DETERMINANDO SI GANO EL PARTIDO
            ganadorSet = resultadoSets[i].simularSet(interfaz);

            if (ganoPartido(ganadorSet)) {
                ganadorPartido = ganadorSet;
                interfaz.mostrarResultadoFinalPartido(this);
                break;
            }
            else {
                interfaz.mostrarResultadoParcialPartido(this);
            }
        }
        return ganadorPartido;
    }

    private boolean ganoPartido(Jugador ganadorSet) {
        boolean gano = false;

        if (ganadorSet == jugador1) {
            cantSetsJ1++;
            if (cantSetsJ1 > (cantSets/2)) {
                gano = true;
            }
        } else if (ganadorSet == jugador2) {
            cantSetsJ2++;
            if (cantSetsJ2 > (cantSets/2)) {
                gano = true;
            }
        }

        return gano;
    }

    @Override
    public String toString() {
        StringBuilder stringJ1 = new StringBuilder(String.format("%-15s", jugador1));
        StringBuilder stringJ2 = new StringBuilder(String.format("%-15s", jugador2));
        for (int i = 0; i < (cantSetsJ1+cantSetsJ2); i++) {

            // SI FUE TIEBREAK QUE MUESTRE LOS PUNTOS
            if (resultadoSets[i].isTiebreak()) {
                stringJ1.append(String.format("%-5s", resultadoSets[i].getCantGamesJ1() +
                        "(" + resultadoSets[i].getGames()[12].getPuntosJ1() + ")"));
                stringJ2.append(String.format("%-5s", resultadoSets[i].getCantGamesJ2() +
                        "(" + resultadoSets[i].getGames()[12].getPuntosJ2() + ")"));
            }

            else {
                stringJ1.append(String.format("%-5s", resultadoSets[i].getCantGamesJ1()));
                stringJ2.append(String.format("%-5s", resultadoSets[i].getCantGamesJ2()));
            }
        }
        return stringJ1 + "\n" + stringJ2;
    }
}
