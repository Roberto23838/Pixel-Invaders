public class Jugador {

    private String nombre;
    private int salud;
    private int ataque;
    private int puntaje;

    public Jugador(String nombre, int salud, int ataque, int puntaje){
        this.nombre = nombre;
        this.salud = salud;
        this.ataque = ataque;
        this.puntaje = puntaje;
    }

    public void atacar(Enemigo enemigo){
        enemigo.ataqueRecibido(this.ataque);
        System.out.println(this.nombre + " ataca a " + enemigo.getNombre() + " causando" + this.ataque + " de daño.");
    }

    public void ataqueRecibido(int cantidad){
        this.salud -= cantidad;
        System.out.println(this.nombre + " recibe" + cantidad + " de daño. Salud restante:" + this.salud);
    }

    static class Enemigo{

        private String nombre;
        private int salud;

        public Enemigo(String nombre, int salud){
            this.nombre = nombre;
            this.salud = salud;
        }

        public void ataqueRecibido(int cantidad){
            this.salud -= cantidad;

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
            this.salud = salud;
        }
    }

    public boolean estadoVivo(){
        return this.salud > 0;
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
        this.salud = salud;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
}
