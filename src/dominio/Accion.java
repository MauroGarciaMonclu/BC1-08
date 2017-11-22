package dominio;

import java.util.ArrayList;

public class Accion {
	private int desplazamiento[]= new int[2];;
	private int distribucion[][];

	public Accion(ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>> suc) {
		desplazamiento[0] = suc.get(0).get(0).get(0).get(0);
		desplazamiento[1] = suc.get(0).get(0).get(0).get(1);
		distribucion = new int[3][suc.get(1).size()];
		for (int i = 0; i < distribucion[0].length; i++) {
			distribucion[0][i] = suc.get(1).get(i).get(0).get(0);
			distribucion[1][i] = suc.get(1).get(i).get(1).get(0);
			distribucion[2][i] = suc.get(1).get(i).get(1).get(1);
		}
	}

	public int[] getDesplazamiento() {
		return desplazamiento;
	}

	public int[][] getDistribucion() {
		return distribucion;
	}

	public String toString() {
		String cadena;
		cadena = "(" + desplazamiento[0] + ", " + desplazamiento[1] + "), (";
		for (int i = 0; i < distribucion[0].length; i++) {
			cadena += "(" + distribucion[0][i] + ", (" + distribucion[1][i] + ", " + distribucion[2][i] + "))";
			if (i < distribucion[0].length - 1) {
				cadena += ", ";
			}
		}
		cadena += ")";
		return cadena;
	}
}