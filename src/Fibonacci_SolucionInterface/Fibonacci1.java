package Fibonacci_SolucionInterface;

class Fibo implements Runnable {
	Thread t;
	int numeros;

	Fibo(int numeros) {
		this.numeros = numeros;
		t = new Thread(this, "Hilo Fibonacci");
		t.start();
	}

	public void run() {
		System.out.println("Los " + numeros + " primeros términos de la serie de Fibonacci son: ");
		int fibo1 = 1;
		int fibo2 = 1;
		System.out.print(fibo1 + " ");
		for (int i = 2; i <= numeros; i++) {
			System.out.print(fibo2 + " ");
			fibo2 = fibo1 + fibo2;
			fibo1 = fibo2 - fibo1;
		}
		System.out.println();
	}
}

public class Fibonacci1 {
	public static void main(String args[]) {
		new Fibo(10);
	}
}