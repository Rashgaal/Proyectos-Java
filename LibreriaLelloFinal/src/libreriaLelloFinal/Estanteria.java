package libreriaLelloFinal;

import java.util.ArrayList;

public class Estanteria {
	private String idEstanteria;
	private int capacidad;
	private ArrayList<Libro> libros;

	public Estanteria(String idEstanteria, int capacidad) {
		this.idEstanteria = idEstanteria;
		this.capacidad = capacidad;
		this.libros = new ArrayList<>();
	}

	// GETTERS:

	public String getIdEstanteria() {
		return idEstanteria;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public ArrayList<Libro> getLibros() {
		return libros;
	}

	// MÉTODOS PARA TRABAJAR CON LOS LIBROS (agregar, eliminar, buscar y listar):

	@Override
	public String toString() {
		return "Estanteria [idEstanteria=" + idEstanteria + ", capacidad=" + capacidad + ", libros=" + libros + "]";
	}

	public boolean agregarLibro(Libro libro) {
		if (libros.size() < capacidad) {
			libros.add(libro);
			return true;
		}
		return false;
	}

	public boolean eliminarLibro(String isbn) {
		return libros.removeIf(libro -> libro.getIsbnLibro().equals(isbn));
	}

	public Libro buscarLibro(String isbn) {
		for (Libro libro : libros) {
			if (libro.getIsbnLibro().equals(isbn)) {
				return libro;
			}
		}
		return null;
	}

	public void listarLibros() {
		for (Libro libro : libros) {
			System.out.println(libro);
		}
	}

}
