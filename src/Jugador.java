public class Jugador {
    private String nombre;
    private int salud;
    private int ataque;
    private int puntaje;

    public Jugador(int salud, int ataque, int puntaje) {
        this.salud = salud;
        this.ataque = ataque;
        this.puntaje = puntaje;
    }

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public void atacar(Enemigo e) {

    }

    public boolean estaVivo() {
        return this.salud > 0;
    }

    public void recibirDaño(int cantidad) {
        salud -= cantidad;
        if (salud < 0) salud = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud = Math.max(salud, 0);
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = Math.max(ataque, 0);
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = Math.max(puntaje, 0);
    }
}