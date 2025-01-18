package registroPersonal;

import java.time.LocalDate;
import java.util.Random;

public class Registro {

	private String nombre;
	private String primerApellido;
	private String segundoApellido;
	private String dniNie;
	private Fecha fechaNacimiento;
	private String telefono;
	private String correoElectronico;
	private String puestoTrabajo;
	private String contraseña;

	public Registro(String nombre, String primerApellido, String segundoApellido, String dniNie, Fecha fechaNacimiento,
			String telefono, String puestoTrabajo) {

		this.nombre = formatearNombre(nombre);
		this.primerApellido = formatearPrimerApellido(primerApellido);
		this.segundoApellido = formatearSegundoApellido(segundoApellido);
		this.dniNie = dniNie;
		this.fechaNacimiento = fechaNacimiento;
		this.telefono = telefono;
		this.correoElectronico = crearCorreoElectronico(nombre, primerApellido, segundoApellido);
		this.puestoTrabajo = formatearPuestoTrabajo(puestoTrabajo);
		this.contraseña = crearContraseña();

	}

	public Registro() {
	}

	private String formatearNombre(String nombre) {

		String[] variosNombres = nombre.split(" ");
		String formateoNombreCompleto = " ";

		for (int i = 0; i < variosNombres.length; i++) {
			variosNombres[i] = variosNombres[i].substring(0, 1).toUpperCase()
					+ variosNombres[i].substring(1).toLowerCase();
			formateoNombreCompleto += " " + variosNombres[i];
		}

		formateoNombreCompleto = formateoNombreCompleto.trim();
		return formateoNombreCompleto;
	}

	private String formatearPrimerApellido(String primerApellido) {

		String formateoPrimerApellido = " ";
		String[] variosPrimerApellidos = primerApellido.split("-");

		for (int i = 0; i < variosPrimerApellidos.length; i++) {
			variosPrimerApellidos[i] = variosPrimerApellidos[i].substring(0, 1).toUpperCase()
					+ variosPrimerApellidos[i].substring(1).toLowerCase();
			formateoPrimerApellido += variosPrimerApellidos[i] + "-";
		}

		formateoPrimerApellido = formateoPrimerApellido.substring(0, formateoPrimerApellido.length() - 1).trim();
		return formateoPrimerApellido;
	}

	private String formatearSegundoApellido(String segundoApellido) {

		String formateoSegundoApellido = " ";
		String[] variosSegundoApellidos = segundoApellido.split("-");

		for (int i = 0; i < variosSegundoApellidos.length; i++) {
			variosSegundoApellidos[i] = variosSegundoApellidos[i].substring(0, 1).toUpperCase()
					+ variosSegundoApellidos[i].substring(1).toLowerCase();
			formateoSegundoApellido += variosSegundoApellidos[i] + "-";
		}

		formateoSegundoApellido = formateoSegundoApellido.substring(0, formateoSegundoApellido.length() - 1).trim();
		return formateoSegundoApellido;
	}

	private String formatearPuestoTrabajo(String puestoTrabajo) {

		puestoTrabajo = puestoTrabajo.substring(0, 1).toUpperCase() + puestoTrabajo.substring(1).toLowerCase();

		return puestoTrabajo;
	}

	private String crearCorreoElectronico(String nombre, String primerApellido, String segundoApellido) {

		correoElectronico = this.nombre.substring(0, 1) + this.primerApellido.substring(0, 1).toLowerCase()
				+ this.segundoApellido.substring(0, 1).toLowerCase() + "@cenec.es";
		return correoElectronico;
	}

	private String crearContraseña() {

		String contraseña = null;
		String[] abecedario = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "Ñ", "O", "P",
				"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

		String primeraLetra, segundaLetra, contraseñaNumeros;
		Random passwordAleatorio = new Random();
		contraseñaNumeros = String.format("%06d", passwordAleatorio.nextInt(1000000));
		primeraLetra = abecedario[passwordAleatorio.nextInt(27)];
		segundaLetra = abecedario[passwordAleatorio.nextInt(27)].toLowerCase();
		contraseña = primeraLetra + contraseñaNumeros + segundaLetra;

		return contraseña;

	}

	public int calcularEdadAños() {

		int edadAnio = 0;
		LocalDate fechaActual = LocalDate.now();
		LocalDate fechaNac = LocalDate.of(this.fechaNacimiento.getAño(), this.fechaNacimiento.getMes(),
				this.fechaNacimiento.getDia());

		edadAnio = fechaNac.until(fechaActual).getYears();

		return edadAnio;
	}

	public int calcularEdadMeses() {

		int edadMes;
		LocalDate fechaActual = LocalDate.now();
		LocalDate fechaNac = LocalDate.of(this.fechaNacimiento.getAño(), this.fechaNacimiento.getMes(),
				this.fechaNacimiento.getDia());

		edadMes = (fechaNac.until(fechaActual).getYears() * 12) + fechaNac.until(fechaActual).getMonths();

		return edadMes;
	}

	public int calcularEdadDias() {

		int edadDia;
		LocalDate fechaActual = LocalDate.now();
		LocalDate fechaNac = LocalDate.of(this.fechaNacimiento.getAño(), this.fechaNacimiento.getMes(),
				this.fechaNacimiento.getDia());

		edadDia = (fechaNac.until(fechaActual).getYears() * 365) + (fechaNac.until(fechaActual).getMonths() * 30)
				+ fechaNac.until(fechaActual).getDays();

		return edadDia;
	}

	@Override
	public String toString() {
		return "Nombre completo = " + nombre + " " + primerApellido + segundoApellido + ", dni = " + dniNie
				+ ". Fecha de nacimiento = " + fechaNacimiento.toString() + " (" + calcularEdadAños() + " años, "
				+ calcularEdadMeses() + " meses, " + calcularEdadDias() + " días). Telefono = " + telefono
				+ ". Correo electrónico = " + correoElectronico + " . Puesto de trabajo = " + puestoTrabajo
				+ " . Y la contraseña es = " + contraseña + " .";
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getDniNie() {
		return dniNie;
	}

	public void setDniNie(String dniNie) {
		this.dniNie = dniNie;
	}

	public Fecha getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Fecha fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getPuestoTrabajo() {
		return puestoTrabajo;
	}

	public void setPuestoTrabajo(String puestoTrabajo) {
		this.puestoTrabajo = puestoTrabajo;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

}
