package ITV_Solucion2;

import java.util.concurrent.Semaphore;

public class Main {
	public static Semaphore s;

	public static void main(String[] args) {
		int puestos = (int) (Math.random() * (6 - 1) + 1);//Puestos entre 1 y 5
		int clientes = (int) (Math.random() * (51 - 20) + 20);//Clientes entre 20 y 50

		System.err.println("Hay " + clientes + " clientes esperando a pasar la ITV");
		System.err.println("Hay " + puestos + " puestos de inspección");

		Puesto[] puesto = new Puesto[puestos];//Se crea array con los puestos obtenidos anteriormente
		Cliente[] cliente = new Cliente[clientes];//Se crea array con los clientes obtenidos anteriormente

		for (int i = 0; i < puesto.length; i++) {
			puesto[i] = new Puesto(i);//Se crea el puesto y se le pasa su id
		}

		for (int i = 0; i < cliente.length; i++) {
			//Se crea el cliente y se le pasa su id y el puesto aleatorio para este cliente en cuestión
			cliente[i] = new Cliente(i, puesto[(int) (Math.random() * puestos)]);
		}

		for (int i = 0; i < puesto.length; i++) {
			puesto[i].start();//Comienza el puesto
		}

		for (int i = 0; i < cliente.length; i++) {
			cliente[i].start();//Comienza el cliente
		}

		for (int i = 0; i < cliente.length; i++) {
			try {
				cliente[i].join();//Espera a que los clientes terminen
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.err.println("Se cierra la ITV");
	}
}