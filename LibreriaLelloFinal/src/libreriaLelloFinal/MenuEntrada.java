package libreriaLelloFinal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MenuEntrada {

    static HashMap<LocalDate, List<Entrada>> entradasPorDia = rellenarEntradasAleatorias();
    private static final int LIMITE_VENTA_DIA = 100;

    static void mostrarMenuEntrada() {
        boolean salir = false;
        int opcion;
        while (!salir) {
            System.out.println("\n[MENÚ ENTRADA]");
            System.out.println("1. Vender entradas.");
            System.out.println("2. Listar todas las entradas.");
            System.out.println("3. Listar entradas por fecha.");
            System.out.println("4. Atrás.");
            opcion = MainLibreria.leerEntero("Introduce una opción: ");
            switch (opcion) {
            case 1:
                venderEntrada();
                break;
            case 2:
                listarEntradas();
                break;
            case 3:
                listarEntradasPorFecha();
                break;
            case 4:
                salir = true;
                break;
            default:
                System.out.println("Su elección no coincide con ninguna opción válida.");
                break;
            }
        }
    }

    // PERMITE VENDER ENTRADAS (DOS TIPOS), LIMITA LA VENTA DE ENTRADAS POR DÍA
    private static void venderEntrada() {
        Entrada nuevaEntrada = null;
        int opcion = 0;
        do {
            opcion = MainLibreria.leerEntero("¿Qué tipo de entrada? 1. Normal - 2. Prioritaria");
            if (opcion == 1) {
                nuevaEntrada = new EntradaNormal();
            } else if (opcion == 2) {
                nuevaEntrada = new EntradaPrioritaria();
            } else {
                System.out.println("Por favor, seleccione una de las dos opciones válidas.");
            }
        } while (opcion != 1 && opcion != 2);

        LocalDate fechaEntrada = nuevaEntrada.asignarFechaHora().toLocalDate();

        List<Entrada> entradasEseDia = entradasPorDia.getOrDefault(fechaEntrada, new ArrayList<>());

        if (entradasEseDia.size() >= LIMITE_VENTA_DIA) {
            System.out.println("No se pueden vender más entradas para este día. Límite de " + LIMITE_VENTA_DIA + " alcanzado.");
        } else {
            entradasEseDia.add(nuevaEntrada);
            entradasPorDia.put(fechaEntrada, entradasEseDia);
            System.out.println("Entrada vendida: " + nuevaEntrada);

            // Suma el precio de las entradas al fondo total, más tarde se descuenta una vez que se aplica el descuento.
            double gestionFondoTotal = MenuCaja.getFondoTotalAcumulado() + nuevaEntrada.getPrecioEntrada();
            MenuCaja.setFondoTotalAcumulado(gestionFondoTotal);
        }
    }

    // LISTA LAS ENTRADAS POR FECHA
    private static void listarEntradas() {
        System.out.println("Listado de todas las entradas (normales y prioritarias):");
        for (LocalDate fecha : entradasPorDia.keySet()) {
            System.out.println("Fecha: " + fecha);
            for (Entrada entrada : entradasPorDia.get(fecha)) {
                System.out.println(entrada);
            }
        }
    }

    // PERMITE SELECCIONAR UNA ENTRADA SIEMPRE QUE HAYAN REGISTRADAS.
    public static Entrada seleccionarEntrada() {
        LocalDate hoy = LocalDate.now();
        List<Entrada> entradasHoy = entradasPorDia.getOrDefault(hoy, new ArrayList<>());

        if (entradasHoy.isEmpty()) {
            System.out.println("No hay entradas disponibles para hoy.");
            return null;
        }

        System.out.println("Listado de entradas para hoy:");
        for (int i = 0; i < entradasHoy.size(); i++) {
            System.out.println((i + 1) + ". " + entradasHoy.get(i));
        }

        int seleccion = MainLibreria.leerEntero("Seleccione el número de la entrada que desea seleccionar: ");
        if (seleccion > 0 && seleccion <= entradasHoy.size()) {
            return entradasHoy.get(seleccion - 1);
        } else {
            System.out.println("Selección inválida.");
            return null;
        }
    }

    // Método para rellenar el HashMap con entradas aleatorias y devolverlo.
    private static HashMap<LocalDate, List<Entrada>> rellenarEntradasAleatorias() {
        HashMap<LocalDate, List<Entrada>> entradas = new HashMap<>();
        LocalDate fecha = LocalDate.now();
        List<Entrada> entradasDelDia = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Entrada entrada = Entrada.crearEntradaAleatoria();
            Entrada.asignarFechaHoraAleatoria(entrada, fecha);
            entradasDelDia.add(entrada);
        }

        entradas.put(fecha, entradasDelDia);
        return entradas;
    }

    // Método para listar entradas de un día específico.
    private static void listarEntradasPorFecha() {
        LocalDate fecha = seleccionarFecha();
        List<Entrada> entradasDelDia = entradasPorDia.get(fecha);
        if (entradasDelDia == null || entradasDelDia.isEmpty()) {
            System.out.println("No hay entradas para la fecha: " + fecha);
        } else {
            System.out.println("Entradas para la fecha: " + fecha);
            for (Entrada entrada : entradasDelDia) {
                System.out.println(entrada);
            }
        }
    }

    // Método para seleccionar una fecha.
    private static LocalDate seleccionarFecha() {
        int dia = MainLibreria.leerEntero("Introduce el día (1-31): ");
        int mes = MainLibreria.leerEntero("Introduce el mes (1-12): ");
        int anio = MainLibreria.leerEntero("Introduce el año (YYYY): ");
        return LocalDate.of(anio, mes, dia);
    }
}
