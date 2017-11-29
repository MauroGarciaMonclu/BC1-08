package dominio;

import java.util.ArrayList;

public class Accion {
	private int desplazamiento[] = new int[2];;
	private int distribucion[][];

	public Accion(int[] desplazamiento,int[][] distribucion) {
		this.desplazamiento=desplazamiento;
		this.distribucion=distribucion;
	}

	public int[] getDesplazamiento() {
		return desplazamiento;
	}

	public void setDesplazamiento(int[] desplazamiento) {
		this.desplazamiento = desplazamiento;
	}

	public int[][] getDistribucion() {
		return distribucion;
	}

	public void setDistribucion(int[][] distribucion) {
		this.distribucion = distribucion;
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