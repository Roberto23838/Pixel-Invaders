import java.util.Random;
//Representación de la nave del jugador

public class Jugador {
    public String nombre;
    private int salud;
    private int ataque;
    private int puntaje;


    //Constructor

    public Jugador(String nombreUsuario, int salud, int ataque, int puntaje) {
        Random random = new Random();
		int numeroAleatorio = random.nextInt(100)+1;
		this.nombre = nombreUsuario + numeroAleatorio;

        this.salud = salud;
        this.ataque = ataque;
        this.puntaje = puntaje;
    }
	//Metodos
	public void atacar(Enemigo e){

	}

	public boolean estaVivo(){
		return this.salud > 0;
	}
	public void recibirDaño(int cantidad){

	}

	public void mejora(Mejora m){

	}


	//Setters & Getters

    public String getNombre() {
        return nombre;
    }
    public void setSalud(int salud) {
        this.salud = salud;
		if (this.salud < 0) this.salud = 0;
    }
    public int getSalud() {
        return salud;
    }
    public int getAtaque() {
        return ataque;
    }
    public void setAtaque(int ataque) {
        this.ataque = ataque;
		if (this.ataque < 0) this.ataque = 0;
    }
    public int getPuntaje() {
        return puntaje;
    }
    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
		if (this.puntaje < 0) this.puntaje = 0;
    }
}

