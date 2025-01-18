package libreriaLelloFinal;

public class Merchandising implements VerDatos {

	protected static int contadorProducto = 0;
	protected int idProducto;
	protected String descripcionProducto;
	protected String nombreProducto;
	protected String tematicaProducto;
	protected double precioProducto;
	protected int stockProducto;

	public Merchandising(String descripcionProducto, String nombreProducto, String tematicaProducto,
			double precioProducto) {
		this.idProducto = contadorProducto + 1;
		this.descripcionProducto = descripcionProducto;
		this.nombreProducto = nombreProducto;
		this.tematicaProducto = tematicaProducto;
		this.precioProducto = precioProducto;
		this.stockProducto = 25;
		contadorProducto++;
	}

	public Merchandising() {
		this.idProducto = contadorProducto + 1;
		contadorProducto++;
	}

	@Override
	public String toString() {
		return "Merchandising [id=" + idProducto + ", descripcion=" + descripcionProducto + ", nombre=" + nombreProducto
				+ ", tematica=" + tematicaProducto + ", precio=" + precioProducto + ", stock=" + stockProducto + "]";
	}

	public int getId() {
		return idProducto;
	}

	public void setId(int id) {
		this.idProducto = id;
	}

	public String getDescripcion() {
		return descripcionProducto;
	}

	public void setDescripcion(String descripcion) {
		this.descripcionProducto = descripcion;
	}

	public String getNombre() {
		return nombreProducto;
	}

	public void setNombre(String nombre) {
		this.nombreProducto = nombre;
	}

	public String getTematica() {
		return tematicaProducto;
	}

	public void setTematica(String tematica) {
		this.tematicaProducto = tematica;
	}

	public double getPrecio() {
		return precioProducto;
	}

	public void setPrecio(double precio) {
		this.precioProducto = precio;
	}

	public int getStock() {
		return stockProducto;
	}

	public void setStock(int stock) {
		this.stockProducto = stock;
	}

	public boolean reducirStock(int cantidad) {
		boolean flag = true;
		if (stockProducto < cantidad) {
			flag = false;
		}
		return flag;
	}

	@Override
	public void mostrarDatos() {
		System.out.println("ID del producto: " + idProducto);
		System.out.println("Descripción del producto: " + descripcionProducto);
		System.out.println("Nombre del producto: " + nombreProducto);
		System.out.println("Temática del producto: " + tematicaProducto);
		System.out.println("Precio del producto: " + precioProducto);
		System.out.println("Stock disponible del producto: " + stockProducto);
	}

	public void reducirStockMerchandising(Merchandising objetoAComprar, int cantidad) {
        if (reducirStock(cantidad)) { // si la operación es aceptada:
            objetoAComprar.setStock(objetoAComprar.getStock() - cantidad); // valorAntiguo - lo que pido
        }
    }
}
