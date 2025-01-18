package libreriaLelloFinal;

public class Libro implements VerDatos {
	private String tituloLibro;
	private String autorLibro;
	private String isbnLibro;
	private String edicionLibro;
	private String editorialLibro;
	private double precioLibro;
	private String generoLibro;
	private String idiomaLibro;
	private int stockLibro;

	public Libro(String tituloLibro, String autorLibro, String isbnLibro, String edicionLibro, String editorialLibro,
			double precioLibro, String generoLibro, String idiomaLibro, int stockLibro) {
		this.tituloLibro = tituloLibro;
		this.autorLibro = autorLibro;
		this.isbnLibro = isbnLibro;
		this.edicionLibro = edicionLibro;
		this.editorialLibro = editorialLibro;
		this.precioLibro = precioLibro;
		this.generoLibro = generoLibro;
		this.idiomaLibro = idiomaLibro;
		this.stockLibro = stockLibro;
	}

	// GETTERS, SETTERS y TOSTRING:

	@Override
	public String toString() {
		return "Libro [Título: " + tituloLibro + ", Autor: " + autorLibro + ", ISBN: " + isbnLibro + ", Edición: "
				+ edicionLibro + ", Editorial: " + editorialLibro + ", Precio: " + precioLibro + ", Género: "
				+ generoLibro + ", Idioma: " + idiomaLibro + ", Stock: " + stockLibro + "]";
	}

	public String getTituloLibro() {
		return tituloLibro;
	}

	public void setTituloLibro(String tituloLibro) {
		this.tituloLibro = tituloLibro;
	}

	public String getAutorLibro() {
		return autorLibro;
	}

	public void setAutorLibro(String autorLibro) {
		this.autorLibro = autorLibro;
	}

	public String getIsbnLibro() {
		return isbnLibro;
	}

	public void setIsbnLibro(String isbnLibro) {
		this.isbnLibro = isbnLibro;
	}

	public String getEdicionLibro() {
		return edicionLibro;
	}

	public void setEdicionLibro(String edicionLibro) {
		this.edicionLibro = edicionLibro;
	}

	public String getEditorialLibro() {
		return editorialLibro;
	}

	public void setEditorialLibro(String editorialLibro) {
		this.editorialLibro = editorialLibro;
	}

	public double getPrecioLibro() {
		return precioLibro;
	}

	public void setPrecioLibro(double precioLibro) {
		this.precioLibro = precioLibro;
	}

	public String getGeneroLibro() {
		return generoLibro;
	}

	public void setGeneroLibro(String generoLibro) {
		this.generoLibro = generoLibro;
	}

	public String getIdiomaLibro() {
		return idiomaLibro;
	}

	public void setIdiomaLibro(String idiomaLibro) {
		this.idiomaLibro = idiomaLibro;
	}

	public int getStockLibro() {
		return stockLibro;
	}

	public void setStockLibro(int stockLibro) {
		this.stockLibro = stockLibro;
	}

	// MÉTODOS PARA TRABAJAR CON LOS LIBROS (reducir stock):

	public boolean reducirStock(int cantidad) {
		if (stockLibro >= cantidad) {
			return true;
		}
		return false;
	}

	@Override
	public void mostrarDatos() {
		System.out.println("Título del libro: " + tituloLibro);
		System.out.println("Autor del libro: " + autorLibro);
		System.out.println("ISBN del libro: " + isbnLibro);
		System.out.println("Edición del libro: " + edicionLibro);
		System.out.println("Editorial del libro: " + editorialLibro);
		System.out.println("Género del libro: " + generoLibro);
		System.out.println("Idioma del libro: " + idiomaLibro);
		System.out.println("Precio del libro: " + precioLibro);
		System.out.println("Stock disponible del libro: " + stockLibro);
	}
	
	public void reducirStockLibro(Libro libroAComprar, int cantidad) {
        if (reducirStock(cantidad)) { // si la operación es aceptada:
            libroAComprar.setStockLibro(libroAComprar.getStockLibro() - cantidad); // valorAntiguo - lo que pido
        }
    }
}
