package libreriaLelloFinal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Entrada {
	private static int idContador = 0;
	private int id;
	protected double precioEntrada;
	protected double descuento;
	protected boolean descuentoCanjeado;
	protected LocalDateTime fechaCompra;
	protected LocalDateTime fechaHora;

	public Entrada() {
		this.id = idContador++;
		this.descuento = precioEntrada;
		this.descuentoCanjeado = false;
		this.fechaCompra = LocalDateTime.now();
	}

	public LocalDateTime asignarFechaHora() {
		List<LocalDate> fechasDisponibles = generarFechasDisponibles();

		// Mostrar las fechas disponibles
		System.out.println("Fechas disponibles:");
		for (int i = 0; i < fechasDisponibles.size(); i++) {
			System.out.println((i + 1) + ". " + fechasDisponibles.get(i));
		}

		// Seleccionar una fecha
		int seleccionFecha;
		do {
			seleccionFecha = MainLibreria.leerEntero("\nSeleccione una fecha (número): ");
		} while (seleccionFecha < 1 || seleccionFecha > fechasDisponibles.size());

		LocalDate fechaSeleccionada = fechasDisponibles.get(seleccionFecha - 1);

		// Seleccionar la hora
		int hora;
		int minutos;

		do {
			hora = MainLibreria.leerEntero("\nIngrese la hora (8-20): ");
		} while (hora < 8 || hora > 20);

		do {
			minutos = MainLibreria.leerEntero("\nIngrese los minutos (00 o 30): ");
		} while (minutos != 0 && minutos != 30);

		fechaHora = LocalDateTime.of(fechaSeleccionada, LocalTime.of(hora, minutos));
		System.out.println("\nFecha y hora asignadas: " + fechaHora);
		return fechaHora;
	}

	// Método para generar fechas disponibles.
	private List<LocalDate> generarFechasDisponibles() {
		List<LocalDate> fechas = new ArrayList<>();
		LocalDate hoy = LocalDate.now();

		// Generar fechas para los próximos 7 días como ejemplo.
		for (int i = 0; i < 7; i++) {
			fechas.add(hoy.plusDays(i));
		}

		return fechas;
	}

	// Métodos para generar entradas aleatorias y asignar fechas y horas.
	public static Entrada crearEntradaAleatoria() {
		Random random = new Random();
		if (random.nextBoolean()) {
			return new EntradaNormal();
		} else {
			return new EntradaPrioritaria();
		}
	}

	public static void asignarFechaHoraAleatoria(Entrada entrada, LocalDate fecha) {
		Random random = new Random();
		int hora = random.nextInt(13) + 8; // Horas entre 8 y 20
		int minutos = random.nextBoolean() ? 0 : 30; // Minutos 00 o 30
		LocalDateTime fechaHora = LocalDateTime.of(fecha, LocalTime.of(hora, minutos));
		entrada.setFechaHora(fechaHora);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrecioEntrada() {
		return precioEntrada;
	}

	public void setPrecioEntrada(double precioEntrada) {
		this.precioEntrada = precioEntrada;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public boolean isDescuentoCanjeado() {
		return descuentoCanjeado;
	}

	public void setDescuentoCanjeado(boolean descuentoCanjeado) {
		this.descuentoCanjeado = descuentoCanjeado;
	}

	public LocalDateTime getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(LocalDateTime fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	@Override
	public String toString() {
		return "Entrada ID: " + id + " - Fecha de compra: " + fechaCompra + " - Fecha de visita: " + fechaHora
				+ " Precio: " + precioEntrada;
	}
}
