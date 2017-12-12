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
		int Xt = es.getXt();
		int Yt = es.getYt();
		int K = es.getK();
		int MAX = es.getMAX();
		ArrayList<ArrayList<Integer>> posiblesMovimientos = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> posiblesDistribucion = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> combinaciones = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < terreno.length; i++) {
			for (int j = 0; j < terreno[i].length; j++) {
				if (Comprobar_Sucesor(terreno, Xt, Yt, i, j)) {
					ArrayList<Integer> coordenada = new ArrayList<Integer>();
					coordenada.add(j);
					coordenada.add(i);
					posiblesMovimientos.add(coordenada);
				}
			}
		}
		if (terreno[Xt][Yt] > K) {
			int total = 0;
			int sobrante = terreno[Xt][Yt] - K;
			for (int i = 0; i < terreno.length; i++) {
				for (int j = 0; j < terreno[i].length; j++) {
					if (Comprobar_Sucesor(terreno, Xt, Yt, i, j)) {
						ArrayList<Integer> coordenada = new ArrayList<Integer>();
						coordenada.add(j);
						coordenada.add(i);
						posiblesDistribucion.add(coordenada);
					}
				}
			}
			int[] aux = new int[posiblesDistribucion.size()];
			Combinar(sobrante, 0, aux);
			for (int i = 0; i < combinacionesAux.size(); i++) {
				total = 0;
				boolean maximo = true;
				for (int j = 0; j < combinacionesAux.get(i).size(); j++) {
					total += combinacionesAux.get(i).get(j);
					if (combinacionesAux.get(i).get(j)
							+ terreno[posiblesDistribucion.get(j).get(0)][posiblesDistribucion.get(j).get(1)] > MAX) {
						maximo = false;
					}
				}
				if (total == sobrante && maximo) {
					combinaciones.add(combinacionesAux.get(i));
				}
			}
		} else {
			ArrayList<Integer> nulo = new ArrayList<Integer>();
			ArrayList<Integer> nulo2 = new ArrayList<Integer>();
			nulo.add(0);
			combinaciones.add(nulo);
			nulo2.add(0);
			nulo2.add(0);
			posiblesDistribucion.add(nulo2);
		}
		sucesores = new Sucesor[combinaciones.size() * posiblesMovimientos.size()];
		int n = 0;
		for (int i = 0; i < posiblesMovimientos.size(); i++) {
			for (int j = 0; j < combinaciones.size(); j++) {
				int[] desplazamiento = new int[2];
				desplazamiento[0] = posiblesMovimientos.get(i).get(0);
				desplazamiento[1] = posiblesMovimientos.get(i).get(1);
				int[][] distribucion = new int[3][combinaciones.get(j).size()];
				for (int k = 0; k < distribucion[0].length; k++) {
					distribucion[0][k] = combinaciones.get(j).get(k);
					distribucion[1][k] = posiblesDistribucion.get(k).get(0);
					distribucion[2][k] = posiblesDistribucion.get(k).get(1);
				}
				Accion ac = new Accion(desplazamiento, distribucion);
				Estado esAux = new Estado(ac, K, MAX, terreno, Xt, Yt);
				sucesores[n] = new Sucesor(esAux);
				n++;
			}
		}
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

	public Estado getEs() {
		return es;
	}

	public void setEs(Estado es) {
		this.es = es;
	}
}