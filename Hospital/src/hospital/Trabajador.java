package hospital;

import java.sql.Date;

public class Trabajador {

	private int idTrabajador;
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	private double salario;
	private int idEspecialidad;

	public Trabajador() {

	}

	public Trabajador(String nombre, String apellido, Date fechaNacimiento, double salario, int idEspecialidad) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.salario = salario;
		this.idEspecialidad = idEspecialidad;
	}

	@Override
	public String toString() {
		return "Trabajador --> " + idTrabajador + " | Nombre: " + nombre + " | Apellido: " + apellido
				+ " | Fecha de nacimiento: " + fechaNacimiento + " | Salario: " + salario
				+ " | Adjunto a la especialidad" + idEspecialidad;
	}

	public int getIdTrabajador() {
		return idTrabajador;
	}

	public void setIdTrabajador(int idTrabajador) {
		this.idTrabajador = idTrabajador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public int getIdEspecialidad() {
		return idEspecialidad;
	}

	public void setIdEspecialidad(int idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}

}
