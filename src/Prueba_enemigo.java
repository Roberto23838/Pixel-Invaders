
public class Prueba_enemigo {

	public int main(String[] args) {
		Enemigo enemigo1=new Enemigo("Atacante",1,1);
		System.out.println("Tipo: " + enemigo1.getTipo());
		System.out.println("Salud: " + enemigo1.getSalud());
		System.out.println("Ataque: " + enemigo1.getAtaque());
		return 0;
		
	}

}
