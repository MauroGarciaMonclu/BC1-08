package dominio;

import java.util.PriorityQueue;

public class Frontera {
	private PriorityQueue<Nodo> frontera;

	public Frontera() {
	}

	public PriorityQueue<Nodo> getFrontera() {
		return frontera;
	}

	public void setFrontera(PriorityQueue<Nodo> frontera) {
		this.frontera = frontera;
	}

	public void crearFrontera() {
		setFrontera(new PriorityQueue<Nodo>(new CompararNodos()));
	}

	public void Insertar(Nodo nodo) {
		frontera.add(nodo);
	}

	public Nodo Elimina() {
		return frontera.poll();
	}

	public boolean esVacia() {
		return frontera.isEmpty();
	}
}