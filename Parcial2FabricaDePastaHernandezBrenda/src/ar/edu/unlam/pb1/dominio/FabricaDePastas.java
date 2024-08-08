package ar.edu.unlam.pb1.dominio;

import ar.edu.unlam.pb1.dominio.enums.TipoDePasta;

public class FabricaDePastas {

	private String nombrePedido;
	private Pasta[] pastas;
	private Pasta[] pedido;

	public FabricaDePastas(String nombrePedido) {
		// TODO: Completar el constructor para que el producto funcione correctamente.
		this.nombrePedido=nombrePedido;
		this.pastas = new Pasta[TipoDePasta.values().length];
		this.pedido= new Pasta[10];
		this.inicializarFabricaDePastas();
	}

	// TODO: Completar los getters y setters que considere necesarios.

	public boolean agregarPastaAlPedido(String codigoPasta, int cantidad) {
		// TODO: Se debe buscar la pasta por su codigo y agregarla al pedido solo si hay
		// cantidad suficiente de la pasta solicitada. Luego de agregarla, se debe
		// actualizar la cantidad de la pasta agregada en el array de pastas (considerar
		// el
		// metodo descontarCantidadDePastaDisponible()). Devuelve verdadero en caso de
		// completar la
		// operacion o falso en caso de no poder realizar la operacion por el motivo que
		// sea.

		
		
		
		Pasta pasta = obtenerPastaPorCodigo(codigoPasta);
		
		 if (pasta == null || pasta.getCantidad() < cantidad) {
		        return false;
		    }
	
		Pasta nuevoPedido = new Pasta(codigoPasta, pasta.getTipoDePasta(), pasta.isEsRellena(), pasta.getPrecio(),
				cantidad);

		int i = 0;
		boolean agregada = false;

		while(i<this.pedido.length && !agregada) {
			if(this.pedido[i]==null) {
				pedido[i]=nuevoPedido;
				descontarCantidadDePastaDisponible(codigoPasta, cantidad);
				agregada=true;
			}
			i++;
		}

		return agregada;
	}

	private Pasta obtenerPastaPorCodigo(String codigoPasta) {
		// TODO: Se debe buscar en el array de pastas y devolver la pasta que coincida
		// con el codigoPasta
		// suministrado, o null en caso de que no exista una pasta con ese codigoPasta.

		Pasta pastaEncontrada = null;

		for (int i = 0; i < pastas.length; i++) {
			if (pastas[i] != null && pastas[i].getCodigo().equals(codigoPasta)) {
				pastaEncontrada=pastas[i];
				break;
			}
		}

		return pastaEncontrada;
	}

	private void descontarCantidadDePastaDisponible(String codigoPasta, int cantidadADescontar) {
		// TODO: Actualiza la cantidad de pasta en el array de pastas.

		int cantidadActualizada = 0;

		for (int i = 0; i < pastas.length; i++) {
			if (pastas[i] != null && pastas[i].getCodigo().equals(codigoPasta)) {
				cantidadActualizada = pastas[i].getCantidad() - cantidadADescontar;
				pastas[i].setCantidad(cantidadActualizada);
				
			}
		}

	}

	public double obtenerTotalDelPedido() {
		// TODO: Calcula y devuelve el total del pedido considerando el precio de la
		// pasta y la cantidad solicitada en el pedido.

		double totalPedido = 0.0;

		for (int i = 0; i < pedido.length; i++) {
			if (pedido[i] != null) {
				totalPedido += (pedido[i].getPrecio() * pedido[i].getCantidad());

			}
		}

		return totalPedido;
	}

	public Pasta obtenerPastaDeUnTipoDePastaConMenorCantidadEnElPedido(TipoDePasta tipoDePasta) {
		// TODO: Devuelve la pasta que aplique al tipoDePasta indicado, que posea menor
		// cantidad en el pedido.

		Pasta pastaDeUnTipoConMenorCantidad = null;
		int pedidos = 0;

		for (int i = 0; i < pedido.length; i++) {
			if (pedido[i] != null && pedido[i].getTipoDePasta().equals(tipoDePasta)) {
				if (pastaDeUnTipoConMenorCantidad == null
						|| pedido[i].getCantidad() < pastaDeUnTipoConMenorCantidad.getCantidad()) {
					pastaDeUnTipoConMenorCantidad = pedido[i];
				}
			}
		}

		return pastaDeUnTipoConMenorCantidad;
	}

	public Pasta[] obtenerPedidoPorPrecioDePastaDescendente() {
		// TODO: Debe ordenar y devolver las pastas en el pedido de manera descendente,
		// debiendo quedar en la primera posicion la pasta de mayor precio.

		Pasta aux = null;

		for (int i = 1; i < pedido.length; i++) {
			for (int j = 0; j < pedido.length - 1; j++) {

				if (pedido[j] != null && pedido[j + 1] != null && pedido[j].getPrecio() < pedido[j + 1].getPrecio()) {
					aux = pedido[j];
					pedido[j] = pedido[j + 1];
					pedido[j + 1] = aux;
				}

			}
		}

		return pedido;
	}

	private Pasta agregarPasta(String codigo, TipoDePasta tipoDePasta, boolean esRellena, double precio,
			int cantidadEnStock) {
		return new Pasta(codigo, tipoDePasta, esRellena, precio, cantidadEnStock);
	}

	private void inicializarFabricaDePastas() {
		int codigo = 100;
		for (int i = 0; i < TipoDePasta.values().length; i++) {

			this.pastas[i] = this.agregarPasta("" + codigo, TipoDePasta.values()[i], i != 0, ((i * 7) + 115.5),
					(i * 7 + 15));
			codigo += 100;
		}

	}

	public String getNombrePedido() {
		return nombrePedido;
	}

	public void setNombrePedido(String nombrePedido) {
		this.nombrePedido = nombrePedido;
	}

	public Pasta[] getPastas() {
		return pastas;
	}

	public void setPastas(Pasta[] pastas) {
		this.pastas = pastas;
	}

	public Pasta[] getPedido() {
		return pedido;
	}

	public void setPedido(Pasta[] pedido) {
		this.pedido = pedido;
	}
	
}
