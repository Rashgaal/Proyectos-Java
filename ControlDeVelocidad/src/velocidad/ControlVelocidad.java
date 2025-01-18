package velocidad;

public class ControlVelocidad {

	private int velocidadActual;
	private int mayorVelocidad;
	private boolean alarma = false;
	public static final int LIMITE_VELOCIDAD = 120;

	public ControlVelocidad(int velActual, int maxVel, boolean alarma) {

		this.velocidadActual = velActual;
		this.mayorVelocidad = maxVel;
		this.alarma = alarma;
	}

	public ControlVelocidad() {
	}

	public void incrementarVelocidad(int incremento) {

		if (incremento < 0) {
			System.out.println("No puedes acelerar negativamente porque te cargas el motor.");
		} else {

			velocidadActual = velocidadActual + incremento;
			if (velocidadActual > mayorVelocidad) {
				mayorVelocidad = velocidadActual;
			}

			if (velocidadActual > LIMITE_VELOCIDAD) {
				velocidadActual = LIMITE_VELOCIDAD;
				alarma = true;
			}
		}
	}

	public void reducirVelocidad(int decremento) {

		velocidadActual = velocidadActual - decremento;

		if (velocidadActual <= 0) {
			velocidadActual = 0;
			if (velocidadActual == 0) {
				alarma = false;
				mayorVelocidad = 0;
			}
		}
	}

	public void consultaVelocidad() {
		System.out.println("La velocidad actual es: " + velocidadActual + "Km/h.");
	}

	public void maximoHistorico() {
		System.out.println("La velocidad máxima histórica ha sido: " + mayorVelocidad + "Km/h.");
	}

	public void estadoAlarma() {
		System.out.println("La alarma esta: " + alarma);
	}

	@Override
	public String toString() {
		return "La velocidad actual es de: " + velocidadActual + "Km/h . El máximo histórico ha sido: " + mayorVelocidad
				+ "Km/h . La alarma está: " + alarma;
	}

	public int getVelocidadActual() {
		return velocidadActual;
	}

	public void setVelocidadActual(int velocidadActual) {
		this.velocidadActual = velocidadActual;
	}

	public int getMayorVelocidad() {
		return mayorVelocidad;
	}

	public void setMayorVelocidad(int mayorVelocidad) {
		this.mayorVelocidad = mayorVelocidad;
	}

	public boolean isAlarma() {
		return alarma;
	}

	public void setAlarma(boolean alarma) {
		this.alarma = alarma;
	}

}
