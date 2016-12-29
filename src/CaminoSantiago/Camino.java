package CaminoSantiago;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Camino {
	public static void main(String[] args) {
		int nPeregrinos = 0;
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		System.out.print("Indique el número de peregrinos que empiezan el camino: ");
		try {
			nPeregrinos = Integer.parseInt(br.readLine());
		} catch (NumberFormatException | IOException e1) {
			System.err.println("Debe introducir un número válido");
		}

		Barca barca = new Barca();
		Barquero barquero = new Barquero(barca, nPeregrinos);
		Peregrino[] peregrinoV = new Peregrino[nPeregrinos];

		barquero.start();
		for (int i = 0; i < peregrinoV.length; i++) {
			Thread peregrino = new Peregrino((i + 1), barca);
			peregrino.start();
			peregrinoV[i] = (Peregrino) peregrino;
		}
		try {
			barquero.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < peregrinoV.length; i++) {
			try {
				peregrinoV[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("\n¡¡ Todos los peregrinos han llegado a Santiago !!");

	}
}

class Peregrino extends Thread {
	private int id;
	private final Barca barca;

	public Peregrino(int id, Barca barca) {
		this.id = id;
		this.barca = barca;
	}

	public void run() {
		int tCamino = (int) (Math.random() * (4000 - 1000) + 1000);
		int tSantiago = (int) (Math.random() * (1000 - 500) + 500);
		try {
			sleep(tCamino);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("El peregrino " + id + " llega al lago");
		barca.esperarBarca(id);
		System.out.println("El peregrino " + id + " se se sube a la barca");
		barca.esperarViaje(id);
		System.out.println("El peregrino " + id + " se baja de la barca");
		try {
			sleep(tSantiago);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.err.println("El peregrino " + id + " llega a Santiago");
	}

}

class Barca {
	private int id;
	private boolean isOcupado;
	private boolean isViajando;

	public Barca() {
		id = 0;
		isOcupado = false;
		isViajando = false;
	}

	public synchronized void esperarBarca(int idPeregrino) {
		while (isOcupado) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		id = idPeregrino;
		isOcupado = true;
		notifyAll();
	}

	public synchronized void esperarPeregrino() {
		while (!isOcupado) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		isViajando = true;
		notify();
	}

	public synchronized void esperarViaje(int idPeregrino) {
		while (isViajando) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized void desembarcar() {
		isViajando = false;
		notifyAll();
	}

	public synchronized void regresarBarca() {
		id = 0;
		isOcupado = false;
		notify();
	}
}

class Barquero extends Thread {
	private final Barca barca;
	private int nPeregrinos;

	public Barquero(Barca barca, int nPeregrinos) {
		this.barca = barca;
		this.nPeregrinos = nPeregrinos;
	}

	public void run() {
		while (nPeregrinos > 0) {
			barca.esperarPeregrino();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			barca.desembarcar();
			nPeregrinos--;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			barca.regresarBarca();
		}
	}
}
/* - Creación del objeto compartido Barca, con métodos sincronizados (1)
- Creación correcta de todos los hilos para los peregrinos y para el barquero (1)
* Los Peregrinos no necesitan conocer al Barquero, ya que se sincronizan con él a través del objeto compartido Barca.
- Espera de los Peregrinos a que la Barca esté libre (1)
- Aviso del Peregrino al Barquero cuando llega al lago (0/1)
* El peregrino debería hacer un notify para despertar al barquero
- Espera del Peregrino por el trayecto en Barca (1)
- Aviso al Peregrino del final del trayecto en Barca (0/1)
* El barquero debería haber despertado a algún peregrino, para que suba a la barca
- Espera del Barquero a que haya algún peregrino (0/1)
* El barquero debería hacer un wait hasta que llegara algún peregrino
- Aviso del Barquero de que ha regresado (1)
- Ejecución y final correcto de programa (1/2)
* No has conseguido que tu programa funcione correctamente
*/