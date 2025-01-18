package libreriaLelloFinal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainLibreria {

	static ArrayList<Libro> librosOficiales = CargarTiendaBasica.generarLibrosIniciales();
	static ArrayList<Merchandising> merchaOficial = CargarTiendaBasica.generarMerchaInicial();
	static ArrayList<Exposiciones> listaExposiciones = CargarTiendaBasica.variasExposiciones(merchaOficial);
	static Exposiciones exposicionOficial = listaExposiciones.get(0); // coger El Principito

	public static void main(String[] args) {
		int eleccionMenu;
		do {
			eleccionMenu = MenuInicial();
			opcionesMenuInicial(eleccionMenu);
		} while (eleccionMenu != 6);
	}

	public static int MenuInicial() {
		System.out.println("1 - Menú gestión trabajadores");
		System.out.println("2 - Menú gestión estanterias");
		System.out.println("3 - Menú gestión exposicion");
		System.out.println("4 - Menú gestión compras");
		System.out.println("5 - Menú gestión entradas");
		System.out.println("6 - Salir");

		return leerEntero("Marca la opcion que quieres hacer");
	}

	public static void opcionesMenuInicial(int e) {
		switch (e) {
		case 1:
			int eleccion;
			do {
				eleccion = MenuEmpleados.mostrarMenu();
				MenuEmpleados.eleccionMenu(eleccion);
			} while (eleccion != 8);
			break;
		case 2:
			int opcion;
			do {
				opcion = MenuEstanterias.mostrarMenu();
				MenuEstanterias.eleccionMenu(opcion);
			} while (opcion != 7);
			break;
		case 3:
			int opcion3 = 0;
			opcion3 = MenuExposicion.mostrarMenu();
			MenuExposicion.eleccionMenu(opcion3, exposicionOficial);
			break;
		case 4:
			MenuCaja.mostrarMenuCaja();
			break;
		case 5:
			MenuEntrada.mostrarMenuEntrada();
			break;
		case 6:
			System.out.println("Saliendo del sistema");
			break;
		default:
			System.out.println("Debes introducir el número de la opcion que deseas realizar");
			break;
		}
	}

	public static String leerCadena(String pregunta) {
		BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
		String respuesta = null;
		try {
			System.out.println(pregunta);
			respuesta = leer.readLine();
		} catch (IOException e) {
			System.err.println("Error de entrada/salida.");
		}
		return respuesta;
	}

	public static int leerEntero(String pregunta) {
		while (true) {
			try {
				return Integer.parseInt(leerCadena(pregunta));
			} catch (NumberFormatException e) {
				System.out.println("Entrada inválida. Por favor, introduce un número entero.");
			}
		}
	}

}
