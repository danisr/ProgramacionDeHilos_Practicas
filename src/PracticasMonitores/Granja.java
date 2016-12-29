package PracticasMonitores;

class Pastor extends Thread {
	private Comedero comedero;
	private int tAlimenta;
	public int estado = 0;
	public int alimentado = 1;
	public int acabar = 2;
	public int empezar = 3;

	public Pastor(Comedero c) {
		this.comedero = c;
		tAlimenta = 5;
		estado = empezar;
	}

	public void run() {
		while (estado != acabar) {
			if (estado == empezar) {
				estado = alimentado;
			} else if (estado == alimentado) {
				comedero.añadirComida();
			}
			try {
				sleep(tAlimenta);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

class Oveja extends Thread {
	private Comedero comedero;
	private int id;
	private int tComiendo;
	public int estado;
	public int durmiendo = 1;
	public int comiendo = 2;
	public int pastando = 3;
	public int iComer;
	public boolean hayComida;

	public Oveja(Comedero comedero, int id) {
		this.comedero = comedero;
		this.id = id;
		tComiendo = 10;
		estado = pastando;
		iComer = 0;
	}

	public void run() {
		while (estado != durmiendo) {
			if (estado == pastando) {
				System.out.println("La oveja " + id + " está pastando");
				estado = comiendo;
				hayComida = comedero.comer();
				if (hayComida) {
					estado = comiendo;
				} else {
					estado = pastando;
				}

			} else if (estado == comiendo) {
				System.out.println("la oveja " + id + " está comiendo");
				iComer++;
				if (iComer == 5) {
					estado = durmiendo;
					System.out.println("La oveja " + id + " está durmiendo");
				} else {
					estado = pastando;
				}

				try {
					sleep(tComiendo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}

class Comedero {
	private int c;
	
	public Comedero() {
		c = 0;
	}

	public synchronized boolean comer() {
		if (c > 0) {
			c--;
		}
		return true;
	}
	public synchronized void añadirComida() {
		c++;
	}
}

public class Granja {
	public static void main(String[] args) {
		System.out.println("¡Bienvenidos a nuestra granja de ovejas!");
		Comedero comedero = new Comedero();
		Pastor pastor = new Pastor(comedero);
		pastor.start();	
		
		for (int i = 1; i <= 20; i++) { //Se deben crear 20 objetos de Oveja
			Oveja oveja = new Oveja(comedero, i);
			oveja.start();			
		}
		
		try {
			pastor.join();
			} catch (InterruptedException e) {
				e.printStackTrace();			
		}
		pastor.estado = pastor.acabar;
		System.out.println("La granja se cierra");
	}
}