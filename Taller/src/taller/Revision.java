package taller;

public class Revision extends Trabajo {

	private static final long serialVersionUID = 1L;
	private final double INCREMENTOREVISION = 30;

	public Revision(double hora) {
		super(hora);
		super.setPrecioFinal(calcularImporte());
	}

	@Override
	public double calcularImporte() {
		return (super.getMANO_OBRA_HORA() * super.getHora()) + INCREMENTOREVISION;
	}

	@Override
	public String toString() {
		return super.toString() + "| Trabajo: REVISION | Horas trabajadas: " + super.getHora()
				+ " | El coste actual es de : " + super.getPrecioFinal() + " | Tipo trabajo : REVISION";
	}

	public double getINCREMENTOREVISION() {
		return INCREMENTOREVISION;
	}

}
