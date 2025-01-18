package hospital;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.TimeZone;

public class ConectorHospital {

	private String bbdd = "hospital";
	private String login = "root";
	private String password = "";
	private String url = "jdbc:mysql://localhost:3306/" + bbdd + "?serverTimezone=" + TimeZone.getDefault().getID();
	private static Connection con = null;

	public ConectorHospital() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("No se encontró el Driver");
		}

		try {
			con = DriverManager.getConnection(url, login, password);
		} catch (SQLException e) {
			System.err.println("Error al conectar con la base de datos");
		}

		if (con != null) {
			Statement orden;
			try {
				orden = con.createStatement();
				int i = orden.executeUpdate(
						"CREATE TABLE IF NOT EXISTS `joaquin_departamentos` (\n`idDepartamento` int(10) PRIMARY KEY AUTO_INCREMENT,\n`nombreDepartamento` VARCHAR(30) NOT NULL,\n`planta` int(10) NOT NULL\n)ENGINE=InnoDB;");
				// System.err.println("Primera tabla creada, si 0 bien --> " + i); esto
				// comprueba que la tabla se hace

				int j = orden.executeUpdate(
						"CREATE TABLE IF NOT EXISTS `joaquin_especialidades` (\n`idEspecialidad` int(10) PRIMARY KEY AUTO_INCREMENT,\n`nombreEspecialidad` VARCHAR(30) NOT NULL,\n`orientacion` ENUM('NORTE','SUR','ESTE','OESTE') NOT NULL,\n`idDepartamento` int(10) NOT NULL,\nFOREIGN KEY(`idDepartamento`) REFERENCES `joaquin_departamentos`(`idDepartamento`)\n)ENGINE=InnoDB;");
				// System.err.println("Segunda tabla creada, si 0 bien --> " + j); esto
				// comprueba que la tabla se hace
				int k = orden.executeUpdate(
						"CREATE TABLE IF NOT EXISTS `joaquin_trabajadores` (\n`idTrabajador` int(10) PRIMARY KEY AUTO_INCREMENT,\n`nombreTrabajador` VARCHAR(30) NOT NULL,\n`apellidoTrabajador` VARCHAR(30) NOT NULL,\n`fechaNacimiento` DATE NOT NULL,\n`salario` DECIMAL(6,2) NOT NULL,\n`idEspecialidad` INT(10) NOT NULL,\nFOREIGN KEY(`idEspecialidad`) REFERENCES `joaquin_especialidades`(`idEspecialidad`)\n)ENGINE=InnoDB;");
				// System.err.println("Tercera tabla creada, si 0 bien --> " + k); esto
				// comprueba que la tabla se hace
				orden.close();
			} catch (SQLException e) {
				System.err.println("Error al crear las tablas");
			}
		}
	}

	public static void desconectarBD() {
		con = null;
	}

	public static boolean agregarDepartamento(Departamento agregar) {
		boolean agregado = false;

		try {
			Statement añadir = con.createStatement();
			int i = añadir.executeUpdate("INSERT INTO `joaquin_departamentos`(`nombreDepartamento`,`planta`) VALUES ('"
					+ agregar.getNombre() + "', " + agregar.getPlanta() + ");");
			if (i > 0) {
				agregado = true;
			}
			añadir.close();
		} catch (SQLException e) {
			System.err.println("Error de E/S agregarDpto");
		}
		return agregado;
	}

	public static boolean modificarDepartamento(int idModificar, Departamento modificar) {

		boolean cambiado = false;
		try {
			Statement cambio = con.createStatement();
			int i = cambio.executeUpdate("UPDATE `joaquin_departamentos` SET `nombreDepartamento` = '"
					+ modificar.getNombre() + "', `planta` = " + modificar.getPlanta() + " WHERE `idDepartamento` = "
					+ idModificar + ";");
			if (i > 0) {
				cambiado = true;
			}
			cambio.close();
		} catch (SQLException e) {
			System.err.println("Error de E/S modificarDpto");
		}
		return cambiado;
	}

	public static boolean eliminarDepartamento(int idEliminar) {

		boolean eliminado = false;

		try {
			Statement borrar = con.createStatement();
			int i = borrar
					.executeUpdate("DELETE FROM `joaquin_departamentos` WHERE `idDepartamento` = " + idEliminar + ";");
			if (i > 0) {
				eliminado = true;
			}
			borrar.close();
		} catch (SQLException e) {
			System.err.println("Error de E/S eliminarDpto");
		}
		return eliminado;
	}

	public static boolean comprobarTrabajadoresParaNoBorrar(int idDepEliminar) {
		boolean hayTrabajadores = false;

		try {
			Statement comprobarTrabajadores = con.createStatement();
			ResultSet resSet;
			resSet = comprobarTrabajadores.executeQuery(
					"SELECT COUNT(joaquin_trabajadores.idTrabajador) AS totalTrabajadores FROM joaquin_trabajadores WHERE joaquin_trabajadores.idEspecialidad IN (SELECT joaquin_especialidades.idEspecialidad FROM joaquin_especialidades WHERE idDepartamento = "
							+ idDepEliminar + ");");
			if (resSet.next()) {
				int contarTrabajadores = resSet.getInt("totalTrabajadores");
				if (contarTrabajadores > 0) {
					hayTrabajadores = true;
				}
			}
			comprobarTrabajadores.close();
		} catch (SQLException e) {
			System.err.println("Error al comprobar si hay trabajadores asociados");
		}
		return hayTrabajadores;
	}

	public static boolean comprobarEspecialidadesAsociadas(int idDepEliminar) {
		boolean hayEspecialidades = false;

		try {
			Statement comprobarEsp = con.createStatement();
			ResultSet resSet;
			resSet = comprobarEsp.executeQuery(
					"SELECT COUNT(joaquin_especialidades.idEspecialidad) AS totalEspecialidades FROM joaquin_especialidades WHERE idDepartamento = "
							+ idDepEliminar + ";");
			if (resSet.next()) {
				int contarEsp = resSet.getInt("totalEspecialidades");
				if (contarEsp > 0) {
					hayEspecialidades = true;
				}
			}
			comprobarEsp.close();
		} catch (SQLException e) {
			System.err.println("Error al comprobar si hay especialidades asociadas");
		}
		return hayEspecialidades;
	}

	public static boolean eliminarEspecialidadesAsociadas(int idDepAsociado) {
		boolean Espeliminadas = false;

		try {
			Statement borrarEsp = con.createStatement();
			int i = borrarEsp.executeUpdate(
					"DELETE FROM `joaquin_especialidades` WHERE `idDepartamento` = " + idDepAsociado + ";");
			if (i > 0) {
				Espeliminadas = true;
			}
			borrarEsp.close();
		} catch (SQLException e) {
			System.err.println("Error al eliminar especialidades asociadas al departamento");
		}
		return Espeliminadas;
	}

	public static boolean agregarEspecialidad(Especialidad agregar) {
		boolean agregado = false;

		try {
			Statement añadir = con.createStatement();
			int i = añadir.executeUpdate(
					"INSERT INTO `joaquin_especialidades`(`nombreEspecialidad`,`orientacion`,`idDepartamento`) VALUES ('"
							+ agregar.getNombre() + "', '" + agregar.getOrientacion() + "', "
							+ agregar.getIdDepartamento() + ");");
			if (i > 0) {
				agregado = true;
			}
			añadir.close();
		} catch (SQLException e) {
			System.err.println("Error de E/S agregarEsp");
		}
		return agregado;
	}

	public static boolean comprobarQueHayDepartamento() {
		boolean hayDpto = false;

		try {
			Statement comprobarDep = con.createStatement();
			ResultSet resSet;
			resSet = comprobarDep.executeQuery("SELECT COUNT(`idDepartamento`) FROM `joaquin_departamentos`;");
			if (resSet.next()) {
				int contarDptos = resSet.getInt(1);
				if (contarDptos > 0) {
					hayDpto = true;
				}
			}
			comprobarDep.close();
		} catch (SQLException e) {
			System.err.println("Error al comprobar si hay departamentos");
		}
		return hayDpto;
	}

	public static boolean modificarEspecialidad(int idModificar, Especialidad modificar) {

		boolean cambiado = false;
		try {
			Statement cambio = con.createStatement();
			int i = cambio.executeUpdate(
					"UPDATE `joaquin_especialidades` SET `nombreEspecialidad` = '" + modificar.getNombre()
							+ "', `orientacion` = '" + modificar.getOrientacion() + "', `idDepartamento` = "
							+ modificar.getIdDepartamento() + " WHERE `idEspecialidad` = " + idModificar + ";");
			if (i > 0) {
				cambiado = true;
			}
			cambio.close();
		} catch (SQLException e) {
			System.err.println("Error de E/S modificarEsp");
		}
		return cambiado;
	}

	public static boolean eliminarEspecialidad(int idEliminar) {

		boolean eliminado = false;

		try {
			Statement borrar = con.createStatement();
			int i = borrar
					.executeUpdate("DELETE FROM `joaquin_especialidades` WHERE `idEspecialidad` = " + idEliminar + ";");
			if (i > 0) {
				eliminado = true;
			}
			borrar.close();
		} catch (SQLException e) {
			System.err.println("Error de E/S eliminarEsp");
		}
		return eliminado;
	}

	public static boolean comprobarTrabajadoresEspecialidad(int idEspEliminar) {

		boolean hayTrabajadores = false;

		try {
			Statement comprobarTrabajadores = con.createStatement();
			ResultSet resSet;
			resSet = comprobarTrabajadores
					.executeQuery("SELECT COUNT(`idTrabajador`) FROM `joaquin_trabajadores` WHERE `idEspecialidad` = "
							+ idEspEliminar + ";");
			if (resSet.next()) {
				int contarTrabajadores = resSet.getInt(1);
				if (contarTrabajadores > 0) {
					hayTrabajadores = true;
				}
			}
			comprobarTrabajadores.close();
		} catch (SQLException e) {
			System.err.println("Error al comprobar si hay trabajadores asociados");
		}
		return hayTrabajadores;
	}

	public static boolean agregarTrabajador(Trabajador agregar) {
		boolean agregado = false;

		try {
			Statement añadir = con.createStatement();
			int i = añadir.executeUpdate(
					"INSERT INTO `joaquin_trabajadores`(`nombreTrabajador`,`apellidoTrabajador`,`fechaNacimiento`,`salario`,`idEspecialidad`) VALUES ('"
							+ agregar.getNombre() + "', '" + agregar.getApellido() + "', '"
							+ agregar.getFechaNacimiento() + "', " + agregar.getSalario() + ", "
							+ agregar.getIdEspecialidad() + ");");
			if (i > 0) {
				agregado = true;
			}
			añadir.close();
		} catch (SQLException e) {
			System.err.println("Error de E/S agregarTra");
		}

		return agregado;
	}

	public static boolean comprobarQueHayEspecialidad() {
		boolean hayEsp = false;

		try {
			Statement comprobarEsp = con.createStatement();
			ResultSet resSet;
			resSet = comprobarEsp.executeQuery("SELECT COUNT(`idEspecialidad`) FROM `joaquin_especialidades`;");
			if (resSet.next()) {
				int contarEsp = resSet.getInt(1);
				if (contarEsp > 0) {
					hayEsp = true;
				}
			}
			comprobarEsp.close();
		} catch (SQLException e) {
			System.err.println("Error al comprobar si hay especialidades");
		}
		return hayEsp;
	}

	public static boolean modificarTrabajador(int idModificar, Trabajador modificar) {

		boolean cambiado = false;
		try {
			Statement cambio = con.createStatement();
			int i = cambio.executeUpdate("UPDATE `joaquin_trabajadores` SET `nombreTrabajador` = '"
					+ modificar.getNombre() + "', `apellidoTrabajador` = '" + modificar.getApellido()
					+ "', `fechaNacimiento` = '" + modificar.getFechaNacimiento() + "', `salario` = "
					+ modificar.getSalario() + ", `idEspecialidad` = " + modificar.getIdEspecialidad()
					+ " WHERE `idTrabajador` = " + idModificar + ";");
			if (i > 0) {
				cambiado = true;
			}
			cambio.close();
		} catch (SQLException e) {
			System.err.println("Error de E/S modificarTra");
		}
		return cambiado;
	}

	public static boolean eliminarTrabajador(int idEliminar) {

		boolean eliminado = false;

		try {
			Statement borrar = con.createStatement();
			int i = borrar
					.executeUpdate("DELETE FROM `joaquin_trabajadores` WHERE `idTrabajador` = " + idEliminar + ";");
			if (i > 0) {
				eliminado = true;
			}
			borrar.close();
		} catch (SQLException e) {
			System.err.println("Error de E/S eliminarTra");
		}
		return eliminado;
	}

	public static ArrayList<Departamento> listarTodosDepartamentos() {

		ResultSet resSet;
		ArrayList<Departamento> listarTodosDep = new ArrayList<Departamento>();

		try {
			Statement listarDep = con.createStatement();
			resSet = listarDep.executeQuery("SELECT * FROM `joaquin_departamentos`;");
			while (resSet.next()) {
				Departamento listar = new Departamento();
				listar.setIdDepartamento(resSet.getInt("idDepartamento"));
				listar.setNombre(resSet.getString("nombreDepartamento"));
				listar.setPlanta(resSet.getInt("planta"));
				listarTodosDep.add(listar);
			}
			listarDep.close();
		} catch (SQLException e) {
			System.err.println("Error de E/S listarDepartamentos");
		}
		return listarTodosDep;
	}

	public static ArrayList<Especialidad> listarTodasEspecialidades() {

		ResultSet resSet;
		ArrayList<Especialidad> listarTodosEsp = new ArrayList<Especialidad>();

		try {
			Statement listarEsp = con.createStatement();
			resSet = listarEsp.executeQuery("SELECT * FROM `joaquin_especialidades`;");
			while (resSet.next()) {
				Especialidad listar = new Especialidad();
				listar.setIdEspecialidad(resSet.getInt("idEspecialidad"));
				listar.setNombre(resSet.getString("nombreEspecialidad"));
				listar.setOrientacion(Orientacion.valueOf(resSet.getString("orientacion")));
				listar.setIdDepartamento(resSet.getInt("idDepartamento"));
				listarTodosEsp.add(listar);
			}
			listarEsp.close();
		} catch (SQLException e) {
			System.err.println("Error de E/S listarDepartamentos");
		}
		return listarTodosEsp;
	}

	public static ArrayList<Trabajador> listarTodosTrabajadores() {

		ResultSet resSet;
		ArrayList<Trabajador> listarTodosTra = new ArrayList<Trabajador>();

		try {
			Statement listarTra = con.createStatement();
			resSet = listarTra.executeQuery("SELECT * FROM `joaquin_trabajadores`;");
			while (resSet.next()) {
				Trabajador listar = new Trabajador();
				listar.setIdTrabajador(resSet.getInt("idTrabajador"));
				listar.setNombre(resSet.getString("nombreTrabajador"));
				listar.setApellido(resSet.getString("apellidoTrabajador"));
				listar.setFechaNacimiento(resSet.getDate("fechaNacimiento"));
				listar.setSalario(resSet.getDouble("salario"));
				listar.setIdEspecialidad(resSet.getInt("idEspecialidad"));
				listarTodosTra.add(listar);
			}
			listarTra.close();
		} catch (SQLException e) {
			System.err.println("Error de E/S listarDepartamentos");
		}
		return listarTodosTra;
	}

	public static ArrayList<String> listarDptoNumeroEspecialidades() {

		ResultSet resSet;
		ArrayList<String> cantidadEspecialidades = new ArrayList<String>();
		try {
			Statement ordenXEsp = con.createStatement();
			resSet = ordenXEsp.executeQuery(
					"SELECT joaquin_departamentos.nombreDepartamento AS nombreDepartamento, COUNT(joaquin_especialidades.idEspecialidad) AS cantidad FROM joaquin_departamentos LEFT JOIN joaquin_especialidades ON joaquin_departamentos.idDepartamento = joaquin_especialidades.idDepartamento GROUP BY joaquin_departamentos.nombreDepartamento ORDER BY cantidad DESC;");
			while (resSet.next()) {
				String texto = "El departamento " + resSet.getString("nombreDepartamento") + " tiene "
						+ resSet.getInt("cantidad") + " especialidades asociadas.";
				cantidadEspecialidades.add(texto);
			}
			ordenXEsp.close();
		} catch (SQLException e) {
			System.err.println("Error al listar los departamentos por cantidad de especialidades");
		}
		return cantidadEspecialidades;
	}

	public static ArrayList<String> listarEspecialidadesPorSalario() {

		ResultSet resSet;
		ArrayList<String> especialidadesPorSalario = new ArrayList<String>();
		try {
			Statement EspXSalario = con.createStatement();
			resSet = EspXSalario.executeQuery(
					"SELECT joaquin_especialidades.nombreEspecialidad AS nombreEspecialidad, SUM(joaquin_trabajadores.salario) AS total FROM joaquin_especialidades LEFT JOIN joaquin_trabajadores ON joaquin_especialidades.idEspecialidad = joaquin_trabajadores.idEspecialidad GROUP BY joaquin_especialidades.nombreEspecialidad ORDER BY total DESC;");
			while (resSet.next()) {
				String texto = "La suma de los sueldos de los trabajadores de la especialidad "
						+ resSet.getString("nombreEspecialidad") + " es de " + resSet.getDouble("total") + "€.";
				especialidadesPorSalario.add(texto);
			}
			EspXSalario.close();
		} catch (SQLException e) {
			System.err.println("Error al listar las especialidades por sueldo total de trabajadores");
		}
		return especialidadesPorSalario;
	}

	public static ArrayList<String> trabajadoresPorSalario() {

		ResultSet resSet;
		ArrayList<String> trabajadoresSalario = new ArrayList<String>();
		try {
			Statement TrabajadorXSalario = con.createStatement();
			resSet = TrabajadorXSalario.executeQuery(
					"SELECT joaquin_trabajadores.nombreTrabajador AS nombreTrabajador, joaquin_trabajadores.salario AS salario FROM joaquin_trabajadores ORDER BY joaquin_trabajadores.salario DESC;");
			while (resSet.next()) {
				String texto = "Nombre : " + resSet.getString("nombreTrabajador") + " | Sueldo : "
						+ resSet.getDouble("salario") + "€.";
				trabajadoresSalario.add(texto);
			}
			TrabajadorXSalario.close();
		} catch (SQLException e) {
			System.err.println("Error al listar los trabajadores por salario");
		}
		return trabajadoresSalario;
	}

	public static ArrayList<String> trabajadoresPorDpto() {

		ResultSet resSet;
		ArrayList<String> trabajadoresDpto = new ArrayList<String>();
		try {
			Statement TrabajadorXDpto = con.createStatement();
			resSet = TrabajadorXDpto.executeQuery(
					"SELECT joaquin_trabajadores.nombreTrabajador AS nombreTrabajador, joaquin_departamentos.nombreDepartamento AS nombreDepartamento FROM joaquin_trabajadores, joaquin_especialidades, joaquin_departamentos WHERE joaquin_trabajadores.idEspecialidad = joaquin_especialidades.idEspecialidad AND joaquin_especialidades.idDepartamento = joaquin_departamentos.idDepartamento ORDER BY joaquin_departamentos.idDepartamento;");
			while (resSet.next()) {
				String texto = resSet.getString("nombreDepartamento") + " | " + resSet.getString("nombreTrabajador");
				trabajadoresDpto.add(texto);
			}
			TrabajadorXDpto.close();
		} catch (SQLException e) {
			System.err.println("Error al listar los trabajadores por departamentos");
		}
		return trabajadoresDpto;
	}

	public static ArrayList<String> trabajadoresPorEsp() {

		ResultSet resSet;
		ArrayList<String> trabajadoresEsp = new ArrayList<String>();
		try {
			Statement TrabajadorXEsp = con.createStatement();
			resSet = TrabajadorXEsp.executeQuery(
					"SELECT joaquin_trabajadores.nombreTrabajador AS nombreTrabajador, joaquin_especialidades.nombreEspecialidad AS nombreEspecialidad FROM joaquin_trabajadores, joaquin_especialidades WHERE joaquin_trabajadores.idEspecialidad = joaquin_especialidades.idEspecialidad ORDER BY joaquin_especialidades.idEspecialidad;");
			while (resSet.next()) {
				String texto = resSet.getString("nombreEspecialidad") + " | " + resSet.getString("nombreTrabajador");
				trabajadoresEsp.add(texto);
			}
			TrabajadorXEsp.close();
		} catch (SQLException e) {
			System.err.println("Error al listar los trabajadores por especialidades");
		}
		return trabajadoresEsp;
	}

}
