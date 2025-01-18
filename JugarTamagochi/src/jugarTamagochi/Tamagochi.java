package jugarTamagochi;

public class Tamagochi {

	private String nombre;
	private int hambre;
	private int sueno;
	private int higiene;
	private int diversion;
	public boolean vivo = true;

	public Tamagochi(String nombre, int hambre, int sueno, int higiene, int diversion, boolean vivo) {

		this.nombre = nombre;
		this.hambre = hambre;
		this.sueno = sueno;
		this.higiene = higiene;
		this.diversion = diversion;
		this.vivo = vivo;
	}

	public Tamagochi(String nombre, int hambre, int sueno, int higiene, int diversion) {

		this.nombre = nombre;
		this.hambre = hambre;
		this.sueno = sueno;
		this.higiene = higiene;
		this.diversion = diversion;
	}

	public Tamagochi() {
	}

	public void comer() {

		if (hambre >= 100) {
			hambre = 100;
			System.out.println(nombre + " está lleno y no puede comer más");
		} else {
			hambre = hambre + 20;
			sueno = sueno - 10;
			higiene = higiene - 10;
			diversion = diversion - 10;
			if (hambre > 100) {
				hambre = 100;
			}

			System.out.println(nombre + " ha comido.");

			estadoMuerto();

		}
	}

	public void dormir() {

		if (sueno > 100) {
			sueno = 100;
			System.out.println(nombre + " está completamente descansado y no puede dormir más");
		} else {
			sueno = sueno + 20;
			hambre = hambre - 10;
			higiene = higiene - 10;
			diversion = diversion - 10;
			if (sueno > 100) {
				sueno = 100;
			}

			System.out.println(nombre + " ha dormido.");

			estadoMuerto();

		}
	}

	public void banho() {

		if (higiene > 100) {
			higiene = 100;
			System.out.println(nombre + " está completamente limpio y no se puede bañar más");
		} else {
			higiene = higiene + 20;
			hambre = hambre - 10;
			sueno = sueno - 10;
			diversion = diversion - 10;
			if (higiene > 100) {
				higiene = 100;
			}

			System.out.println(nombre + " se ha limpiado.");

			estadoMuerto();

		}
	}

	public void jugar() {

		if (diversion > 100) {
			diversion = 100;
			System.out.println(nombre + " ha jugado mucho y no puede jugar más");
		} else {
			diversion = diversion + 20;
			hambre = hambre - 10;
			sueno = sueno - 10;
			higiene = higiene - 10;
			if (diversion > 100) {
				diversion = 100;
			}

			System.out.println(nombre + " ha jugado.");

			estadoMuerto();

		}
	}

	public void estadoMuerto() {

		if (hambre <= 0 || sueno <= 0 || higiene <= 0 || diversion <= 0) {

			hambre = 0;
			sueno = 0;
			higiene = 0;
			diversion = 0;
			vivo = false;

			System.out.println(nombre + " ha muerto.");
			System.out.println("   _______");
			System.out.println("  /       \\\\");
			System.out.println(" /         \\\\");
			System.out.println("|   R.I.P.  ||");
			System.out.println("    " + nombre);
			System.out.println("|           ||");
			System.out.println("|           ||");
			System.out.println("|___________||");

		}

	}

	@Override
	public String toString() {
		String estado;
		if (vivo == true) {
			estado = "Vivo";
		} else {
			estado = "Muerto";
		}
		return nombre + ": Hambre = " + hambre + ". Sueño = " + sueno + ". Higiene = " + higiene + ". Diversion = "
				+ diversion + " . Estado = " + estado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getHambre() {
		return hambre;
	}

	public void setHambre(int hambre) {
		this.hambre = hambre;
	}

	public int getSueno() {
		return sueno;
	}

	public void setSueno(int sueno) {
		this.sueno = sueno;
	}

	public int getHigiene() {
		return higiene;
	}

	public void setHigiene(int higiene) {
		this.higiene = higiene;
	}

	public int getDiversion() {
		return diversion;
	}

	public void setDiversion(int diversion) {
		this.diversion = diversion;
	}

	public boolean isVivo() {
		return vivo;
	}

	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}

}