
public class Enemigo {
	//Atributos
	public String tipo;
	private int salud;
	private int ataque;
	//Constructor
	public Enemigo(String tipo, int salud, int ataque) {
		this.tipo=tipo;
		this.salud=salud;
		this.ataque=ataque;
	}
	//Metodos
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo=tipo;
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

}
