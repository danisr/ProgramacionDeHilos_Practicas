package ContadorDescontrolado;

class Hilo extends Thread {
	public void run(){		
		int numero = 1;
		while (true){
			System.out.println(numero++);
		}
	}	
}

public class ContadorDescontrolado{	
	public static void main(String[] args) throws InterruptedException {
		Thread Hilo = new Thread(new Hilo());
		System.out.println("Comienzo del contador descontrolado:");
		Hilo.start();
		Thread.sleep(1000);
		Hilo.stop();
		System.out.println("\nFin del contador descontrolado.");		
	}
}