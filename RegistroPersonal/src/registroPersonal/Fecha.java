package registroPersonal;

//para poder usar la fecha actual//

public class Fecha {
	// atributos o parametros
	private int dia;
	private int mes;
	private int año;

	public Fecha(int vDia, int vMes, int vAño) {
		// constructor, pongo los valores en el orden que los voy a pedir

		this.año = vAño;
		this.mes = comprobarMes(vMes);
		this.dia = comprobarDia(vDia);
		// voy dejando arriba los parametros que son correctos y no van a cambiar
		// y los muevo a razón de los que voy moviendo
	}

	private int comprobarDia(int vDia) {
		int diasPorMes[] = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		if (vDia > 0 && vDia <= diasPorMes[mes]) {
			return vDia;
		} else if ((mes == 2 && vDia == 29) && (año % 4 == 0 && (año % 100 != 0 || año % 400 == 0))) {
			return vDia;
		} else {
			return 1;
		}

	}

	private int comprobarMes(int vMes) {

		if (vMes > 0 || vMes < 13) {
			return vMes;
		} else {
			return vMes = 1;

		}
	}

	@Override
	public String toString() {
		return dia + " del " + mes + " del " + año;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
	}

}
