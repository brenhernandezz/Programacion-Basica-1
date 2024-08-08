package ar.edu.unlam.pb1.dominio;

public class Test {

	private int id;
	private String tipo;
	private Categoria categoria;
	private double puntaje;

	// TODO: Completar la clase con lo necesario para garantizar el correcto funcionamiento
	
	public Test(int id, String tipo, Categoria categoria) {
		this.id=id;
		this.tipo=tipo;
		this.categoria=categoria;
		this.puntaje=0;
		
	}

	public double getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(double puntaje) {
		this.puntaje = puntaje;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Test [id=" + id + ", tipo=" + tipo + ", categoria=" + categoria + ", puntaje=" + puntaje + "]";
	}


}
