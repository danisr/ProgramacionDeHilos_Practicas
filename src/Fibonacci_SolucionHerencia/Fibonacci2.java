package Fibonacci_SolucionHerencia;

class Fibo2 extends Thread {
	int numeros;

	public Fibo2(int numeros) {
		this.numeros = numeros;
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

public class Fibonacci2 {
	public static void main(String args[]) throws InterruptedException {
		Thread miHilo = new Fibo2(10);
		miHilo.start();
	}
}