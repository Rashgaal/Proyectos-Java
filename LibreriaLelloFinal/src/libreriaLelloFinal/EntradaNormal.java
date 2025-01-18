package libreriaLelloFinal;

public class EntradaNormal extends Entrada {

	public EntradaNormal() {
		this.precioEntrada = 8;
	}

	@Override
	public String toString() {
		return super.toString() + " - Tipo: Normal. Coste: 8€";
	}

}
