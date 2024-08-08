package ar.edu.unlam.pb1.dominio;

import java.util.Arrays;

public class GestionAplicacion {

	private Usuario[] usuarios;
	private Test[] tests;
	private Usuario usuario;

	public GestionAplicacion() {
		this.usuarios = new Usuario[1000];
		this.tests = new Test[10];
		this.inicializarAplicacion();

	}

	public Usuario buscarUsuarioConCorreo(String correo) {
		// Busca un usuario entre los usuarios de la aplicacion que tenga el correo
		// suministrado.
		// Si encuentra un usuario con ese correo, lo deuelve. Si no encuentra un
		// usuario con ese correo, entonces devuelve null.

		Usuario usuarioBuscadoPorCorreo = null;

		for (int i = 0; i < usuarios.length; i++) {
			if (usuarios[i] != null && usuarios[i].getCorreo().equalsIgnoreCase(correo)) {
				usuarioBuscadoPorCorreo = usuarios[i];
			}
		}

		return usuarioBuscadoPorCorreo;
	}

	public Usuario iniciarSesion(String correo, String contrasenia) {
		// Obtiene un usuario buscandolo por su correo.
		// Si existe, verifica que la contrasenia sea correcta. Si asi es, devuelve el
		// usuario.
		// Si la contrasenia no coincide, debe devolver null

		Usuario usuario = this.buscarUsuarioConCorreo(correo);


		if (usuario != null && !usuario.getContrasenia().equalsIgnoreCase(contrasenia)) {
			usuario=null;
		}

		return usuario;
	}

	public boolean verificarCorreo(String correo) {
		// Tiene que tener un caracter arroba ('@') y terminar en ".com" para que sea un
		// correo valido

		if (correo.contains("@") && correo.endsWith(".com")) {
			return true;
		}

		return false;
	}

	public boolean registrarUsuario(Usuario usuario) {
		// Agrega un usuario a la aplicacion.

		for (int i = 0; i < usuarios.length; i++) {
			if (usuarios[i] == null) {
				usuarios[i] = usuario;
				return true; // se registro
			}
		}

		return false;
	}

	public Test[] getTests() {
		return tests;
	}

	public Test obtenerTestPorSuId(int id) {
		// Busca entre los tests de la aplicacion si alguno tiene el id suministrado.
		// Si hay un test con ese id, se devuelve el mismo, caso contrario, se devuelve
		// null

		Test testPorId = null;

		for (int i = 0; i < tests.length; i++) {
			if (tests[i] != null && tests[i].getId() == id) {
				testPorId = tests[i]; // lo busco
			}
		}

		return testPorId;
	}

	private Test crearTest(int id, String tipo, Categoria categoria) {
		return new Test(id, tipo + " " + id, categoria);
	}

	private void inicializarAplicacion() {

		String tipo = "";
		Categoria categoria;

		for (int i = 0; i < this.tests.length; i++) {

			if (i < 3) {
				tipo = "Imagenes";
				categoria = Categoria.IMAGENES;
			} else if (i >= 3 && i < 7) {
				tipo = "Pagos";
				categoria = Categoria.PAGOS;
			} else if (i >= 7 && i < 9) {
				tipo = "Vestimenta";
				categoria = Categoria.VESTIMENTA;
			} else {
				tipo = "Crucigramas";
				categoria = Categoria.CRUCIGRAMAS;
			}

			this.tests[i] = crearTest((i + 1), tipo, categoria);
		}

	}

	public Usuario[] getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuario[] usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setTests(Test[] tests) {
		this.tests = tests;
	}

	@Override
	public String toString() {
		return "GestionAplicacion [usuarios=" + Arrays.toString(usuarios) + ", tests=" + Arrays.toString(tests)
				+ ", usuario=" + usuario + "]";
	}

}
