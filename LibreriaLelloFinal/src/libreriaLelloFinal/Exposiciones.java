package libreriaLelloFinal;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Exposiciones implements VerDatos {

	private String nombre;
	private int capacidad;
	private String tematica;
	private ArrayList<Merchandising> merchandising;
	private Date inicio;
	private Date fin;

	public Exposiciones(String nombre, int capacidad, String tematica, ArrayList<Merchandising> merchandising, Date inicio,
			Date fin) {
		this.nombre = nombre;
		this.capacidad = capacidad;
		this.tematica = tematica;
		this.merchandising = merchandising;
		this.inicio = inicio;
		this.fin = fin;
	}

	public Exposiciones() {

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	
	public String getTematica() {
		return tematica;
	}

	public void setTematica(String tematica) {
		this.tematica = tematica;
	}

	public ArrayList<Merchandising> getMerchandising() {
		return merchandising;
	}

	public void setMerchandising(ArrayList<Merchandising> merchandising) {
		this.merchandising = merchandising;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	@Override
	public void mostrarDatos() {
		System.out.println("Nombre de la exposición: " + nombre);
		System.out.println("Capacidad de la exposición: " + capacidad);
		System.out.println("Libro de la temática: " + tematica);
		System.out.println("Merchandising disponible:");
		for (Merchandising objeto : merchandising) {
			objeto.mostrarDatos();
		}
		System.out.println("Fecha de inicio de la exposición: " + inicio);
		System.out.println("Fecha de fin de la exposición: " + fin);
	}

	public static Exposiciones generarNuevaExposicion() {
		ArrayList<Exposiciones> listaExposiciones = MainLibreria.listaExposiciones;
        Random rand = new Random();
        Exposiciones expoAleatoria = listaExposiciones.get(rand.nextInt(listaExposiciones.size()));

        Exposiciones nuevaExpo = new Exposiciones(
            expoAleatoria.getNombre(),
            expoAleatoria.getCapacidad(),
            expoAleatoria.getTematica(),
            expoAleatoria.getMerchandising(),
            expoAleatoria.getInicio(),
            expoAleatoria.getFin()
        );

        return nuevaExpo;
    }
}
