package dominio;

import java.util.*;

public class Frontera implements Comparable<Nodo> {
	private Queue<Nodo> frontera;

	public Frontera() {
	}

	public Queue<Nodo> getFrontera() {
		return frontera;
	}

	public void setFrontera(Queue<Nodo> frontera) {
		this.frontera = frontera;
	}

	public void crearFrontera() {
		Queue<Nodo> vacia = new LinkedList<Nodo>();
		setFrontera(vacia);
	}

	@Override
	public int compareTo(Nodo n) {
		return Integer.compare(frontera.peek().getValor(), n.getValor());
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
}