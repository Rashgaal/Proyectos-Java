package taller;

import java.io.Serializable;

public abstract class Trabajo implements Serializable {

	private static final long serialVersionUID = 1L;
	private final double MANO_OBRA_HORA = 45;
	private static int id = 0;
	private int idTotal;
	private boolean terminado = false;
	private double hora;
	private static int trabajoLista = 0;
	private int numeroTrabajoLista;
	private double precioFinal;

	public Trabajo(double hora) {

		if (trabajoLista <= 99) {
			trabajoLista++;
			this.numeroTrabajoLista = trabajoLista;
		} else {
			trabajoLista = 1;
			this.numeroTrabajoLista = trabajoLista;
		}
		id++;
		this.idTotal = id;
		this.hora = hora;
		this.precioFinal = calcularImporte();
	}

	public abstract double calcularImporte();

	@Override
	public String toString() {
		if (terminado) {
			return "Trabajo numero: " + numeroTrabajoLista + " | ID: " + idTotal + " | Trabajo terminado.";
		} else {
			return "Trabajo numero: " + numeroTrabajoLista + " | ID: " + idTotal + " | No terminado.";
		}
	}

	public int getId() {
		return id;
	}

	public static void setId(int id) {
		Trabajo.id = id;
	}

	public int getIdTotal() {
		return idTotal;
	}

	public void setIdTotal(int idTotal) {
		this.idTotal = idTotal;
	}

	public boolean isTerminado() {
		return terminado;
	}

	public void setTerminado(boolean terminado) {
		this.terminado = terminado;
	}

	public double getHora() {
		return hora;
	}

	public void setHora(double hora) {
		this.hora = hora;
	}

	public static int getTrabajoLista() {
		return trabajoLista;
	}

	public static void setTrabajoLista(int trabajoLista) {
		Trabajo.trabajoLista = trabajoLista;
	}

	public int getNumeroTrabajoLista() {
		return numeroTrabajoLista;
	}

	public void setNumeroTrabajoLista(int numeroTrabajoLista) {
		this.numeroTrabajoLista = numeroTrabajoLista;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public double getPrecioFinal() {
		return precioFinal;
	}

	public void setPrecioFinal(double precioFinal) {
		this.precioFinal = precioFinal;
	}

	public double getMANO_OBRA_HORA() {
		return MANO_OBRA_HORA;
	}

}
