package Orden_LetrasNumeros;

import java.util.concurrent.Semaphore;

public class Principal {

	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(0);

		Letras letras = new Letras(semaforo); //Será el segundo hilo
		Numeros numeros = new Numeros(semaforo); //Será el primer hilo

		letras.start();
		numeros.start();

		System.out.println("Inicio Programa Principal");
		try {
			letras.join();
			numeros.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Fin Programa Principal");
	}
}