package negocio;

import interfaz.Interfaz;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    private Interfaz interfaz;
    private Jugador jugador1;
    private Jugador jugador2;
    private Game game;

    @Before
    public void setUp() {
        interfaz = new Interfaz();
        jugador1 = new Jugador("Nadal", 50);
        jugador2 = new Jugador("Federer", 50);
        game = new Game(jugador1, jugador2, false);
    }

    @Test
    public void testProbabilidades() {
        jugador1.setProbGanar(100);
        jugador2.setProbGanar(0);
        Jugador ganador = game.simularGame(interfaz);
        assertEquals(jugador1, ganador);

        jugador1.setProbGanar(0);
        jugador2.setProbGanar(100);
        game = new Game(jugador1, jugador2, false);
        ganador = game.simularGame(interfaz);

        assertEquals(jugador2, ganador);
    }

    @Test
    public void testTieBreak() {
        game.setTiebreak(true);
        game.simularGame(interfaz);

        assertTrue((game.getPuntosJ1() >= 7 && (game.getPuntosJ1() - game.getPuntosJ2() >= 2))
                || (game.getPuntosJ2() >= 7 && (game.getPuntosJ2() - game.getPuntosJ1() >= 2)));
    }

    @Test
    public void testDeuce() {
        game.setPuntosJ1(3);
        game.setPuntosJ2(3);
        Jugador ganador = game.simularGame(interfaz);

        assertTrue((ganador == jugador1 && game.getPuntosJ1() == 4 && game.getPuntosJ2() == 3) ||
                (ganador == jugador2 && game.getPuntosJ1() == 3 && game.getPuntosJ2() == 4));
    }

}