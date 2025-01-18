package guerra;

import java.util.Random;
import java.util.Scanner;

public class CounterStrike {

	private static Guerrillero[] banda1 = new Guerrillero[15];
	private static Guerrillero[] banda2 = new Guerrillero[15];
	private static String nombreBanda1, nombreBanda2;
	private static int ronda = 1;

	public static void main(String[] args) {

		int perder;

		crearBandas();
		crearGuerrilleros();
		listarBandas();
		int empieza = quienComienza();
		do {

			System.out.println("SE REPOSICIONAN PARA LA RONDA.");
			disparos(empieza);
			System.out.println("RECUENTO DE VIVOS.");
			perder = recuento();
			reposicionamiento();
		} while (perder != 1);
	}

	public static void crearBandas() {

		Scanner scLetras = new Scanner(System.in);

		System.out.println("¿Cómo se llama la primera banda?");
		nombreBanda1 = scLetras.nextLine();
		System.out.println("¿Cómo se llama la segunda banda?");
		nombreBanda2 = scLetras.nextLine();

	}

	public static void crearGuerrilleros() {
		String[] nombresBanda1 = { "Ivanovich", "Sergei", "Dmitri", "Nikolai", "Vladimir", "Aleksandr", "Mikhail",
				"Svetlana", "Olganova", "Tatiana", "Victorovich", "Marianna", "Natalia", "Yelena", "Yurinova" };
		String[] nombresBanda2 = { "Yoseuf", "Davide", "Mosheit", "Itzhak", "Daniel", "Avraham", "Aryeya", "Eliodas",
				"Shimon", "Michael", "Yitzhak", "Yehuda", "Zvichko", "Yitzchak", "Mordechai" };
		int contadorBanda1 = 0;
		int contadorBanda2 = 0;
		int eleccionTipoGuerrillero;
		Random tipoGuerrillero = new Random();

		for (int i = 0; i < 15; i++) {

			eleccionTipoGuerrillero = tipoGuerrillero.nextInt(2);

			if (eleccionTipoGuerrillero == 0) {
				Francotirador GuerrilleroFranco = new Francotirador(nombresBanda1[contadorBanda1]);
				banda1[contadorBanda1] = GuerrilleroFranco;
				contadorBanda1++;
			} else {
				Lanzacohetes GuerrilleroLanza = new Lanzacohetes(nombresBanda1[contadorBanda1]);
				banda1[contadorBanda1] = GuerrilleroLanza;
				contadorBanda1++;
			}
		}
		for (int j = 0; j < 15; j++) {

			eleccionTipoGuerrillero = tipoGuerrillero.nextInt(2);

			if (eleccionTipoGuerrillero == 0) {
				Francotirador GuerrilleroFranco = new Francotirador(nombresBanda2[contadorBanda2]);
				banda2[contadorBanda2] = GuerrilleroFranco;
				contadorBanda2++;
			} else {
				Lanzacohetes GuerrilleroLanza = new Lanzacohetes(nombresBanda2[contadorBanda2]);
				banda2[contadorBanda2] = GuerrilleroLanza;
				contadorBanda2++;
			}
		}
	}

	public static void listarBandas() {
		System.out.println("\033[91m" + nombreBanda1 + "\033[37m");
		for (int i = 0; i < banda1.length; i++) {
			System.out.println(banda1[i]);
		}
		System.out.println("\033[94m" + nombreBanda2 + "\033[37m");
		for (int j = 0; j < banda2.length; j++) {
			System.out.println(banda2[j]);
		}
	}

	public static int quienComienza() {

		int empieza;
		Random quienEmpieza = new Random();
		empieza = quienEmpieza.nextInt(2);
		if (empieza == 0) {
			System.out.println("-----------------------------------------------");
			System.out.println("\033[32mComienza la banda ---> " + nombreBanda1 + "\033[37m");
		} else {
			System.out.println("-----------------------------------------------");
			System.out.println("\033[32mComienza la banda ---> " + nombreBanda2 + "\033[37m");
		}

		return empieza;
	}

	public static void disparos(int empieza) {

		System.out.println("-----------------------------------------------");
		System.out.println("\033[94mComienza la ronda : " + ronda + "\033[37m");
		System.out.println("-----------------------------------------------");
		ronda++;

		if (empieza == 0) {
			ataques(banda1, banda2);
			ataques(banda2, banda1);
		} else {
			ataques(banda2, banda1);
			ataques(banda1, banda2);
		}
	}

	public static int recuento() {

		int perdido = 0, vivoBanda1 = 0, vivoBanda2 = 0;

		System.out.println("\033[91m" + nombreBanda1 + "\033[37m");
		for (int i = 0; i < banda1.length; i++) {
			if (banda1[i].isVivo() == true) {
				System.out.println(banda1[i]);
				vivoBanda1++;
			}
		}
		System.out.println("\033[93mQuedan " + vivoBanda1 + " individuos vivos.\033[37m");

		System.out.println("\033[94m" + nombreBanda2 + "\033[37m");
		for (int j = 0; j < banda2.length; j++) {
			if (banda2[j].isVivo() == true) {
				System.out.println(banda2[j]);
				vivoBanda2++;
			}
		}
		System.out.println("\033[93mQuedan " + vivoBanda2 + " individuos vivos.\033[37m");

		if (vivoBanda1 == 0) {
			System.out.println("\033[31mLa banda " + nombreBanda1 + " ha perdido.\033[37m");
			System.out.println("\033[92mLa banda " + nombreBanda2 + " ha ganado.\033[37m");
		} else if (vivoBanda2 == 0) {
			System.out.println("\033[31mLa banda " + nombreBanda2 + " ha perdido.\033[37m");
			System.out.println("\033[92mLa banda " + nombreBanda1 + " ha ganado.\033[37m");
		}

		if (vivoBanda1 == 0 || vivoBanda2 == 0) {
			perdido = 1;
			return perdido;
		} else {
			return 0;
		}
	}

	public static void reposicionamiento() {

		Random arribaAbajo = new Random();
		Random cuantoSeMueve = new Random();
		int upDown, movimiento, plantaMovimiento, plantaMax = 10, plantaMin = 1;

		for (int i = 0; i < banda1.length; i++) {
			upDown = arribaAbajo.nextInt(2);
			movimiento = cuantoSeMueve.nextInt(6);
			if (upDown == 0) {
				plantaMovimiento = banda1[i].getPlantaEdificio();
				banda1[i].setPlantaEdificio(plantaMovimiento - movimiento);
				if (banda1[i].getPlantaEdificio() < plantaMin) {
					banda1[i].setPlantaEdificio(plantaMovimiento + movimiento);
				}
			} else if (upDown == 1) {
				plantaMovimiento = banda1[i].getPlantaEdificio();
				banda1[i].setPlantaEdificio(plantaMovimiento + movimiento);
				if (banda1[i].getPlantaEdificio() > plantaMax) {
					banda1[i].setPlantaEdificio(plantaMovimiento - movimiento);
				}
			}
		}
		for (int j = 0; j < banda2.length; j++) {
			upDown = arribaAbajo.nextInt(2);
			movimiento = cuantoSeMueve.nextInt(6);
			if (upDown == 0) {
				plantaMovimiento = banda2[j].getPlantaEdificio();
				banda2[j].setPlantaEdificio(plantaMovimiento - movimiento);
				if (banda2[j].getPlantaEdificio() < plantaMin) {
					banda2[j].setPlantaEdificio(plantaMovimiento + movimiento);
				}
			} else if (upDown == 1) {
				plantaMovimiento = banda2[j].getPlantaEdificio();
				banda2[j].setPlantaEdificio(plantaMovimiento + movimiento);
				if (banda2[j].getPlantaEdificio() > plantaMax) {
					banda2[j].setPlantaEdificio(plantaMovimiento - movimiento);
				}
			}
		}
	}

	public static void ataques(Guerrillero[] atacantes, Guerrillero[] defensores) {

		Random elegirTirador = new Random();
		Random elegirPlanta = new Random();
		Random upDownLanza = new Random();
		int tirador, plantaAtacada = elegirPlanta.nextInt(10) + 1, danhoAtaque, vidaActual, desvioLanza, arribaOAbajo;
		boolean tiradorVivoEncontrado;

		do {
			tirador = elegirTirador.nextInt(15);
			tiradorVivoEncontrado = atacantes[tirador].isVivo();
		} while (!tiradorVivoEncontrado && algunoVivo(atacantes));

		if (tiradorVivoEncontrado == true) {
			do {
				plantaAtacada = elegirPlanta.nextInt(10) + 1;

				if (atacantes[tirador] instanceof Francotirador && atacantes[tirador].isVivo()) {
					danhoAtaque = ((Francotirador) atacantes[tirador]).getDanho();

					for (int i = 0; i < defensores.length; i++) {
						if (defensores[i].getPlantaEdificio() == plantaAtacada) {
							vidaActual = defensores[i].getVida();
							defensores[i].setVida(vidaActual - danhoAtaque);
							if (defensores[i].getVida() <= 0) {
								defensores[i].marcarMuerto();
							}
						}
					}

				} else if (atacantes[tirador] instanceof Lanzacohetes && atacantes[tirador].isVivo()) {

					desvioLanza = ((Lanzacohetes) atacantes[tirador]).getAlcance();
					arribaOAbajo = upDownLanza.nextInt(2);

					if (arribaOAbajo == 0) {
						plantaAtacada = plantaAtacada + desvioLanza;
						if (plantaAtacada > 10) {
							plantaAtacada = plantaAtacada - desvioLanza;
						}
					} else {
						plantaAtacada = plantaAtacada - desvioLanza;
						if (plantaAtacada < 1) {
							plantaAtacada = plantaAtacada + desvioLanza;
						}
					}
					for (int i = 0; i < defensores.length; i++) {
						if (defensores[i].getPlantaEdificio() == plantaAtacada) {
							defensores[i].marcarMuerto();
						}
					}
				}
			} while (!atacantes[tirador].isVivo());
		}
		System.out.println("\033[92m" + atacantes[tirador].getNombre() + " ha disparado a la planta " + plantaAtacada
				+ "\033[37m");
	}

	private static boolean algunoVivo(Guerrillero[] atacante) {

		for (int i = 0; i < atacante.length; i++) {
			Guerrillero atacanteElegido = atacante[i];
			if (atacanteElegido.isVivo()) {
				return true;
			}
		}
		return false;
	}
}