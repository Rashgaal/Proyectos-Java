package libreriaLelloFinal;

import java.util.ArrayList;

public class Reponedor extends Trabajador {

	private static ArrayList<Estanteria> estanterias = new ArrayList<>();

	public Reponedor(String nombre, String apellido1, String apellido2, String dni, String email, int telefono) {
		super(nombre, apellido1, apellido2, dni, email, telefono);
	}

	public Reponedor() {

	}

	@Override
	public String toString() {
		return "Reponedor " + super.toString();
	}

	public static void agregarLibroEstanteria() {
		String idEstanteria = MainLibreria.leerCadena("Ingrese el ID de la estantería: ");
		Estanteria estanteria = buscarEstanteria(idEstanteria);

		if (estanteria != null) {
			if (estanteria.getLibros().size() < estanteria.getCapacidad()) {
				String titulo = MainLibreria.leerCadena("Ingrese el título del libro: ");
				String autor = MainLibreria.leerCadena("Ingrese el autor del libro: ");
				String isbn = MainLibreria.leerCadena("Ingrese el ISBN del libro: ");
				String edicion = MainLibreria.leerCadena("Ingrese la edición del libro: ");
				String editorial = MainLibreria.leerCadena("Ingrese la editorial del libro: ");
				double precio = Double.parseDouble(MainLibreria.leerCadena("Ingrese el precio del libro: "));
				String genero = MainLibreria.leerCadena("Ingrese el género del libro: ");
				String idioma = MainLibreria.leerCadena("Ingrese el idioma del libro: ");
				int stock = Integer.parseInt(MainLibreria.leerCadena("Ingrese la cantidad en stock del libro: "));

				Libro nuevoLibro = new Libro(titulo, autor, isbn, edicion, editorial, precio, genero, idioma, stock);

				if (estanteria.agregarLibro(nuevoLibro)) {
					System.out.println("Libro agregado a la estantería");
				} else {
					System.out.println("No se pudo agregar el libro. La estantería está llena.");
				}
			} else {
				System.out.println("La estantería está llena.");
			}
		} else {
			System.out.println("Estantería no encontrada.");
		}
	}

	public static void modificarLibroEstanteria() {
		String idEstanteria = MainLibreria.leerCadena("Ingrese el ID de la estantería: ");
		Estanteria estanteria = buscarEstanteria(idEstanteria);

		if (estanteria != null) {
			String isbn = MainLibreria.leerCadena("Ingrese el ISBN del libro a modificar: ");
			Libro libro = estanteria.buscarLibro(isbn);

			if (libro != null) {
				System.out.println("Libro encontrado. Ingrese los nuevos datos.");
				String nuevoTitulo = MainLibreria.leerCadena("Ingrese el nuevo título del libro: ");
				String nuevoAutor = MainLibreria.leerCadena("Ingrese el nuevo autor del libro: ");
				String nuevaEdicion = MainLibreria.leerCadena("Ingrese la nueva edición del libro: ");
				String nuevaEditorial = MainLibreria.leerCadena("Ingrese la nueva editorial del libro: ");
				double nuevoPrecio = Double.parseDouble(MainLibreria.leerCadena("Ingrese el nuevo precio del libro: "));
				String nuevoGenero = MainLibreria.leerCadena("Ingrese el nuevo género del libro: ");
				String nuevoIdioma = MainLibreria.leerCadena("Ingrese el nuevo idioma del libro: ");
				int nuevoStock = Integer
						.parseInt(MainLibreria.leerCadena("Ingrese la nueva cantidad en stock del libro: "));

				libro.setTituloLibro(nuevoTitulo);
				libro.setAutorLibro(nuevoAutor);
				libro.setEdicionLibro(nuevaEdicion);
				libro.setEditorialLibro(nuevaEditorial);
				libro.setPrecioLibro(nuevoPrecio);
				libro.setGeneroLibro(nuevoGenero);
				libro.setIdiomaLibro(nuevoIdioma);
				libro.setStockLibro(nuevoStock);

				System.out.println("Libro modificado.");
			} else {
				System.out.println("Libro no encontrado.");
			}
		} else {
			System.out.println("Estantería no encontrada.");
		}
	}

	public static void eliminarLibroEstanteria() {
		String idEstanteria = MainLibreria.leerCadena("Ingrese el ID de la estantería: ");
		Estanteria estanteria = buscarEstanteria(idEstanteria);

		if (estanteria != null) {
			String isbn = MainLibreria.leerCadena("Ingrese el ISBN del libro a eliminar: ");

			if (estanteria.eliminarLibro(isbn)) {
				System.out.println("Libro eliminado de la estantería.");
			} else {
				System.out.println("Libro no encontrado.");
			}
		} else {
			System.out.println("Estantería no encontrada.");
		}
	}

	public static Estanteria buscarEstanteria(String idEstanteria) {
		for (Estanteria estanteria : estanterias) {
			if (estanteria.getIdEstanteria().equals(idEstanteria)) {
				return estanteria;
			}
		}
		return null;
	}

	public static void reponerMerchandising(ArrayList<Merchandising> lista) {

		int CAPACIDAD_MAXIMA = 100;
		for (Merchandising m : lista) {
			if (m.getStock() != CAPACIDAD_MAXIMA) {
				m.stockProducto += 30;
			}

			if (m.getStock() > CAPACIDAD_MAXIMA) {
				m.setStock(CAPACIDAD_MAXIMA);
			}
		}
	}
}
