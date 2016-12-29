package Orden_LetrasNumeros;

import java.util.concurrent.Semaphore;

public class Letras extends Thread {
	Semaphore semaforoLetras;
	String abecedario;

	public Letras(Semaphore semaforoLetras) { //Constructor
		this.semaforoLetras = semaforoLetras;
		abecedario = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,ñ,o,p,q,r,s,t,u,v,w,x,y,z";
	}

	public void run() {
		try {
			semaforoLetras.acquire(); // Semáforo--. Si semáforo es menor a 0 le pasa a la cola y bloquea la tarea
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.print(abecedario.substring(0, 19));
		System.out.println();
		//Ya no hace falta hacer un release() porque no hay nada más que imprimir
	}
}