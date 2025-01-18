package libreriaLelloFinal;

public abstract class Persona {

	private String nombre;
	private String apellido1;
	private String apellido2;
	private String dni;
	private String email;
	private int telefono;

	public Persona(String nombre, String apellido1, String apellido2, String dni, String email, int telefono) {
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.dni = dni;
		this.email = email;
		this.telefono = telefono;
	}

	public Persona() {

	}

	@Override
	public String toString() {
		return "- Nombre: " + nombre + " " + apellido1 + " " + apellido2 + " - DNI: " + dni + " - Correo electrónico: "
				+ email + " - Teléfono: " + telefono;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

}
