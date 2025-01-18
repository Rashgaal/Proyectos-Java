package jugarTamagochi;

import java.util.Scanner;

public class PruebaTamagochi {

	private static Tamagochi[] conjuntoTamagochi = new Tamagochi[100];
	private static Scanner sc = new Scanner(System.in);
	private static Scanner sc2 = new Scanner(System.in);
	private static int contadorTamagochi = 0;
	private static String name;
	private static int hungry;
	private static int sleep;
	private static int higiene;
	private static int fun;
	private static boolean comprobar = true;

	// Estas variables estaticas solo se usa en caso de variables constantes, para
	// crear la clase, scanner y quizas contador//
	public static void main(String[] args) {

		int opcion;

		do {
			opcion = menu();
			eleccionMenu(opcion);
		} while (opcion != 5);

	}

	public static int menu() {

		int opcion = 0;

		System.out.println("********************************");
		System.out.println("*************MENÚ***************");
		System.out.println("| 1 = Crear un Tamagochi.");
		System.out.println("| 2 = Listado de Tamagochis.");
		System.out.println("| 3 = Interactuar con los Tamagochis.");
		System.out.println("| 4 = Alerta de estados.");
		System.out.println("| 5 = Salir del juego.");

		try {
			opcion = sc.nextInt();
		} catch (java.util.InputMismatchException e) {
			sc.next();
			System.out.println("Error.");
		}

		return opcion;

	}

	public static void eleccionMenu(int opcion) {

		switch (opcion) {
		case 1:
			try {
				crearTamagochi();
			} catch (java.util.InputMismatchException e) {
				sc2.next();
				System.out.println(
						"Algún valor que has introducido no se corresponde. Para nombre, alfanumérico: Charmander. Para estadísticas, numérico: 50.");
				comprobar = true;
			}
			break;
		case 2:
			listadoTamagochi();
			break;
		case 3:
			elegirTamagochi();
			break;
		case 4:
			alertaEstado();
			break;
		case 5:
			System.out.println("Saliendo del juego.");
			break;
		default:
			System.out.println("El dato introducido no se corresponde con ninguna opción.");
		}
	}

	public static void crearTamagochi() throws java.util.InputMismatchException {

		if (contadorTamagochi >= conjuntoTamagochi.length) {
			System.out.println("No se pueden añadir más Tamagochis.");
		} else {
			System.out.println("¿Cómo se llama el Tamagochi?");
			name = sc.next();
			do {

				System.out.println("Nivel de hambre.");
				hungry = sc2.nextInt();

				if (hungry > 100) {
					hungry = 100;
					System.out.println("Como has introducido un valor superior al máximo, se ha ajustado al máximo");
				} else if (hungry <= 0) {
					hungry = 50;
					System.out.println("Como has introducido un valor negativo, se ha ajustado a 50");
				}
				System.out.println("Nivel de sueño.");
				sleep = sc2.nextInt();
				if (sleep > 100) {
					sleep = 100;
					System.out.println("Como has introducido un valor superior al máximo, se ha ajustado al máximo");
				} else if (sleep <= 0) {
					sleep = 50;
					System.out.println("Como has introducido un valor negativo, se ha ajustado a 50");
				}
				System.out.println("Nivel de higiene.");
				higiene = sc2.nextInt();
				if (higiene > 100) {
					higiene = 100;
					System.out.println("Como has introducido un valor superior al máximo, se ha ajustado al máximo");
				} else if (higiene <= 0) {
					higiene = 50;
					System.out.println("Como has introducido un valor negativo, se ha ajustado a 50");
				}
				System.out.println("Nivel de diversion.");
				fun = sc2.nextInt();
				if (fun > 100) {
					fun = 100;
					System.out.println("Como has introducido un valor superior al máximo, se ha ajustado al máximo");
				} else if (fun <= 0) {
					fun = 50;
					System.out.println("Como has introducido un valor negativo, se ha ajustado a 50");
				}
				comprobar = false;

			} while (comprobar == true);
			Tamagochi bicho = new Tamagochi(name, hungry, sleep, higiene, fun);
			conjuntoTamagochi[contadorTamagochi] = bicho;
			contadorTamagochi++;
		}

	}

	public static void listadoTamagochi() {
		if (contadorTamagochi == 0) {
			System.out.println("No hay Tamagochis creados aún.");
		} else {
			for (int i = 0; i < contadorTamagochi; i++) {
				System.out.println(conjuntoTamagochi[i].toString());
			}
		}
	}

	public static void elegirTamagochi() {

		if (contadorTamagochi == 0) {
			System.out.println("No hay Tamagochis para interactuar.");
			return;
		} else {
			do {
				System.out.println("¿Con qué Tamagochi quieres interactuar?");
				for (int i = 0; i < contadorTamagochi; i++) {
					System.out.println(i + 1 + " " + conjuntoTamagochi[i].getNombre());
				}
				try {
					int seleccion = sc2.nextInt();

					if (seleccion > 0 && seleccion <= contadorTamagochi) {
						Tamagochi tamagochiSeleccionado = conjuntoTamagochi[seleccion - 1];
						accionTamagochi(tamagochiSeleccionado);
						comprobar = false;
					} else {
						System.out.println("Selección inválida.");
						comprobar = true;
					}

				} catch (java.util.InputMismatchException e) {
					sc2.next();
					System.out.println("Debe introducir el número del Tamagochi con el que quieres interactuar.");
				}
			} while (comprobar == true);
		}

	}

	public static void accionTamagochi(Tamagochi bicho) {

		int elegirAccion = 0;

		do {
			if (bicho.isVivo() == true) {
				System.out.println("********************************");
				System.out.println("| 1 = Dar de comer a " + bicho.getNombre() + ".");
				System.out.println("| 2 = Dormir a " + bicho.getNombre() + ".");
				System.out.println("| 3 = Limpiar a " + bicho.getNombre() + ".");
				System.out.println("| 4 = Jugar con " + bicho.getNombre() + ".");
				System.out.println("| 5 = Volver al menú principal.");

				try {
					elegirAccion = sc2.nextInt();
				} catch (java.util.InputMismatchException e) {
					sc2.next();
					System.out.println("Debe introducir el número de la acción que desea realizar.");
				}

				switch (elegirAccion) {

				case 1:
					bicho.comer();

					break;
				case 2:
					bicho.dormir();

					break;
				case 3:
					bicho.banho();

					break;
				case 4:
					bicho.jugar();

					break;
				case 5:
					System.out.println("Saliendo al menú principal.");
					break;
				default:
					System.out.println("Esa acción no se corresponde con las opciones.");
					break;
				}

			} else {

				System.out.println(bicho.getNombre() + " está muerto y no puede realizar ninguna acción.");

			}

		} while (elegirAccion != 5 && bicho.isVivo() == true);

	}

	public static void alertaEstado() {

		if (contadorTamagochi == 0) {
			System.out.println("No hay Tamagochis que analizar.");
		} else {
			for (int i = 0; i < contadorTamagochi; i++) {

				if (((conjuntoTamagochi[i].getHambre() <= 20) || (conjuntoTamagochi[i].getSueno() <= 20)
						|| (conjuntoTamagochi[i].getHigiene() <= 20) || (conjuntoTamagochi[i].getDiversion() <= 20))
						&& (conjuntoTamagochi[i].isVivo() == true)) {
					System.out.println(conjuntoTamagochi[i].getNombre()
							+ " tiene alguna estadística baja,puede morir pronto y se le va a atender.");
					System.out.println("Hambre = " + conjuntoTamagochi[i].getHambre());
					System.out.println("Sueño = " + conjuntoTamagochi[i].getSueno());
					System.out.println("Higiene = " + conjuntoTamagochi[i].getHigiene());
					System.out.println("Diversión = " + conjuntoTamagochi[i].getDiversion());

					if (conjuntoTamagochi[i].getHambre() <= 20) {
						conjuntoTamagochi[i].setHambre(30);
					}
					if (conjuntoTamagochi[i].getSueno() <= 20) {
						conjuntoTamagochi[i].setSueno(30);
					}
					if (conjuntoTamagochi[i].getHigiene() <= 20) {
						conjuntoTamagochi[i].setHigiene(30);
					}
					if (conjuntoTamagochi[i].getDiversion() <= 20) {
						conjuntoTamagochi[i].setDiversion(30);
					}
				} else if (((conjuntoTamagochi[i].getHambre() > 20) || (conjuntoTamagochi[i].getSueno() > 20)
						|| (conjuntoTamagochi[i].getHigiene() > 20) || (conjuntoTamagochi[i].getDiversion() > 20))
						&& (conjuntoTamagochi[i].isVivo() == true)) {
					System.out.println(conjuntoTamagochi[i].getNombre() + " se encuentra sano y sin problemas.");
				}
			}
		}
	}
}
