package negocio;

import interfaz.Interfaz;

public class Game {
    private Jugador jugador1;
    private Jugador jugador2;
    private int puntosJ1;
    private int puntosJ2;
    private boolean tiebreak;
    private int saque;
    private static final Object[] PUNTAJES = {0, 15, 30, 40, "Adv"};

    public Game() {}

    public Game(Jugador jugador1, Jugador jugador2, boolean esTiebreak) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.puntosJ1 = 0;
        this.puntosJ2 = 0;
        this.tiebreak = esTiebreak;
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

    public int getPuntosJ1() {
        return puntosJ1;
    }

    public void setPuntosJ1(int puntosJ1) {
        this.puntosJ1 = puntosJ1;
    }

    public int getPuntosJ2() {
        return puntosJ2;
    }

    public void setPuntosJ2(int puntosJ2) {
        this.puntosJ2 = puntosJ2;
    }

    public boolean isTiebreak() {
        return tiebreak;
    }

    public void setTiebreak(boolean tiebreak) {
        this.tiebreak = tiebreak;
    }

    public int getSaque() {
        return saque;
    }

    public void setSaque(int saque) {
        this.saque = saque;
    }

    public Jugador simularGame(Interfaz interfaz) {
        Jugador ganadorGame = null;
        Jugador ganadorPunto;

        while (ganadorGame == null) {

            ganadorPunto = simularPunto(jugador1, jugador2);

            // DETERMINANDO GANADOR DEL GAME
            if (ganoGame(ganadorPunto)) {
                ganadorGame = ganadorPunto;
                if (!tiebreak) {
                    continue;
                }
            }

            // SUMANDO PUNTOS
            if (ganadorPunto == jugador1) {
                if (tiebreak) {
                    puntosJ1++;
                }
                else {
                    if (puntosJ2 == 4) { puntosJ2--; }
                    else { puntosJ1++; }
                }
            } else if (ganadorPunto == jugador2) {
                if (tiebreak) {
                    puntosJ2++;
                }
                else {
                    if (puntosJ1 == 4) { puntosJ1--; }
                    else { puntosJ2++; }
                }
            }

            interfaz.mostrarResultadoParcialGame(this, ganadorPunto);

            // DEMORA PARA VISUALIZACION
            try {
                Thread.sleep(800);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return ganadorGame;
    }

    private boolean ganoGame(Jugador ganadorPunto) {
        boolean gano = false;

        if (ganadorPunto == jugador1) {
            if (tiebreak) {
                // IGUAL A 6 Y DIFERENCIA DE 1 PORQUE NO SE SUMÓ EL PUNTO TODAVIA
                gano = (puntosJ1 >= 6 && puntosJ1 - puntosJ2 >= 1);
            }
            else {
                gano = (puntosJ1 == 3 && puntosJ2 <= 2) || (puntosJ1 == 4 && puntosJ2 == 3);
            }
        } else if (ganadorPunto == jugador2) {
            if (tiebreak) {
                // DIFERENCIA DE 1 PORQUE NO SE SUMÓ EL PUNTO TODAVIA
                gano = (puntosJ2 >= 6 && puntosJ2 - puntosJ1 >= 1);
            }
            else {
                gano = (puntosJ2 == 3 && puntosJ1 <= 2) || (puntosJ2 == 4 && puntosJ1 == 3);
            }
        }

        return gano;
    }

    private Jugador simularPunto(Jugador jugador1, Jugador jugador2) {
        Jugador ganadorPunto;

        // POR PROBABILIDAD DESIGNAMOS AL GANADOR DEL PUNTO
        if (jugador1.getProbGanar() * Math.random() > jugador2.getProbGanar() * Math.random()) {
            ganadorPunto = jugador1;
        }
        else {
            ganadorPunto = jugador2;
        }

        return ganadorPunto;
    }

    @Override
    public String toString() {
        String stringJ1;
        String stringJ2;

        if (saque == 0) {
            stringJ1 = String.format("%-15s", jugador1 + " (*)");
            stringJ2 = String.format("%-15s", jugador2);
        } else {
            stringJ1 = String.format("%-15s", jugador1);
            stringJ2 = String.format("%-15s", jugador2 + " (*)");
        }

        if (tiebreak) {
            stringJ1 += String.format("|%-5s", puntosJ1);
            stringJ2 += String.format("|%-5s", puntosJ2);
        }
        else {
            stringJ1 += String.format("|%-5s", PUNTAJES[puntosJ1]);
            stringJ2 += String.format("|%-5s", PUNTAJES[puntosJ2]);
        }

        return stringJ1 + "\n" + stringJ2;
    }
}
