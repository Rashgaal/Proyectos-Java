package guerra;

import java.util.Random;

public abstract class Guerrillero {

	protected String nombre;
	protected int vida;
	protected int plantaEdificio;
	protected boolean vivo = true;

	public Guerrillero(String nombre) {
		this.nombre = nombre;
		this.vida = 9;
		this.plantaEdificio = plantaInicial();
		this.vivo = true;
	}

	public static int plantaInicial() {
		int dondeComienza;
		Random inicio = new Random();
		dondeComienza = inicio.nextInt(10) + 1;
		return dondeComienza;
	}

	public void marcarMuerto() {
		this.vivo = false;
		this.vida = 0;
	}

	@Override
	public String toString() {
		return "\033[33m🏃\033[37m " + nombre + "\t \033[31m🖤\033[37m = " + vida + "   \033[30m🏢\033[37m = " + plantaEdificio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public int getPlantaEdificio() {
		return plantaEdificio;
	}

	public void setPlantaEdificio(int plantaEdificio) {
		this.plantaEdificio = plantaEdificio;
	}

	public boolean isVivo() {
		return vivo;
	}

	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}

}
