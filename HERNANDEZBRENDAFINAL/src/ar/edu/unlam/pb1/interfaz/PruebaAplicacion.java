package ar.edu.unlam.pb1.interfaz;

import java.util.Scanner;
import ar.edu.unlam.pb1.dominio.Categoria;
import ar.edu.unlam.pb1.dominio.GestionAplicacion;
import ar.edu.unlam.pb1.dominio.Test;
import ar.edu.unlam.pb1.dominio.Usuario;
import ar.edu.unlam.pb1.dominio.enums.MenuUsuario;
import ar.edu.unlam.pb1.interfaz.enums.MenuPrincipal;

public class PruebaAplicacion {
	private static final int SALIR = 99;
	private static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {

		GestionAplicacion gestionAplicacion = new GestionAplicacion();
		MenuPrincipal opcionMenuPrincipal = null;
		int numeroIngresado = 0;

		do {
			mostrarPorPantalla("\nBienVenido a MobiusMind Test de deteccion temprana de enfermedades cognitivas ");
			mostrarMenuPrincipal();

			opcionMenuPrincipal = obtenerOpcionDeMenuPrincipal(numeroIngresado);

			switch (opcionMenuPrincipal) {
			case REGISTRARME:
				registrarme(gestionAplicacion);

				break;
			case INICIAR_SESION:
				iniciarSesionEn(gestionAplicacion);

				break;
			case SALIR:
				mostrarPorPantalla("Finalizando programa...");
				break;
			}

			// Complete la condicion para que el programa funcione correctamente
		} while (opcionMenuPrincipal != MenuPrincipal.SALIR);

		// liberar correctamente los recursos usados
	}

	private static void registrarme(GestionAplicacion gestionAplicacion) {
		// Pide el ingreso de los datos necesarios para crear un usuario
		// Debe verificar que el correo sea valido y que no exista otro usuario
		// registrado en la aplicacion con dicho correo (se sugiere buscar el usuario
		// por correo)
		// Una vez obtenidos los datos, se procede a registrar el usuario en la
		// aplicacion mostrando un mensaje en caso de exito y otro en caso de error.

		String correo;
		String contrasenia;

		do {
			correo = ingresarString("Ingrese el correo electronico: ");
			if (!gestionAplicacion.verificarCorreo(correo)) {
				System.out.println("Error de sintaxis al registrar correo ");
			} else if (gestionAplicacion.buscarUsuarioConCorreo(correo) != null) {
				System.out.println("El usuario ya fue registrado.");
			}
		} while (!gestionAplicacion.verificarCorreo(correo)
				|| gestionAplicacion.buscarUsuarioConCorreo(correo) != null);

		contrasenia = ingresarString("Ingrese su contrasenia: ");

		Usuario usuarioRegistrado = new Usuario(correo, contrasenia);

		boolean seRegistro = gestionAplicacion.registrarUsuario(usuarioRegistrado);

		if (seRegistro) {
			mostrarPorPantalla("El registro del usuario fue exitoso ");
		} else {
			mostrarPorPantalla("Error al registrar el usuario.");
		}

	}

	private static void iniciarSesionEn(GestionAplicacion gestionAplicacion) {
		mostrarPorPantalla("\n\nInicio de sesion");
		// Pide el ingreso de credenciales (correo y contrasenia)
		// Verifica si con esas credenciales se puede iniciar sesion en la aplicacion
		// Si el inicio de sesion es exitoso, se muestra un mensaje de exito y seguido
		// el menu del usuario (método menuUsuario()), caso
		// contrario, se muestra el mensaje de error: "Usuario y/o contrasenia invalido"

		String correo;
		String contrasenia;

		correo = ingresarString("Ingrese su correo para iniciar sesion: ");
		contrasenia = ingresarString("Ingresar contrasenia: ");

		Usuario usuario = gestionAplicacion.iniciarSesion(correo, contrasenia);

		if (usuario != null) {
			mostrarPorPantalla("El inicio de sesion fue exitoso ");
			menuUsuario(usuario, gestionAplicacion);
		} else {
			mostrarPorPantalla("Error al iniciar sesion ");
		}

	}

	private static void menuUsuario(Usuario usuario, GestionAplicacion gestionAplicacion) {
		MenuUsuario opcionMenuUsuario = null;
		int numeroIngresado = 0;

		do {
			mostrarMenuUsuario();
			numeroIngresado = ingresarNumeroEntero("\n\nIngrese opcion:");
			opcionMenuUsuario = obtenerOpcionDeMenuUsuario(numeroIngresado);
			int opcion = 0;

			switch (opcionMenuUsuario) {
			case MIS_TESTS:
				// Obtiene los test del usuario y los muestra.
				// Solicita el ingreso de un numero entero mostrando un mensaje. Dicho numero
				// corresponde con el ID de algun test.
				// Se debe completar el test ingresado. Si se ingresa 99 se sale del menu actual

				do {
					mostrarTest(gestionAplicacion.getTests());
				
					opcion = ingresarNumeroEntero("Ingrese el ID del test o ingrese 99 para salir");

					if (opcion != SALIR) {
						usuario.realizarTest(opcion);
					}

				} while (opcion != SALIR);

				break;
			case COLECCION_DE_TEST:
				do {
					mostrarTest(gestionAplicacion.getTests());
					mostrarMenuParaAgregarUnTest();
					opcion = ingresarNumeroEntero("\nIngrese opcion:");

					// Si el numero ingresado se encuentra entre 1 y 10 inclusive, entonces se
					// procede a seleccionar el test
					// Se sugiere obtener desde la aplicacion el test por su ID y luego agregarlo a
					// los tests del usuario
					// Es necesario mostrar un mensaje de exito en caso de seleccionar un nuevo
					// test.
					// Si no pudo agregar el tests, entonces mostrar el mensaje: "No fue posible
					// obtener el test solicitado, verifique que no se haya agregado antes."

					if (opcion >= 1 && opcion <= 10) {

						Test miTest = gestionAplicacion.obtenerTestPorSuId(opcion);

						if (miTest != null) {
							boolean agregado = usuario.agregarAMiLista(miTest);

							if (agregado) {
								mostrarPorPantalla("Exito al agregar a la lista un nuevo test");
							} else {
								mostrarPorPantalla(
										"No fue posible obtener el test solicitado, verifique que no se haya agregado antes.");
							}

						}

					}

				} while (opcion != SALIR);

				break;
			case TEST_CON_MAS_PUNTAJE_POR_CATEGORIA:
				/*
				 * TODO: Mostrar una descripcion que indique para que categoria se mostrara el
				 * test con mas puntaje obtenido y cual fue l test con mas puntaje obtenido para
				 * cada categoria.
				 */
				
			mostrarPorPantalla( "El test con mas puntaje de " + Categoria.IMAGENES);
			mostrarTest( usuario.obtenerTestMasPuntajePorCategoria(Categoria.IMAGENES));
				
			mostrarPorPantalla("El test con mas puntaje de " + Categoria.VESTIMENTA);
			mostrarTest(usuario.obtenerTestMasPuntajePorCategoria(Categoria.VESTIMENTA));
			
			
			mostrarPorPantalla("El test con mas puntaje de " + Categoria.CRUCIGRAMAS);
			mostrarTest(usuario.obtenerTestMasPuntajePorCategoria(Categoria.CRUCIGRAMAS));
				
				break;
			case SALIR:
				mostrarPorPantalla("Acaba de salir de la sesion");
				break;
			}

		} while (!opcionMenuUsuario.equals(MenuUsuario.SALIR));

	}

	private static void mostrarTest(Test test) {
		/*
		 * Debe mostrar por pantalla todos los elementos del test si son validos. En
		 * caso de error mostrar la descripcion "Error de test""
		 */
		if (test != null) {
			mostrarPorPantalla("\n" + test.toString());
		} else {
			mostrarPorPantalla("Sin test");
		}

	}

	private static void mostrarTest(Test[] tests) {

		for (int i = 0; i < tests.length; i++) {
			if (tests[i] != null) {
				mostrarPorPantalla("\n" + tests[i].toString());
			}
		}
	}

	private static MenuPrincipal obtenerOpcionDeMenuPrincipal(int numeroIngresado) {

		// Obtener la opcion del menu principal correcta, en caso de ser invalida,
		// asignar la
		// opcion del registro de usuario.

		do {

			numeroIngresado = ingresarNumeroEntero("\n\nIngrese opcion:");
			if (numeroIngresado < 0 || numeroIngresado > MenuPrincipal.values().length) {
				mostrarPorPantalla("Opcion invalida. ");
			}
		} while (numeroIngresado < 0 || numeroIngresado > MenuPrincipal.values().length);

		return MenuPrincipal.values()[numeroIngresado - 1];
	}

	private static MenuUsuario obtenerOpcionDeMenuUsuario(int numeroIngresado) {
		return MenuUsuario.values()[numeroIngresado - 1];
	}

	private static int ingresarNumeroEntero(String mensaje) {
		mostrarPorPantalla(mensaje);
		return teclado.nextInt();
	}

	private static String ingresarString(String mensaje) {
		mostrarPorPantalla(mensaje);
		return teclado.next();
	}

	private static void mostrarMenuParaAgregarUnTest() {
		mostrarPorPantalla("\n\nIngrese el ID del test que desea agregar o 99 para salir:");
	}

	private static void mostrarMenuUsuario() {
		mostrarPorPantalla(
				"\n\n1) Mis Tests\n2) Coleccion de Tests\n3) Test con mas puntaje obtenido por categoria\n4) Salir");
	}

	private static void mostrarMenuPrincipal() {
		mostrarPorPantalla("\n\n1) Iniciar sesion\n2) Registrame\n3) Salir ");
	}

	private static void mostrarPorPantalla(String mensaje) {
		System.out.println(mensaje);
	}

}
