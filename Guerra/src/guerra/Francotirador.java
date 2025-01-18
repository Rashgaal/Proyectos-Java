package guerra;

import java.util.Random;

public class Francotirador extends Guerrillero {

	private int danho;

	public Francotirador(String nombre) {
		super(nombre);
		this.danho = danhoFrancotirador();
	}

	public static int danhoFrancotirador() {

		int danhoArma;
		Random danhoFrancotirador = new Random();

		return danhoArma = danhoFrancotirador.nextInt(9) + 1;
	}

	@Override
	public String toString() {
		return super.toString() + "   \033[35m🔫\033[37m = " + danho + " .";
	}

	public int getDanho() {
		return danho;
	}

	public void setDanho(int danho) {
		this.danho = danho;
	}

}