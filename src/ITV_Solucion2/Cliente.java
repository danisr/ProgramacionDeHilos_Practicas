package ITV_Solucion2;

public class Cliente extends Thread {
	public int iTiempoEspera;
	public int idCliente;
	public Puesto p;

	public Cliente(int id, Puesto p) {//id viene del for (i) y p del for (i)
		iTiempoEspera = (int)((Math.random() * (101 - 10) + 10));//Tiempo de espera entre 10 y 100msg
		this.idCliente = id;
		idCliente++;
		this.p = p;
	}

	public void run() {
		super.run();
		try {
			p.entrarEnPuesto(idCliente);//Cliente entra en puesto y se le dice su id
			System.out.println("El cliente " + idCliente + " espera " + iTiempoEspera + " milisegundos");
			sleep(iTiempoEspera);
			p.salirDelPuesto();//Cliente sale del puesto
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}