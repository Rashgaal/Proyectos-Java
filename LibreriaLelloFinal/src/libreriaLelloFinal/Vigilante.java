package libreriaLelloFinal;

public class Vigilante extends Trabajador {

	private boolean posicion; // true planta baja - false planta alta

	public Vigilante(String nombre, String apellido1, String apellido2, String dni, String email, int telefono) {
		super(nombre, apellido1, apellido2, dni, email, telefono);
		this.posicion = true;
	}

	public Vigilante() {

	}

	@Override
	public String toString() {
		return "Vigilante " + super.toString() + ", planta = " + posicionVigilante(posicion);
	}

	public static String posicionVigilante(boolean posicion) {

		String lugar;
		if (posicion) {
			lugar = "baja";
		} else {
			lugar = "alta";
		}
		return lugar;
	}

	public boolean isPosicion() {
		return posicion;
	}

	public void setPosicion(boolean posicion) {
		this.posicion = posicion;
	}

}
