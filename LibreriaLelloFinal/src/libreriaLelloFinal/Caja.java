package libreriaLelloFinal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Caja {

	private List<Libro> listaTempLibros = new ArrayList<>();
	private List<Merchandising> listaTempMerchandising = new ArrayList<>();

	private static int idContador = 0;
	private int id;
	private List<TicketCompra> tickets;
	private Dependiente dependienteResponsable;
	private boolean cajaAbierta;

	private static double fondoInicial = 1000;
	private double fondo;

	public Caja(Dependiente dependiente) {
		this.id = idContador++;
		this.tickets = new ArrayList<>();
		this.dependienteResponsable = dependiente;
		this.cajaAbierta = true;
		this.fondo = fondoInicial;
	}

	// CARRITO COMPRA, LLAMA A LOS MÉTODOS AUXILIARES PARA AÑADIR PRODUCTO/CANTIDAD,
	// MUESTRA EL CARRITO Y LLEVA HASTA LA CONFIRMACIÓN PARA FINALIZAR COMPRA.
	public void carritoCompra(List<Libro> listaTempLibro, List<Merchandising> listaTempMerch) {
		List<ProductoCarrito> carrito = new ArrayList<>();
		double totalCarrito = 0;

		// Agregar libros al carrito
		totalCarrito += agregarLibrosAlCarrito(listaTempLibro, carrito);

		// Agregar merchandising al carrito
		totalCarrito += agregarMerchAlCarrito(listaTempMerch, carrito);

		mostrarCarrito(carrito, totalCarrito);
		confirmarYFinalizarCompra(carrito, totalCarrito, listaTempLibro, listaTempMerch);
	}

	// MÉTODO AUXILIAR PARA AÑADIR LIBROS Y CONTROLAR LA CANTIDAD.
	private double agregarLibrosAlCarrito(List<Libro> libros, List<ProductoCarrito> carrito) {
		double total = 0;
		Iterator<Libro> it = libros.iterator();

		while (it.hasNext()) {
			Libro lib = it.next();
			int cantidad = MainLibreria.leerEntero("Ingrese la cantidad para " + lib.getTituloLibro() + ":");

			if (cantidad == 0) {
				System.out.println("\nNo puede añadir 0 unidades de un producto. Eliminado del carrito.");
				it.remove(); // Uso del método remove del Iterator para eliminar el libro.
			} else if (cantidad <= lib.getStockLibro()) {
				carrito.add(new ProductoCarrito(lib.getTituloLibro(), lib.getPrecioLibro(), cantidad));
				total += lib.getPrecioLibro() * cantidad;
				System.out.println("Libro añadido al carrito.");
			} else {
				System.out.println("No hay suficiente stock para " + lib.getTituloLibro());
				it.remove(); // Uso del método remove del Iterator para eliminar el libro.
			}
		}

		return total;
	}

	// MÉTODO AUXILIAR PARA AÑADIR MERCH Y CONTROLAR LA CANTIDAD.
	private double agregarMerchAlCarrito(List<Merchandising> merchandising, List<ProductoCarrito> carrito) {
		double total = 0;
		Iterator<Merchandising> it = merchandising.iterator();

		while (it.hasNext()) {
			Merchandising merch = it.next();
			int cantidad = MainLibreria.leerEntero("Ingrese la cantidad para " + merch.getNombre() + ":");

			if (cantidad == 0) {
				System.out.println("\nNo puede añadir 0 unidades de un producto. Eliminado del carrito.");
				it.remove(); // Uso del método remove del Iterator para eliminar el merchandising.
			} else if (cantidad <= merch.getStock()) {
				carrito.add(new ProductoCarrito(merch.getNombre(), merch.getPrecio(), cantidad));
				total += merch.getPrecio() * cantidad;
				System.out.println("Merchandising añadido al carrito.");
			} else {
				System.out.println("No hay suficiente stock para " + merch.getNombre());
				it.remove(); // Uso del método remove del Iterator para eliminar el merchandising.
			}
		}

		return total;
	}

	// MUESTRA UN RESUMEN DE LOS ARTÍCULOS QUE SE ENCUENTRAN EN EL CARRITO.
	private void mostrarCarrito(List<ProductoCarrito> carrito, double totalCarrito) {
		if (!carrito.isEmpty()) {
			System.out.println("\n====================================");
			System.out.println("Productos en el carrito:");
			System.out.println("====================================");
			for (ProductoCarrito producto : carrito) {
				System.out.println(producto);
			}
			System.out.println("====================================");
			System.out.println("Total del carrito: $" + (float) totalCarrito);
			System.out.println("====================================");
		}
	}

	// ULTIMA COMPROBACIÓN PARA CONFIRMAR O CANCELAR LOS ARTÍCULOS DEL CARRITO.
	private void confirmarYFinalizarCompra(List<ProductoCarrito> carrito, double totalCarrito,
			List<Libro> listaTempLibro, List<Merchandising> listaTempMerch) {
		int opcionMenu = 0;

		if (carrito.isEmpty()) {
			System.out.println("El carrito está vacío. No se puede realizar la operación.");
		} else {
			do {
				opcionMenu = MainLibreria.leerEntero(
						"[CONFIRMACIÓN] ¿Desea realizar la operación? 1. Sí - 2. No (volver atrás y mantener selección de artículos) - 3. Cancelar.");
				if (opcionMenu == 1) {
					Entrada entradaTemp = MenuEntrada.seleccionarEntrada();
					if (entradaTemp != null) {
						finalizarCompra(totalCarrito, entradaTemp, carrito, listaTempLibro, listaTempMerch);
					} else {
						// Limpia las listas temporales.
						listaTempLibros.clear();
						listaTempMerchandising.clear();

						System.out.println("Compra cancelada.");
					}
				} else if (opcionMenu == 2) {
					System.out.println("Volviendo atrás, se mantiene la selección de artículos.");
				} else if (opcionMenu == 3) {
					System.out.println("Compra cancelada.");

					// Limpia las listas temporales.
					listaTempLibros.clear();
					listaTempMerchandising.clear();
				}

			} while (opcionMenu != 1 && opcionMenu != 2 && opcionMenu != 3);
		}
	}

	// PROCESO PARA FINALIZAR LA COMPRA DONDE SE HACEN LOS CÁLCULOS FINALES, SE
	// GENERA UN TICKET CON LA COMPRA
	public void finalizarCompra(double totalCarrito, Entrada entrada, List<ProductoCarrito> carrito,
			List<Libro> listaTempLibro, List<Merchandising> listaTempMerch) {
		double impuestoBase = 13;
		double impuestos = totalCarrito * impuestoBase / 100;
		double totalCompra = totalCarrito + impuestos;
		double descuento = calcularDescuento(entrada, totalCompra);

		// Resta el precio del descuento de las entradas al fondo total al ser aplicado.
		double gestionFondoTotal = MenuCaja.getFondoTotalAcumulado() - descuento;
		MenuCaja.setFondoTotalAcumulado(gestionFondoTotal);

		// Hace el descuento al total de la compra y lo suma a la caja.
		totalCompra -= descuento;
		fondo += totalCompra;

		// Añadir el ticket a la lista de tickets.
		tickets.add(new TicketCompra(totalCarrito, impuestos, descuento, dependienteResponsable, entrada, carrito));
		System.out.println("Compra finalizada con éxito. Total con impuestos: $" + (float) totalCompra);

		// PROBAR METODO REDUCIRSTOCK NUEVO
		for (ProductoCarrito producto : carrito) {
			for (Libro libro : listaTempLibro) {
				if (libro.getTituloLibro().equals(producto.getNombre())) {
					libro.reducirStockLibro(libro, producto.getCantidad());
				}
			}
			for (Merchandising merch : listaTempMerch) {
				if (merch.getNombre().equals(producto.getNombre())) {
					merch.reducirStockMerchandising(merch, producto.getCantidad());
				}
			}
		}

		// Activa el campo descuentoCanjeado para que no se vuelva a aplicar.
		entrada.setDescuentoCanjeado(true);

		// Obtener el índice del último ticket añadido
		int indiceUltimoTicket = tickets.size() - 1;

		// Imprime el índice del último ticket
		System.out.println(tickets.get(indiceUltimoTicket));

		// Limpia las listas temporales.
		listaTempLibros.clear();
		listaTempMerchandising.clear();
	}

	// CALCULA EL DESCUENTO DEPENDIENDO DE SI ES UNA ENTRADA NORMAL O PRIORITARIA.
	private double calcularDescuento(Entrada entrada, double totalCompra) {
		double descuento = 0;
		if (!entrada.isDescuentoCanjeado()) {
			if (entrada instanceof EntradaNormal) {
				descuento = 8;
			} else if (entrada instanceof EntradaPrioritaria) {
				descuento = 15;
			}
			if (descuento > totalCompra) {
				descuento = totalCompra;
			} else if (entrada.isDescuentoCanjeado() == true) {
				System.out.println("Su descuento ya ha sido canjeado en una compra anterior.");
			}
		}
		return descuento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<TicketCompra> getTickets() {
		return tickets;
	}

	public void setTickets(List<TicketCompra> tickets) {
		this.tickets = tickets;
	}

	public Dependiente getDependienteResponsable() {
		return dependienteResponsable;
	}

	public void setDependienteResponsable(Dependiente dependienteResponsable) {
		this.dependienteResponsable = dependienteResponsable;
	}

	public boolean isCajaAbierta() {
		return cajaAbierta;
	}

	public void setCajaAbierta(boolean cajaAbierta) {
		this.cajaAbierta = cajaAbierta;
	}

	public double getFondo() {
		return fondo;
	}

	public void setFondo(double fondo) {
		this.fondo = fondo;
	}

	public static double getFondoInicial() {
		return fondoInicial;
	}

	public static void setFondoInicial(double fondoInicial) {
		Caja.fondoInicial = fondoInicial;
	}

	public List<Libro> getListaTempLibros() {
		return listaTempLibros;
	}

	public void setListaTempLibros(List<Libro> listaTempLibros) {
		this.listaTempLibros = listaTempLibros;
	}

	public List<Merchandising> getListaTempMerchandising() {
		return listaTempMerchandising;
	}

	public void setListaTempMerchandising(List<Merchandising> listaTempMerchandising) {
		this.listaTempMerchandising = listaTempMerchandising;
	}

	@Override
	public String toString() {
		return "ID Caja: " + id + " | " + dependienteResponsable + " - Caja abierta: " + cajaAbierta
				+ " - Fondo actual: " + fondo;
	}
}
