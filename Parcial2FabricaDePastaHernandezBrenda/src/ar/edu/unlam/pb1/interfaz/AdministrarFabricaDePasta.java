package ar.edu.unlam.pb1.interfaz;

import java.util.Scanner;

import ar.edu.unlam.pb1.dominio.Pasta;
import ar.edu.unlam.pb1.dominio.FabricaDePastas;
import ar.edu.unlam.pb1.dominio.enums.TipoDePasta;
import ar.edu.unlam.pb1.interfaz.enums.MenuPrincipal;

public class AdministrarFabricaDePasta {
	private static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {

		String nombrePedido = ingresarString("\nIngrese el nombre del pedido:");
		int opcion = 0;

		FabricaDePastas fabricaDePastas = new FabricaDePastas(nombrePedido);
		MenuPrincipal opcionMenu = null;

		do {
			mostrarPorPantalla(
					"\n\n*****************************************\n\tPEDIDO " + fabricaDePastas.getNombrePedido());

			opcionMenu = ingresarOpcionDeMenuPrincipalValida();

			switch (opcionMenu) {
			case MOSTRAR_PASTA_DE_UN_TIPO_DE_PASTA_CON_MENOR_CANTIDAD_EN_El_PEDIDO:
				mostrarPastaDeUnTipoDePastaConMenorCantidadEnElPedido(fabricaDePastas);
				break;
			case AGREGAR_PASTA_A_PEDIDO:
				agregarPastaAPedido(fabricaDePastas);
				break;
			case MOSTRAR_PEDIDO_ORDENADO_POR_PRECIO_DE_PASTA_DESCENDENTE:
				mostrarPedidoOrdenadoPorPrecioDeOPastaDescendente(fabricaDePastas);
				break;
			case MOSTRAR_TOTAL_DEL_PEDIDO:
				mostrarTotalDelPedido(fabricaDePastas);
				break;
			case SALIR:
				mostrarPorPantalla("Hasta pronto!");
				break;
			}
		} while (opcionMenu != MenuPrincipal.SALIR);

	}

	private static void mostrarTotalDelPedido(FabricaDePastas fabricaDePastas) {
		// TODO: Se debe obtener el total del pedido desde la fabrica y luego mostrarlo.

		mostrarPorPantalla("El total del pedido es: x" + fabricaDePastas.obtenerTotalDelPedido());

	}

	private static void mostrarPedidoOrdenadoPorPrecioDeOPastaDescendente(FabricaDePastas fabricaDePastas) {
		// TODO: Se debe obtener el pedido de la fabrica y mostrar las pastas en el
		// mismo.
		mostrarArrayDePastas(fabricaDePastas.obtenerPedidoPorPrecioDePastaDescendente());
	}

	private static void mostrarPastaDeUnTipoDePastaConMenorCantidadEnElPedido(FabricaDePastas fabricaDePastas) {
		// TODO: Se debe mostrar los tipos de pasta disponibles, solicitar la opcion
		// deseada como texto y luego obtener desde la fabrica la pasta del TipoDePasta
		// ingresado con menor cantidad en el pedido. Mostrar la pasta
		// obtenida.

		TipoDePasta pastaIngresada = ingresarTipoDePasta();

		if (pastaIngresada != null) {
			mostrarPasta(fabricaDePastas.obtenerPastaDeUnTipoDePastaConMenorCantidadEnElPedido(pastaIngresada));

		} else {
			mostrarPorPantalla("No hay ningun tipo de pasta de ese tipo.");
		}

	}

	private static TipoDePasta ingresarTipoDePasta() {
		mostrarTiposDePasta();

		String pastaIngresada = ingresarString("Ingrese el tipo de pasta: ").toUpperCase();

		return TipoDePasta.valueOf(pastaIngresada);
	}

	private static TipoDePasta ingresarOpcionDeTipoDePasta() {
		int opcion;

		do {
			mostrarTiposDePasta();
			opcion = ingresarEntero("Ingrese opcion del menu:");
			if (opcion == 99) {
				break;
			}

			if (opcion < 0 || opcion > TipoDePasta.values().length) {
				mostrarPorPantalla("Opcion invalida.");
			}

		} while (opcion < 0 || opcion > TipoDePasta.values().length);

		return TipoDePasta.values()[opcion - 1];
	}

	private static void agregarPastaAPedido(FabricaDePastas fabricaDePastas) {
		// TODO: Debe mostrar las pastas disponibles en la fabrica, solicitar el ingreso
		// del codigo de la pasta deseada, la cantidad que se quiere agregar al pedido
		// (debe ser mayor a cero) y luego agregarla al pedido de la fabrica. Mostrar un
		// mensaje de exito si fue posible agregar al pedido o de error en caso
		// contrario.
		int cantidadPastas = 0;
		mostrarArrayDePastas(fabricaDePastas.getPastas());

		String codigo = ingresarString("Ingrese el codigo de la pasta a pedir: ");

		do {
			cantidadPastas = ingresarEntero("Ingrese la cantidad: ");
			if (cantidadPastas <= 0) {
				mostrarPorPantalla("La cantidad es insuficiente ");
			}
		} while (cantidadPastas <= 0);

		if (fabricaDePastas.agregarPastaAlPedido(codigo, cantidadPastas)) {
			mostrarPorPantalla("El pedido de pasta se agrego exitosamente ");
		} else {
			mostrarPorPantalla("El pedido no se pudo ingresar ");
		}

	}

	private static MenuPrincipal ingresarOpcionDeMenuPrincipalValida() {
		// TODO: Se debe mostrar el menu principal y solicitar el ingreso de la opcion
		// deseada. Se debe validar que la opcion ingresada sea valida para el menu
		// mostrado y luego devolverla.

		int opcion;
		do {
			mostrarMenuPrincipal();
			opcion = ingresarEntero("Ingrese opcion del menu:");

			if (opcion < 0 || opcion > MenuPrincipal.values().length) {
				mostrarPorPantalla("Opcion invalida.");
			}

		} while (opcion < 0 || opcion > MenuPrincipal.values().length);

		return MenuPrincipal.values()[opcion - 1];
	}

	private static void mostrarMenuPrincipal() {
		String menu = "\n*****Menu Pedido Pastas On line*****\n";
		for (int i = 0; i < MenuPrincipal.values().length; i++) {
			menu += (i + 1) + "- " + MenuPrincipal.values()[i].getDescripcion() + "\n";
		}
		mostrarPorPantalla(menu);
	}

	private static void mostrarTiposDePasta() {
		String tiposDePasta = "\n*****Tipos de pasta*****\n";
		for (int i = 0; i < TipoDePasta.values().length; i++) {
			tiposDePasta += TipoDePasta.values()[i] + "\n";
		}
		mostrarPorPantalla(tiposDePasta);
	}

	private static int ingresarEntero(String mensaje) {
		mostrarPorPantalla(mensaje);
		return teclado.nextInt();
	}

	private static String ingresarString(String mensaje) {
		mostrarPorPantalla(mensaje);
		return teclado.next();
	}

	private static void mostrarPorPantalla(String mensaje) {
		System.out.println(mensaje);
	}

	private static void mostrarPasta(Pasta pasta) {

		if (pasta != null) {
			mostrarPorPantalla("Su pasta es: " + pasta.toString());
		} else {
			mostrarPorPantalla("Sin pasta");
		}

	}

	private static void mostrarArrayDePastas(Pasta[] pastas) {
		for (int i = 0; i < pastas.length; i++) {
			if (pastas[i] != null) {
				mostrarPorPantalla(pastas[i].toString());
			}
		}
	}
}
