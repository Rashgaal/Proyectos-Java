package hospital;

public class Departamento {

	private int idDepartamento;
	private String nombre;
	private int planta;

	public Departamento() {

	}

	public Departamento(String nombre, int planta) {
		this.nombre = nombre;
		this.planta = planta;
	}

	@Override
	public String toString() {
		return "Departamento --> " + idDepartamento + " | Nombre: " + nombre + " | Planta: " + planta;
	}

	public int getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(int idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPlanta() {
		return planta;
	}

	public void setPlanta(int planta) {
		this.planta = planta;
	}

}
