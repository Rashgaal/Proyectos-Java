package ahorcado;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JuegoAhorcado {

	private static Scanner scPalabra = new Scanner(System.in);
	private static Scanner scNumero = new Scanner(System.in);
	private static String palabraSecreta;
	private static String[] letrasAcertadas;
	private static int intentos = 6;
	private static boolean controlResolver = true;

	public static void main(String[] args) {

		int eleccionMenuInicial;
		do {
			intentos = 6;
			controlResolver = true;
			eleccionMenuInicial = menuAhorcado();
			opcionesMenuInicial(eleccionMenuInicial);
		} while (eleccionMenuInicial != 2);

	}

	public static int menuAhorcado() {

		int opcionMenuInicial = 0;

		System.out.println("**********************************");
		System.out.println("******BIENVENIDO AL AHORCADO******");
		System.out.println("| 1 = Jugar.");
		System.out.println("| 2 = Salir.");

		try {
			opcionMenuInicial = scNumero.nextInt();
		} catch (java.util.InputMismatchException e) {
			System.out.println("\033[31mFallo en la elección.\033[97m");
			scNumero.next();
		}

		return opcionMenuInicial;
	}

	public static void opcionesMenuInicial(int opcionMenuInicial) {

		switch (opcionMenuInicial) {
		case 1:
			introducirPalabra();
			juegoAhorcado();
			break;
		case 2:
			System.out.println("¡Hasta pronto! Vuelve si quieres jugar de nuevo.");
			break;
		default:
			System.out.println("\033[31mElige (1) para jugar o (2) para salir del juego.\033[97m");
			break;
		}
	}

	public static String introducirPalabra() {

		boolean checkPalabraSecreta = true;

		while (checkPalabraSecreta) {
			System.out.println("Introduce la palabra con la que quieres jugar.");
			palabraSecreta = scPalabra.next().toUpperCase();

			String controlPalabraSecreta = "^([a-zA-ZáéíóúÁÉÍÓÚñÑ])+$";
			Pattern patronPalabraSecreta = Pattern.compile(controlPalabraSecreta);
			Matcher coincidenciaPalabraSecreta = patronPalabraSecreta.matcher(palabraSecreta);

			if (!coincidenciaPalabraSecreta.matches()) {
				System.out.println("\033[31mLa palabra introducida contiene carácteres no permitidos.\033[97m");
			} else {
				System.out.println("\033[32mComienza el juego.\033[97m");
				checkPalabraSecreta = false;
			}
		}
		letrasAcertadas = new String[palabraSecreta.length()];
		for (int i = 0; i < letrasAcertadas.length; i++) {
			letrasAcertadas[i] = " " + "_";
		}
		return palabraSecreta;
	}

	public static void juegoAhorcado() {

		String[] letrasPalabraSecreta = palabraSecreta.split("");
		int letraResolver;

		muñeco(intentos);

		for (int i = 0; i < letrasPalabraSecreta.length; i++) {
			System.out.print(" " + "_");
		}
		System.out.println();

		do {
			letraResolver = menuLetraResolver();
			opcionesLetraResolver(letraResolver);
		} while (intentos > 0 && controlResolver == true);

	}

	public static int menuLetraResolver() {

		int opcionLetraResolver = 0;

		System.out.println("¿Qué quieres hacer?");
		System.out.println("| 1 = Letra.");
		System.out.println("| 2 = Resolver.");

		try {
			opcionLetraResolver = scNumero.nextInt();
		} catch (java.util.InputMismatchException e) {
			System.out.println("\033[31mFallo en la elección.\033[97m");
			scNumero.nextLine();
			System.out.print("");
		}

		return opcionLetraResolver;
	}

	public static void opcionesLetraResolver(int opcionLetraResolver) {

		switch (opcionLetraResolver) {
		case 1:
			introducirLetra();
			break;
		case 2:
			resolverPalabra();
			break;
		default:
			System.out.println(
					"\033[31mElige (1) para introducir una nueva letra o (2) para resolver la palabra.\033[97m");
			break;
		}
	}

	public static void introducirLetra() {

		String letraIntroducida;
		boolean controlLetra = true;

		System.out.println("Elige una letra.");
		letraIntroducida = scPalabra.next().toUpperCase();

		for (int i = 0; i < palabraSecreta.length(); i++) {
			if (letraIntroducida.charAt(0) == palabraSecreta.charAt(i)) {
				letrasAcertadas[i] = " " + letraIntroducida;
				controlLetra = false;
			} else {
				letrasAcertadas[i] = letrasAcertadas[i];
			}
		}
		if (!controlLetra) {
			System.out.println("\033[32mLa letra introducida se encuentra en la palabra secreta.\033[97m");
			mostrarPalabraParcial();
		} else {
			System.out.println("\033[31mLo siento pero la letra introducida no se encuentra en la palabra.\033[97m");
			intentos--;
			muñeco(intentos);
			if (intentos > 0) {
				mostrarPalabraParcial();
			}
		}
		System.out.println();
	}

	public static void resolverPalabra() {
		String resolver;
		System.out.println("¿Cuál es la palabra que estamos buscando?");
		resolver = scPalabra.next().toUpperCase();
		if (resolver.equals(palabraSecreta)) {
			ganar();
		} else {
			intentos = 0;
			muñeco(0);
			controlResolver = false;
		}

	}

	public static void mostrarPalabraParcial() {
		for (int i = 0; i < letrasAcertadas.length; i++) {
			System.out.print(letrasAcertadas[i]);
		}
	}

	public static void muñeco(int intentos) {

		switch (intentos) {
		case 6:
			System.out.println(" ______");
			System.out.println("|      |");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|___");
			System.out.println();
			vidas();
			break;
		case 5:
			System.out.println(" ______");
			System.out.println("|      |");
			System.out.println("|      \033[31mO\033[97m");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|___");
			System.out.println();
			vidas();
			break;
		case 4:
			System.out.println(" ______");
			System.out.println("|      |");
			System.out.println("|      \033[31mO\033[97m");
			System.out.println("|      \033[31m|\033[97m");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|___");
			System.out.println();
			vidas();
			break;
		case 3:
			System.out.println(" ______");
			System.out.println("|      |");
			System.out.println("|      \033[31mO\033[97m");
			System.out.println("|     \033[31m/|\033[97m");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|___");
			System.out.println();
			vidas();
			break;
		case 2:
			System.out.println(" ______");
			System.out.println("|      |");
			System.out.println("|      \033[31mO\033[97m");
			System.out.println("|     \033[31m/|\\\033[97m");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|___");
			System.out.println();
			vidas();
			break;
		case 1:
			System.out.println(" ______");
			System.out.println("|      |");
			System.out.println("|     \033[31m O\033[97m");
			System.out.println("|     \033[31m/|\\\033[97m");
			System.out.println("|     \033[31m/\033[97m");
			System.out.println("|");
			System.out.println("|___");
			System.out.println();
			vidas();
			break;
		case 0:
			System.out.println(" ______");
			System.out.println("|      |");
			System.out.println("|      \033[31mO\033[97m");
			System.out.println("|     \033[31m/|\\\033[97m");
			System.out.println("|     \033[31m/ \\\033[97m");
			System.out.println("|");
			System.out.println("|___");
			System.out.println();
			vidas();
			System.out.println("\033[31mHas perdido, la respuesta correcta era: " + palabraSecreta + " .\033[97m");
			break;
		}
	}

	public static void vidas() {
		for (int i = 0; i < intentos; i++) {
			System.out.print("\033[31m\u2763\033[97m" + " ");
		}
		System.out.println();
	}

	public static void ganar() {

		System.out.println("\033[32m¡¡¡FELICIDADES!!!\033[97m");
		System.out.println("   \033[33m*****  ");
		System.out.println(" *       * ");
		System.out.println("*  O   O  *");
		System.out.println("*    ∆    *");
		System.out.println(" * \\___/ * ");
		System.out.println("   *****\033[97m   ");
		System.out.println(
				"Has acertado la palabra secreta: " + palabraSecreta + " .Aún te quedaban " + intentos + " vidas.");
		controlResolver = false;

	}
}
