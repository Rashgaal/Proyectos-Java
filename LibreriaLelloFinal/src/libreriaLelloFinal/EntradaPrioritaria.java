package libreriaLelloFinal;

public class EntradaPrioritaria extends Entrada {

	public EntradaPrioritaria() {
		this.precioEntrada = 15;
	}

	@Override
	public String toString() {
		return super.toString() + " - Tipo: Prioritaria. Coste: 15€";
	}
}
