package dominio;
import java.io.*;
import java.util.*;
public class Problema {
	private Estado es;
	private EspacioEstado ee;
	private Nodo nodoSolucion;
	public Problema(Estado es) {
		this.es = es;
		ee = new EspacioEstado(es);
		nodoSolucion = null;
	}
	public String busqueda(int prof_Max, int inc_Prof) {
		String solucion = "";
		int profundidadActual = inc_Prof;
		boolean esSolucion = false;
		while (!esSolucion && profundidadActual <= inc_Prof) {
			solucion = busquedaAcotada("ProfundidadAcotada", profundidadActual);
			profundidadActual = profundidadActual + inc_Prof;
		}
		return solucion;
	}
	public String busquedaAcotada(String estrategia, int prof_Max) {
		String solucion = "";
		boolean esSolucion = false;
		boolean continuar = true;
		Frontera frontera = new Frontera();
		frontera.crearFrontera();
		Nodo nInicial = new Nodo(es);
		frontera.insertar(nInicial);
		while (!esSolucion && !frontera.esVacia()) {
			Nodo nActual = frontera.eliminar();
			if (esObjetivo(nActual.getEstadoActual()) && continuar) {
				esSolucion = true;
			} else {
				if (continuar) {
					Sucesor[] sucesores = ee.Generar_Sucesores(nActual.getEstadoActual());
					Nodo[] listaNodos = crearListaNodos(sucesores, nActual, estrategia, prof_Max);
					for (int i = 0; i < listaNodos.length; i++) {
						frontera.insertar(listaNodos[i]);
					}
					if(nActual.getDesplazamiento()!=null)
					System.out.println(nActual.getAccionString());
				} else {
					continuar = true;
				}
			}
			if (esSolucion) {
				solucion = "";
				solucion = crearSolucion(nActual);
				setNodoSolucion(nActual);
				StringTokenizer str = new StringTokenizer(solucion, "\n");
				PrintWriter salida = null;
				try {
					salida = new PrintWriter(new FileWriter("Solucion.txt"));
					salida.println(estrategia + ":");
					while (str.hasMoreTokens()) {
						salida.println(str.nextToken());
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				salida.close();
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
		for (int i = 0; i < es.getTerreno().getTerreno().length; i++) {
			for (int j = 0; j < es.getTerreno().getTerreno()[i].length; j++) {
				if (es.getTerreno().getTerreno()[i][j] != es.getTerreno().getK()) {
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