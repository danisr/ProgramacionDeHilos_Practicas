package Orden_LetrasNumeros;

import java.util.concurrent.Semaphore;

public class Numeros extends Thread {
	Semaphore semaforoNumeros;
	int numero;

	public Numeros(Semaphore semaforoNumeros) {//Constructor
		this.semaforoNumeros = semaforoNumeros;
		numero = 0;
	}

	public void run() {		
		while (numero < 10) {
			numero++;
			System.out.print((numero) + (numero < 10 ? "," : ""));
		}
		System.out.println();
		semaforoNumeros.release(); //Semáforo++. Si semáforo <= a 0, saca al primero de la lista de espera, (que serán las letras) 
	}
}