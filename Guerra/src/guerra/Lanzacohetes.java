package guerra;

import java.util.Random;

public class Lanzacohetes extends Guerrillero {

	private int alcance;

	public Lanzacohetes(String nombre) {

		super(nombre);
		this.alcance = alcanceLanzacohetes();
	}

	public static int alcanceLanzacohetes() {

		int valorAlcance;
		Random alcanceLanzacohetes = new Random();

		return valorAlcance = alcanceLanzacohetes.nextInt(3) + 1;
	}

	@Override
	public String toString() {
		return super.toString() + "   \033[36m➚\033[37m = " + alcance + " .";
	}

	public int getAlcance() {
		return alcance;
	}

	public void setAlcance(int alcance) {
		this.alcance = alcance;
	}

}
