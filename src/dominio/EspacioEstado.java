package dominio;

import java.util.*;

public class EspacioEstado {
	private ArrayList<ArrayList<Integer>> combinacionesAux = new ArrayList<ArrayList<Integer>>();
	private Estado es;

	public EspacioEstado(Estado es) {
		this.es = es;
	}

	public Sucesor[] Generar_Sucesores(Estado es) {
		Sucesor[] sucesores;
		int[][] terreno = es.getTerreno();
		int Xt = es.getTractor()[0];
		int Yt = es.getTractor()[1];
		int K = es.getK();
		ArrayList<Integer> coordenadasDesplazamiento = new ArrayList<Integer>();
		ArrayList<Integer> coordenadasAux = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> desplazamientos = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> distribucion = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> combinaciones = new ArrayList<ArrayList<Integer>>();
		int total = 0;
		for (int i = 0; i < terreno.length; i++) {
			for (int j = 0; j < terreno[i].length; j++) {
				if (Comprobar_Sucesor(terreno, Xt, Yt, i, j)) {
					coordenadasDesplazamiento = new ArrayList<Integer>();
					coordenadasDesplazamiento.add(i);
					coordenadasDesplazamiento.add(j);
					desplazamientos.add(coordenadasDesplazamiento);
				}
			}
		}
		if (terreno[Xt][Yt] > K) {
			for (int i = 0; i < desplazamientos.size(); i++) {
				coordenadasAux = desplazamientos.get(i);
				if (terreno[coordenadasAux.get(0)][coordenadasAux.get(1)] < K) {
					distribucion.add(coordenadasAux);
				}
			}
			int[] aux = new int[distribucion.size()];
			Combinar(terreno[Xt][Yt] - K, 0, aux);
			for (int i = 0; i < combinacionesAux.size(); i++) {
				for (int j = 0; j < distribucion.size(); j++) {
					total += combinacionesAux.get(i).get(j);
				}
				if (total == terreno[Xt][Yt] - K) {
					combinaciones.add(combinacionesAux.get(i));
				}
				total = 0;
			}
		} else {
			ArrayList<Integer> noDistribucion = new ArrayList<Integer>();
			noDistribucion.add(0);
			distribucion.add(noDistribucion);
			noDistribucion.add(0);
			combinaciones.add(noDistribucion);
		}
		ArrayList<ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>>> resultado = new ArrayList<ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>>>();
		ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>> elemento = new ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>>();
		ArrayList<ArrayList<ArrayList<Integer>>> elemento21 = new ArrayList<ArrayList<ArrayList<Integer>>>();
		ArrayList<ArrayList<ArrayList<Integer>>> elemento2 = new ArrayList<ArrayList<ArrayList<Integer>>>();
		ArrayList<ArrayList<Integer>> elemento3 = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> elemento31 = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> elemento4 = new ArrayList<Integer>();
		for (int i = 0; i < desplazamientos.size(); i++) {
			elemento31 = new ArrayList<ArrayList<Integer>>();
			elemento31.add(desplazamientos.get(i));
			if (combinaciones.size() != 0) {
				for (int j = 0; j < combinaciones.size(); j++) {
					elemento = new ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>>();
					elemento2 = new ArrayList<ArrayList<ArrayList<Integer>>>();
					elemento21 = new ArrayList<ArrayList<ArrayList<Integer>>>();
					elemento21.add(elemento31);
					elemento.add(elemento21);
					for (int k = 0; k < distribucion.size(); k++) {
						elemento3 = new ArrayList<ArrayList<Integer>>();
						elemento4 = new ArrayList<Integer>();
						elemento4.add(combinaciones.get(j).get(k));
						elemento3.add(elemento4);
						elemento3.add(distribucion.get(k));
						elemento2.add(elemento3);
					}
					elemento.add(elemento2);
					resultado.add(elemento);
				}
			} else {
				elemento = new ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>>();
				elemento21 = new ArrayList<ArrayList<ArrayList<Integer>>>();
				elemento21.add(elemento31);
				elemento.add(elemento21);
				resultado.add(elemento);
			}
		}
		sucesores = new Sucesor[resultado.size()];
		for (int i = 0; i < resultado.size(); i++) {
			Accion ac = new Accion(resultado.get(i));
			Estado auxEs = new Estado(es.getTer(), ac);
			//System.out.println("hola");
			sucesores[i] = new Sucesor(auxEs);
		}
		combinacionesAux = new ArrayList<ArrayList<Integer>>();
		return sucesores;
	}

	private void Combinar(int sobrante, int i, int[] posiciones) {
		if (i != posiciones.length) {
			for (int j = 0; j <= sobrante; j++) {
				posiciones[i] = j;
				Combinar(sobrante, i + 1, posiciones);
			}
		} else {
			ArrayList<Integer> aux = new ArrayList<Integer>();
			for (int j = 0; j < posiciones.length; j++) {
				aux.add(posiciones[j]);
			}
			combinacionesAux.add(aux);
		}
	}

	private boolean Comprobar_Sucesor(int[][] terreno, int Xt, int Yt, int Xs, int Ys) {
		boolean verdad = false;
		if (Xt == Xs + 1 && Yt == Ys && Xt != 0) {
			verdad = true;
		} else if (Xt == Xs && Yt == Ys + 1 && Yt != 0) {
			verdad = true;
		} else if (Xt == Xs && Yt == Ys - 1 && Yt != terreno[Xt].length - 1) {
			verdad = true;
		} else if (Xt == Xs - 1 && Yt == Ys && Xt != terreno.length - 1) {
			verdad = true;
		}
		return verdad;
	}
}