package dominio;

import java.util.ArrayList;

public class EspacioEstado {
	public ArrayList<ArrayList<Integer>> combinaciones = new ArrayList<ArrayList<Integer>>();

	public Sucesor[] Generar_Sucesores(Estado es) {
		int[][] terreno = es.getTerrenoM();
		int Xt = es.getTractor()[0];
		int Yt = es.getTractor()[1];
		int K = es.getK();
		ArrayList<ArrayList<Integer>> sucesores = new ArrayList<ArrayList<Integer>>();
		ArrayList[] cooSuc = new ArrayList[4];
		ArrayList<Integer> coor = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> sucesoresPosi = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> combinacionesK = new ArrayList<ArrayList<Integer>>();
		int sobrante;
		int n = 0;
		int total = 0;
		for (int i = 0; i < terreno.length; i++) {
			for (int j = 0; j < terreno[i].length; j++) {
				if (Comprobar_Sucesor(terreno, Xt, Yt, i, j)) {
					cooSuc[n] = new ArrayList<Integer>();
					cooSuc[n].add(i);
					cooSuc[n].add(j);
					sucesores.add(cooSuc[n]);
					n++;
				}
			}
		}
		if (terreno[Xt][Yt] > K) {
			sobrante = terreno[Xt][Yt] - K;
			for (int i = 0; i < sucesores.size(); i++) {
				coor = sucesores.get(i);
				if (terreno[coor.get(0)][coor.get(1)] < K) {
					sucesoresPosi.add(coor);
				}
			}
			int[] aux = new int[sucesoresPosi.size()];
			Combinar(sobrante, 0, aux);
			for (int i = 0; i < combinaciones.size(); i++) {
				for (int j = 0; j < sucesoresPosi.size(); j++) {
					total += combinaciones.get(i).get(j);
				}
				if (total == sobrante) {
					combinacionesK.add(combinaciones.get(i));
				}
				total = 0;
			}
		}
		ArrayList<ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>>> resultado = new ArrayList<ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>>>();
		ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>> elemento = new ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>>();
		ArrayList<ArrayList<ArrayList<Integer>>> elemento21 = new ArrayList<ArrayList<ArrayList<Integer>>>();
		ArrayList<ArrayList<ArrayList<Integer>>> elemento2 = new ArrayList<ArrayList<ArrayList<Integer>>>();
		ArrayList<ArrayList<Integer>> elemento3 = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> elemento31 = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> elemento4 = new ArrayList<Integer>();
		for (int i = 0; i < sucesores.size(); i++) {
			elemento31 = new ArrayList<ArrayList<Integer>>();
			elemento31.add(sucesores.get(i));
			if (combinacionesK.size() != 0) {
				for (int j = 0; j < combinacionesK.size(); j++) {
					elemento = new ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>>();
					elemento21 = new ArrayList<ArrayList<ArrayList<Integer>>>();
					elemento2 = new ArrayList<ArrayList<ArrayList<Integer>>>();
					elemento21.add(elemento31);
					elemento.add(elemento21);
					for (int k = 0; k < sucesoresPosi.size(); k++) {
						elemento3 = new ArrayList<ArrayList<Integer>>();
						elemento4 = new ArrayList<Integer>();
						elemento4.add(combinacionesK.get(j).get(k));
						elemento3.add(elemento4);
						elemento3.add(sucesoresPosi.get(k));
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
		Sucesor[] suc = new Sucesor[resultado.size()];
		for (int i = 0; i < resultado.size(); i++) {
			suc[i] = new Sucesor(resultado.get(i));
		}
		int[][] terreno2 = new int[terreno.length][terreno[0].length];
		System.out.println("| Movi. | | Transporte de tierra");
		for (int i = 0; i < resultado.size(); i++) {
			System.out.println(resultado.get(i).toString());
		}
		System.out.println();
		leer.cadena("Pulsa Enter para el nuevo terreno y la nueva posicion");
		System.out.println();
		for (int i = 0; i < resultado.size(); i++) {
			for (int j = 0; j < terreno2.length; j++) {
				for (int k = 0; k < terreno2[j].length; k++) {
					terreno2[j][k] = terreno[j][k];
				}
			}
			System.out.println("-----------------------------------");
			System.out.println(resultado.get(i).toString());
			if (combinacionesK.size() != 0) {
				for (int j = 0; j < resultado.get(i).get(1).size(); j++) {
					terreno2[Xt][Yt] = terreno2[Xt][Yt] - resultado.get(i).get(1).get(j).get(0).get(0);
					terreno2[resultado.get(i).get(1).get(j).get(1).get(0)][resultado.get(i).get(1).get(j).get(1)
							.get(1)] = resultado.get(i).get(1).get(j).get(0).get(0)
									+ terreno2[resultado.get(i).get(1).get(j).get(1).get(0)][resultado.get(i).get(1)
											.get(j).get(1).get(1)];
				}
				es.getTerreno().setTerreno(terreno2);
				System.out.println("Nuevo terreno\t\t-:-");
				es.getTerreno().Mostrar_Terreno();
			}
			System.out.println("Nueva posicion del tractor\t-:-\n\t\t" + resultado.get(i).get(0).get(0).toString());
			System.out.println("-----------------------------------");
			System.out.println();
		}
		return suc;
	}

	public void Combinar(int sobrante, int i, int[] posiciones) {
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
			combinaciones.add(aux);
		}
	}

	public boolean Comprobar_Sucesor(int[][] terreno, int Xt, int Yt, int Xs, int Ys) {
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

	public boolean EsObjetivo(int terreno[][], int K) {
		boolean verificar = true;
		for (int i = 0; i < terreno.length; i++) {
			for (int j = 0; j < terreno[i].length; j++) {
				if (terreno[i][j] != K) {
					verificar = false;
				}
			}
		}
		return verificar;
	}
}