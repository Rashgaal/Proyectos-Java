package libreriaLelloFinal;

import java.time.LocalDateTime;
import java.util.List;

public class TicketCompra {
    private int id;
    private double importeCompra;
    private double impuestos;
    private double descuento;
    private double total;
    private LocalDateTime fechaCompra; // Uso de LocalDateTime para manejar la fecha y hora de las entradas.
    private Dependiente dependienteResponsable;
    private List<ProductoCarrito> productos;

    public TicketCompra(double importeCompra, double impuestos, double descuento, Dependiente dependienteResponsable, Entrada entrada, List<ProductoCarrito> productos) {
        this.id = entrada.getId(); // Obtener el ID de la instancia de Entrada
        this.importeCompra = importeCompra;
        this.impuestos = impuestos;
        this.descuento = descuento;
        this.total = (importeCompra + impuestos) - descuento;
        this.fechaCompra = LocalDateTime.now();
        this.dependienteResponsable = dependienteResponsable;
        this.productos = productos;
    }

    public int getId() {
        return id;
    }

    public double getImporteTotalCompra() {
        return importeCompra;
    }

    public double getDescuento() {
        return descuento;
    }

    public LocalDateTime getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDateTime fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    @Override
    public String toString() {
        String productosListados = "";
        for (ProductoCarrito producto : productos) {
            productosListados += producto.toString() + "\n";
        }

        return "\n========= TICKET DE COMPRA =========\n" +
                "ID del Ticket: " + id + "\n" +
                "Fecha: " + fechaCompra + "\n" +
                "--------------ARTÍCULOS-------------\n" +
                productosListados +
                "--------------OPERACION-------------\n" +
                "Importe de compra: " + (float) importeCompra + "€ \n" +
                "Impuestos: " + (float) impuestos + "€ \n" +
                "Descuento aplicado: -" + (float) descuento + "€ \n" +
                "TOTAL A PAGAR: " + (float) total + "€ \n" +
                "------------------------------------\n" +
                "Le atendió: " + dependienteResponsable.getNombre() + "\n" +
                "====================================\n";
    }
}
