package taller;

public class ChapaPintura extends Reparacion {

	private static final long serialVersionUID = 1L;

	public ChapaPintura(double hora, double precioMateriales) {
		super(hora, precioMateriales);
		super.setPrecioFinal(calcularImporte());
	}

	@Override
	public double calcularImporte() {
		return ((super.getHora() * super.getMANO_OBRA_HORA()) + super.getPrecioMateriales()) * 1.30;
	}

	@Override
	public String toString() {
		return super.toString() + " | Tipo reparacion: CHAPA Y PINTURA";
	}

}
