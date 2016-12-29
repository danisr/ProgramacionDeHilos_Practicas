package PracticasMonitores;

import java.util.Scanner;

class Contador2 extends Thread {
	private int c;
	private char vocal;
	private String palabra;

	public Contador2(char vocal, String palabra) {
		this.c = 0;
		this.vocal = vocal;
		this.palabra = palabra;
	}

	public synchronized void increment() {
		c++;
	}

	public synchronized int value() {
		return c;
	}

	public void run() {
		for (int i = 0; i < palabra.length(); i++) {
			synchronized (palabra) {
				if (vocal == palabra.charAt(i)) {
					increment();
				}
			}
		}
		System.out.println("Hay " + c + " '" + vocal + "'");
	}
}

public class ContadorVocales2 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Escribe una palabra: ");
		String palabra = scan.nextLine();

		//Se crean los 5 hilos y se pasa por parámetro la vocal a buscar y la palabra escrita
		Contador2 hiloA = new Contador2('a', palabra);
		Contador2 hiloE = new Contador2('e', palabra);
		Contador2 hiloI = new Contador2('i', palabra);
		Contador2 hiloO = new Contador2('o', palabra);
		Contador2 hiloU = new Contador2('u', palabra);

		try {
			hiloA.start(); //Se inician los 5 hilos
			hiloE.start();
			hiloI.start();
			hiloO.start();
			hiloU.start();
			hiloA.join(); //Se espera a los 5 hilos
			hiloE.join();
			hiloI.join();
			hiloO.join();
			hiloU.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}