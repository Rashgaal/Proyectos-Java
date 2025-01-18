package libreriaLelloFinal;

import java.util.ArrayList;
import java.util.Random;

public class MenuEstanterias {

	private static ArrayList<Estanteria> estanterias = new ArrayList<>();

	public static int mostrarMenu() {
		crearEstanteriaPorIdioma("Español", 99); // Estantería para probar límite de libros
		crearEstanteriaPorIdioma("Portugués", 50);
		crearEstanteriaPorIdioma("Inglés", 50);
		crearEstanteriaPorIdioma("Francés", 50);
		crearEstanteriaPorIdioma("Alemán", 50);
		crearEstanteriaPorIdioma("Italiano", 50);

		System.out.println("---Menú Gestión de Estanterías---");
		System.out.println("1 - Agregar Libro");
		System.out.println("2 - Eliminar Libro");
		System.out.println("3 - Listar Todos los Libros");
		System.out.println("4 - Actualizar Información del Libro");
		System.out.println("5 - Verificar Disponibilidad");
		System.out.println("6 - Filtrar Libros");
		System.out.println("7 - Volver al Menú Principal");

		return MainLibreria.leerEntero("Marca la opción que deseas realizar");
	}

	public static void eleccionMenu(int eleccion) {
		boolean volverAlMenu = true;

		while (volverAlMenu) {
			switch (eleccion) {

			case 1:
				System.out.println("Has elegido 'Agregar Libro'");
				agregarLibroAEstanteria();
				break;
			case 2:
				System.out.println("Has elegido 'Eliminar Libro'");
				eliminarLibroDeEstanteria();
				break;
			case 3:
				System.out.println("Has elegido 'Listar Todos los Libros'");
				listarLibrosEnEstanteria();
				break;
			case 4:
				System.out.println("Has elegido 'Actualizar Información del Libro'");
				modificarLibroEnEstanteria();
				break;
			case 5:
				System.out.println("Has elegido 'Verificar Disponibilidad'");
				verificarDisponibilidadDeLibro();
				break;
			case 6:
				System.out.println("Has elegido 'Filtrar Libros'");
				filtrarLibrosSubMenu();
				break;
			case 7:
				System.out.println("Volviendo al menú principal");
				volverAlMenu = false;
				break;
			default:
				System.out.println("Opción inválida. Por favor, inténtelo de nuevo.");
				break;
			}

			if (volverAlMenu) {
				eleccion = mostrarMenu();
			}
		}
	}

	public static void crearEstanteriaPorIdioma(String idioma, int cantidadLibros) {
		Estanteria estanteria = new Estanteria("estanteria-" + idioma.toLowerCase(), 100);
		estanterias.add(estanteria);
		generarLibrosAleatorios(estanteria, cantidadLibros, idioma);
	}

	public static void agregarLibroAEstanteria() {
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
				int stock = MainLibreria.leerEntero("Ingrese la cantidad en stock del libro: ");

				Libro nuevoLibro = new Libro(titulo, autor, isbn, edicion, editorial, precio, genero, idioma, stock);

				estanteria.agregarLibro(nuevoLibro);
				System.out.println("Libro agregado a la estantería");

			} else {
				System.out.println("La estantería está llena.");
			}
		} else {
			System.out.println("Estantería no encontrada.");
		}
	}

	public static void eliminarLibroDeEstanteria() {
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

	public static void listarLibrosEnEstanteria() {
		String idEstanteria = MainLibreria.leerCadena("Ingrese el ID de la estantería: ");
		Estanteria estanteria = buscarEstanteria(idEstanteria);

		if (estanteria != null) {
			estanteria.listarLibros();
		} else {
			System.out.println("Estantería no encontrada.");
		}
	}

	public static void modificarLibroEnEstanteria() {
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
				int nuevoStock = MainLibreria.leerEntero("Ingrese la nueva cantidad en stock del libro: ");

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

	public static void verificarDisponibilidadDeLibro() {
		String idEstanteria = MainLibreria.leerCadena("Ingrese el ID de la estantería: ");
		Estanteria estanteria = buscarEstanteria(idEstanteria);

		if (estanteria != null) {
			String isbn = MainLibreria.leerCadena("Ingrese el ISBN del libro a verificar disponibilidad: ");
			Libro libro = estanteria.buscarLibro(isbn);

			if (libro != null) {
				System.out.println("Stock disponible del libro: " + libro.getStockLibro());
			} else {
				System.out.println("Libro no encontrado.");
			}
		} else {
			System.out.println("Estantería no encontrada.");
		}
	}

	public static void filtrarLibrosSubMenu() {
		boolean volverAlMenu = true;

		while (volverAlMenu) {
			System.out.println("Elija un criterio de filtrado:");
			System.out.println("1 - Filtrar por Idioma");
			System.out.println("2 - Filtrar por Título");
			System.out.println("3 - Filtrar por Autor");
			System.out.println("4 - Filtrar por Editorial");
			System.out.println("5 - Filtrar por Género");
			System.out.println("6 - Volver al Menú Principal");

			int opcionFiltro = MainLibreria.leerEntero("Seleccione una opción:");

			switch (opcionFiltro) {
			case 1:
				System.out.println("Has elegido 'Filtrar por Idioma'");
				filtrarLibros("idioma");
				break;
			case 2:
				System.out.println("Has elegido 'Filtrar por Título'");
				filtrarLibros("titulo");
				break;
			case 3:
				System.out.println("Has elegido 'Filtrar por Autor'");
				filtrarLibros("autor");
				break;
			case 4:
				System.out.println("Has elegido 'Filtrar por Editorial'");
				filtrarLibros("editorial");
				break;
			case 5:
				System.out.println("Has elegido 'Filtrar por Género'");
				filtrarLibros("genero");
				break;
			case 6:
				System.out.println("Volviendo al menú principal");
				volverAlMenu = false;
				break;
			default:
				System.out.println("Opción inválida. Por favor, inténtelo de nuevo.");
				break;
			}
		}
	}

	public static void filtrarLibros(String criterio) {
		String idEstanteria = MainLibreria.leerCadena("Ingrese el ID de la estantería: ");
		Estanteria estanteria = buscarEstanteria(idEstanteria);

		if (estanteria != null) {
			String valor = MainLibreria.leerCadena("Ingrese el " + criterio + " de los libros a listar: ");
			for (Libro libro : estanteria.getLibros()) {
				switch (criterio) {
				case "idioma":
					if (libro.getIdiomaLibro().equalsIgnoreCase(valor)) {
						System.out.println(libro);
					}
					break;
				case "titulo":
					if (libro.getTituloLibro().equalsIgnoreCase(valor)) {
						System.out.println(libro);
					}
					break;
				case "autor":
					if (libro.getAutorLibro().equalsIgnoreCase(valor)) {
						System.out.println(libro);
					}
					break;
				case "editorial":
					if (libro.getEditorialLibro().equalsIgnoreCase(valor)) {
						System.out.println(libro);
					}
					break;
				case "genero":
					if (libro.getGeneroLibro().equalsIgnoreCase(valor)) {
						System.out.println(libro);
					}
					break;
				default:
					System.out.println("Criterio de filtrado inválido.");
					break;
				}
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

	public static void generarLibrosAleatorios(Estanteria estanteria, int cantidad, String idioma) {
		Random random = new Random();
		String[] titulos = { "Don Quijote de la Mancha", "Crimen y castigo", "Orgullo y prejuicio",
				"Las aventuras de Tom Sawyer", "Matar a un ruiseñor", "La metamorfosis", "El guardián entre el centeno",
				"Cien años de soledad", "El retrato de Dorian Gray", "La divina comedia", "La isla del tesoro",
				"El corazón de las tinieblas", "Robinson Crusoe", "Guerra y paz", "El gran Gatsby",
				"La vuelta al mundo en 80 días", "Los miserables", "La guerra de los mundos", "Drácula",
				"Frankenstein" };

		String[] autores = { "Miguel de Cervantes", "Fyodor Dostoevsky", "Jane Austen", "Mark Twain", "Harper Lee",
				"Franz Kafka", "J.D. Salinger", "Gabriel García Márquez", "Oscar Wilde", "Dante Alighieri",
				"Robert Louis Stevenson", "Joseph Conrad", "Daniel Defoe", "Lev Tolstoy", "F. Scott Fitzgerald",
				"Julio Verne", "Victor Hugo", "H.G. Wells", "Bram Stoker", "Mary Shelley" };

		String[] editoriales = { "Anaya", "Penguin", "Alianza", "Planeta", "Seix Barral", "Nova", "Random House",
				"DeBolsillo", "Vintage", "Alfaguara", "RBA", "Galaxia Gutenberg" };

		String[] generos = { "Ficción", "Drama", "Aventura", "Ciencia Ficción", "Clásico", "Fantasía", "Misterio",
				"Suspense", "Terror", "Romance", "Histórico", "Poesía" };

		for (int i = 0; i < cantidad; i++) {
			String titulo = titulos[random.nextInt(titulos.length)];
			String autor = autores[random.nextInt(autores.length)];
			String isbn = "ISBN" + (1000 + random.nextInt(9000));
			String edicion = "Edición " + (1 + random.nextInt(10));
			String editorial = editoriales[random.nextInt(editoriales.length)];
			double precio = 5 + (50 - 5) * random.nextDouble();
			String genero = generos[random.nextInt(generos.length)];
			int stock = 1 + random.nextInt(100);

			Libro libro = new Libro(titulo, autor, isbn, edicion, editorial, precio, genero, idioma, stock);
			estanteria.agregarLibro(libro);
		}
	}

}
