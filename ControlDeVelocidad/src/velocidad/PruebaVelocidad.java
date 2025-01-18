package velocidad;

import java.util.Scanner;

public class PruebaVelocidad {

	private static Scanner sc = new Scanner(System.in);
	private static ControlVelocidad control = new ControlVelocidad();
	private static int velocidadInicio;
	private static int subirVelocidad;
	private static int bajarVelocidad;
	public static boolean comprueba;

	public static void main(String[] args) {

		int opcion;

		do {
			opcion = menu();
			eleccion(opcion);
		} while (opcion != 7);

	}

	public static int menu() {

		int opcion = 0;

		System.out.println("********************************");
		System.out.println("*************MENÚ***************");
		System.out.println("| 1 = Conocer la velocidad actual.");
		System.out.println("| 2 = Conocer la velocidad máxima histórica.");
		System.out.println("| 3 = Conocer el estado de la alarma.");
		System.out.println("| 4 = Incrementar velocidad.");
		System.out.println("| 5 = Disminuir velocidad.");
		System.out.println("| 6 = Conocer estado completo.");
		System.out.println("| 7 = Salir del control.");

		try {
			opcion = sc.nextInt();
		} catch (java.util.InputMismatchException e) {
			sc.next();
			System.out.println("Error.");
		}

		return opcion;

	}

	public static void eleccion(int opcion) {

		switch (opcion) {
		case 1:
			control.consultaVelocidad();
			break;
		case 2:
			control.maximoHistorico();
			break;
		case 3:
			control.estadoAlarma();
			break;
		case 4:
			do {
				System.out.println("¿Cuánto quieres subir la velocidad?");
				try {
					subirVelocidad = sc.nextInt();
					control.incrementarVelocidad(subirVelocidad);
					comprueba = false;
				} catch (java.util.InputMismatchException e) {
					sc.next();
					System.out.println("Debe introducir un valor numérico positivo. Por ejemplo: 30.");
					comprueba = true;
				}
			} while (comprueba == true);
			break;
		case 5:
			do {
				System.out.println("¿Cuánto quieres bajar la velocidad?");
				try {
					bajarVelocidad = sc.nextInt();
					control.reducirVelocidad(bajarVelocidad);
					comprueba = false;
				} catch (java.util.InputMismatchException e) {
					sc.next();
					System.out.println("Debe introducir un valor numérico. Por ejemplo: 25.");
					comprueba = true;
				}
			} while (comprueba == true);
			break;
		case 6:
			System.out.println(control.toString());
			break;
		case 7:
			System.out.println("Saliendo del control.");
			break;
		default:
			System.out.println("El parámetro introducido no corresponde con ninguna operación");
			break;
		}
	}
}
