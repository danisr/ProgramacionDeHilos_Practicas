package PracticasMonitores;

import java.util.Scanner;

class Contador {
	private int c = 0;

	public Contador(int num) {
		this.c = num;
	}

	public synchronized void increment() {
		c++;
	}

	public synchronized int value() {
		return c;
	}
}

class Rastrea extends Thread {
	private Contador cont;
	private String texto;
	private char letra;

	public Rastrea(Contador cont, String texto, char letra) {
		this.cont = cont;
		this.texto = texto;
		this.letra = letra;
	}

	public void run() {
		for (int i = 0; i < texto.length(); i++)
			if (texto.charAt(i) == letra)
				cont.increment();
	}
}

public class ContadorVocales {
	public static void main(String[] args) {
		String miTexto;
		Scanner sc = new Scanner(System.in);
		System.out.print("Introduzca el texto: ");
		miTexto = sc.nextLine();
		Contador contador = new Contador(0);
		Thread contadorA = new Rastrea(contador, miTexto, 'a');
		Thread contadorE = new Rastrea(contador, miTexto, 'e');
		Thread contadorI = new Rastrea(contador, miTexto, 'i');
		Thread contadorO = new Rastrea(contador, miTexto, 'o');
		Thread contadorU = new Rastrea(contador, miTexto, 'u');
		contadorA.start();
		contadorE.start();
		contadorI.start();
		contadorO.start();
		contadorU.start();
		try {
			contadorA.join();
			contadorE.join();
			contadorI.join();
			contadorO.join();
			contadorU.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.print("El número de vocales del texto es: " + contador.value());
	}
}