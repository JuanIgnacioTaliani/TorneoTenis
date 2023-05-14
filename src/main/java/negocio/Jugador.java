package negocio;

public class Jugador {
    private String nombre;
    private int probGanar;

    public Jugador() {}

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public Jugador(String nombre, int probGanar) {
        this.nombre = nombre;
        this.probGanar = probGanar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getProbGanar() {
        return probGanar;
    }

    public void setProbGanar(int probGanar) {
        this.probGanar = probGanar;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
