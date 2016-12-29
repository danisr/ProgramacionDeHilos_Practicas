package PracticasMonitores;

//El c�digo original tiene un problema de alto consumo de recursos por procesos improductivos. Para solucionar el 
//problema del "spin lock" se usa "synchronized" en los m�todos "guardar" y "sacar" de la clase "Dep�sito" y en cada
//uno de estos m�todos se utilizan los m�todos wait() y notify().

class Productor extends Thread {
	private Deposito deposito;

	public Productor(Deposito d) {
		deposito = d;
	}

	public void run() {
		for (int i = 1; i < 20; i++) {
			deposito.guardar(); //Va a guardar tantas veces como se encuentre el dep�sito vac�o (lo intentar� 19 veces, de 1 a 19)
		}
	}
}

class Consumidor extends Thread {
	private Deposito deposito;

	public Consumidor(Deposito d) {
		deposito = d;
	}

	public void run() {
		for (int i = 1; i < 20; i++) {
			deposito.sacar(); //Va a sacar tantas veces como se encuentre el dep�sito lleno (lo intentar� 19 veces, de 1 a 19)
		}
	}
}

class Deposito {
	private int elementos = 0;
	boolean existanElementos;

	//El synchronized no hace nada porque s�lo pasa un hilo. Pero si hubieran 20 productores no se pegar�an entre ellos
	public synchronized void guardar() {
		while (existanElementos == true) {
			try {
				this.wait(); //Mientras existan elementos se espera
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		elementos++;
		System.out.println("Se guarda un elemento. Tenemos: " + elementos);
		existanElementos = true; //Cambia la bandera a true para que el Consumidor ejecute su m�todo sacar()
		notify();		
	}
	
	//El synchronized no hace nada porque s�lo pasa un hilo. Pero si hubieran 20 consumidores no se pegar�an entre ellos
	public synchronized void sacar() {
		while (existanElementos == false) {
			try {
				this.wait(); //Mientras no existan elementos el hilo espera
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		elementos--;
		System.out.println("Se saca un elemento.   Tenemos: " + elementos);
		existanElementos = false; //Cambia la bandera a false para que el Productor ejecute su m�todo guardar()
		notify();
	}
}

public class Productor_Consumidor {
	public static void main(String[] args) { //Hilo Principal
		System.out.println("Inicio del Programa");
		Deposito d = new Deposito(); //Se crea el deposito compartido
		Productor p = new Productor(d); //Se crea el hilo Productor
		Consumidor c = new Consumidor(d); //Se creal el hilo Consumidor

		try {
			p.start(); //Se inician los hilos
			c.start();
			p.join(); //Se espera a los hilos
			c.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Fin del Programa");
	}
}