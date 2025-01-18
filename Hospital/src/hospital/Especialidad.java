package hospital;

public class Especialidad {

	private int idEspecialidad;
	private String nombre;
	private Orientacion orientacion;
	private int idDepartamento;

	public Especialidad() {

	}

	public Especialidad(String nombre, Orientacion orientacion, int idDepartamento) {
		this.nombre = nombre;
		this.orientacion = orientacion;
		this.idDepartamento = idDepartamento;
	}

	@Override
	public String toString() {
		return "Especialidad --> " + idEspecialidad + " | Nombre: " + nombre + " | Orientacion: " + orientacion
				+ " | Adjunto al departamento: " + idDepartamento;
	}

	public int getIdEspecialidad() {
		return idEspecialidad;
	}

	public void setIdEspecialidad(int idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Orientacion getOrientacion() {
		return orientacion;
	}

	public void setOrientacion(Orientacion orientacion) {
		this.orientacion = orientacion;
	}

	public int getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(int idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

}
