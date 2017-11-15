package dominio;

public class Nodo {
	private Nodo nodoPadre;
	private Estado estadoActual;
	private int[] desplazamiento;
	private int[][] distribucion;
	private int valor;
	private int costo;
	public Nodo(Estado estadoActual) {
		nodoPadre = null;
		this.estadoActual = estadoActual;
		desplazamiento = null;
		distribucion = null;
		valor = 0;
		costo = 0;
	}

	public Nodo(Nodo nodoPadre, Estado estadoActual, int[] desplazamiento, int[][] distribucion, int valor) {
		this.nodoPadre = nodoPadre;
		this.estadoActual = estadoActual;
		this.desplazamiento = desplazamiento;
		this.distribucion = distribucion;
		this.valor = valor;
		if(distribucion!=null) {
		costo = estadoActual.Costo(distribucion);
		}
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	public Nodo getNodoPadre() {
		return nodoPadre;
	}

	public void setNodoPadre(Nodo nodoPadre) {
		this.nodoPadre = nodoPadre;
	}

	public Estado getEstadoActual() {
		return estadoActual;
	}

	public void setEstadoActual(Estado estadoActual) {
		this.estadoActual = estadoActual;
	}
	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
	public int[] getDesplazamiento() {
		return desplazamiento;
	}

	public void setDesplazamiento(int[] desplazamiento) {
		this.desplazamiento = desplazamiento;
	}

	public int[][] getDistribucion() {
		return distribucion;
	}

	public void setDistribucion(int[][] distribucion) {
		this.distribucion = distribucion;
	}

	public String getAccionString() {
		String accion = "<<" + Integer.toString(desplazamiento[0]) + ", " + Integer.toString(desplazamiento[1]) + ">,<";
		for (int i = 0; i < distribucion[0].length; i++) {
			accion = accion+"<" + Integer.toString(distribucion[0][i]) + ", <" + Integer.toString(distribucion[1][i]) + ", "
					+ Integer.toString(distribucion[2][i]) + ">>";
		}
		accion += ">>";
		return accion;
	}
}