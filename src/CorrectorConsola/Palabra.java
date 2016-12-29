package CorrectorConsola;

public class Palabra {
	private String palabra;
	private boolean corregida;

	public Palabra() {
		this.palabra = "";
		this.corregida = true;
	}

	public void leer(String palabra) {
		this.palabra = palabra;
		this.corregida = false;
	}

	public String corregir() {
		char inicial = palabra.charAt(0);
		String resto = palabra.substring(1, palabra.length());
		inicial = Character.toUpperCase(inicial);
		resto = resto.toLowerCase();
		this.corregida = true;
		return inicial + resto;
	}

	public boolean isCorregida() {
		return corregida;
	}

	public String getPalabra() {
		return palabra;
	}

	public void setCorregida() {
		this.corregida = true;
	}
}