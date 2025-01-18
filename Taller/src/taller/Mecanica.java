package taller;

public class Mecanica extends Reparacion {

	private static final long serialVersionUID = 1L;

	public Mecanica(double hora, double precioMateriales) {
		super(hora, precioMateriales);
		setPrecioFinal(calcularImporte());
	}

	public double calcularImporte() {
		return ((super.getHora() * super.getMANO_OBRA_HORA()) + super.getPrecioMateriales()) * 1.10;
	}

	@Override
	public String toString() {
		return super.toString() + " | Tipo reparacion: MECANICA";
	}

}
