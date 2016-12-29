package CuentaAtras;

class PrimerHilo implements Runnable {
	Thread t;
	int counter;

	PrimerHilo(int counter) {
		this.counter = counter;
		t = new Thread(this, "Hilo contador");
		t.start();
	}

	public void run() {
		try {
			System.out.println("Comienzo de la cuenta atrás:");
			for (int i = counter; i > 0; i--) {
				System.out.println(i);
				Thread.sleep(1000);
			}
			System.out.println("Fin de la cuenta atrás");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class CuentaAtras {
	public static void main(String args[]) {
		new PrimerHilo(10);
	}
}