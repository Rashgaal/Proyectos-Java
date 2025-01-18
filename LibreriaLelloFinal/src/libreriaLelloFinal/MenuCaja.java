package libreriaLelloFinal;

import java.util.ArrayList;
import java.util.List;

public class MenuCaja {

    private static List<Caja> listaCajas = new ArrayList<>();
    private static double fondoTotalAcumulado = 10000;

    // LIMITAR LA SELECCION DE ENTRADAS A HOY.

    static void mostrarMenuCaja() {
        boolean salir = false;
        int opcion;
        while (!salir) {
            System.out.println("\n[MENÚ CAJA]");
            System.out.println("1. Abrir una nueva caja.");
            System.out.println("2. Seleccionar una caja abierta existente.");
            System.out.println("3. Revisar fondo total.");
            System.out.println("4. Recuento de ventas por dependiente.");
            System.out.println("5. Atrás.");
            opcion = MainLibreria.leerEntero("Introduce una opción: ");
            System.out.println();
            switch (opcion) {
            case 1:
                abrirCaja();
                break;
            case 2:
                seleccionarCajaAbierta();
                break;
            case 3:
                revisarFondoTotal();
                break;
            case 4:
                recaudacionPorDependiente();
                break;
            case 5:
                salir = true;
                break;
            default:
                System.out.println("Su elección no coincide con ninguna opción válida.");
                break;
            }
        }
    }

    // PERMITE LOCALIZAR LA RECAUDACIÓN TOTAL DE CADA DEPENDIENTE,
    private static void recaudacionPorDependiente() {
        Dependiente dependiente = null;

        do {
            // Lista los empleados para poder seleccionar uno.
        	System.out.println("Lista de dependientes: ");
        	MenuEmpleados.listarDependientes();
            String dniDependiente = MainLibreria.leerCadena("Introduce el DNI del dependiente: ").toUpperCase();

            for (Trabajador t : MenuEmpleados.empleados) {
                if (t.getDni().equals(dniDependiente)) {
                    dependiente = (Dependiente) t;
                    System.out.println("Dependiente seleccionado: " + t);
                }
            }

        } while (dependiente == null); // Mientras que no se otorgue un valor válido a dependiente, iterará en el
                                       // bucle.

        double recaudacionTotal = 0;

        // Filtra las cajas a cargo del dependiente seleccionado y suma la recaudación
        for (Caja caja : listaCajas) {
            if (caja.getDependienteResponsable().equals(dependiente)) {
                recaudacionTotal += (caja.getFondo() - Caja.getFondoInicial());
            }
        }

        System.out.println("Recaudación total del dependiente " + dependiente.getNombre() + " "
                + dependiente.getApellido1() + " " + dependiente.getApellido2() + ": " + (float) recaudacionTotal + "€");
    }

    // PERMITE ABRIR LA CAJA UNICAMENTE CON UN DEPENDIENTE EXISTENTE, DEJA LA OPCIÓN
    // A CREARLO EN CASO DE QUE NO EXISTA.
    private static void abrirCaja() {
        Dependiente dependiente = null;
        do {
            // Lista los empleados para poder seleccionar uno.
        	System.out.println("Lista de dependientes: ");
            MenuEmpleados.listarDependientes();
            String dniDependiente = MainLibreria.leerCadena("Introduce el DNI del dependiente: ").toUpperCase();

            for (Trabajador t : MenuEmpleados.empleados) {
                if (t.getDni().equals(dniDependiente)) {
                    dependiente = (Dependiente) t;
                    System.out.println("Dependiente seleccionado: " + t);
                }
            }

        } while (dependiente == null); // Mientras que no se otorgue un valor válido a dependiente, iterará en el
                                       // bucle.

        // Instancia una nueva caja a nombre del dependiente seleccionado y la añade a
        // la lista que contiene todas las cajas creadas.
        Caja nuevaCaja = new Caja(dependiente);
        listaCajas.add(nuevaCaja);

        // Resta al fondo total acumulado el fondo inicial de la caja.
        fondoTotalAcumulado -= Caja.getFondoInicial();

        // Accede al menú de caja con una caja activa.
        mostrarMenuCajaAbierta(nuevaCaja);
    }

    // PERMITE SELECCIONAR UNA CAJA PARA MANEJARLA UNICAMENTE SI ESTÁ ABIERTA.
    private static void seleccionarCajaAbierta() {
        boolean salir = false;

        while (!salir) {
            if (listaCajas.isEmpty()) {
                System.out.println("No hay cajas disponibles para seleccionar.\n");
                salir = true;
            } else {
                for (Caja c : listaCajas) {
                    System.out.println(c);
                }

                int seleccionCaja = MainLibreria.leerEntero("Introduce la ID de la caja que deseas seleccionar: ");
                if (seleccionCaja >= 0 && seleccionCaja < listaCajas.size()
                        && listaCajas.get(seleccionCaja).isCajaAbierta()) {
                    mostrarMenuCajaAbierta(listaCajas.get(seleccionCaja));
                    salir = true;
                } else {
                    System.out.println("La caja seleccionada no existe o no está abierta.\n");
                }
            }

        }
    }

    // MUESTRA LA INFORMACIÓN SOBRE EL FONDO TOTAL ACTUAL DE LA EMPRESA.
    private static void revisarFondoTotal() {
        System.out.println("El fondo total de la empresa es: " + fondoTotalAcumulado);
    }

    // MENÚ AL QUE SOLO SE PUEDE ACCEDER CON UNA CAJA ABIERTA.
    private static void mostrarMenuCajaAbierta(Caja caja) {
        boolean salir = false;
        int opcion;
        while (!salir) {
            System.out.println("\n[MENÚ CAJA ABIERTA]");
            System.out.println("1. Proceso de compra.");
            System.out.println("2. Cerrar caja (recuento incluído).");
            System.out.println("3. Volver atrás.");
            opcion = MainLibreria.leerEntero("Introduce una opción: ");
            switch (opcion) {
            case 1:
                procesoCompra(caja);
                break;
            case 2:
                cerrarCaja(caja);
                salir = true;
                break;
            case 3:
                salir = true;
                break;
            default:
                System.out.println("Su elección no coincide con ninguna opción válida.");
                break;
            }
        }
    }

    // PERMITE CERRAR LA CAJA Y EVITA QUE SE PUEDA VOLVER A ABRIR. INFORMA DE LA
    // RECAUDACIÓN CONCRETA DE LA CAJA.
    private static void cerrarCaja(Caja caja) {

        // Marca la caja como cerrada y suma el fondo local de la caja al general
        // (fondoTotalAcumulado).
        caja.setCajaAbierta(false);
        fondoTotalAcumulado += caja.getFondo();

        if (!caja.getTickets().isEmpty()) {
            int opcion = MainLibreria.leerEntero(
                    "¿Desea imprimir todos los tickets de compra relacionados a esta caja?" + "\n 1. Imprimir - 2. Salir.");
            if (opcion == 1) {
                System.out.println(caja.getTickets());
            } else {
                System.out.println("Caja cerrada con éxito. Recaudación de la caja: " + (float) caja.getFondo());
            }
        } else {
            System.out.println("La caja ha sido cerrada sin ningún registro.");
        }

    }

    // PERMITE AÑADIR LIBROS Y MERCHANDISING A UNA LISTA TEMPORAL, SI AMBAS ESTÁN
    // VACÍAS NO PROCEDERÁ. SI NO, LLAMARÁ A LA CAJA PARA CONCRETAR LAS UNIDADES Y
    // FINALIZAR LA COMPRA.
    private static void procesoCompra(Caja caja) {
        boolean salir = false;

        while (!salir) {
            System.out.println("1 - Añadir libro");
            System.out.println("2 - Añadir merchandising");
            System.out.println("3 - Finalizar selección de artículos");
            System.out.println("4 - Cancelar.");

            int opcion = MainLibreria.leerEntero("Seleccione una opción: ");
            switch (opcion) {
            case 1:
                anhadirLibro(caja);
                break;
            case 2:
                anhadirMerchandising(caja);
                break;
            case 3:
                if (caja.getListaTempLibros().isEmpty() && caja.getListaTempMerchandising().isEmpty()) {
                    System.out.println("No se puede procesar la compra porque no hay artículos en el carrito.");
                } else {
                    caja.carritoCompra(caja.getListaTempLibros(), caja.getListaTempMerchandising());
                }
                salir = true;
                break;
            case 4:
                salir = true;
                break;
            default:
                System.out.println("Opción inválida. Por favor, inténtelo de nuevo.");
                break;
            }
        }
    }

    // MÉTODO AUXILIAR PARA AÑADIR LIBROS A LA LISTA TEMPORAL (SOLO TIPO, CANTIDAD NO).
    private static void anhadirLibro(Caja caja) {
        System.out.println("Libros disponibles:");
        for (int i = 0; i < MainLibreria.librosOficiales.size(); i++) {
            System.out.println((i + 1) + ". " + MainLibreria.librosOficiales.get(i));
        }
        int seleccion = MainLibreria.leerEntero("Seleccione el número del libro que desea añadir: ");
        if (seleccion > 0 && seleccion <= MainLibreria.librosOficiales.size()) {
            caja.getListaTempLibros().add(MainLibreria.librosOficiales.get(seleccion - 1));
            System.out.println("Libro añadido al carrito temporal.");
        } else {
            System.out.println("Selección inválida.");
        }
    }

    // MÉTODO AUXILIAR PARA AÑADIR MERCHANDISING A LA LISTA TEMPORAL (SOLO TIPO, CANTIDAD NO).
    private static void anhadirMerchandising(Caja caja) {
        System.out.println("Merchandising disponible:");
        for (int i = 0; i < MainLibreria.merchaOficial.size(); i++) {
            System.out.println((i + 1) + ". " + MainLibreria.merchaOficial.get(i));
        }
        int seleccion = MainLibreria.leerEntero("Seleccione el número del merchandising que desea añadir: ");
        if (seleccion > 0 && seleccion <= MainLibreria.merchaOficial.size()) {
            caja.getListaTempMerchandising().add(MainLibreria.merchaOficial.get(seleccion - 1));
            System.out.println("Merchandising añadido al carrito temporal.");
        } else {
            System.out.println("Selección inválida.");
        }
    }

    public static List<Caja> getListaCajas() {
        return listaCajas;
    }

    public static void setListaCajas(List<Caja> listaCajas) {
        MenuCaja.listaCajas = listaCajas;
    }

    public static double getFondoTotalAcumulado() {
        return fondoTotalAcumulado;
    }

    public static void setFondoTotalAcumulado(double fondoTotalAcumulado) {
        MenuCaja.fondoTotalAcumulado = fondoTotalAcumulado;
    }
}
