package ITV_Solucion2;

import java.util.concurrent.Semaphore;

public class Puesto extends Thread {
	Semaphore semaforo;
	int idPuesto = 0;
	int idCliente = 0;

	public Puesto(int id) {// recibe la (i) del for del main
		semaforo = new Semaphore(1);
		this.idPuesto = id;
		idPuesto++;
	}

	public void entrarEnPuesto(int idcliente) {// recibe la id del cliente
		try {
			semaforo.acquire();// Si el cliente es el primero en llegar sigue, sino se para
			this.idCliente = idcliente;
			System.out.println("El cliente " + idCliente + " entra en puesto: " + idPuesto);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void salirDelPuesto() {
		System.out.println("El cliente " + idCliente + " sale del puesto: " + idPuesto);
		semaforo.release();// El cliente libera el puesto
	}
}
