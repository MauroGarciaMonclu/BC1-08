package dominio;

import java.util.*;

public class Frontera {
	private Queue<Nodo> frontera = new PriorityQueue<Nodo>();

	public Frontera() {
	}

	public Queue<Nodo> getFrontera() {
		return frontera;
	}

	public void setFrontera(Queue<Nodo> frontera) {
		this.frontera = frontera;
	}

	public void crearFrontera() {
		Queue<Nodo> vacia = new PriorityQueue<Nodo>();
		setFrontera(vacia);
	}

	public void insertar(Nodo nodo) {
		frontera.add(nodo);
	}

	public Nodo eliminar() {
		return frontera.poll();
	}

	public boolean esVacia() {
		return frontera.isEmpty();
	}

	public void insertaLista(Nodo[] ln) {
		for (int i = 0; i < ln.length; i++) {
			frontera.add(ln[i]);
		}
	}
}