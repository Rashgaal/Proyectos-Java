package taller;

public abstract class Reparacion extends Trabajo {

	private static final long serialVersionUID = 1L;
	private double precioMateriales;

	public Reparacion(double hora, double precioMateriales) {
		super(hora);
		this.precioMateriales = precioMateriales;
	}

	@Override
	public abstract double calcularImporte();

	@Override
	public String toString() {
		return super.toString() + "| Trabajo: REPARACION | Horas trabajadas: " + super.getHora()
				+ " | Precio Materiales: " + precioMateriales + " | El coste actual es de : " + super.getPrecioFinal();
	}

	public double getPrecioMateriales() {
		return precioMateriales;
	}

	public void setPrecioMateriales(double precioMateriales) {
		this.precioMateriales = precioMateriales;
	}

}
