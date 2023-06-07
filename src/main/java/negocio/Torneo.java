package negocio;

import interfaz.Interfaz;

public class Torneo {
    private String nombre;
    private Partido[] cuartosFinal;
    private Partido[] semifinales;
    private Partido finalTorneo;
    private Jugador[] participantes;

    public Torneo() {}

    public Torneo(String nombre) {
        this.nombre = nombre;
        this.cuartosFinal = new Partido[4];
        this.semifinales = new Partido[2];
        this.finalTorneo = new Partido();
        this.participantes = new Jugador[8];
    }

    public Torneo(String nombre, Partido finalTorneo) {
        this.nombre = nombre;
        this.finalTorneo = finalTorneo;
    }

    public Torneo(String nombre, Partido[] cuartosFinal, Partido[] semifinales, Partido finalTorneo, Jugador[] participantes) {
        this.nombre = nombre;
        this.cuartosFinal = cuartosFinal;
        this.semifinales = semifinales;
        this.finalTorneo = finalTorneo;
        this.participantes = participantes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Partido[] getCuartosFinal() {
        return cuartosFinal;
    }

    public void setCuartosFinal(Partido[] cuartosFinal) {
        this.cuartosFinal = cuartosFinal;
    }

    public Partido[] getSemifinales() {
        return semifinales;
    }

    public void setSemifinales(Partido[] semifinales) {
        this.semifinales = semifinales;
    }

    public Partido getFinalTorneo() {
        return finalTorneo;
    }

    public void setFinalTorneo(Partido finalTorneo) {
        this.finalTorneo = finalTorneo;
    }

    public Jugador[] getParticipantes() {
        return participantes;
    }

    public void setParticipantes(Jugador[] participantes) {
        this.participantes = participantes;
    }

    public Jugador[] jugarCuartosFinal(Interfaz interfaz) {
        Jugador[] ganadoresCuartos = new Jugador[4];

        for (int i = 0; i < 4; i++) {
            cuartosFinal[i] = new Partido(participantes[i], participantes[i+4], 3);

            ganadoresCuartos[i] = cuartosFinal[i].simularPartido(interfaz);
        }

        //interfaz.mostrarCuartosFinal(this);

        return ganadoresCuartos;
    }

    public Jugador[] jugarSemifinales(Jugador[] ganadoresCuartos, Interfaz interfaz) {
        Jugador[] ganadoresSemis = new Jugador[2];

        for (int i = 0; i < 2; i++) {
            semifinales[i] = new Partido(ganadoresCuartos[i], ganadoresCuartos[i+2], 3);

            ganadoresSemis[i] = semifinales[i].simularPartido(interfaz);
        }

        //interfaz.mostarSemifinales(this);

        return ganadoresSemis;
    }

    public Jugador jugarFinal(Jugador[] ganadoresSemis, Interfaz interfaz) {
        finalTorneo = new Partido(ganadoresSemis[0], ganadoresSemis[1], 3);
        return finalTorneo.simularPartido(interfaz);
    }

    @Override
    public String toString() {
        return nombre;
    }
}
