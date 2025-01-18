package libreriaLelloFinal;

public abstract class Trabajador extends Persona {

	private boolean turno; // false mañana - true tarde

	public Trabajador(String nombre, String apellido1, String apellido2, String dni, String email, int telefono) {
		super(nombre, apellido1, apellido2, dni, email, telefono);
		this.turno = false;
	}

	public Trabajador() {

	}

	@Override
	public String toString() {
		return super.toString() + "- Turno: " + horarioTurno(turno);
	}

	public static String horarioTurno(boolean turno) {

		String horario;
		if (turno) {
			horario = "tarde";
		} else {
			horario = "mañana";
		}
		return horario;
	}

	public boolean isTurno() {
		return turno;
	}

	public void setTurno(boolean turno) {
		this.turno = turno;
	}

}
