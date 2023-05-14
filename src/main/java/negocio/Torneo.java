package negocio;

public class Torneo {
    private String nombre;
    private Partido finalTorneo;

    public Torneo() {}

    public Torneo(String nombre, Partido finalTorneo) {
        this.nombre = nombre;
        this.finalTorneo = finalTorneo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Partido getFinalTorneo() {
        return finalTorneo;
    }

    public void setFinalTorneo(Partido finalTorneo) {
        this.finalTorneo = finalTorneo;
    }

    @Override
    public String toString() {
        return "FINAL DEL TORNEO \"" + nombre.toUpperCase() + "\"\n" + finalTorneo;
    }
}
