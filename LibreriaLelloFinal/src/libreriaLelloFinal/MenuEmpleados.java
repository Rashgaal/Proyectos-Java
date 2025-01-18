package libreriaLelloFinal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MenuEmpleados {

	static ArrayList<Trabajador> empleados = cargarEmpleadosIniciales();
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static int mostrarMenu() {
		int eleccion = 0;

		System.out.println("---Menú Gestion de Empleados---");
		System.out.println("1 - Añadir empleado");
		System.out.println("2 - Eliminar empleado");
		System.out.println("3 - Editar empleado");
		System.out.println("4 - Listar empleados");
		System.out.println("5 - Modificar turno de trabajo");
		System.out.println("6 - Modificar posicion del vigilante");
		System.out.println("7 - Modificar posicion del controlador");
		System.out.println("8 - Volver al menu inicial");

		try {
			eleccion = Integer.parseInt(br.readLine());
		} catch (NumberFormatException e) {
			System.err.println("No ha introducido un número");
		} catch (IOException e) {
			System.err.println("Error de E/S (mostrarMenu)");
		}

		return eleccion;
	}

	public static void eleccionMenu(int e) {

		switch (e) {
		case 1:
			crearEmpleado();
			break;
		case 2:
			eliminarEmpleado();
			break;
		case 3:
			modificarEmpleado();
			break;
		case 4:
			listarEmpleados();
			break;
		case 5:
			modificarTurno();
			break;
		case 6:
			modificarPosicionVigilante();
			break;
		case 7:
			modificarPosicionControlador();
			break;
		case 8:
			System.out.println("Volviendo al menú inicial");
			break;
		default:
			System.out.println("Debes introducir el numero de la opcion que desea realizar");
			break;
		}
	}

	public static void crearEmpleado() {

		String nombre = null, apellido1 = null, apellido2 = null, dni = null, email = null;
		int telefono = 0;
		int tipo = tipoEmpleado();

		try {
			System.out.println("Indica el nombre");
			nombre = br.readLine().toUpperCase();
			System.out.println("Indica el primer apellido");
			apellido1 = br.readLine().toUpperCase();
			System.out.println("Indica el segundo apellido");
			apellido2 = br.readLine().toUpperCase();
			System.out.println("Indica el DNI");
			dni = br.readLine().toUpperCase();
			System.out.println("Indica el correo electronico");
			email = br.readLine().toUpperCase();
			System.out.println("Indica el telefono");
			telefono = Integer.parseInt(br.readLine());
		} catch (NumberFormatException e) {
			System.err.println("No ha introducido un número");
		} catch (IOException e) {
			System.err.println("Error de E/S (mostrarMenu)");
		}

		switch (tipo) {
		case 1:
			Controlador nuevo1 = new Controlador(nombre, apellido1, apellido2, dni, email, telefono);
			getEmpleados().add(nuevo1);
			break;
		case 2:
			Dependiente nuevo2 = new Dependiente(nombre, apellido1, apellido2, dni, email, telefono);
			getEmpleados().add(nuevo2);
			break;
		case 3:
			Vigilante nuevo3 = new Vigilante(nombre, apellido1, apellido2, dni, email, telefono);
			getEmpleados().add(nuevo3);
			break;
		case 4:
			Reponedor nuevo4 = new Reponedor(nombre, apellido1, apellido2, dni, email, telefono);
			getEmpleados().add(nuevo4);
			break;
		}
	}

	public static int tipoEmpleado() {

		int tipo = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Elige el tipo de empleado");
		System.out.println("1 - Controlador");
		System.out.println("2 - Dependiente");
		System.out.println("3 - Vigilante");
		System.out.println("4 - Reponedor");

		try {
			tipo = Integer.parseInt(br.readLine());
		} catch (NumberFormatException e) {
			System.err.println("No ha introducido un número");
		} catch (IOException e) {
			System.err.println("Error de E/S (mostrarMenu)");
		}

		return tipo;
	}

	public static void listarEmpleados() {
		for (Trabajador ver : getEmpleados()) {
			System.out.println(ver);
		}
	}

	public static void eliminarEmpleado() {

		String dniEliminar = null;
		listarEmpleados();

		try {
			System.out.println("Indica el DNI del empleado que quieres eliminar");
			dniEliminar = br.readLine().toUpperCase();
			Trabajador borrar = null;
			for (Trabajador eliminar : getEmpleados()) {
				if (eliminar.getDni().equals(dniEliminar)) {
					borrar = eliminar;
				}
			}

			if (borrar != null) {
				empleados.remove(borrar);
			}
		} catch (IOException e) {
			System.err.println("Error de E/S (eliminarEmpleado)");
		}
	}

	public static void modificarEmpleado() {

		String dniModificar = null, nombre = null, apellido1 = null, apellido2 = null, dni = null, email = null;
		int telefono = 0;
		listarEmpleados();

		try {
			System.out.println("Indica el DNI del empleado que quieres modificar");
			dniModificar = br.readLine().toUpperCase();

			for (Trabajador modificar : getEmpleados()) {
				if (modificar.getDni().equals(dniModificar)) {
					System.out.println("Indica el nombre");
					nombre = br.readLine().toUpperCase();
					modificar.setNombre(nombre);
					System.out.println("Indica el primer apellido");
					apellido1 = br.readLine().toUpperCase();
					modificar.setApellido1(apellido1);
					System.out.println("Indica el segundo apellido");
					apellido2 = br.readLine().toUpperCase();
					modificar.setApellido2(apellido2);
					System.out.println("Indica el DNI");
					dni = br.readLine().toUpperCase();
					modificar.setDni(dni);
					System.out.println("Indica el correo electronico");
					email = br.readLine().toUpperCase();
					modificar.setEmail(email);
					System.out.println("Indica el telefono");
					telefono = Integer.parseInt(br.readLine());
					modificar.setTelefono(telefono);
				}
			}
		} catch (IOException e) {
			System.err.println("Error de E/S (eliminarEmpleado)");
		}
	}

	public static void modificarTurno() {

		String dniModificar = null;
		boolean turnoCambiado = false;
		listarEmpleados();

		try {
			System.out.println("Indica el DNI del empleado que quieres cambiarle el turno");
			dniModificar = br.readLine().toUpperCase();

			for (Trabajador modTurno : getEmpleados()) {
				if (modTurno.getDni().equals(dniModificar)) {
					if (modTurno.isTurno() == true) {
						modTurno.setTurno(false);
						turnoCambiado = true;
					} else {
						modTurno.setTurno(true);
						turnoCambiado = true;
					}
				}
			}
		} catch (IOException e) {
			System.err.println("Error de E/S (modificarTurno)");
		}
		if (turnoCambiado) {
			System.out.println("Se ha cambiado el turno correctamente");
		} else {
			System.out.println("No se ha cambiado el turno");
		}
	}

	public static void modificarPosicionVigilante() {

		String dniVigilante;
		boolean posicionCambiada = false;
		for (Trabajador ver : empleados) {
			if (ver instanceof Vigilante) {
				System.out.println(ver);
			}
		}

		try {
			System.out.println("Indica el DNI del vigilante que quieres cambiarle la posicion");
			dniVigilante = br.readLine().toUpperCase();

			for (Trabajador modPosicionVig : getEmpleados()) {
				if (modPosicionVig.getDni().equals(dniVigilante) && modPosicionVig instanceof Vigilante) {
					if (((Vigilante) modPosicionVig).isPosicion()) {
						((Vigilante) modPosicionVig).setPosicion(false);
						posicionCambiada = true;
					} else {
						((Vigilante) modPosicionVig).setPosicion(true);
						posicionCambiada = true;
					}
				}
			}
		} catch (IOException e) {
			System.err.println("Error de E/S (mod posicion vigilante)");
		}
		if (posicionCambiada) {
			System.out.println("Se ha cambiado la posicion correctamente");
		} else {
			System.out.println("No se ha cambiado la posicion");
		}
	}

	public static void modificarPosicionControlador() {

		String dniControlador;
		boolean posicionCambiada = false;
		for (Trabajador ver : getEmpleados()) {
			if (ver instanceof Controlador) {
				System.out.println(ver);
			}
		}

		try {
			System.out.println("Indica el DNI del controlador que quieres cambiarle la posicion");
			dniControlador = br.readLine().toUpperCase();

			for (Trabajador modPosicionContr : getEmpleados()) {
				if (modPosicionContr.getDni().equals(dniControlador) && modPosicionContr instanceof Controlador) {
					if (((Controlador) modPosicionContr).isLugar()) {
						((Controlador) modPosicionContr).setLugar(false);
						posicionCambiada = true;
					} else {
						((Controlador) modPosicionContr).setLugar(true);
						posicionCambiada = true;
					}
				}
			}
		} catch (IOException e) {
			System.err.println("Error de E/S (mod posicion controlador)");
		}
		if (posicionCambiada) {
			System.out.println("Se ha cambiado la posicion correctamente");
		} else {
			System.out.println("No se ha cambiado la posicion");
		}
	}

	// Método que obtiene la lista de dependientes
	public static List<Dependiente> obtenerListaDependientes() {
		List<Dependiente> dependientes = new ArrayList<>();
		for (Trabajador ver : empleados) {
			if (ver instanceof Dependiente) {
				dependientes.add((Dependiente) ver);
			}
		}
		return dependientes;
	}

	// Método que imprime la lista de dependientes
	public static void listarDependientes() {
		List<Dependiente> dependientes = obtenerListaDependientes();
		for (Dependiente dependiente : dependientes) {
			System.out.println("DNI: " + dependiente.getDni() + " | " + dependiente.getNombre() + " "
					+ dependiente.getApellido1() + " " + dependiente.getApellido2());
		}
	}

	public static ArrayList<Trabajador> cargarEmpleadosIniciales() {
		ArrayList<Trabajador> cargar = new ArrayList<Trabajador>();

		Vigilante vigilante1 = new Vigilante("JUAN", "PEREZ", "GOMEZ", "12345678A", "JUAN.PEREZ@EXAMPLE.COM",
				600123456);
		Vigilante vigilante2 = new Vigilante("ANA", "LOPEZ", "MARTINEZ", "23456789B", "ANA.LOPEZ@EXAMPLE.COM",
				600234567);
		Vigilante vigilante3 = new Vigilante("CARLOS", "GARCIA", "FERNANDEZ", "34567890C", "CARLOS.GARCIA@EXAMPLE.COM",
				600345678);
		Vigilante vigilante4 = new Vigilante("MARIA", "SANCHEZ", "RODRIGUEZ", "45678901D", "MARIA.SANCHEZ@EXAMPLE.COM",
				600456789);

		Reponedor reponedor1 = new Reponedor("PEDRO", "HERNANDEZ", "LOPEZ", "56789012E", "PEDRO.HERNANDEZ@EXAMPLE.COM",
				600567890);
		Reponedor reponedor2 = new Reponedor("LUCIA", "DIAZ", "PEREZ", "67890123F", "LUCIA.DIAZ@EXAMPLE.COM",
				600678901);
		Reponedor reponedor3 = new Reponedor("MIGUEL", "JIMENEZ", "GONZALEZ", "78901234G", "MIGUEL.JIMENEZ@EXAMPLE.COM",
				600789012);
		Reponedor reponedor4 = new Reponedor("LAURA", "MORALES", "VAZQUEZ", "89012345H", "LAURA.MORALES@EXAMPLE.COM",
				600890123);

		Controlador controlador1 = new Controlador("JOSE", "TORRES", "MENDEZ", "90123456I", "JOSE.TORRES@EXAMPLE.COM",
				600901234);
		Controlador controlador2 = new Controlador("ELENA", "ROMERO", "CRUZ", "01234567J", "ELENA.ROMERO@EXAMPLE.COM",
				600012345);
		Controlador controlador3 = new Controlador("RAUL", "NAVARRO", "ORTEGA", "12345678K", "RAUL.NAVARRO@EXAMPLE.COM",
				600123456);
		Controlador controlador4 = new Controlador("CARMEN", "RUBIO", "SANTOS", "23456789L", "CARMEN.RUBIO@EXAMPLE.COM",
				600234567);

		Dependiente dependiente1 = new Dependiente("SERGIO", "RUIZ", "FLORES", "34567890M", "SERGIO.RUIZ@EXAMPLE.COM",
				600345678);
		Dependiente dependiente2 = new Dependiente("PATRICIA", "MARIN", "PENA", "45678901N",
				"PATRICIA.MARIN@EXAMPLE.COM", 600456789);
		Dependiente dependiente3 = new Dependiente("JAVIER", "CASTRO", "RAMOS", "56789012O",
				"JAVIER.CASTRO@EXAMPLE.COM", 600567890);
		Dependiente dependiente4 = new Dependiente("MARTA", "ORTIZ", "GIL", "67890123P", "MARTA.ORTIZ@EXAMPLE.COM",
				600678901);

		cargar.add(vigilante1);
		cargar.add(vigilante2);
		cargar.add(vigilante3);
		cargar.add(vigilante4);
		cargar.add(reponedor1);
		cargar.add(reponedor2);
		cargar.add(reponedor3);
		cargar.add(reponedor4);
		cargar.add(controlador1);
		cargar.add(controlador2);
		cargar.add(controlador3);
		cargar.add(controlador4);
		cargar.add(dependiente1);
		cargar.add(dependiente2);
		cargar.add(dependiente3);
		cargar.add(dependiente4);

		return cargar;
	}

	public static ArrayList<Trabajador> getEmpleados() {
		return empleados;
	}

	public static void setEmpleados(ArrayList<Trabajador> empleados) {
		MenuEmpleados.empleados = empleados;
	}

}
