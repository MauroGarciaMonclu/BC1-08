package dominio;

import java.io.*;
import java.util.*;

public class Problema {
	
	private Estado es;
	private EspacioEstado ee;
	private Nodo nodoSolucion;
	private ArrayList<Nodo> listaPoda = new ArrayList<Nodo>();

	public Problema(Estado es) {
		this.es = es;
		ee = new EspacioEstado(es);
		nodoSolucion = null;
	}

	public String busqueda(String estrategia, int prof_Max, int inc_Prof) throws IOException {
		String solucion = "";
		int profundidadActual = inc_Prof;
		boolean esSolucion = false;
		while (!esSolucion && profundidadActual <= inc_Prof) {
			solucion = busquedaAcotada(estrategia, prof_Max);
			profundidadActual = profundidadActual + inc_Prof;
		}
		return solucion;
	}

	public String busquedaAcotada(String estrategia, int prof_Max) throws IOException {
		String solucion = "";
		boolean esSolucion = false;
		Frontera frontera = new Frontera();
		frontera.crearFrontera();
		Nodo nInicial = new Nodo(es);
		int[] desaux = { -1, -1 };
		int[][] disaux = { { -1 }, { -1 }, { -1 } };
		Nodo aux = new Nodo(nInicial, es, desaux, disaux, 0, 0);
		listaPoda.add(aux);
		frontera.insertar(nInicial);
		Nodo nActual = nInicial;
		while (!esSolucion && !frontera.esVacia() && nActual.getProf() <= prof_Max) {
			nActual = frontera.eliminar();
			if (listaPoda.size() > 1) {
				for (int i = 0; i < listaPoda.size(); i++) {
					if (nActual.getAccionString().equals(listaPoda.get(i).getAccionString())) {
						if (nActual.getValor() > listaPoda.get(i).getValor()) {
							nActual = frontera.eliminar();
						}
					}
				}
			}
			if (esObjetivo(nActual.getEstadoActual())) {
				esSolucion = true;
			} else {
				EspacioEstado eeAux = new EspacioEstado(nActual.getEstadoActual());
				Sucesor[] sucesores = eeAux.Generar_Sucesores(nActual.getEstadoActual());
				Nodo[] listaNodos = crearListaNodos(sucesores, nActual, estrategia, prof_Max);
				for (int i = 0; i < listaNodos.length; i++) {
					boolean igual = true;
					for (int j = 0; j < listaPoda.size(); j++) {
						if (listaNodos[i].getAccionString().equals(listaPoda.get(j).getAccionString())) {
							if (listaNodos[i].getValor() < listaPoda.get(j).getValor()) {
								listaPoda.remove(j);
							}
							else {
								igual = false;
							}
						}
					}
					if (igual) {
						listaPoda.add(listaNodos[i]);
					}
				}
				frontera.insertaLista(listaNodos);
				for (int i = 0; i < nActual.getEstadoActual().getTerreno().length; i++) {
					for (int k = 0; k < nActual.getEstadoActual().getTerreno()[i].length; k++) {
						System.out.print(nActual.getEstadoActual().getTerreno()[i][k] + " ");

						/*
						 * System.out.println(listaNodos[i].getAccionString()+" ");
						 * System.out.print(listaNodos[i].getEstadoActual().getXt()+" ");
						 * System.out.print(listaNodos[i].getEstadoActual().getYt()+" ");
						 * System.out.println();
						 */
					}
					System.out.println();
				}
			}
			if (esSolucion) {
				solucion = "";
				solucion = crearSolucion(nActual);
				setNodoSolucion(nActual);
				boolean comprobar = true;
				int n = 0;
				File nomb = new File("Solucion.txt");
				while (comprobar) {
					if (n == 0) {
						nomb = new File("Solucion.txt");
					} else {
						nomb = new File("Solucion (" + n + ").txt");
					}
					if (!nomb.exists()) {
						comprobar = false;
					}
					n++;
				}
				FileWriter fich = new FileWriter(nomb);
				BufferedWriter bw = new BufferedWriter(fich);
				PrintWriter pw = null;
				try {
					pw = new PrintWriter(fich);
					pw.print(solucion);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (null != fich)
							fich.close();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
				bw.close();
			} else {
				solucion = "No se ha encontrado solucion";
			}
		}
		return solucion;
	}

	public Nodo[] crearListaNodos(Sucesor[] sucesores, Nodo nodoActual, String estrategia, int profundidad) {
		Nodo[] listaNodos = new Nodo[sucesores.length];
		switch (estrategia) {
		case "Anchura":
			listaNodos = new Nodo[sucesores.length];
			for (int i = 0; i < sucesores.length; i++) {
				listaNodos[i] = new Nodo(nodoActual, sucesores[i].getEstado(), sucesores[i].getDesplazamiento(),
						sucesores[i].getDistribucion(), sucesores[i].getCoste(), nodoActual.getValor() + 1);
			}
			break;
		case "ProfundidadSimple":
			listaNodos = new Nodo[sucesores.length];
			for (int i = 0; i < sucesores.length; i++) {
				listaNodos[i] = new Nodo(nodoActual, sucesores[i].getEstado(), sucesores[i].getDesplazamiento(),
						sucesores[i].getDistribucion(), sucesores[i].getCoste(), 1000000 - nodoActual.getValor());
			}
			break;
		case "ProfundidadAcotada":
			if (nodoActual.getValor() + 1 <= profundidad) {
				listaNodos = new Nodo[sucesores.length];
				for (int i = 0; i < sucesores.length; i++) {
					listaNodos[i] = new Nodo(nodoActual, sucesores[i].getEstado(), sucesores[i].getDesplazamiento(),
							sucesores[i].getDistribucion(), sucesores[i].getCoste(), 1000000 - nodoActual.getValor());
				}
				
			} else {
				listaNodos = null;
			}
			break;
		case "CosteUniforme":
			listaNodos = new Nodo[sucesores.length];
			for (int i = 0; i < sucesores.length; i++) {
				listaNodos[i] = new Nodo(nodoActual, sucesores[i].getEstado(), sucesores[i].getDesplazamiento(),
						sucesores[i].getDistribucion(), sucesores[i].getCoste(), nodoActual.getCosto());
			}
			break;
		case "A*":
			listaNodos = new Nodo[sucesores.length];
			for (int i = 0; i < listaNodos.length; i++) {
				listaNodos[i] = new Nodo(nodoActual, sucesores[i].getEstado(), sucesores[i].getDesplazamiento(),
						sucesores[i].getDistribucion(), sucesores[i].getCoste(),
						nodoActual.getCosto() + 1 + ee.heuristica(sucesores[i].getEstado()));
			}
			break;

		}
		return listaNodos;
	}

	public String crearSolucion(Nodo nodoSolucion) {
		String acciones = "";
		if (nodoSolucion.getNodoPadre() == null) {
			acciones = "";
		} else {
			acciones += crearSolucion(nodoSolucion.getNodoPadre()) + nodoSolucion.getAccionString() + "\n";
		}
		return acciones;
	}

	public boolean esObjetivo(Estado es) {
		boolean verificar = true;
		for (int i = 0; i < es.getTerreno().length; i++) {
			for (int j = 0; j < es.getTerreno()[i].length; j++) {
				if (es.getTerreno()[i][j] != es.getK()) {
					verificar = false;
				}
			}
		}
		return verificar;
	}

	public Nodo getNodoSolucion() {
		return nodoSolucion;
	}

	public void setNodoSolucion(Nodo nodoSolucion) {
		this.nodoSolucion = nodoSolucion;
	}
}