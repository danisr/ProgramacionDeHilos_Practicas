package Explicacion_y_SolucionAlternativa;

import java.util.concurrent.Semaphore;

class LlaveA_Alternativa extends Thread {
	Semaphore semaforoLlaveA;

	public LlaveA_Alternativa(Semaphore SemaforoLlaveA) {
		this.semaforoLlaveA = SemaforoLlaveA;
	}

	public void run() {
		try {
			semaforoLlaveA.acquire(); // Semáforo--. Si semáforo es menor a 0, le pasa a la cola y bloquea la tarea
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("LlaveA terminando");
		semaforoLlaveA.release(); // Semáforo++. Si semáforo <= a 0, saca al primero de la lista de espera
	}
}

class LlaveB_Alternativa extends Thread {
	Semaphore semaforoLlaveB;

	public LlaveB_Alternativa(Semaphore SemaforoLlaveB) {
		this.semaforoLlaveB = SemaforoLlaveB;
	}

	public void run() {
		try {
			semaforoLlaveB.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("LlaveB terminando");
		semaforoLlaveB.release();
	}
}

public class CorrerAlternativo {
	public static void main(String[] args) throws InterruptedException {
		Semaphore semaforo = new Semaphore(1);
		Thread a = new LlaveA_Alternativa(semaforo);
		Thread b = new LlaveB_Alternativa(semaforo);
		a.start();
		b.start();
		System.out.println("Comienzo del hilo principal");
		a.join();
		b.join();
		System.out.println("Fin del hilo principal");
	}
}