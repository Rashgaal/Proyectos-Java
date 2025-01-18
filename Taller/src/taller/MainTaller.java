package taller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainTaller {

	private static String rutaTrabajos = "trabajos.data";
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static Trabajo[] listaTrabajos = new Trabajo[100];

	public static void main(String[] args) {
		int menu = 0;
		iniciarPrograma();
		do {
			menu = MenuPrincipal();
			accionMenuPrincipal(menu);
		} while (menu != 6);
	}

	public static void iniciarPrograma() {
		File archivoTrabajos = new File(rutaTrabajos);

		try {
			if (!archivoTrabajos.exists() && !archivoTrabajos.isFile()) {
				archivoTrabajos.createNewFile();
				System.out.println("Archivo 'trabajos.data' ha sido creado.");
				cargarIniciales();
			} else {
				System.out.println("Bienvenido al Taller");
				recuperarDatos();
			}
		} catch (IOException e) {
			System.err.println("Error de entrada/salida");
		}
	}

	public static void cargarIniciales() {

		Trabajo primero = new Revision(1);
		Trabajo segundo = new Mecanica(2, 100);
		Trabajo tercero = new ChapaPintura(4, 400);
		Trabajo cuarto = new Revision(2);
		Trabajo quinto = new Mecanica(3, 150);
		Trabajo sexto = new ChapaPintura(2, 200);
		Trabajo septimo = new Revision(3);
		Trabajo octavo = new Mecanica(1, 50);
		Trabajo noveno = new ChapaPintura(3, 300);
		Trabajo decimo = new Revision(5);

		listaTrabajos[0] = primero;
		listaTrabajos[1] = segundo;
		listaTrabajos[2] = tercero;
		listaTrabajos[3] = cuarto;
		listaTrabajos[4] = quinto;
		listaTrabajos[5] = sexto;
		listaTrabajos[6] = septimo;
		listaTrabajos[7] = octavo;
		listaTrabajos[8] = noveno;
		listaTrabajos[9] = decimo;

		encontrarUltimasPosiciones();

		System.out.println("Se han cargado los datos iniciales correctamente");
	}

	public static void recuperarDatos() {

		try {

			FileInputStream fis = new FileInputStream(rutaTrabajos);
			ObjectInputStream recuperar = new ObjectInputStream(fis);

			Object objeto = recuperar.readObject();

			if (objeto instanceof Trabajo[]) {

				listaTrabajos = (Trabajo[]) objeto;

			}
			System.out.println("Datos recuperados");

			recuperar.close();
			fis.close();
		} catch (IOException e) {
			System.err.println("Error de entrada/salida.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		encontrarUltimasPosiciones();

	}

	public static int MenuPrincipal() {

		int opcion = 0;

		System.out.println("¿Qué quieres hacer?");
		System.out.println("Marca el número de la opción que se desea hacer");
		System.out.println("1 - Añadir un trabajo.");
		System.out.println("2 - Buscar un trabajo.");
		System.out.println("3 - Modificar un trabajo.");
		System.out.println("4 - Listar todos los trabajos.");
		System.out.println("5 - Listar pendientes.");
		System.out.println("6 - Salir del programa.");

		try {
			opcion = Integer.parseInt(br.readLine());
		} catch (NumberFormatException e) {
			System.err.println("Debe introducir un número.");
			;
		} catch (IOException e) {
			System.err.println("Error de entrada y salida.");
		}

		return opcion;
	}

	public static int MenuModificar() {

		int opcion = 0;

		System.out.println("¿Qué quieres modificar?");
		System.out.println("Marca el número de la opción que se desea hacer");
		System.out.println("1 - Añadir horas.");
		System.out.println("2 - Añadir coste de materiales.");
		System.out.println("3 - Finalizar un trabajo.");
		System.out.println("4 - Eliminar un trabajo.");
		System.out.println("5 - Volver al menú principal.");

		try {
			opcion = Integer.parseInt(br.readLine());
		} catch (NumberFormatException e) {
			System.err.println("Debe introducir un número.");
			;
		} catch (IOException e) {
			System.err.println("Error de entrada y salida.");
		}

		return opcion;

	}

	public static void accionMenuPrincipal(int e) {

		int eleccionModificar;

		switch (e) {
		case 1:
			anhadirTrabajo();
			break;
		case 2:
			buscarTrabajo();
			break;
		case 3:
			do {
				eleccionModificar = MenuModificar();
				accionesMenuModificar(eleccionModificar);
			} while (eleccionModificar != 5);
			break;
		case 4:
			listarTodosTrabajos();
			break;
		case 5:
			listarPendientes();
			break;
		case 6:
			System.out.println("Saliendo del sistema.");
			guardarSalir();
			break;
		default:
			System.out.println("Utilice las opciones numéricas.");
			break;
		}
	}

	public static void accionesMenuModificar(int e) {
		switch (e) {
		case 1:
			anhadirHoras();
			break;
		case 2:
			anhadirCosteMateriales();
			break;
		case 3:
			finalizarTrabajo();
			break;
		case 4:
			eliminarTrabajo();
			break;
		case 5:
			System.out.println("Volviendo al menú inicial.");
			break;
		default:
			System.out.println("Utilice una de las opciones numéricas.");
			break;
		}
	}

	public static void anhadirTrabajo() {

		int tipoTrabajo = 0, horas = 0;
		double precioMateriales = 0;

		System.out.println(
				"¿Qué tipo de trabajo quieres añadir?\n1. REVISION.\n2. MECANICA\n3. CHAPA Y PINTURA\n(Indica el número de la opción que quieres)");

		try {
			tipoTrabajo = Integer.parseInt(br.readLine());

			if (tipoTrabajo == 1) {

				System.out.println("¿Cuántas horas tiene el trabajo?");
				horas = Integer.parseInt(br.readLine());

				Revision nuevo = new Revision(horas);
				anhadirArray(nuevo);

			} else if (tipoTrabajo == 2) {

				System.out.println("¿Cuántas horas tiene el trabajo?");
				horas = Integer.parseInt(br.readLine());
				System.out.println("Introduce el precio de los materiales iniciales.");
				precioMateriales = Double.parseDouble(br.readLine());

				Mecanica nuevo = new Mecanica(horas, precioMateriales);
				anhadirArray(nuevo);

			} else if (tipoTrabajo == 3) {

				System.out.println("¿Cuántas horas tiene el trabajo?");
				horas = Integer.parseInt(br.readLine());
				System.out.println("Introduce el precio de los materiales iniciales.");
				precioMateriales = Double.parseDouble(br.readLine());

				ChapaPintura nuevo = new ChapaPintura(horas, precioMateriales);
				anhadirArray(nuevo);

			} else {
				System.out.println("Introduce uno de los números del menú.");
			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error de entrada/salida.");
		}

	}

	public static void anhadirArray(Trabajo f) {

		int posicionArray = 0, idMaxima = 0, posicionTrabajoMaxima = 0;
		boolean vacio = false;

		for (int i = 1; i < listaTrabajos.length; i++) {

			if (listaTrabajos[0] == null) {
				listaTrabajos[0] = f;
			} else if (listaTrabajos[i] == null && listaTrabajos[i - 1] != null) {
				vacio = true;
				posicionArray = i;

			} else if (listaTrabajos[i] != null && listaTrabajos[i - 1] != null) {
				if (idMaxima < listaTrabajos[i].getIdTotal()) {
					idMaxima = listaTrabajos[i].getIdTotal();
				}
				if (posicionTrabajoMaxima < listaTrabajos[i].getNumeroTrabajoLista()) {
					posicionTrabajoMaxima = listaTrabajos[i].getNumeroTrabajoLista();
				}
			}
		}

		if (vacio) {
			listaTrabajos[posicionArray] = f;
			f.setNumeroTrabajoLista(posicionTrabajoMaxima + 1);
			Trabajo.setTrabajoLista(posicionTrabajoMaxima + 1);
		} else {
			for (int i = 0; i < listaTrabajos.length; i++) {
				if (listaTrabajos[i].getIdTotal() < idMaxima) {
					idMaxima = listaTrabajos[i].getIdTotal();
					posicionArray = i;
				}
			}
			listaTrabajos[posicionArray] = f;

		}
		System.out.println("Trabajo añadido a la lista.");
	}

	public static void buscarTrabajo() {

		int tipoTrabajo = 0;

		System.out.println("¿Qué trabajos quieres ver?\n1. REVISIONES.\n2. CHAPA Y PINTURA.\n3. MECANICA.");
		try {
			tipoTrabajo = Integer.parseInt(br.readLine());

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error de entrada/salida");
		}

		for (Trabajo tipos : listaTrabajos) {
			if (tipoTrabajo == 1 && tipos instanceof Revision) {
				System.out.println(tipos);

			} else if (tipoTrabajo == 2 && tipos instanceof ChapaPintura) {
				System.out.println(tipos);

			} else if (tipoTrabajo == 3 && tipos instanceof Mecanica) {
				System.out.println(tipos);

			}
		}
	}

	public static void listarTodosTrabajos() {

		for (Trabajo mostrar : listaTrabajos) {
			if (mostrar != null) {
				System.out.println(mostrar.toString());
			}
		}
	}

	public static void listarPendientes() {
		for (Trabajo pendientes : listaTrabajos) {
			if (pendientes != null && !pendientes.isTerminado()) {
				System.out.println(pendientes);
			}
		}
	}

	public static void guardarSalir() {

		try {
			FileOutputStream fos = new FileOutputStream(rutaTrabajos);
			ObjectOutputStream guardar = new ObjectOutputStream(fos);

			File guardarArchivo = new File(rutaTrabajos);

			if (guardarArchivo.exists() && guardarArchivo.isFile()) {
				guardar.writeObject(listaTrabajos);
				System.out.println("Se han guardado los cambios.");
			} else {
				System.out.println("No se han guardado los cambios.");
			}

			guardar.close();
			fos.close();
		} catch (IOException e) {
			System.err.println("Error de entrada/salida");
		}
	}

	public static void anhadirHoras() {

		int modificar = 0, añadir = 0;

		System.out.println("¿A qué trabajo le quieres añadir horas? (Indica su ID).");
		listarPendientes();
		do {

			try {
				modificar = Integer.parseInt(br.readLine());

				System.out.println("¿Cuántas horas quieres añadir?");

				añadir = Integer.parseInt(br.readLine());

			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Error de entrada/salida.");
			}

		} while (añadir < 0);

		for (Trabajo cambioHora : listaTrabajos) {
			if (cambioHora != null && cambioHora.getIdTotal() == modificar)
				cambioHora.setHora(cambioHora.getHora() + añadir);
		}
	}

	public static void anhadirCosteMateriales() {

		int cambio = 0, añadirMateriales = 0;

		System.out.println("¿A qué trabajo le quieres añadir coste de materiales? (Indica su ID).");
		for (Trabajo pendientes : listaTrabajos) {
			if (pendientes != null && !pendientes.isTerminado()
					&& (pendientes instanceof Mecanica || pendientes instanceof ChapaPintura)) {
				System.out.println(pendientes);
			}
		}
		do {

			try {
				cambio = Integer.parseInt(br.readLine());

				System.out.println("Indica el importe que quieres sumar.");

				añadirMateriales = Integer.parseInt(br.readLine());

			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Error de entrada/salida.");
			}

		} while (añadirMateriales < 0);

		for (Trabajo cambioMateriales : listaTrabajos) {
			if (cambioMateriales != null && (cambioMateriales.getIdTotal() == cambio)
					&& (cambioMateriales instanceof Reparacion))
				((Reparacion) cambioMateriales)
						.setPrecioMateriales(((Reparacion) cambioMateriales).getPrecioMateriales() + añadirMateriales);
		}
	}

	public static void finalizarTrabajo() {

		int terminar = 0;

		System.out.println("Indica la ID del trabajo que has finalizado.");
		listarPendientes();

		try {
			terminar = Integer.parseInt(br.readLine());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error de entrada/salida.");
		}

		for (Trabajo terminarTrabajo : listaTrabajos) {
			if (terminarTrabajo != null && terminarTrabajo.getIdTotal() == terminar) {
				terminarTrabajo.setTerminado(true);

				if (terminarTrabajo instanceof Mecanica || terminarTrabajo instanceof ChapaPintura
						|| terminarTrabajo instanceof Revision) {
					System.out.println("El coste total ha sido de : " + terminarTrabajo.calcularImporte() + "€.");
				}
			}
		}
	}

	public static void eliminarTrabajo() {

		int eliminar = 0;

		System.out.println("Indica la ID del trabajo que vas a eliminar.");
		listarTodosTrabajos();

		try {
			eliminar = Integer.parseInt(br.readLine());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error de entrada/salida.");
		}

		for (int i = 0; i < listaTrabajos.length; i++) {
			Trabajo apoyo = listaTrabajos[i];
			if (apoyo != null && apoyo.getIdTotal() == eliminar) {
				listaTrabajos[i] = null;
				
			}
		}
		reajustarArray();
		System.out.println("El trabajo ha sido eliminado de la base de datos.");
	}

	public static void reajustarArray() {

		Trabajo[] nuevoArray = new Trabajo[listaTrabajos.length];
	    int recuperarNumero = 1;
	    for (int i = 0, j = 0; i < listaTrabajos.length; i++) {
	        if (listaTrabajos[i] != null) {
	            nuevoArray[j] = listaTrabajos[i];
	            nuevoArray[j].setNumeroTrabajoLista(recuperarNumero++);
	            j++;
	        }
	    }
	    listaTrabajos = nuevoArray;
	}

	public static void encontrarUltimasPosiciones() {

		int posicionMaxima = 0, trabajoMaximo = 0;

		for (Trabajo buscarMaximo : listaTrabajos) {
			if (buscarMaximo != null && buscarMaximo.getIdTotal() > posicionMaxima) {
				posicionMaxima = buscarMaximo.getIdTotal();

			}
			if (buscarMaximo != null && buscarMaximo.getNumeroTrabajoLista() > trabajoMaximo) {
				trabajoMaximo = buscarMaximo.getNumeroTrabajoLista();
			}

		}
		Trabajo.setId(posicionMaxima);
		Trabajo.setTrabajoLista(trabajoMaximo);
	}
}
