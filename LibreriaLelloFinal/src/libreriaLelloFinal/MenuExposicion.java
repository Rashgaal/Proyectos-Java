package libreriaLelloFinal;

import java.util.ArrayList;
import java.util.ListIterator;

public class MenuExposicion {

	public static int mostrarMenu() {
		System.out.println("1 - Modificar la exposición"); // GENERA NUEVA EXPOSICIÓN QUE REEMPLAZA A LA ANTIGUA
		System.out.println("2 - Reponer merchandising"); // + 30 EN TODO EL MERCHANDISING
		System.out.println("3 - Retirar merchandising"); // ELIMINA UN OBJETO CONCRETO
		System.out.println("4 - Ver el libro temático de la exposición"); // DICE EL LIBRO DE LA EXPOSICIÓN
		System.out.println("5 - Ver el merchandising disponible de la exposición"); // LISTA EL MERCHA ACTUAL DE ESA
																					// EXPOSICIÓN
		System.out.println("6 - Volver al menú principal");
		return Integer.parseInt(MainLibreria.leerCadena("Marca la opcion que quieres hacer"));
	}

	public static void eleccionMenu(int e, Exposiciones exposicion) {
		do {
			switch (e) {
			case 1:
				exposicion = modificarExposicionActual();
				break;
			case 2:
				reponerMerchandising(exposicion);
				break;
			case 3:
				eliminarMerchandisingDeLaExposicion(exposicion);
				break;
			case 4:
				verTematica(exposicion);
				break;
			case 5:
				verMerchandisingDeLaExposicion(exposicion);
				break;
			case 6:
				System.out.println("Volviendo al menú principal ...");
				break;
			default:
				System.out.println("Opción no válida");
				break;
			}

			if (e != 6) { // provisional que funciona
				e = mostrarMenu();
			}

		} while (e != 6);

		MainLibreria.exposicionOficial = exposicion; // tras todas las operaciones, cambio la exposición del main
														// oficial

	}

	public static Exposiciones modificarExposicionActual() { // Genera una nueva exposición
		Exposiciones exposicion = Exposiciones.generarNuevaExposicion();
		return exposicion;
	}

	public static void reponerMerchandising(Exposiciones exposicion) {
		ArrayList<Merchandising> lista = exposicion.getMerchandising();
		Reponedor.reponerMerchandising(lista); // Llamada a Reponedor
	}

	public static Merchandising encontrarObjetoMerchandisingPorId(Exposiciones exposicion) {
		Merchandising m = new Merchandising(); // creo Objeto para después devolverlo
		ArrayList<Merchandising> lista = exposicion.getMerchandising();
		int id = Integer.parseInt(MainLibreria.leerCadena("Ingresa la ID del objeto que quieras borrar:"));
		for (Merchandising objeto : lista) {
			if (id == objeto.getId()) {
				m = objeto;
			}
		}
		return m;
	}

	public static void eliminarMerchandisingDeLaExposicion(Exposiciones exposicion) {
		ArrayList<Merchandising> lista = exposicion.getMerchandising();
		ListIterator<Merchandising> iterador = lista.listIterator();
		Merchandising objetoBorrable = encontrarObjetoMerchandisingPorId(exposicion);

		if (objetoBorrable != null) { // si existe el id ingresado:
			Merchandising m;
			while (iterador.hasNext()) {
				m = iterador.next();
				if (objetoBorrable.getId() == m.getId()) {
					iterador.remove();
				}
			}
		}

	}

	public static void verTematica(Exposiciones exposicion) {
		System.out.println("Libro con temática de " + exposicion.getTematica());
	}

	public static void verMerchandisingDeLaExposicion(Exposiciones exposicion) {
		ArrayList<Merchandising> lista = exposicion.getMerchandising();
		for (Merchandising objeto : lista) {
			objeto.mostrarDatos();
		}
	}

}
