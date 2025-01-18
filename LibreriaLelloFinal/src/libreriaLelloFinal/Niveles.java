package libreriaLelloFinal;

import java.util.ArrayList;

public abstract class Niveles {
	protected ArrayList<Estanteria> estanterias = new ArrayList<>();
	// protected ArrayList<Exposicion> exposicion = new ArrayList<>();

	public abstract void agregarEstanteria(Estanteria estanteria);
}
