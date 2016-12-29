package Explicacion_y_SolucionAlternativa;
/* --Problema de Inanición al necesitarse mutuamente (interbloqueo)
 * 1- En el main se inicializan las variables boolean 'CerrojoA' y 'CerrojoB' a true.
 * 2- Se crean los hilos 'a' y 'b' y se inician.
 * 3- Cuatro posibles casos (problema de sincronización por 'Interbloqueo'):
 *    3.1- Si el hilo 'a' se adelanta en su ejecución no se imprime el primer syso del 'Thread' y se ejecuta el método
 *          run() de la clase 'LlaveA', éste pone 'CerrojoA' a true e imprime "LlaveA terminando". Al estar ahora la 
 *          variable 'CerrojoA' en true ya no se ejecuta el metodo run() de la clase 'LlaveB' de tal forma que la variable
 *          'CerrojoB' siempre va a estar en false con lo cual habrá un bucle inifinito con el syso "LlaveA terminando".
 *    3.2- Misma lógica que el punto 3.1 sólo que ahora se imprimirá por consola "LlaveB terminando" correspondiente al
 *          syso del método run() de la clase 'LlaveB' quedándose también en bucle inifinito.
 *    3.3- Es lo que sería el funcionamiento lógico:
 *     	   3.3.1- Se imprime por consola el primer syso del 'Thread' "Comienzo del hilo principal".
 *         3.3.2- Se ejecuta el hilo 'a' y muy poco después el hilo 'b', se cambia 'CerrojoA' a true y muy poco después 
 *                 'CerrojoB' a true, se imprime por pantalla "LlaveA terminando" y muy poco después "LlaveB terminando",
 *                 el hilo 'a' sale del while al ser 'CerrojoB' true y termina su ejecución, el hilo 'b' sale del while
 *                 al ser 'CerrojoA' true y termina su ejecución.
 *         3.3.3- Misma lógica que el punto 3.3.2, se imprime el primer syso del 'Thread' pero ahora el primer hilo en
 *                 ejecutarse es el 'b' y luego el 'a' de tal forma que se imprime primero "LlaveB terminando" y muy poco
 *                 después "LlaveA terminando", el primer hilo en finalizar es el 'b' y después el 'a'.
 *         3.3.4- Se imprime por consola el segundo syso del 'Thread' "Fin del hilo principal" y termina el programa.
 */
class Puerta { //Metodo constructor con dos variables boolean y una variable int
	public static boolean CerrojoA;
	public static boolean CerrojoB;
	public static int contador;
}

class LlaveA extends Thread { //Clase 'LlaveA' que hereda de Thread
	public void run() {
		while (!Puerta.CerrojoB) { //Mientras que 'CerrojoB' sea distinto de true
			Puerta.CerrojoA = true; //Cambia 'CerrojoA' a true
			System.out.println("LlaveA terminando"); //Imprime...
		}
	}
}

class LlaveB extends Thread { //Clase 'LlaveB' que hereda de Thread
	public void run() {
		while (!Puerta.CerrojoA) { //Mientras que 'CerrojoA' sea distinto de true
			Puerta.CerrojoB = true; //Cambia 'CerrojoB' a true
			System.out.println("LlaveB terminando"); //Imprime...
		}
	}
}

public class Correr {
	public static void main(String[] args) throws InterruptedException {
		Puerta.CerrojoA = false; //Inicializa boolean 'CerrojoA' a false
		Puerta.CerrojoB = false; //Inicializa boolean 'CerrojoB' a false
		Thread a = new LlaveA(); //Crea hilo 'a'
		Thread b = new LlaveB(); //Crea hilo 'b'
		a.start(); //Comienza hilo 'a'
		b.start(); //Comienza hilo 'b'
		System.out.println("Comienzo del hilo principal");
		a.join(); //Hilo principal espera a que hilo 'a' termine su ejecución
		b.join(); //Hilo principal espera a que hilo 'b' termine su ejecución
		System.out.println("Fin del hilo principal");
	}
}