package AvisadorCorreo;

import java.util.Scanner;

public class AvisadorCorreo extends Thread {
	String mensaje;

	public AvisadorCorreo(String mensaje) {
		this.mensaje = mensaje;
		this.setPriority(MIN_PRIORITY);
	}

	public void run() {
		boolean recibido = false;
		try {
			while (!mensaje.equals("") && recibido == false) {
				Thread.sleep(500);
				while (mensaje.equals("nuevo")) {
					Thread.sleep(5000);
					System.err.println("Nuevo Correo");
					if (mensaje.equals("leer")) {
						recibido = true;
					}
				}
			}
		} catch (InterruptedException e) {
			System.out.println("Fin del programa");
		}
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean cerrar = false;
		while (cerrar == false) {
			String mensaje = scan.nextLine();
			Thread aC = new AvisadorCorreo(mensaje);
			aC.start();

			if (mensaje.equals("fin")) {
				cerrar = true;
				aC.interrupt();
			}
		}
	}
}