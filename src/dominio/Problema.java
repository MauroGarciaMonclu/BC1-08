package dominio;

import java.io.*;

public class Problema {
	private Estado es;
	private EspacioEstado ee;
	private Nodo nodoSolucion;

	public Problema(Estado es) {
		this.es = es;
		ee = new EspacioEstado(es);
		nodoSolucion = null;
	}

	public String busqueda(String estrategia,int prof_Max, int inc_Prof)  throws IOException{
		String solucion = "";
		int profundidadActual = inc_Prof;
		boolean esSolucion = false;
		while (!esSolucion && profundidadActual <= inc_Prof) {
			solucion = busquedaAcotada(estrategia, profundidadActual);
			profundidadActual = profundidadActual + inc_Prof;
		}
		return solucion;
	}

	public String busquedaAcotada(String estrategia, int prof_Max)  throws IOException{
		String solucion = "";
		boolean esSolucion = false;
		Frontera frontera = new Frontera();
		frontera.crearFrontera();
		Nodo nInicial = new Nodo(es);
		frontera.insertar(nInicial);
		while (!esSolucion && !frontera.esVacia()) {
			Nodo nActual = frontera.eliminar();
			if (esObjetivo(nActual.getEstadoActual())) {
				esSolucion = true;
			} else {
				EspacioEstado eeAux= new EspacioEstado(nActual.getEstadoActual());
				Sucesor[] sucesores = eeAux.Generar_Sucesores(nActual.getEstadoActual());
				Nodo[] listaNodos = crearListaNodos(sucesores, nActual, estrategia, prof_Max);
				frontera.insertaLista(listaNodos);
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
						sucesores[i].getDistribucion(), nodoActual.getValor() + 1);
			}
			break;
		case "ProfundidadSimple":
			listaNodos = new Nodo[sucesores.length];
			for (int i = 0; i < sucesores.length; i++) {
				listaNodos[i] = new Nodo(nodoActual, sucesores[i].getEstado(), sucesores[i].getDesplazamiento(),
						sucesores[i].getDistribucion(), 1000000 - nodoActual.getValor());
			}
			break;
		case "ProfundidadAcotada":
			if (nodoActual.getValor() + 1 <= profundidad) {
				listaNodos = new Nodo[sucesores.length];
				for (int i = 0; i < sucesores.length; i++) {
					listaNodos[i] = new Nodo(nodoActual, sucesores[i].getEstado(), sucesores[i].getDesplazamiento(),
							sucesores[i].getDistribucion(), 1000000 - nodoActual.getValor());
				}
			} else {
				listaNodos = null;
			}
			break;
		case "CosteUniforme":
			listaNodos = new Nodo[sucesores.length];
			for (int i = 0; i < sucesores.length; i++) {
				listaNodos[i] = new Nodo(nodoActual, sucesores[i].getEstado(), sucesores[i].getDesplazamiento(),
						sucesores[i].getDistribucion(), nodoActual.getCosto());
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