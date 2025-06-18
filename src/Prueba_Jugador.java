

import java.util.Scanner;

public class Prueba_Jugador {
	//Campo 20% clase nave 20% enemiogo 20%

	public static void main(String[] args) {
		Scanner lector=new Scanner (System.in);
		System.out.println("Su nombre jugador:");
		Jugador jugador=new Jugador(lector.nextLine(),3,1,0);
		System.out.printf("Jugador:%5s 	Salud %d", jugador.getNombre(),jugador.getSalud());
		lector.close();
	}

}
