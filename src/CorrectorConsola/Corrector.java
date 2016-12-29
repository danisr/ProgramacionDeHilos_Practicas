package CorrectorConsola;

public class Corrector extends Thread {
	Palabra pal;

	public Corrector(Palabra pal) {
		this.pal = pal;
	}

	public void run() {
		String correcto = "";
		String texto = "";
		try {
			do {
				sleep(500);
				if (!pal.isCorregida()) {
					pal.setCorregida();
					texto = pal.getPalabra();
					correcto = pal.corregir();
					if (!texto.equals(correcto) && !texto.equalsIgnoreCase("fin"))
						System.err.println("Error: " + correcto);
				}
			} while (!texto.equalsIgnoreCase("fin"));
		} catch (InterruptedException e) {
			System.out.println("Corrector abortado");
		}
		System.out.println("Fin Corrector");
	}
}