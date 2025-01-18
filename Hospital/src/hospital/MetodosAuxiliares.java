package hospital;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MetodosAuxiliares {
	
	public static void cargando(int segundos) {
		long principio, fin;
		principio = System.currentTimeMillis();
		do {
			fin = System.currentTimeMillis();
		} while ((fin - principio) < (segundos * 1000));
	}
	
	public static String leerCadena(String pregunta) {
		BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
		String respuesta = null;
		try {
			System.out.println(pregunta);
			respuesta = leer.readLine();
		} catch (IOException e) {
			System.err.println("Error de E/S. leerCadena");
		}
		return respuesta;
	}
}
