package ar.edu.unlam.pb1.dominio;

import java.util.Random;

public class Usuario {

	private String correo;
	private String contrasenia;
	private Test[] test;
	private static final int CANTIDAD_DE_TEST=100;

	public Usuario(String correo, String contrasenia) {
		// Daremos un espacio de 100 tests (declarar como constante final y estatica la
		// cantidad de test) a cada nuevo usuario
		this.correo = correo;
		this.contrasenia = contrasenia;
		this.test = new Test[CANTIDAD_DE_TEST];
	}

	public boolean agregarAMiLista(Test test) {
		// Verifica no tener el test adquirido
		// En caso de no tenerlo, se agrega a tests.

		boolean loTengo = verificarDuplicado(test.getId());

		if (!loTengo) {
			for (int i = 0; i < this.test.length; i++) {
				if (this.test[i] == null) {
					this.test[i] = test;
					return true;
				}
			}
		}

		return false;
	}

	public boolean verificarDuplicado(int id) {
		// Verifica si tengo el test con el id suministrado en mis tests
		// Devuelve verdadero en caso de poseer el tests.

		for (int i = 0; i < test.length; i++) {
			if (test[i] != null && test[i].getId() == id) {
				return true; // lo tengo
			}
		}

		return false;
	}

	public Test obtenerTestMasPuntajePorCategoria(Categoria categoria) {
		// Revisa los test que cumplen con la categoria suministrada y obtiene el
		// test con mas puntaje obtenido de dicha categoria

		Test testConMasPuntajePorCategoria = null;
		

		for (int i = 0; i < test.length; i++) {
			if (test[i] != null && test[i].getCategoria().equals(categoria)) {
				if(testConMasPuntajePorCategoria==null || test[i].getPuntaje() > testConMasPuntajePorCategoria.getPuntaje()) {
					testConMasPuntajePorCategoria= test[i];
				}
					
			}
		}

		return testConMasPuntajePorCategoria;
	}

	public Test[] obtenerTestOrdenadosPorCategoria() {
		// Obtiene los tests del usuario ordenados por categoria.

		Test aux = null;

		for (int i = 0; i < test.length - 1; i++) {
			for (int j = 0; j < test.length - 1 - i; j++) {
				if (test[j] != null && test[j + 1] != null ) {
					if(test[j].getCategoria().compareTo(test[j+1].getCategoria())>0) {
						aux = test[j];
						test[j] = test[j + 1];
						test[j + 1] = aux;
				}
	
				}
			}
		}

		return test;
	}

	public void realizarTest(int id) {
		// Revisa entre los tests si alguno tiene el id suministrado. Si lo encuentra,
		// le agrega un puntaje decimal entre 1 y 10.
		// Siempre deberia encontrar el tests con el id que llega como parametro

		// Debe aplicar la formula puntaje +(min + (rango * (max - min))
		int max = 10;
		int min = 1;
		double rango = new Random().nextDouble();
		double puntaje = 0;

		for (int i = 0; i < test.length; i++) {
			if (test[i] != null && test[i].getId() == id) { // lo encontre

				puntaje += (min + (rango * (max - min)));

				test[i].setPuntaje(puntaje);
			}
		}

	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public Test[] getTest() {
		return test;
	}

	public void setTest(Test[] test) {
		this.test = test;
	}

}
