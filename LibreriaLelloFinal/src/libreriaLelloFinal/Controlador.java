package libreriaLelloFinal;

public class Controlador extends Trabajador {

	private boolean lugar; // true entrada - false escalera

	public Controlador(String nombre, String apellido1, String apellido2, String dni, String email, int telefono) {
		super(nombre, apellido1, apellido2, dni, email, telefono);
		this.lugar = true;
	}

	public Controlador() {

	}

	@Override
	public String toString() {
		return "Controlador , lugar = " + posicionControlador(lugar) + super.toString();
	}

	public static String posicionControlador(boolean lugar) {

		String posicion;
		if (lugar) {
			posicion = "entrada";
		} else {
			posicion = "escalera";
		}
		return posicion;
	}

	public boolean isLugar() {
		return lugar;
	}

	public void setLugar(boolean lugar) {
		this.lugar = lugar;
	}

}
