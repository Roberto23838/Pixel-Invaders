

import java.util.Scanner;

public class Prueba_Jugador {

	public static void main(String[] args) {
		Scanner lector=new Scanner (System.in);
		System.out.println("Su nombre jugador");
		Jugador jugador=new Jugador(lector.nextLine(),100,10,1000);
		System.out.printf("Jugador:%5s 	Salud %d", jugador.getNombre(),jugador.getSalud());
		lector.close();
	}

}
