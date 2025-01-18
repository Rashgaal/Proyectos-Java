package hospital;

import java.sql.Date;
import java.util.ArrayList;

public class MenusHospital {

	public static int menuPrincipal() {

		System.out.println("---BIENVENIDO A LA BASE DE DATOS DEL HOSPITAL---");
		MetodosAuxiliares.cargando(2);
		System.out.println("1 - Gestionar departamentos");
		System.out.println("2 - Gestionar especialidades");
		System.out.println("3 - Gestionar trabajadores");
		System.out.println("4 - Salir");

		return Integer.parseInt(MetodosAuxiliares.leerCadena("Marca el numero del menu al que quieres acceder"));
	}

	public static void opcionesMenuPrincipal(int a) {
		switch (a) {
		case 1:
			int opcionDepartamento;
			do {
				opcionDepartamento = menuDepartamentos();
				opcionesMenuDepartamentos(opcionDepartamento);
			} while (opcionDepartamento != 5);
			break;
		case 2:
			int opcionEspecialidad;
			do {
				opcionEspecialidad = menuEspecialidades();
				opcionesMenuEspecialidades(opcionEspecialidad);
			} while (opcionEspecialidad != 5);
			break;
		case 3:
			int opcionTrabajador;
			do {
				opcionTrabajador = menuTrabajadores();
				opcionesMenuTrabajadores(opcionTrabajador);
			} while (opcionTrabajador != 7);
			break;
		case 4:
			System.out.println("Guardando datos...");
			MetodosAuxiliares.cargando(2);
			ConectorHospital.desconectarBD();
			System.out.println("Datos guardados");
			break;
		default:
			System.out.println("Debes introducir el numero de la opcion que quieres realizar");
			break;
		}
	}

	public static int menuDepartamentos() {

		System.out.println("1 - Crear un departamento");
		System.out.println("2 - Borrar un departamento");
		System.out.println("3 - Modificar un departamento");
		System.out.println("4 - Listar departamentos (numero de especialidades)");
		System.out.println("5 - Volver al menu principal");

		return Integer.parseInt(MetodosAuxiliares.leerCadena("Indica el numero de la opcion que deseas realizar"));
	}

	public static void opcionesMenuDepartamentos(int b) {
		switch (b) {
		case 1:
			if (ConectorHospital.agregarDepartamento(departamentoApoyo())) {
				System.out.println("Departamento agregado");
			} else {
				System.out.println("Departamento no agregado");
			}
			break;
		case 2:
			ArrayList<Departamento> mostrarEliminar = ConectorHospital.listarTodosDepartamentos();
			if (mostrarEliminar.isEmpty()) {
				System.out.println("No existen departamentos actualmente");
			} else {
				for (Departamento ver : mostrarEliminar) {
					System.out.println("ID --> " + ver.getIdDepartamento() + " | " + ver.getNombre());
				}
				MetodosAuxiliares.cargando(3);
				int depEliminar = Integer
						.parseInt(MetodosAuxiliares.leerCadena("Indica la ID del departamento que quieres eliminar"));
				if (ConectorHospital.comprobarTrabajadoresParaNoBorrar(depEliminar)) {
					System.out.println("No se puede eliminar el departamento ya que hay trabajadores asociados");
				} else {
					if (ConectorHospital.comprobarEspecialidadesAsociadas(depEliminar)) {
						String respuestaEliminarEsp = MetodosAuxiliares.leerCadena(
								"El departamento que quiere eliminar tiene especialidades asociadas\n¿Estás seguro de que quieres eliminar el departamento y sus especialidades asociadas?")
								.toLowerCase();
						if (respuestaEliminarEsp.equals("si")) {
							if (ConectorHospital.eliminarEspecialidadesAsociadas(depEliminar)) {
								System.out.println("Las especialidades asociadas al departamento han sido eliminadas");
							} else {
								System.out
										.println("Las especialidades asociadas al departamento no han sido eliminadas");
							}
							if (ConectorHospital.eliminarDepartamento(depEliminar)) {
								System.out.println("Departamento eliminado correctamente");
							} else {
								System.out.println("El departamento no se ha eliminado");
							}
						}
					} else {
						if (ConectorHospital.eliminarDepartamento(depEliminar)) {
							System.out.println("Departamento eliminado correctamente");
						} else {
							System.out.println("El departamento no se ha eliminado");
						}
					}
				}
			}
			break;
		case 3:
			ArrayList<Departamento> mostrarModificar = ConectorHospital.listarTodosDepartamentos();
			if (mostrarModificar.isEmpty()) {
				System.out.println("No existen departamentos actualmente");
			} else {
				for (Departamento ver : mostrarModificar) {
					System.out.println(ver.toString());
				}
				MetodosAuxiliares.cargando(3);
				int depModificar = Integer
						.parseInt(MetodosAuxiliares.leerCadena("Indica la ID del departamento que quieres modificar"));
				if (ConectorHospital.modificarDepartamento(depModificar, departamentoApoyo())) {
					System.out.println("Se ha modificado el departamento correctamente");
				} else {
					System.out.println("El departamento no se ha modificado");
				}
			}
			break;
		case 4:
			ArrayList<String> informacion = ConectorHospital.listarDptoNumeroEspecialidades();
			if (informacion.isEmpty()) {
				System.out.println("No hay informacion en este momento");
			} else {
				MetodosAuxiliares.cargando(2);
				for (String mostrar : informacion) {
					System.out.println(mostrar);
				}
			}
			break;
		case 5:
			System.out.println("Volviendo al menu principal");
			MetodosAuxiliares.cargando(2);
			break;
		default:
			System.out.println("Debes introducir el numero de la opcion que quieres realizar");
			break;
		}
	}

	public static int menuEspecialidades() {

		System.out.println("1 - Crear una especialidad");
		System.out.println("2 - Borrar una especialidad");
		System.out.println("3 - Modificar una especialidad");
		System.out.println("4 - Listar especialidad (salario de sus trabajadores)");
		System.out.println("5 - Volver al menu principal");

		return Integer.parseInt(MetodosAuxiliares.leerCadena("Indica el numero de la opcion que deseas realizar"));
	}

	public static void opcionesMenuEspecialidades(int c) {
		switch (c) {
		case 1:
			if (ConectorHospital.comprobarQueHayDepartamento()) {
				Especialidad añadir = especialidadApoyo();
				if (ConectorHospital.agregarEspecialidad(añadir)) {
					System.out.println("Especialidad agregada");
				} else {
					System.out.println("Especialidad no agregada");
				}
			} else {
				System.out.println("No se pueden crear especialidades si no existen departamentos");
				MetodosAuxiliares.cargando(2);
				String respuestaCrearDpto = MetodosAuxiliares.leerCadena("¿Quieres crear ahora un departamento?")
						.toLowerCase();
				if (respuestaCrearDpto.equals("si")) {
					if (ConectorHospital.agregarDepartamento(departamentoApoyo())) {
						System.out.println("Departamento agregado");
					} else {
						System.out.println("Departamento no agregado");
					}
				}
			}
			break;
		case 2:
			ArrayList<Especialidad> mostrarEliminar = ConectorHospital.listarTodasEspecialidades();
			if (mostrarEliminar.isEmpty()) {
				System.out.println("No existen especialidades actualmente");
			} else {
				for (Especialidad ver : mostrarEliminar) {
					System.out.println("ID --> " + ver.getIdEspecialidad() + " | " + ver.getNombre());
				}
				MetodosAuxiliares.cargando(2);
				int espEliminar = Integer
						.parseInt(MetodosAuxiliares.leerCadena("Indica la ID de la especialidad que quieres eliminar"));
				if (ConectorHospital.comprobarTrabajadoresEspecialidad(espEliminar)) {
					System.out.println("No se puede borrar la especialidad porque tiene trabajadores asociados");
				} else {
					if (ConectorHospital.eliminarEspecialidad(espEliminar)) {
						System.out.println("Especialidad eliminada correctamente");
					} else {
						System.out.println("La especialidad no se ha eliminado");
					}
				}
			}
			break;
		case 3:
			ArrayList<Especialidad> mostrarModificar = ConectorHospital.listarTodasEspecialidades();
			if (mostrarModificar.isEmpty()) {
				System.out.println("No existen especialidades actualmente");
			} else {
				for (Especialidad ver : mostrarModificar) {
					System.out.println(ver.toString());
				}
				MetodosAuxiliares.cargando(3);
				int espModificar = Integer.parseInt(
						MetodosAuxiliares.leerCadena("Indica la ID de la especialidad que quieres modificar"));
				if (ConectorHospital.modificarEspecialidad(espModificar, especialidadApoyo())) {
					System.out.println("Se ha modificado la especialidad correctamente");
				} else {
					System.out.println("La especialidad no se ha modificado");
				}
			}
			break;
		case 4:
			ArrayList<String> informacion = ConectorHospital.listarEspecialidadesPorSalario();
			if (informacion.isEmpty()) {
				System.out.println("No hay informacion en este momento");
			} else {
				MetodosAuxiliares.cargando(2);
				for (String mostrar : informacion) {
					System.out.println(mostrar);
				}
			}
			break;
		case 5:
			System.out.println("Volviendo al menu principal");
			MetodosAuxiliares.cargando(2);
			break;
		default:
			System.out.println("Debes introducir el numero de la opcion que quieres realizar");
			break;
		}
	}

	public static int menuTrabajadores() {

		System.out.println("1 - Crear un trabajador");
		System.out.println("2 - Borrar un trabajador");
		System.out.println("3 - Modificar un trabajador");
		System.out.println("4 - Listar trabajadores (salario)");
		System.out.println("5 - Listar trabajadores (especialidad)");
		System.out.println("6 - Listar trabajadores (departamento)");
		System.out.println("7 - Volver al menu principal");

		return Integer.parseInt(MetodosAuxiliares.leerCadena("Indica el numero de la opcion que deseas realizar"));
	}

	public static void opcionesMenuTrabajadores(int d) {
		switch (d) {
		case 1:
			if (ConectorHospital.comprobarQueHayEspecialidad()) {
				if (ConectorHospital.agregarTrabajador(trabajadorApoyo())) {
					System.out.println("Trabajador agregado");
				} else {
					System.out.println("Trabajador no agregado");
				}
			} else {
				String respuestaCrearEspecialidad = MetodosAuxiliares.leerCadena(
						"No se pueden crear trabajadores si no existen especialidades.\n¿Quieres crear ahora una especialidad?")
						.toLowerCase();
				if (respuestaCrearEspecialidad.equals("si")) {
					if (ConectorHospital.comprobarQueHayDepartamento()) {
						if (ConectorHospital.agregarEspecialidad(especialidadApoyo())) {
							System.out.println("Especialidad agregada");
						} else {
							System.out.println("Especialidad no agregada");
						}
					} else {
						String respuestaCrearDepartamento = MetodosAuxiliares.leerCadena(
								"No se pueden crear especialidades si no existen departamentos.\n¿Quieres crear ahora un departamento?")
								.toLowerCase();
						if (respuestaCrearDepartamento.equals("si")) {
							if (ConectorHospital.agregarDepartamento(departamentoApoyo())) {
								System.out.println("Departamento agregado");
							} else {
								System.out.println("Departamento no agregado");
							}
						}
					}
				}
			}
			break;
		case 2:
			ArrayList<Trabajador> mostrarEliminar = ConectorHospital.listarTodosTrabajadores();
			if (mostrarEliminar.isEmpty()) {
				System.out.println("No existen trabajadores actualmente");
			} else {
				for (Trabajador ver : mostrarEliminar) {
					System.out.println("ID --> " + ver.getIdTrabajador() + " | " + ver.getNombre());
				}
				MetodosAuxiliares.cargando(2);
				int traEliminar = Integer
						.parseInt(MetodosAuxiliares.leerCadena("Indica la ID del trabajador que quieres eliminar"));
				if (ConectorHospital.eliminarTrabajador(traEliminar)) {
					System.out.println("Trabajador eliminado correctamente");
				} else {
					System.out.println("El trabajador no se ha eliminado");
				}
			}
			break;
		case 3:
			ArrayList<Trabajador> mostrarModificar = ConectorHospital.listarTodosTrabajadores();
			if (mostrarModificar.isEmpty()) {
				System.out.println("No existen trabajadores actualmente");
			} else {
				for (Trabajador ver : mostrarModificar) {
					System.out.println(ver.toString());
				}
				MetodosAuxiliares.cargando(2);
				int traModificar = Integer
						.parseInt(MetodosAuxiliares.leerCadena("Indica la ID del trabajador que quieres modificar"));
				if (ConectorHospital.modificarTrabajador(traModificar, trabajadorApoyo())) {
					System.out.println("Se ha modificado el trabajador correctamente");
				} else {
					System.out.println("El trabajador no se ha modificado");
				}
			}
			break;
		case 4:
			ArrayList<String> listaSalario = ConectorHospital.trabajadoresPorSalario();
			if (listaSalario.isEmpty()) {
				System.out.println("No hay informacion en este momento");
			} else {
				MetodosAuxiliares.cargando(3);
				for (String mostrar : listaSalario) {
					System.out.println(mostrar);
				}
			}
			break;
		case 5:
			ArrayList<String> listaEspecialidades = ConectorHospital.trabajadoresPorEsp();
			if (listaEspecialidades.isEmpty()) {
				System.out.println("No hay informacion en este momento");
			} else {
				MetodosAuxiliares.cargando(3);
				for (String mostrar : listaEspecialidades) {
					System.out.println(mostrar);
				}
			}
			break;
		case 6:
			ArrayList<String> listaDepartamentos = ConectorHospital.trabajadoresPorDpto();
			if (listaDepartamentos.isEmpty()) {
				System.out.println("No hay informacion en este momento");
			} else {
				MetodosAuxiliares.cargando(3);
				for (String mostrar : listaDepartamentos) {
					System.out.println(mostrar);
				}
			}
			break;
		case 7:
			System.out.println("Volviendo al menu principal");
			MetodosAuxiliares.cargando(2);
			break;
		default:
			System.out.println("Debes introducir el numero de la opcion que quieres realizar");
			break;
		}
	}

	public static Departamento departamentoApoyo() {
		Departamento apoyoDep = new Departamento();
		apoyoDep.setNombre(MetodosAuxiliares.leerCadena("Indica el nombre del departamento"));
		do {
			apoyoDep.setPlanta(Integer
					.parseInt(MetodosAuxiliares.leerCadena("Indica la planta donde se encuentra el departamento")));
			if (apoyoDep.getPlanta() > 15 || apoyoDep.getPlanta() < 0) {
				System.out.println("Las plantas del hospital van de la 0 a la 15");
			}
		} while (apoyoDep.getPlanta() < 0 || apoyoDep.getPlanta() > 15);
		return apoyoDep;
	}

	public static Especialidad especialidadApoyo() {
		Especialidad apoyoEsp = new Especialidad();
		apoyoEsp.setNombre(MetodosAuxiliares.leerCadena("Indica el nombre de la especialidad"));
		int orientacion = Integer.parseInt(
				MetodosAuxiliares.leerCadena("Indica la orientacion\n1 - NORTE\n2 - SUR\n3 - ESTE\n4 - OESTE"));
		do {
			switch (orientacion) {
			case 1:
				apoyoEsp.setOrientacion(Orientacion.NORTE);
				break;
			case 2:
				apoyoEsp.setOrientacion(Orientacion.SUR);
				break;
			case 3:
				apoyoEsp.setOrientacion(Orientacion.ESTE);
				break;
			case 4:
				apoyoEsp.setOrientacion(Orientacion.OESTE);
				break;
			default:
				System.out.println("Debes introducir el número de una orientacion");
				break;
			}
		} while (orientacion > 4 || orientacion < 1);
		ArrayList<Departamento> ver = ConectorHospital.listarTodosDepartamentos();
		for (Departamento mostrar : ver) {
			System.out.println("ID --> " + mostrar.getIdDepartamento() + " | " + mostrar.getNombre());
		}
		apoyoEsp.setIdDepartamento(Integer.parseInt(
				MetodosAuxiliares.leerCadena("Indica la ID del departamento al que pertenece la especialidad")));
		return apoyoEsp;
	}

	public static Trabajador trabajadorApoyo() {
		Trabajador apoyoTra = new Trabajador();
		apoyoTra.setNombre(MetodosAuxiliares.leerCadena("Indica el nombre del trabajador"));
		apoyoTra.setApellido(MetodosAuxiliares.leerCadena("Indica el apellido del trabajador"));

		int dia = Integer.parseInt(MetodosAuxiliares.leerCadena("Indica el dia de nacimiento del trabajador"));
		int mes = Integer.parseInt(MetodosAuxiliares.leerCadena("Indica el mes de nacimiento del trabajador"));
		int año = Integer.parseInt(MetodosAuxiliares.leerCadena("Indica el año de nacimiento del trabajador"));

		Date fechaNacimiento = Date.valueOf(año + "-" + mes + "-" + dia);
		apoyoTra.setFechaNacimiento(fechaNacimiento);

		apoyoTra.setSalario(Double.parseDouble(MetodosAuxiliares.leerCadena("Indica el sueldo del trabajador")));
		ArrayList<Especialidad> ver = ConectorHospital.listarTodasEspecialidades();
		for (Especialidad mostrar : ver) {
			System.out.println("ID --> " + mostrar.getIdEspecialidad() + " | " + mostrar.getNombre());
		}
		apoyoTra.setIdEspecialidad(Integer.parseInt(
				MetodosAuxiliares.leerCadena("Indica la ID de la especialidad a la que pertenece el trabajador")));
		return apoyoTra;
	}
}
