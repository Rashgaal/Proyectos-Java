package libreriaLelloFinal;

public class Dependiente extends Trabajador {

	public Dependiente(String nombre, String apellido1, String apellido2, String dni, String email, int telefono) {
		super(nombre, apellido1, apellido2, dni, email, telefono);
	}

	public Dependiente() {

	}

	@Override
	public String toString() {
		return "Dependiente " + super.toString();
	}
}
