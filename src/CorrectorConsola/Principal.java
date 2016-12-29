package CorrectorConsola;

import java.util.Scanner;

public class Principal {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Palabra palabra = new Palabra();
		
		// Le paso al hilo corrector la variable (objeto) de sincronización
		Corrector corrector = new Corrector(palabra);
		String lectura = null;
		corrector.setPriority(Thread.MIN_PRIORITY);
		corrector.start();
		
		do {
			System.out.print("Introduce palabra: ");
			lectura = sc.nextLine();
			palabra.leer(lectura);
		} while (!lectura.equalsIgnoreCase("fin"));
		
		try {
			// Espero a que termine el programa corrector (finalización más
			// formal)
			corrector.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Fin Programa Principal");
	}
}