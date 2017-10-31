package dominio;

public class Nodo {
	private Nodo nodoPadre;
	private Estado estadoActual;
	private String accion;
	private int valor;
	private int costo;

	public Nodo(Estado estado) {
		this.nodoPadre = null;
		this.estadoActual = estado;
		this.accion = null;
		this.valor = 0;
		this.costo = 0;
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

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
}