package registroPersonal;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Random;

public class MainRegistro {

	private static Scanner scLetras = new Scanner(System.in);
	private static Scanner scNumeros = new Scanner(System.in);
	private static Registro[] archivo = new Registro[100];
	private static int contador = 0;
	private static boolean checkDniNie = true;

	public static void main(String[] args) {
		
		int eleccionMenu;

		do {
			eleccionMenu = menuRegistro();
			opcionesMenu(eleccionMenu);
		} while (eleccionMenu != 5);

	}
	// VARIABLE.MATCHES("EXPRESION")
	public static int menuRegistro() {

		int opcionMenu = 0;

		System.out.println("********************************");
		System.out.println("*************MENÚ***************");
		System.out.println("| 1 = Cargar registro.");
		System.out.println("| 2 = Mostrar archivo.");
		System.out.println("| 3 = Buscar persona por DNI/NIE.");
		System.out.println("| 4 = Conocer la edad de una persona del registro.");
		System.out.println("| 5 = Salir");
		/* System.out.println("👤,🆔,📅,📆,🗓,☎,📧,✉,📝,📓,🗒"); */

		try {
			opcionMenu = scNumeros.nextInt();

		} catch (java.util.InputMismatchException e) {
			System.out.println("\033[31mError, introduzca el número de la operación que desea realizar.\033[97m");
			scNumeros.nextLine();
			System.out.print("");
		}

		return opcionMenu;

	}

	public static void opcionesMenu(int opcionMenu) {

		switch (opcionMenu) {
		case 1:
			rellenarArchivo();
			break;
		case 2:
			mostrarArchivo();
			break;
		case 3:
			buscarDniNie();
			break;
		case 4:
			mostrarEdad();
			break;
		case 5:
			System.out.println("Saliendo del sistema de archivo.");
			break;
		default:
			System.out.println("\033[31mEl valor introducido está fuera de los parámetros.\033[97m");
			break;
		}
	}

	public static void rellenarArchivo() {

		String nombre = null, primerApellido = null, segundoApellido = null, dniNie = null, fechaNacimiento = null,
				telefono = null, puestoTrabajo = null;
		Fecha fechaObjeto;

		if (contador >= archivo.length) {
			System.out.println("\033[31mEl archivo está completo, no se puede añadir ninguna entrada más.\033[97m");
		} else {

			nombre = validarNombre(nombre);
			primerApellido = validarApellido1(primerApellido);
			segundoApellido = validarApellido2(segundoApellido);
			dniNie = validarFormatoDniNie(dniNie);
			fechaObjeto = validarFecha(fechaNacimiento);
			telefono = validarTelefono(telefono);
			puestoTrabajo = validarPuestoTrabajo(puestoTrabajo);

			Registro persona = new Registro(nombre, primerApellido, segundoApellido, dniNie, fechaObjeto, telefono,
					puestoTrabajo);
			archivo[contador] = persona;

		}
		contador++;
	}

	public static String validarNombre(String nombre) {

		boolean checkNombre = true;

		do {
			System.out.println("Dime el nombre.");
			nombre = scLetras.nextLine();
			// necesita ser nextLine porque si no sólo cogería la primera parte del nombre//

			String controlNombre = "^([a-zA-ZáéíóúÁÉÍÓÚ ])+$";
			// a veces para poner espacios es necesario "\s" (espacio) ó "\S" (sin
			// espacio), a veces se necesitan dos barras previas"\\"//
			Pattern patronNombre = Pattern.compile(controlNombre);
			Matcher coincidenciaNombre = patronNombre.matcher(nombre);

			if (!coincidenciaNombre.matches()) {
				System.out.println("\033[31mEl dato introducido contienen carácteres no permitidos.\033[97m");
			} else {
				System.out.println("\033[32mAceptado.\033[97m");
				checkNombre = false;
			}

		} while (checkNombre);
		return nombre;
	}

	public static String validarApellido1(String primerApellido) {

		boolean checkApellido1 = true;

		do {

			System.out.println("Dime el primer apellido");
			primerApellido = scLetras.nextLine();

			String controlApellido1 = "^([a-zA-ZáéíóúÁÉÍÓÚ\\- ])+$";
			Pattern patronApellido1 = Pattern.compile(controlApellido1);
			Matcher coincidenciaApellido1 = patronApellido1.matcher(primerApellido);

			// compruebo que los caracteres esten dentro del patron creado previamente//
			if (!coincidenciaApellido1.matches()) {
				System.out.println("\033[31mEl dato introducido contienen carácteres no permitidos.\033[97m");
			} else {
				System.out.println("\033[32mAceptado.\033[97m");
				checkApellido1 = false;
			}

		} while (checkApellido1);

		return primerApellido;
	}

	public static String validarApellido2(String segundoApellido) {

		boolean checkApellido2 = true;

		do {

			System.out.println("Dime el segundo apellido");
			segundoApellido = scLetras.nextLine();

			String controlApellido2 = "^([a-zA-ZáéíóúÁÉÍÓÚ\\- ])+$";
			Pattern patronApellido2 = Pattern.compile(controlApellido2);
			Matcher coincidenciaApellido2 = patronApellido2.matcher(segundoApellido);

			// compruebo que los caracteres esten dentro del patron creado previamente//
			if (!coincidenciaApellido2.matches()) {
				System.out.println("\033[31mEl dato introducido contienen carácteres no permitidos.\033[97m");
			} else {
				System.out.println("\033[32mAceptado.\033[97m");
				checkApellido2 = false;
			}
		} while (checkApellido2);

		return segundoApellido;
	}

	public static String validarFormatoDniNie(String dniNie) {

		String formatoDniNieFinal = "";

		do {

			System.out.println("Dime el DNI o NIE.");
			dniNie = scLetras.nextLine();

			if (dniNie.charAt(0) == 'X' || dniNie.charAt(0) == 'Y' || dniNie.charAt(0) == 'Z') {
				formatoDniNieFinal = validarNie(dniNie);

			} else if (dniNie.charAt(0) == '1' || dniNie.charAt(0) == '2' || dniNie.charAt(0) == '3'
					|| dniNie.charAt(0) == '4' || dniNie.charAt(0) == '5' || dniNie.charAt(0) == '6'
					|| dniNie.charAt(0) == '7' || dniNie.charAt(0) == '8' || dniNie.charAt(0) == '9'
					|| dniNie.charAt(0) == '0') {
				formatoDniNieFinal = validarDni(dniNie);

			} else {
				System.out.println("\033[31mEl formato introducido no se corresponde con un NIE.\033[97m");
			}
		} while (checkDniNie == true);

		return formatoDniNieFinal;
	}

	public static String validarDni(String dniNie) {

		String formatoDniNieFinal = "";
		String controlDni = "^([0-9]{8})([a-zA-Z]{1})$";
		char[] arrayLetrasDni = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q',
				'V', 'H', 'L', 'C', 'K', 'E' };

		Pattern patronCreadoDni = Pattern.compile(controlDni);
		Matcher coincidenciaDni = patronCreadoDni.matcher(dniNie);

		if (!coincidenciaDni.matches()) {

			System.out.println(
					"\033[31mEl DNI no tiene un formato válido, recuerda que son 8 números seguido de una letra '00000000X'.\033[97m");

		} else {

			String numerosDni = dniNie.substring(0, 8);
			String letraDni = dniNie.substring(8);
			letraDni = letraDni.toUpperCase();
			formatoDniNieFinal = numerosDni + letraDni;
			int restoNumerosDni;

			restoNumerosDni = Integer.parseInt(numerosDni) % 23;

			if (arrayLetrasDni[restoNumerosDni] == letraDni.charAt(0)) {
				System.out.println("\033[32mAceptado.\033[97m");
				checkDniNie = false;
			} else {
				System.out.println("\033[31mDNI no es correcto.\033[97m");
				checkDniNie = true;

			}

		}

		return formatoDniNieFinal;
	}

	public static String validarNie(String dniNie) {

		String formatoDniNieFinal = "";
		String controlNie = "([x-zX-Z])([0-9]{7})([a-zA-Z])$";
		char[] arrayLetrasNie = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q',
				'V', 'H', 'L', 'C', 'K', 'E' };
		Pattern patronCreadoNie = Pattern.compile(controlNie);
		Matcher coincidenciaNie = patronCreadoNie.matcher(dniNie);

		if (!coincidenciaNie.matches()) {
			System.out.println(
					"\033[31mEl NIE no tiene un formato correcto, recuerde que debe seguir este patrón 'X0000000X' letra-sietenumeros-letra.\033[97m");
		} else {

			String letraInicialNie = dniNie.substring(0, 1);
			String numerosNie = dniNie.substring(1, 8);
			String letraFinalNie = dniNie.substring(8);
			letraInicialNie = letraInicialNie.toUpperCase();
			letraFinalNie = letraFinalNie.toUpperCase();
			formatoDniNieFinal = letraInicialNie + numerosNie + letraFinalNie;
			int restoNumerosNie;

			if (dniNie.substring(0, 1).equals("X")) {
				String unionCadenasX = "0" + numerosNie;
				restoNumerosNie = (Integer.parseInt(unionCadenasX)) % 23;
				if (arrayLetrasNie[restoNumerosNie] == letraFinalNie.charAt(0)) {
					System.out.println("\033[32mAceptado.\033[97m");
					checkDniNie = false;
				} else {
					System.out.println("\033[31mNIE no es correcto.\033[97m");
					checkDniNie = true;
				}
			} else if (dniNie.substring(0, 1).equals("Y")) {
				String unionCadenasY = "1" + numerosNie;
				restoNumerosNie = (Integer.parseInt(unionCadenasY)) % 23;
				if (arrayLetrasNie[restoNumerosNie] == letraFinalNie.charAt(0)) {
					System.out.println("\033[32mAceptado.\033[97m");
					checkDniNie = false;
				} else {
					System.out.println("\033[31mNIE no es correcto.\033[97m");
					checkDniNie = true;
				}
			} else if (dniNie.substring(0, 1).equals("Z")) {
				String unionCadenasZ = "2" + numerosNie;
				restoNumerosNie = (Integer.parseInt(unionCadenasZ)) % 23;
				if (arrayLetrasNie[restoNumerosNie] == letraFinalNie.charAt(0)) {
					System.out.println("\033[32mAceptado.\033[97m");
					checkDniNie = false;
				} else {
					System.out.println("\033[31mNIE no es correcto.\033[97m");
					checkDniNie = true;
				}
			}
		}

		return formatoDniNieFinal;
	}

	public static Fecha validarFecha(String fechaNacimiento) {

		boolean checkFecha = true;

		do {

			System.out.println("Dime la fecha de nacimiento.");
			fechaNacimiento = scLetras.nextLine();

			String controlFecha = "^(([0-2]*)([0-9])||([3])([0-1]))([-])(([0]*)([0-9])||([1])([0-2]))([-])(([0-1])([0-9])([0-9])([0-9])||([2])([0])(([0-1])([0-9])||([2])([0-4])))$";
			Pattern patronFecha = Pattern.compile(controlFecha);
			Matcher coincidenciaFecha = patronFecha.matcher(fechaNacimiento);

			if (!coincidenciaFecha.matches()) {
				System.out.println("\033[31mEl dato introducido está fuera de los parámetros.(dd-mm-aaaa).\033[97m");
			} else {
				System.out.println("\033[32mAceptado.\033[97m");
				checkFecha = false;
			}

		} while (checkFecha == true);

		String[] fechaArray = fechaNacimiento.split("-");
		Fecha fechaObjeto = new Fecha(Integer.parseInt(fechaArray[0]), Integer.parseInt(fechaArray[1]),
				Integer.parseInt(fechaArray[2]));

		return fechaObjeto;
	}

	public static String validarTelefono(String telefono) {

		boolean checkTelefono = true;

		do {

			System.out.println("Dime el teléfono.");
			telefono = scLetras.nextLine();

			String controlTelefono = "^([6-7][0-9]{8})$";
			Pattern patronTelefono = Pattern.compile(controlTelefono);
			Matcher coincidenciaTelefono = patronTelefono.matcher(telefono);

			if (!coincidenciaTelefono.matches()) {
				System.out.println("\033[31mEl dato introducido contienen carácteres no permitidos.\033[97m");
			} else {
				System.out.println("\033[32mAceptado.\033[97m");
				checkTelefono = false;
			}

		} while (checkTelefono == true);

		return telefono;
	}

	public static String validarPuestoTrabajo(String puestoTrabajo) {

		boolean checkPuestoTrabajo = true;

		do {

			System.out.println("Dime el puesto de trabajo.");
			puestoTrabajo = scLetras.nextLine();

			String controlPuestoTrabajo = "^([a-zA-ZáéíóúÁÉÍÓÚ ])+$";
			Pattern patronPuestoTrabajo = Pattern.compile(controlPuestoTrabajo);
			Matcher coincidenciaPuestoTrabajo = patronPuestoTrabajo.matcher(puestoTrabajo);

			if (!coincidenciaPuestoTrabajo.matches()) {
				System.out.println("\033[31mEl dato introducido contienen carácteres no permitidos.\033[97m");
			} else {
				System.out.println("\033[32mAceptado.\033[97m");
				checkPuestoTrabajo = false;
			}

		} while (checkPuestoTrabajo);

		return puestoTrabajo;
	}

	public static void mostrarArchivo() {
		String cadenaNombre, cadenaApellidos, cadenaFechaNac, cadenaDni, cadenaTelefono, cadenaCorreoElectronico,
				cadenaPuestoTrabajo, cadenaContraseña, cadenaVacia;
		cadenaNombre = "NOMBRE";
		cadenaApellidos = "APELLIDOS";
		cadenaFechaNac = "FECHA DE NACIMIENTO";
		cadenaDni = "DNI/NIE";
		cadenaTelefono = "TELEFONO";
		cadenaCorreoElectronico = "CORREO ELECTRÓNICO";
		cadenaPuestoTrabajo = "PUESTO DE TRABAJO";
		cadenaContraseña = "CONTRASEÑA";
		cadenaVacia = " ";

		if (contador > 0) {
			System.out.println(
					"--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.printf("|%15s%10s|%19s%10s|%24s%5s|%12s%5s|%13s%5s|%22s%5s|%21s%5s|%15s%5s|", cadenaNombre,
					cadenaVacia, cadenaApellidos, cadenaVacia, cadenaFechaNac, cadenaVacia, cadenaDni, cadenaVacia,
					cadenaTelefono, cadenaVacia, cadenaCorreoElectronico, cadenaVacia, cadenaPuestoTrabajo, cadenaVacia,
					cadenaContraseña, cadenaVacia);
			System.out.println();
			System.out.println(
					"--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

			for (int i = 0; i < contador; i++) {
				if (archivo[i] != null) {
					System.out.printf("|%-15s%10s|%-19s%10s|%-24s%5s|%-12s%5s|%-13s%5s|%-22s%5s|%-21s%5s|%-15s%5s|",
							archivo[i].getNombre(), cadenaVacia,
							archivo[i].getPrimerApellido() + " " + archivo[i].getSegundoApellido(), cadenaVacia,
							archivo[i].getFechaNacimiento(), cadenaVacia, archivo[i].getDniNie(), cadenaVacia,
							archivo[i].getTelefono(), cadenaVacia, archivo[i].getCorreoElectronico(), cadenaVacia,
							archivo[i].getPuestoTrabajo(), cadenaVacia, archivo[i].getContraseña(), cadenaVacia);
					System.out.println();
				}
			}
			System.out.println(
					"--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		} else {
			System.out.println("No hay registros en el archivo.");
		}
	}

	public static void buscarDniNie() {

		String buscarDniNie;
		boolean coincideBusqueda = false;
		boolean bucleSeguro = true;
		Registro guardarElemento = new Registro();

		do {
			if (contador == 0) {
				System.out.println("\033[31mEl archivo está vacío.\033[97m");
				bucleSeguro = true;
			} else {
				System.out.println("Introduce el DNI/NIE de la persona que quieres ver el registro.");
				buscarDniNie = scLetras.nextLine().toUpperCase();

				for (int i = 0; i < contador; i++) {
					if (buscarDniNie.equals(archivo[i].getDniNie())) {
						guardarElemento = archivo[i];
						coincideBusqueda = true;
					}
				}

				if (!coincideBusqueda) {
					System.out.println("\033[31mEl DNI/NIE introducido no se encuentra en el archivo.\033[97m");
				} else {
					System.out.println("\033[35m👤\033[36m " + guardarElemento.getNombre() + " "
							+ guardarElemento.getPrimerApellido() + " " + guardarElemento.getSegundoApellido() + ".");
					System.out.println("\033[35m🆔\033[36m " + guardarElemento.getDniNie() + ".");
					System.out.println("\033[35m🗓\033[36m " + guardarElemento.getFechaNacimiento() + ".");
					System.out.println("\033[35m☎\033[36m " + guardarElemento.getTelefono() + ".");
					System.out.println("\033[35m📧\033[36m " + guardarElemento.getCorreoElectronico() + " .");
					System.out.println("\033[35m📝\033[36m " + guardarElemento.getPuestoTrabajo() + ".");
					System.out.println("\033[35m🔒\033[36m " + guardarElemento.getContraseña() + ".\033[97m");
					bucleSeguro = false;
				}
			}
		} while (bucleSeguro == true);
	}

	public static void mostrarEdad() {

		int saberEdad;
		Registro guardarEdad = new Registro();
		boolean coincideEdad = false;
		if (contador == 0) {
			System.out.println("No hay personas en el archivo.");
		} else {
			System.out.println("¿De que persona quieres saber la edad?");
			for (int i = 0; i < contador; i++) {
				System.out.println((i + 1) + " " + archivo[i].getNombre());
			}
			saberEdad = scNumeros.nextInt();
			for (int j = 0; j < contador; j++) {
				if (saberEdad == (j + 1)) {
					guardarEdad = archivo[j];
					coincideEdad = true;
				}
			}
			if (!coincideEdad) {
				System.out.println("\033[31mEl número de registro no se encuentra en el archivo.\033[97m");
			} else {
				System.out.println(
						guardarEdad.getNombre() + " nació el " + guardarEdad.getFechaNacimiento() + ", su edad es "
								+ guardarEdad.calcularEdadAños() + " en años, " + guardarEdad.calcularEdadMeses()
								+ " en meses y " + guardarEdad.calcularEdadDias() + " en días.");
			}
		}
	}
}
