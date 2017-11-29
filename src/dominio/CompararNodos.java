package dominio;

import java.util.Comparator;

public class CompararNodos implements Comparator<Nodo> {
	public int compare(Nodo n1, Nodo n2) {
		return n1.getValor() - n2.getValor();
	}
}